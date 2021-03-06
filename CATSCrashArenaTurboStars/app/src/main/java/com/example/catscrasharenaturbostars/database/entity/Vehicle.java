package com.example.catscrasharenaturbostars.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle_table")
public class Vehicle {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "sasija")
    private int sasija;

    @ColumnInfo(name = "raketa")
    private int raketa;

    @ColumnInfo(name = "busilica")
    private int busilica;

    @ColumnInfo(name = "buzdovan")
    private int buzdovan;

    @ColumnInfo(name = "tocak_p")
    private int tocak_p;

    @ColumnInfo(name = "tocak_z")
    private int tocak_z;

    @ColumnInfo(name = "forklift")
    private int forklift;

    @ColumnInfo(name = "testera")
    private int testera;

    @ColumnInfo(name = "levi_deo")
    private int levi_deo;

    @ColumnInfo(name = "desni_deo")
    private int desni_deo;

    @ColumnInfo(name = "levi_tockovi")
    private int levi_tockovi;

    @ColumnInfo(name = "desni_tockovi")
    private int desni_tockovi;

    @ColumnInfo(name = "jacina_odbrane")
    private int jacina_odbrane;

    @ColumnInfo(name = "jacina_napada")
    private int jacina_napada;

    public Vehicle(String username, int sasija, int raketa, int busilica, int buzdovan, int tocak_p, int tocak_z, int forklift, int testera, int levi_deo, int desni_deo, int levi_tockovi, int desni_tockovi, int jacina_odbrane, int jacina_napada) {
        this.username = username;
        this.sasija = sasija;
        this.raketa = raketa;
        this.busilica = busilica;
        this.buzdovan = buzdovan;
        this.tocak_p = tocak_p;
        this.tocak_z = tocak_z;
        this.forklift = forklift;
        this.testera = testera;
        this.levi_deo = levi_deo;
        this.desni_deo = desni_deo;
        this.levi_tockovi = levi_tockovi;
        this.desni_tockovi = desni_tockovi;
        this.jacina_odbrane = jacina_odbrane;
        this.jacina_napada = jacina_napada;
    }

    public int getJacina_odbrane() {
        return jacina_odbrane;
    }

    public void setJacina_odbrane(int jacina_odbrane) {
        this.jacina_odbrane = jacina_odbrane;
    }

    public int getJacina_napada() {
        return jacina_napada;
    }

    public void setJacina_napada(int jacina_napada) {
        this.jacina_napada = jacina_napada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSasija() {
        return sasija;
    }

    public void setSasija(int sasija) {
        this.sasija = sasija;
    }

    public int getRaketa() {
        return raketa;
    }

    public void setRaketa(int raketa) {
        this.raketa = raketa;
    }

    public int getBusilica() {
        return busilica;
    }

    public void setBusilica(int busilica) {
        this.busilica = busilica;
    }

    public int getBuzdovan() {
        return buzdovan;
    }

    public void setBuzdovan(int buzdovan) {
        this.buzdovan = buzdovan;
    }

    public int getTocak_p() {
        return tocak_p;
    }

    public void setTocak_p(int tocak_p) {
        this.tocak_p = tocak_p;
    }

    public int getTocak_z() {
        return tocak_z;
    }

    public void setTocak_z(int tocak_z) {
        this.tocak_z = tocak_z;
    }

    public int getForklift() {
        return forklift;
    }

    public void setForklift(int forklift) {
        this.forklift = forklift;
    }

    public int getTestera() {
        return testera;
    }

    public void setTestera(int testera) {
        this.testera = testera;
    }

    public int getLevi_deo() {
        return levi_deo;
    }

    public void setLevi_deo(int levi_deo) {
        this.levi_deo = levi_deo;
    }

    public int getDesni_deo() {
        return desni_deo;
    }

    public void setDesni_deo(int desni_deo) {
        this.desni_deo = desni_deo;
    }

    public int getLevi_tockovi() {
        return levi_tockovi;
    }

    public void setLevi_tockovi(int levi_tockovi) {
        this.levi_tockovi = levi_tockovi;
    }

    public int getDesni_tockovi() {
        return desni_tockovi;
    }

    public void setDesni_tockovi(int desni_tockovi) {
        this.desni_tockovi = desni_tockovi;
    }
}
