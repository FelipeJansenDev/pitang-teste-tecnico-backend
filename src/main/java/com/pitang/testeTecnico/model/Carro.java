package com.pitang.testeTecnico.model;

import jakarta.persistence.*;

@Entity
public class Carro {

    @Id
    @SequenceGenerator(name = "carro_seq", sequenceName = "CARRO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name="licenseplate", unique = true)
    private String licensePlate;

    @Column(name = "model")
    private String model;

    @Column(name = "manufactureyear")
    private Integer year;

    @ManyToOne
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}