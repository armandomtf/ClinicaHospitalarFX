/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

import java.util.ArrayList;
import java.util.Date;

public class Medico extends AtendenteHospitalar {

    public Medico() {
    }

    @Override
    public String toString() {
        return getNomeCompleto();
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public int getNumeroCRM() {
        return numeroCRM;
    }

    public void setNumeroCRM(int numeroCRM) {
        this.numeroCRM = numeroCRM;
    }

    public ArrayList<String> getAreasEspecialidade() {
        return areasEspecialidade;
    }

    public void setAreasEspecialidade(ArrayList<String> areasEspecialidade) {
        this.areasEspecialidade = areasEspecialidade;
    }

    public boolean isCirurgiao() {
        return cirurgiao;
    }

    public void setCirurgiao(boolean cirurgiao) {
        this.cirurgiao = cirurgiao;
    }

    //Construtor
    public Medico(int numeroCRM, boolean cirurgiao, String setor, int chSemanal, String nomeCompleto, Date dataNascimento, Endereco endereco, ContatoTelEmail contato, Genero genero, ArrayList<String> areasEspecialidade) {
        super(setor, chSemanal, nomeCompleto, dataNascimento, endereco, contato, genero);
        this.idMedico = (long) proximoId++;
        this.numeroCRM = numeroCRM;
        this.cirurgiao = cirurgiao;
        this.areasEspecialidade = areasEspecialidade;
    }

    //Variável estática para gerar id sequencial
    private static int proximoId = 1;
    private Long idMedico;
    private int numeroCRM;
    private ArrayList<String> areasEspecialidade = new ArrayList<String>();
    private boolean cirurgiao;

}
