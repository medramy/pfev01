package com.esprit.pfe.pfev01.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Utilisateur")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    private String password;
    private String identifiant;
    private String raison_sociale;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private int numlic;
    private int numphone;
    private int active = 0;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<File> files = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String identifiant, String raison_sociale, String email, int numlic, int numphone) {
        this.username = username;
        this.password = password;
        this.identifiant = identifiant;
        this.raison_sociale = raison_sociale;
        this.email = email;
        this.numlic = numlic;
        this.numphone = numphone;
    }

    public User(String username, String email, String password, int active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getRaison_sociale() {
        return raison_sociale;
    }

    public void setRaison_sociale(String raison_sociale) {
        this.raison_sociale = raison_sociale;
    }


    public int getNumlic() {
        return numlic;
    }

    public void setNumlic(int numlic) {
        this.numlic = numlic;
    }

    public int getNumphone() {
        return numphone;
    }

    public void setNumphone(int numphone) {
        this.numphone = numphone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", raison_sociale='" + raison_sociale + '\'' +
                ", email='" + email + '\'' +
                ", numlic=" + numlic +
                ", numphone=" + numphone +
                ", active=" + active +
                ", files=" + files +
                ", roles=" + roles +
                '}';
    }
}
