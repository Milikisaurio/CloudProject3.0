/**
 * CloudProject3.0
 * com.learningjava.rest.spring.core
 * Created by Emilio Martínez García
 */

// paquete de donde pertenece esta clase
package com.learningjava.rest.spring.core;

// imports de java: sql y utils
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// lee la base de datos
public class ReadDB {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String THIN_URL = "jdbc:oracle:thin:@35.205.41.45:1521:XE";
    private static final String USER = "usuari";
    private static final String PASSWORD = "usuari";

// crea el objeto consulta de la base de datos
    private Object executeQuery(String query, Function<ResultSet, Object> f) {
        Connection con;
        Statement stmt;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
            stmt = con.createStatement();
            // consulta a la base de datos
            ResultSet rs = stmt.executeQuery(query);
            Object o = f.apply(rs);
            stmt.close();
            con.close();
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    private Object executePreparedStatement(String query
            , Function<PreparedStatement, PreparedStatement> psFunction
            , Function<ResultSet, Object> f) {
        Connection con;
        PreparedStatement ps;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
            ps = con.prepareStatement(query);
            ps = psFunction.apply(ps);
            ResultSet rs = ps.executeQuery(query);
            Object o = f.apply(rs);
            ps.close();
            con.close();
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Restaurant> readRestaurant() {
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        try {

               String query = "SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO FROM " +
                        "RESTAURANTS R,TRESTAURANTS TR WHERE ROWNUM <= 5 AND R.RES_TRS_CODI = TR.TRS_CODI ORDER BY RES_MITJANA DESC";

            ResultSetMapper<Restaurant> a = new ResultSetMapper<>();
            Function<ResultSet, Object> func = new Function<ResultSet, Object>() {
                public Object apply(ResultSet rs) {
                    return a.mapResultSetToObject(rs, Restaurant.class);
                }
            };
            arrayRestaurants = (ArrayList) executeQuery(query, func);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return arrayRestaurants;
    }

    public static Restaurant readRestaurantInfo(String id) {
        Restaurant restaurant = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@35.205.41.45:1521:XE", "usuari", "usuari");
            PreparedStatement stmt = con.prepareStatement("SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO " +
                    "FROM RESTAURANTS R,TRESTAURANTS TR WHERE TR.TRS_CODI=R.RES_TRS_CODI AND R.RES_CODI='" + id + "'");

            // consulta a la base de datos
            ResultSet rs = stmt.executeQuery();

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return restaurant;
    }



    public List readRestaurantAPI(boolean isTraditionalSearch) {
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        ResultSetMapper<Restaurant> mapper = new ResultSetMapper<>();
        try {
            final String query = "SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO FROM " +
                    "RESTAURANTS R,TRESTAURANTS TR WHERE  R.RES_TRS_CODI = TR.TRS_CODI";
            if (isTraditionalSearch) {
                //VERSION GENERICS
                Class.forName(DRIVER);
                Connection con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
                Statement stmt = con.createStatement();
                // otra consulta directa a la base de datos
                ResultSet rs = stmt.executeQuery(query);
                // aqui indicamos que mientras tengamos mas restaurantes, seguir imprimiendolos por pantalla mediante una lista
                arrayRestaurants = mapper.mapResultSetToObject(rs, Restaurant.class);
                stmt.close();
                con.close();
            } else {
                //VERSION FUNCTIONAL
                arrayRestaurants = new ArrayList<>();
                Function<ResultSet, Object> func = new Function<ResultSet, Object>() {
                    public Object apply(ResultSet rs) {
                        return mapper.mapResultSetToObject(rs, Restaurant.class);
                    }
                };
                arrayRestaurants = (ArrayList) executeQuery(query, func);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return arrayRestaurants;
    }

    public List<Restaurant> getRestaurantWithPS(String searchName) {
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        try {
            executePreparedStatement("SELECT * FROM (SELECT R.RES_NOM, R.RES_ADRECA, R.RES_WEB, R.RES_TELEFON, R.RES_URL_IMG, R.RES_CODI, T.TRS_DESCRIPCIO FROM RESTAURANTS R, TRESTAURANTS T WHERE RES_TRS_CODI = TRS_CODI AND LOWER(R.RES_NOM) LIKE ? ORDER BY RES_MITJANA ASC) WHERE ROWNUM <= 5"
                    , rs -> {
                        try {
                            rs.setString(1, "%" + searchName.toLowerCase() + "%");
                        } catch (SQLException e) {
                            System.out.println("mek");
                        }
                        return rs;
                    }
                    , rs -> {
                        ResultSetMapper<Restaurant> mapper = new ResultSetMapper<>();
                        return mapper.mapResultSetToObject(rs, Restaurant.class);
                    });
        } catch (Exception e) {
            System.out.println("error");
        }
        return arrayRestaurants;
    }

}






