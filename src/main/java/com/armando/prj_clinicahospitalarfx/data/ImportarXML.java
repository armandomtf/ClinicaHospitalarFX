/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.data;

import com.armando.prj_clinicahospitalarfx.HomePageController;
import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.armando.prj_clinicahospitalarfx.model.XMLCombine;
import com.armando.prj_clinicahospitalarfx.model.XMLConsultas;
import com.armando.prj_clinicahospitalarfx.model.XMLEnfermeiros;
import com.armando.prj_clinicahospitalarfx.model.XMLMedicos;
import com.armando.prj_clinicahospitalarfx.model.XMLPacientes;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Armando
 */
public class ImportarXML {

    public static void importarXML() {

        HomePageController.pacientes = new ArrayList<>();
        HomePageController.medicos = new ArrayList<>();
        HomePageController.enfermeiros = new ArrayList<>();
        HomePageController.consultas = new ArrayList<>();

        try {
            JAXBContext jaxbContextDados = JAXBContext.newInstance(XMLCombine.class);
            Unmarshaller unMarshallerPaciente = jaxbContextDados.createUnmarshaller();
            XMLCombine dados = (XMLCombine) unMarshallerPaciente.unmarshal(new FileReader("C:\\Users\\Armando\\Desktop\\teste.xml"));
            for (Paciente paciente : dados.getPacientes()) {
                HomePageController.pacientes.add(paciente);
            }
            for (Medico medico : dados.getMedicos()) {
                HomePageController.medicos.add(medico);
            }
            for (Enfermeiro enfermeiro : dados.getEnfermeiros()) {
                HomePageController.enfermeiros.add(enfermeiro);
            }
            for (ConsultaMedica consulta : dados.getConsultas()) {
                HomePageController.consultas.add(consulta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
