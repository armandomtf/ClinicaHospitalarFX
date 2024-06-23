package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.data.ExportarExcel;
import com.armando.prj_clinicahospitalarfx.data.ImportarExcel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ExcelController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void importar() throws IOException {
        // Criando o objeto para usar o método de leitura passando o caminho do arquivo
        ImportarExcel i1 = new ImportarExcel();
        //i1.ReadDataFromExcel(txtArquivo.getText());
        i1.ReadDataFromExcel("bla");
    }

    @FXML
    private void exportar() throws IOException {
        //Criando objeto para usar o método de escrita passando o nome e caminho do arquivo
        ExportarExcel e1 = new ExportarExcel();
        //e1.writeToExcelSheet(txtNomeArq.getText(), txtCaminhoExportar.getText());
        e1.writeToExcelSheet("abc","‪C:\\Users\\Armando\\Desktop\\" );

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
        msg_alerta.setContentText("Paciente: " + msg);
        msg_alerta.showAndWait();
    }

    @FXML
    TextField txtArquivo;
    @FXML
    TextField txtNomeArq;
    @FXML
    TextField txtCaminhoExportar;
}
