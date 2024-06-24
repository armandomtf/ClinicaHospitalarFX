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
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class ExportarXML {

    public static void exportarXML() throws PropertyException, JAXBException, IOException {
        try {

            JAXBContext jaxbContextDados = JAXBContext.newInstance(XMLCombine.class);
            Marshaller marshallerConsulta = jaxbContextDados.createMarshaller();
            marshallerConsulta.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fDados = new FileWriter("C:\\Users\\Armando\\Desktop\\teste.xml");
            ConsultaMedica[] consultas = new ConsultaMedica[HomePageController.consultas.size()];
            for (int i = 0; i < HomePageController.consultas.size(); i++) {
                consultas[i] = HomePageController.consultas.get(i);
            }
            Enfermeiro[] enfermeiros = new Enfermeiro[HomePageController.enfermeiros.size()];
            for (int i = 0; i < HomePageController.enfermeiros.size(); i++) {
                enfermeiros[i] = HomePageController.enfermeiros.get(i);
            }
            Medico[] medicos = new Medico[HomePageController.medicos.size()];
            for (int i = 0; i < HomePageController.medicos.size(); i++) {
                medicos[i] = HomePageController.medicos.get(i);
            }
            Paciente[] pacientes = new Paciente[HomePageController.pacientes.size()];
            for (int i = 0; i < HomePageController.pacientes.size(); i++) {
                pacientes[i] = HomePageController.pacientes.get(i);
            }
            XMLCombine dadosXML = new XMLCombine(pacientes, medicos, consultas, enfermeiros);
            marshallerConsulta.marshal(dadosXML, fDados);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
