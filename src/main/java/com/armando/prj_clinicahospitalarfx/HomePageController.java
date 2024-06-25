package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HomePageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void switchToPacientes() throws IOException {
        //redirecionamento para pagina de pacientes
        App.setRoot("GerenciarPacientes");
    }

    @FXML
    private void switchToMedicos() throws IOException {
        //redirecionamento para pagina de medicos
        App.setRoot("GerenciarMedicos");
    }

    @FXML
    private void switchToEnfermeiros() throws IOException {
        //redirecionamento para pagina de enfermeiros
        App.setRoot("GerenciarEnfermeiros");
    }

    @FXML
    private void switchToConsultas() throws IOException {
        //redirecionamento para pagina de consultas
        App.setRoot("GerenciarConsultas");
    }

    @FXML
    private void switchToDados() throws IOException {
        //redirecionamento para pagina de dados
        App.setRoot("ImportarExportar");
    }

    //instancia das arrays de controles
    public static ArrayList<Medico> medicos = new ArrayList<Medico>();
    public static ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
    public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    public static ArrayList<ConsultaMedica> consultas = new ArrayList<ConsultaMedica>();
 
}

