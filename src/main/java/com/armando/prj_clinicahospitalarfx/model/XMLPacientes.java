/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//classe de parse xml para pacientes
@XmlRootElement(name = "pacientes")
public class XMLPacientes {

    private Paciente[] pacientes;

    public XMLPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
    }

    public XMLPacientes() {
    }

    @XmlElement(name = "paciente")
    public Paciente[] getPacientes() {
        return pacientes;
    }

    public void setPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
    }

}
