package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.data.ExportarExcel;
import com.armando.prj_clinicahospitalarfx.data.ExportarJSON;
import com.armando.prj_clinicahospitalarfx.data.ExportarXML;
import com.armando.prj_clinicahospitalarfx.data.ImportarExcel;
import com.armando.prj_clinicahospitalarfx.data.ImportarJSON;
import com.armando.prj_clinicahospitalarfx.data.ImportarXML;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ImportarExportarController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombo();
    }

    public void preencherCombo() {
        //função para preencher as comboboxes
        cmbExtensaoImportar.getItems().add("Excel");
        cmbExtensaoImportar.getItems().add("JSON");
        cmbExtensaoImportar.getItems().add("XML");
        cmbExtensaoExportar.getItems().add("Excel");
        cmbExtensaoExportar.getItems().add("JSON");
        cmbExtensaoExportar.getItems().add("XML");
    }

    @FXML
    private void switchToHomePage() throws IOException {
        //redirecionamento para homepage
        App.setRoot("HomePage");
    }

    @FXML
    private void importar() throws IOException, ParseException {

        //implementei um switch case para pegar a extensão selecionada na combobox e 
        //redirecionar para a classe correspondente
        try {
            switch (cmbExtensaoImportar.getSelectionModel().getSelectedIndex()) {
                case 0:
                    ImportarExcel ie = new ImportarExcel();
                    ie.importarExcel(txtArquivo.getText());
                    break;
                case 1:
                    ImportarJSON ij = new ImportarJSON();
                    ij.importarJSON(txtArquivo.getText());
                    break;
                case 2:
                    ImportarXML ix = new ImportarXML();
                    ix.importarXML(txtArquivo.getText());
                    break;
                default:
                    throw new Exception();
                //  break;
            }
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());
        }

    }

    @FXML
    private void exportar() throws IOException {
        //implementei um switch case para pegar a extensão selecionada na combobox e 
        //redirecionar para a classe correspondente
        try {
            switch (cmbExtensaoExportar.getSelectionModel().getSelectedIndex()) {
                case 0:
                    ExportarExcel ee = new ExportarExcel();
                    ee.exportarExcel(txtNomeArq.getText(), txtCaminhoExportar.getText());
                    break;
                case 1:
                    ExportarJSON ej = new ExportarJSON();
                    ej.exportarJSON(txtNomeArq.getText(), txtCaminhoExportar.getText());
                    break;
                case 2:
                    ExportarXML ex = new ExportarXML();
                    ex.exportarXML(txtNomeArq.getText(), txtCaminhoExportar.getText());
                    break;
                default:
                    throw new Exception();
                //  break;
            }
        } catch (Exception e) {
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

    private void exibirMsgInfo(String msg) throws IOException {
        //função para exibir popup informando o resultado da operação
        Alert msg_alerta = new Alert(Alert.AlertType.INFORMATION);
        msg_alerta.setTitle("Informação");
        msg_alerta.setHeaderText("Operação realizada com sucesso!");
        msg_alerta.setContentText("Arquivo: " + msg);
        msg_alerta.showAndWait();
    }

    @FXML
    TextField txtArquivo;
    @FXML
    TextField txtNomeArq;
    @FXML
    TextField txtCaminhoExportar;
    @FXML
    ComboBox cmbExtensaoImportar;
    @FXML
    ComboBox cmbExtensaoExportar;

}
