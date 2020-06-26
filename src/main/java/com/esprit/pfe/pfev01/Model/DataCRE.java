package com.esprit.pfe.pfev01.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class DataCRE implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique = true, nullable = false)
    private int id;
    @JsonProperty("Businessdoc")
    private String Businessdoc;
   // @Embedded
    @JsonProperty("records")
    //@JsonFormat(shape=JsonFormat.Shape.ARRAY)
    @OneToMany(fetch = FetchType.EAGER,
                    cascade = CascadeType.ALL,
                    mappedBy = "datacre")
    private List<Records> records = new ArrayList<>();

    //onetoone with file
    //@OneToOne(mappedBy = "datacre")
    //private File file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessdoc() {
        return Businessdoc;
    }

    public void setBusinessdoc(String businessdoc) {
        Businessdoc = businessdoc;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public DataCRE() {
    }
}
