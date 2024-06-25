package com.armando.prj_clinicahospitalarfx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//classe de parse xml para medicos
@XmlRootElement(name = "medicos")
public class XMLMedicos {

    private Medico[] medicos;

    public XMLMedicos() {
    }

    public XMLMedicos(Medico[] medicos) {
        this.medicos = medicos;
    }

    @XmlElement(name = "medico")
    public Medico[] getMedicos() {
        return medicos;
    }

    public void setMedicos(Medico[] medicos) {
        this.medicos = medicos;
    }

}
