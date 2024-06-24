package com.armando.prj_clinicahospitalarfx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "consultas")
public class XMLConsultas {

    private ConsultaMedica[] consultas;

    public XMLConsultas() {
    }

    public XMLConsultas(ConsultaMedica[] consultas) {
        this.consultas = consultas;
    }

    @XmlElement(name = "consulta")
    public ConsultaMedica[] getConsultas() {
        return consultas;
    }

    public void setConsultas(ConsultaMedica[] consultas) {
        this.consultas = consultas;
    }
}
