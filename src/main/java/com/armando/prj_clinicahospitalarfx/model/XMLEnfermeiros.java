package com.armando.prj_clinicahospitalarfx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "enfermeiros")
public class XMLEnfermeiros {

    private Enfermeiro[] enfermeiros;

    public XMLEnfermeiros() {
    }

    public XMLEnfermeiros(Enfermeiro[] enfermeiros) {
        this.enfermeiros = enfermeiros;
    }

    @XmlElement(name = "enfermeiro")
    public Enfermeiro[] getEnfermeiros() {
        return enfermeiros;
    }

    public void setEnfermeiros(Enfermeiro[] enfermeiros) {
        this.enfermeiros = enfermeiros;
    }

}
