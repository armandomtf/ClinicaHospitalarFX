/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

import java.util.Date;

/**
 *
 * @author Armando
 */
public class Enfermeiro extends AtendenteHospitalar {

    @Override
    public String toString() {
        return getNomeCompleto();
    }

    public Long getIdEnfermeiro() {
        return idEnfermeiro;
    }

    public void setIdEnfermeiro(Long idEnfermeiro) {
        this.idEnfermeiro = idEnfermeiro;
    }

    public boolean isTreinadoOpRX() {
        return treinadoOpRX;
    }

    public void setTreinadoOpRX(boolean treinadoOpRX) {
        this.treinadoOpRX = treinadoOpRX;
    }

    //Construtor
    public Enfermeiro(boolean treinadoOpRX, String setor, int chSemanal, String nomeCompleto, Date dataNascimento, Endereco endereco, ContatoTelEmail contato, Genero genero) {
        super(setor, chSemanal, nomeCompleto, dataNascimento, endereco, contato, genero);
        this.idEnfermeiro = (long) proximoId++;
        this.treinadoOpRX = treinadoOpRX;
    }

    //Variável estática para gerar id sequencial
    private static int proximoId = 1;
    private Long idEnfermeiro;
    private boolean treinadoOpRX;

}
