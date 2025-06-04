package com.techxperts.erp.empresa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nit;
    private String direccion;
    private boolean activa;
    private int mesProceso;
    private int anioProceso;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getMesProceso() {
        return mesProceso;
    }

    public void setMesProceso(int mesProceso) {
        this.mesProceso = mesProceso;
    }

    public int getAnioProceso() {
        return anioProceso;
    }

    public void setAnioProceso(int anioProceso) {
        this.anioProceso = anioProceso;
    }
}