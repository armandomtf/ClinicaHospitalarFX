package com.armando.prj_clinicahospitalarfx.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dados")
public class XMLCombine {
    
    private Paciente[] pacientes;
    private Medico[] medicos;
    private ConsultaMedica[] consultas;
    private Enfermeiro[] enfermeiros;

    public XMLCombine() {
    }

    public XMLCombine(Paciente[] pacientes, Medico[] medicos, ConsultaMedica[] consultas, Enfermeiro[] enfermeiros) {
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.consultas = consultas;
        this.enfermeiros = enfermeiros;
    }

    public Paciente[] getPacientes() {
        return pacientes;
    }

    public void setPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
    }

    public Medico[] getMedicos() {
        return medicos;
    }

    public void setMedicos(Medico[] medicos) {
        this.medicos = medicos;
    }

    public ConsultaMedica[] getConsultas() {
        return consultas;
    }

    public void setConsultas(ConsultaMedica[] consultas) {
        this.consultas = consultas;
    }

    public Enfermeiro[] getEnfermeiros() {
        return enfermeiros;
    }

    public void setEnfermeiros(Enfermeiro[] enfermeiros) {
        this.enfermeiros = enfermeiros;
    }
    
    
    
}


