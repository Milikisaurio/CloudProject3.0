/**
 * CloudProject3.0
 * com.learningjava.rest.spring.core
 * Created by Emilio Martínez García
 */

// paquete de donde pertenece esta clase
package com.learningjava.rest.spring.core;

// imports de beans, javax, java lang, sql y utils (arraylist y listas)
import org.apache.commons.beanutils.BeanUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// clase pública
public class ResultSetMapper<T> {
    @SuppressWarnings("unchecked")
    public List<T> mapResultSetToObject(ResultSet rs, Class outputClass) {
        List<T> outputList = null;
        try {
            // asegura resultset no es null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (outputClass.isAnnotationPresent(Entity.class)) {
                    // obtiene los metadatos del resultset
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // obtiene todos los atributos de outputClass
                    Field[] fields = outputClass.getDeclaredFields();
                    while (rs.next()) {
                        T bean = (T) outputClass.newInstance();
                        for (int _iterator = 0; _iterator < rsmd
                                .getColumnCount(); _iterator++) {
                            // obteniendo el nombre de la columna SQL
                            String columnName = rsmd
                                    .getColumnName(_iterator + 1);
                            // leyendo los valores de la columna SQL
                            Object columnValue = rs.getObject(_iterator + 1);
                            // iterando sobre los atributos de outputClass para comprobar si algun atributo tiene la anotación 'Column' con el valor 'name'
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field
                                            .getAnnotation(Column.class);
                                    if (column.name().equalsIgnoreCase(
                                            columnName)
                                            && columnValue != null) {
                                        BeanUtils.setProperty(bean, field
                                                .getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        if (outputList == null) {
                            outputList = new ArrayList<>();
                        }
                        outputList.add(bean);
                    }

                } else {

                    // lanza un error
                }
            } else {
                return null;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return outputList;
    }
}
