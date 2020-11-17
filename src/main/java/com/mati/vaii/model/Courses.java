package com.mati.vaii.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nazov;
    private int cena;
    private String popis;

    protected Courses() {}

    public Courses(String nazov, String popis, int cena) {
        this.nazov = nazov;
        this.popis = popis;
        this.cena = cena;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return "Kurz [id=" + id + ", nazov=" + nazov + ", cena=" + cena + ", popis=" + popis + "]";
    }


}

