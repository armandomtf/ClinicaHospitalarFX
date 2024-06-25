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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ImportarXML {

    public void importarXML(String path) throws IOException {

        //zera as arraylists de controle
        HomePageController.pacientes = new ArrayList<>();
        HomePageController.medicos = new ArrayList<>();
        HomePageController.enfermeiros = new ArrayList<>();
        HomePageController.consultas = new ArrayList<>();

        try {
            //cria o contexto JAXB para a classe XMLCombine que combina nossas 4 entidades
            JAXBContext jaxbContextDados = JAXBContext.newInstance(XMLCombine.class);

            // cria um unmarshaller a partir do contexto JAXB
            Unmarshaller unMarshallerPaciente = jaxbContextDados.createUnmarshaller();

            // da unmarsh no arquivo xml inputado pelo usuario
            XMLCombine dados = (XMLCombine) unMarshallerPaciente.unmarshal(new FileReader(path));

            //percorre cada paciente no 'dados' gerados pelo combine e adiciona ao controle  
            for (Paciente paciente : dados.getPacientes()) {
                HomePageController.pacientes.add(paciente);
            }

            //percorre cada medico no 'dados' gerados pelo combine e adiciona ao controle  
            for (Medico medico : dados.getMedicos()) {
                HomePageController.medicos.add(medico);
            }

            //percorre cada enfermeiro no 'dados' gerados pelo combine e adiciona ao controle  
            for (Enfermeiro enfermeiro : dados.getEnfermeiros()) {
                HomePageController.enfermeiros.add(enfermeiro);
            }

            //percorre cada consulta no 'dados' gerados pelo combine e adiciona ao controle  
            for (ConsultaMedica consulta : dados.getConsultas()) {
                HomePageController.consultas.add(consulta);
            }

            exibirMsgInfo(path);

        } catch (Exception e) {
            e.printStackTrace();
            exibirMsgErroNull(e.getMessage());
        }
    }

    private void exibirMsgErroNull(String s) throws IOException {
        //função para exibir popup de alerta de exceção
        Alert msg_erro = new Alert(Alert.AlertType.ERROR);
        msg_erro.setTitle("Reportando Erro");
        msg_erro.setHeaderText("Favor preencher os controles corretamente");
        msg_erro.setContentText("Erro: " + s);
        //msg_erro.setContentText("Tente novamente!");
        msg_erro.showAndWait();
    }

    private void exibirMsgInfo(String msg) {
        //função para exibir popup informando o resultado da operação
        Alert msg_alerta = new Alert(Alert.AlertType.INFORMATION);
        msg_alerta.setTitle("Informação");
        msg_alerta.setHeaderText("Operação realizada com sucesso!");
        msg_alerta.setContentText("Arquivo: " + msg);
        msg_alerta.showAndWait();

    }
}
