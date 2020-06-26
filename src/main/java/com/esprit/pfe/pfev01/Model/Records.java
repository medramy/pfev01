package com.esprit.pfe.pfev01.Model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@Embeddable
//@JsonFormat(shape=JsonFormat.Shape.ARRAY)
@Entity
public class Records implements Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idRec")
    private int idRec;
    @JsonProperty("numerocompte")
    private String numerocompte;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("debit")
    private float debit;
    @JsonProperty("credit")
    private float credit;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", insertable=true, updatable=false)
    private DataCRE datacre;

   /* @JsonCreator
    public Records(@JsonProperty("id") int id, @JsonProperty("numerocompte") String numerocompte, @JsonProperty("nom") String nom, @JsonProperty("prenom") String prenom, @JsonProperty("debit") float debit, @JsonProperty("credit") float credit) {
        this.id = id;
        this.numerocompte = numerocompte;
        this.nom = nom;
        this.prenom = prenom;
        this.credit = credit;
        this.debit = debit;
    }

    */

    @Override
    public String toString() {
        return "Records{" +
                "idRec='" + idRec + '\'' +
                ", numerocompte='" + numerocompte + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }

    //@JsonCreator
    public Records() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int id) {
        this.idRec = id;
    }

    public String getNumerocompte() {
        return numerocompte;
    }

    public void setNumerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public DataCRE getDatacre() {
        return datacre;
    }

    public void setDatacre(DataCRE datacre) {
        this.datacre = datacre;
    }
}