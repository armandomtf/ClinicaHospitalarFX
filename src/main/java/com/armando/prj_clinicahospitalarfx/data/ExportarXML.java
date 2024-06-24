/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.data;

import static com.armando.prj_clinicahospitalarfx.HomePageController.medicos;
import static com.armando.prj_clinicahospitalarfx.HomePageController.pacientes;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import org.apache.xmlbeans.ObjectFactory;

public class ExportarXML {

    public static void exportarXML() throws PropertyException, JAXBException, IOException {
        XStream xstream = new XStream();

        // Alias the Paciente class to a desired name (optional)
        xstream.alias("paciente", Paciente.class);
        String xmlPac = xstream.toXML(pacientes);

        xstream.alias("medico", Medico.class);
        String xmlMed = xstream.toXML(medicos);
        
        String xmlString = xmlPac+xmlMed;

        FileWriter fileWriter = new FileWriter("C:\\Users\\Armando\\Desktop\\teste.xml");
        fileWriter.write(xmlString);
        fileWriter.close();

    }
}
