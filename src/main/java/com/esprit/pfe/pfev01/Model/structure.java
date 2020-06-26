package com.esprit.pfe.pfev01.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class structure implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Businessdoc;
    private String Label;
    private String Datatype;
    private int Position;
    private String Definition;
    private String DecimalDefinition;
    private String Accesskeyto;
    private String Businessfield;
    private String ClassName;
    private String Name;

    public structure() {
    }

    public structure(String Businessdoc, String Label, String Datatype, int Position, String Definition, String DecimalDefinition, String Accesskeyto, String Businessfield, String ClassName, String Name) {

        this.Businessdoc = Businessdoc;
        this.Label = Label;
        this.Datatype = Datatype;
        this.Position = Position;
        this.Definition = Definition;
        this.DecimalDefinition = DecimalDefinition;
        this.Accesskeyto = Accesskeyto;
        this.Businessfield = Businessfield;
        this.ClassName = ClassName;
        this.Name = Name;
    }

    public structure(String Label, String Datatype, int Position){
        this.Label = Label;
        this.Datatype = Datatype;
        this.Position = Position;
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

    public String getBusinessdoc() {
        return Businessdoc;
    }

    public void setBusinessdoc(String businessdoc) {
        Businessdoc = businessdoc;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getDatatype() {
        return Datatype;
    }

    public void setDatatype(String datatype) {
        Datatype = datatype;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public String getDefinition() {
        return Definition;
    }

    public void setDefinition(String definition) {
        Definition = definition;
    }

    public String getDecimalDefinition() {
        return DecimalDefinition;
    }

    public void setDecimalDefinition(String decimalDefinition) {
        DecimalDefinition = decimalDefinition;
    }

    public String getAccesskeyto() {
        return Accesskeyto;
    }

    public void setAccesskeyto(String accesskeyto) {
        Accesskeyto = accesskeyto;
    }

    public String getBusinessfield() {
        return Businessfield;
    }

    public void setBusinessfield(String businessfield) {
        Businessfield = businessfield;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
