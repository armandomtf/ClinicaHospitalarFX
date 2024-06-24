package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class GerenciarConsultasController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherComboConsultas();
        preencherComboPacientes();
        preencherComboMedicos();

    }

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("HomePage");
    }

    private void preencherComboConsultas() {
        cmbConsultas.getItems().removeAll(cmbConsultas.getItems());
        for (int i = 0; i < HomePageController.consultas.size(); i++) {
            cmbConsultas.getItems().add(HomePageController.consultas.get(i));
        }
    }

    private void preencherComboPacientes() {
        cmbPacientes.getItems().removeAll(cmbPacientes.getItems());
        for (int i = 0; i < HomePageController.pacientes.size(); i++) {
            cmbPacientes.getItems().add(HomePageController.pacientes.get(i));
        }

        cmbPacientes1.getItems().removeAll(cmbPacientes1.getItems());
        for (int i = 0; i < HomePageController.pacientes.size(); i++) {
            cmbPacientes1.getItems().add(HomePageController.pacientes.get(i));
        }
    }

    private void preencherComboMedicos() {
        cmbMedicos.getItems().removeAll(cmbMedicos.getItems());
        for (int i = 0; i < HomePageController.medicos.size(); i++) {
            cmbMedicos.getItems().add(HomePageController.medicos.get(i));
        }

        cmbMedicos1.getItems().removeAll(cmbMedicos1.getItems());
        for (int i = 0; i < HomePageController.medicos.size(); i++) {
            cmbMedicos1.getItems().add(HomePageController.medicos.get(i));
        }
    }

    @FXML
    private void cadastrarConsulta() throws IOException {
        int pacIndex = cmbPacientes1.getSelectionModel().getSelectedIndex();
        int medIndex = cmbMedicos1.getSelectionModel().getSelectedIndex();

        try {
            boolean cirurgia = false;

            //Crio uma array temporária que pegará as consultas existentes do paciente e depois
            //irá incrementar com a nova consulta e salvar no atributo novamente
            ArrayList<ConsultaMedica> consultasTemp = new ArrayList<ConsultaMedica>();
            consultasTemp = HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas();

            if (rdSim1.isSelected()) { //checa se o radio de cirurgia foi selecionado
                cirurgia = true;
            }

            //Cria instancia de consulta e adiciona às arraylists
            ConsultaMedica consulta = new ConsultaMedica(HomePageController.pacientes.get(pacIndex).getIdPaciente(), HomePageController.medicos.get(medIndex).getIdMedico(), txtQueixa1.getText(), txtDiagnostico1.getText(), txtPrescricao1.getText(), cirurgia);
            HomePageController.consultas.add(consulta);
            consultasTemp.add(consulta);

            //Setando no histórico de consultaso do paciente
            HomePageController.pacientes.get(pacIndex).setHistoricoConsultasMedicas(consultasTemp);

            //Atualiza combobox com o novo médico
            cmbConsultas.getItems().add(consulta);
            exibirMsgInfo(consulta.getIdConsulta().toString());
            preencherComboConsultas();
            clearFieldsCadastrar();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " favor preencher corretamente os dados!");
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void salvarConsulta() throws IOException {
        int consIndex = cmbConsultas.getSelectionModel().getSelectedIndex();
        int pacId = cmbPacientes.getSelectionModel().getSelectedIndex() + 1;
        int medId = cmbMedicos.getSelectionModel().getSelectedIndex() + 1;
        int pacIndex = cmbPacientes.getSelectionModel().getSelectedIndex();

        try {

            //atualizando a consulta
            HomePageController.consultas.get(consIndex).setIdPaciente((long) pacId);
            HomePageController.consultas.get(consIndex).setIdMedico((long) medId);
            HomePageController.consultas.get(consIndex).setDiagnostico(txtDiagnostico.getText());
            HomePageController.consultas.get(consIndex).setExameQueixa(txtQueixa.getText());
            HomePageController.consultas.get(consIndex).setPrescricao(txtPrescricao.getText());
            if (rdSim.isSelected()) {
                HomePageController.consultas.get(consIndex).setIndicacaoCirurgica(true);
            } else if (rdNao.isSelected()) {
                HomePageController.consultas.get(consIndex).setIndicacaoCirurgica(false);
            }

            //atualizando historico do paciente
            HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setDiagnostico(txtDiagnostico.getText());
            HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setExameQueixa(txtQueixa.getText());
            HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setPrescricao(txtPrescricao.getText());
            HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setIdMedico((long) medId);
            if (rdSim.isSelected()) {
                HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setIndicacaoCirurgica(true);
            } else if (rdNao.isSelected()) {
                HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().get(consIndex).setIndicacaoCirurgica(false);
            }

            exibirMsgInfo(String.valueOf(consIndex + 1));
            preencherComboConsultas();
            clearFieldsAlterar();
        } catch (Exception e) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " favor preencher corretamente os dados!");
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void deletarConsulta() throws IOException {
        int pacIndex = cmbPacientes.getSelectionModel().getSelectedIndex();
        int consIndex = cmbConsultas.getSelectionModel().getSelectedIndex();

        try {

            HomePageController.pacientes.get(pacIndex).getHistoricoConsultasMedicas().remove(consIndex);
            HomePageController.consultas.remove(consIndex);
            exibirMsgInfo(String.valueOf(consIndex+1));
            preencherComboConsultas();
            clearFieldsAlterar();
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());
            //javax.swing.JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " tente novamente.");
        }
    }

    @FXML
    private void onChangeCombo() throws IOException {
        clearFieldsAlterar();

        int consIndex = cmbConsultas.getSelectionModel().getSelectedIndex();
        int pacId = Integer.parseInt(String.valueOf(HomePageController.consultas.get(consIndex).getIdPaciente())) - 1;
        int medId = Integer.parseInt(String.valueOf(HomePageController.consultas.get(consIndex).getIdMedico())) - 1;

        cmbPacientes.getSelectionModel().select(pacId);
        cmbMedicos.getSelectionModel().select(medId);
        txtQueixa.setText(HomePageController.consultas.get(consIndex).getExameQueixa());
        txtDiagnostico.setText(HomePageController.consultas.get(consIndex).getDiagnostico());
        txtPrescricao.setText(HomePageController.consultas.get(consIndex).getPrescricao());
        if (HomePageController.consultas.get(consIndex).isIndicacaoCirurgica()) {
            rdSim.setSelected(true);
            rdNao.setSelected(false);
        } else {
            rdNao.setSelected(true);
            rdSim.setSelected(false);
        }

    }

    @FXML
    private void clearFieldsAlterar() throws IOException {
        rdSim.setSelected(false);
        rdNao.setSelected(false);
        txtQueixa.setText("");
        txtPrescricao.setText("");
        txtDiagnostico.setText("");
        cmbMedicos.setValue(null);
        cmbPacientes.setValue(null);
    }

    @FXML
    private void clearFieldsCadastrar() throws IOException {
        rdSim1.setSelected(false);
        rdNao1.setSelected(false);
        txtQueixa1.setText("");
        txtPrescricao1.setText("");
        txtDiagnostico1.setText("");
        cmbMedicos1.setValue(null);
        cmbPacientes1.setValue(null);
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
        msg_alerta.setContentText("Consulta: " + msg);
        msg_alerta.showAndWait();
    }

    @FXML
    TextArea txtDiagnostico;
    @FXML
    TextArea txtQueixa;
    @FXML
    TextArea txtPrescricao;
    @FXML
    TextArea txtDiagnostico1;
    @FXML
    TextArea txtQueixa1;
    @FXML
    TextArea txtPrescricao1;

    @FXML
    RadioButton rdSim1;
    @FXML
    RadioButton rdNao1;

    @FXML
    ComboBox<ConsultaMedica> cmbConsultas;
    @FXML
    ComboBox<Paciente> cmbPacientes;
    @FXML
    ComboBox<Paciente> cmbPacientes1;
    @FXML
    ComboBox<Medico> cmbMedicos;
    @FXML
    ComboBox<Medico> cmbMedicos1;

    @FXML
    RadioButton rdSim;
    @FXML
    RadioButton rdNao;

}
