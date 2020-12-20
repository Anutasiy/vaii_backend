package com.mati.vaii.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "feedbacks")
public class Feedbacks {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long idUser;
    @NotNull
    @NotEmpty
    @NotBlank
    private String nazov;
    @NotNull
    @NotEmpty
    @NotBlank
    private String popis;
    private Long idUcitel;

    protected Feedbacks() {}

    public Feedbacks(Long id, Long idUser, String nazov, String popis, Long idUcitel) {
        this.id = id;
        this.idUser = idUser;
        this.nazov = nazov;
        this.popis = popis;
        this.idUcitel = idUcitel;
    }


    public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getNazov() {
        return nazov;
    }

    public String getPopis() {
        return popis;
    }

    public Long getIdUcitel() {
        return idUcitel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void setId(Long id) {
        this.id = id;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setIdUcitel(Long idUcitel) {
        this.idUcitel = idUcitel;
    }

    @Override
    public String toString() {
        return "Feedbacks{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", nazov='" + nazov + '\'' +
                ", popis='" + popis + '\'' +
                ", idUcitel=" + idUcitel +
                '}';
    }
}
