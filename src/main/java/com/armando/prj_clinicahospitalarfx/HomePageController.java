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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class HomePageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void switchToPacientes() throws IOException {
        App.setRoot("GerenciarPacientes");
    }

    @FXML
    private void switchToMedicos() throws IOException {
        App.setRoot("GerenciarMedicos");
    }

    @FXML
    private void switchToEnfermeiros() throws IOException {
        App.setRoot("GerenciarEnfermeiros");
    }

    @FXML
    private void switchToConsultas() throws IOException {
        App.setRoot("GerenciarConsultas");
    }

    @FXML
    private void switchToExcel() throws IOException {
        App.setRoot("Excel");
    }

    public static ArrayList<Medico> medicos = new ArrayList<Medico>();
    public static ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
    public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    public static ArrayList<ConsultaMedica> consultas = new ArrayList<ConsultaMedica>();

    @FXML
    MenuItem menuItemGerenciarPacientes;
    @FXML
    MenuItem menuItemGerenciarMedicos;
    @FXML
    MenuItem menuItemGerenciarEnfermeiros;
    @FXML
    MenuItem menuItemGerenciarConsultas;
    @FXML
    MenuItem menuItemImportarExcel;
    @FXML
    MenuItem menuItemImportarXML;
    @FXML
    MenuItem menuItemImportarJSON;
    @FXML
    MenuItem menuItemExportarExcel;
    @FXML
    MenuItem menuItemExportarXML;
    @FXML
    MenuItem menuItemExportarJSON;
    @FXML
    AnchorPane AnchorPane;

}
