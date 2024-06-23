/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

/**
 *
 * @author Armando
 */
public class Responsavel extends ContatoTelEmail {

    @Override
    public String toString() {
        return getNomeResponsavel();
    }

    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    //Construtor
    public Responsavel(String nomeResponsavel, String telefone, String celular, String email) {
        super(telefone, celular, email);
        this.idResponsavel = (long) proximoId++;
        this.nomeResponsavel = nomeResponsavel;
    }

    //Variável estática para gerar id sequencial
    private static int proximoId = 1;
    private Long idResponsavel;
    private String nomeResponsavel;
}
