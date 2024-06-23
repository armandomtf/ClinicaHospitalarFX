/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

/**
 *
 * @author Armando
 */
public class ConsultaMedica {

    @Override
    public String toString() {
        return getIdConsulta().toString();
    }

    //Construtor
    public ConsultaMedica(Long idPaciente, Long idMedico, String exameQueixa, String diagnostico, String prescricao, boolean indicacaoCirurgica) {
        this.idConsulta = (long) proximoId++;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.exameQueixa = exameQueixa;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.indicacaoCirurgica = indicacaoCirurgica;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getExameQueixa() {
        return exameQueixa;
    }

    public void setExameQueixa(String exameQueixa) {
        this.exameQueixa = exameQueixa;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public boolean isIndicacaoCirurgica() {
        return indicacaoCirurgica;
    }

    public void setIndicacaoCirurgica(boolean indicacaoCirurgica) {
        this.indicacaoCirurgica = indicacaoCirurgica;
    }

    //Variável estática para gerar id sequencial
    private static int proximoId = 1;
    private Long idConsulta;
    private Long idPaciente;
    private Long idMedico;
    private String exameQueixa;
    private String diagnostico;
    private String prescricao;
    private boolean indicacaoCirurgica;

}
