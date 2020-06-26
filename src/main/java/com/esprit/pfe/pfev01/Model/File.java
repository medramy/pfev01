package com.esprit.pfe.pfev01.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;

    public File() {
    }

    public File(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String dataCRE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

    //onetoone with datacre
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "records_id", referencedColumnName = "id")
    //private DataCRE data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDataCRE() {
        return dataCRE;
    }

    public void setDataCRE(String dataCRE) {
        this.dataCRE = dataCRE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

