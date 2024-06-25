package com.armando.prj_clinicahospitalarfx.data;

import com.armando.prj_clinicahospitalarfx.HomePageController;
import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.armando.prj_clinicahospitalarfx.model.XMLCombine;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class ExportarXML {

    public void exportarXML(String nomeArq, String caminhoArq) throws PropertyException, JAXBException, IOException {
        try {
            //cria o contexto JAXB para a classe XMLCombine que combina nossas 4 entidades
            JAXBContext jaxbContextDados = JAXBContext.newInstance(XMLCombine.class);
            //cria o marshaller para converter os dados em XML
            Marshaller marshallerConsulta = jaxbContextDados.createMarshaller();
            //configura o marshaller para formatar a saída XML
            marshallerConsulta.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            //instancia do arquivo de acordo com o input do usuario
            FileWriter fDados = new FileWriter(caminhoArq+nomeArq+".xml");
            
            //preenchimento de array de controle de Consultas
            ConsultaMedica[] consultas = new ConsultaMedica[HomePageController.consultas.size()];
            for (int i = 0; i < HomePageController.consultas.size(); i++) {
                consultas[i] = HomePageController.consultas.get(i);
            }
            
            //preenchimento de array de controle de Enfermeiros
            Enfermeiro[] enfermeiros = new Enfermeiro[HomePageController.enfermeiros.size()];
            for (int i = 0; i < HomePageController.enfermeiros.size(); i++) {
                enfermeiros[i] = HomePageController.enfermeiros.get(i);
            }
            
            //preenchimento de array de controle de Medicos
            Medico[] medicos = new Medico[HomePageController.medicos.size()];
            for (int i = 0; i < HomePageController.medicos.size(); i++) {
                medicos[i] = HomePageController.medicos.get(i);
            }
            
            //preenchimento de array de controle de Pacientes
            Paciente[] pacientes = new Paciente[HomePageController.pacientes.size()];
            for (int i = 0; i < HomePageController.pacientes.size(); i++) {
                pacientes[i] = HomePageController.pacientes.get(i);
            }
            
            //cria uma instância de XMLCombine com todos os dados coletados
            XMLCombine dadosXML = new XMLCombine(pacientes, medicos, consultas, enfermeiros);
            
            //converte os dados em XML e escreve no arquivo especificado
            marshallerConsulta.marshal(dadosXML, fDados);

            exibirMsgInfo(nomeArq+".xml");

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
