/**
 * CloudProject3.0
 * com.learningjava.rest.spring.core
 * Created by Emilio Martínez García
 */

// paquete de donde pertenece esta clase
package com.learningjava.rest.spring.core;

// imports de javax y arraylist
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;

// clase javabean
@Entity()
public class Restaurant {
    @Column(name="RES_CODI")
    private String code;
    @Column(name="RES_NOM")
    private String name;
    @Column(name="RES_ADRECA")
    private String address;
    @Column(name="RES_WEB")
    private String website;
    @Column(name="RES_TELEFON")
    private String numero;
    @Column(name="TRS_DESCRIPCIO")
    private String type;
    @Column(name="RES_URL_IMG")
    private String images;
    @Column(name="RES_MITJANA")
    private String mitjana;

    // getters
    public String getCode() { return code; }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public String getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public String getImages() {
        return images;
    }

    public String getMitjana() {
        return mitjana;
    }

    // setters
    public void setCode(String code) { this.code = code; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setMitjana(String mitjana) {
        this.mitjana = mitjana;
    }

}
