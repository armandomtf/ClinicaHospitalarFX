package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.model.ContatoTelEmail;
import com.armando.prj_clinicahospitalarfx.model.Endereco;
import com.armando.prj_clinicahospitalarfx.model.Genero;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.armando.prj_clinicahospitalarfx.model.Responsavel;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class GerenciarPacientesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombobox();

    }

    @FXML
    private void switchToHomePage() throws IOException {
        //redireciona para homepage
        App.setRoot("HomePage");
    }

    private void preencherCombobox() {
        //preenche a combobox com os pacientes cadastrados
        cmbPacientes.getItems().removeAll(cmbPacientes.getItems());
        for (int i = 0; i < HomePageController.pacientes.size(); i++) {
            cmbPacientes.getItems().add(HomePageController.pacientes.get(i));
        }
    }

    @FXML
    private void cadastrarPaciente() throws IOException {
        //função para cadastrar um paciente com seus respectivos dados
        try {
            Genero genero;
            Date dataNascimento = Date.from(this.dtNascimento1.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));

            if (rdMasc1.isSelected()) {
                genero = Genero.masculino;
            } else {
                genero = Genero.feminino;
            }

            //Instancia de endereco
            Endereco endereco = new Endereco(txtRua1.getText(), Integer.parseInt(txtNumero1.getText()),
                    txtBairro1.getText(), txtCidade1.getText(), txtEstado1.getText(), Integer.parseInt(txtCEP1.getText()));

            //Instancia de contato
            ContatoTelEmail contato = new ContatoTelEmail(txtTelefone1.getText(), txtCelular1.getText(), txtEmail1.getText());

            //Instancia de responsavel
            Responsavel resp = new Responsavel(txtNomeResp1.getText(), txtTelefoneResp1.getText(), txtCelularResp1.getText(), txtEmailResp1.getText());

            //Instancia usuario e adiciona na arraylist de controle
            Paciente paciente = new Paciente(Integer.parseInt(txtIdade1.getText()), new Date(), txtComentarios1.getText(), txtNome1.getText(), dataNascimento, endereco, contato, genero, resp);
            HomePageController.pacientes.add(paciente);

            //Atualiza combobox com o novo paciente
            cmbPacientes.getItems().add(paciente);
            exibirMsgInfo(paciente.getNomeCompleto());
            preencherCombobox();
            clearFieldsCadastrar();
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void salvarPaciente() throws IOException {
        //função para salvar dados atualizados do paciente

        int index = cmbPacientes.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.pacientes.get(index).setNomeCompleto(txtNome.getText());
            HomePageController.pacientes.get(index).setDataNascimento(Date.from(this.dtNascimento.getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
            HomePageController.pacientes.get(index).getEndereco().setRua(txtRua.getText());
            HomePageController.pacientes.get(index).getEndereco().setNumero(Integer.parseInt(txtNumero.getText()));
            HomePageController.pacientes.get(index).getEndereco().setBairro(txtBairro.getText());
            HomePageController.pacientes.get(index).getEndereco().setCidade(txtCidade.getText());
            HomePageController.pacientes.get(index).getEndereco().setEstado(txtEstado.getText());
            HomePageController.pacientes.get(index).getEndereco().setCep(Integer.parseInt(txtCEP.getText()));
            HomePageController.pacientes.get(index).getContato().setTelefone(txtTelefone.getText());
            HomePageController.pacientes.get(index).getContato().setCelular(txtCelular.getText());
            HomePageController.pacientes.get(index).getContato().setEmail(txtEmail.getText());
            if (rdMasc.isSelected()) {
                HomePageController.pacientes.get(index).setGenero(Genero.masculino);
            } else if (rdFem.isSelected()) {
                HomePageController.pacientes.get(index).setGenero(Genero.feminino);
            }
            HomePageController.pacientes.get(index).setIdade(Integer.parseInt(txtIdade.getText()));
            HomePageController.pacientes.get(index).setObsGeral(txtComentarios.getText());
            HomePageController.pacientes.get(index).getContatoResponsavel().setNomeResponsavel(txtNomeResp.getText());
            HomePageController.pacientes.get(index).getContatoResponsavel().setEmail(txtEmailResp.getText());
            HomePageController.pacientes.get(index).getContatoResponsavel().setCelular(txtCelularResp.getText());
            HomePageController.pacientes.get(index).getContatoResponsavel().setTelefone(txtTelefoneResp.getText());

            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {

            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void deletarPaciente() throws IOException {
        //função para excluir um paciente

        int index = cmbPacientes.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.pacientes.remove(index);
            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());

        }
    }

    @FXML
    private void onChangeCombo() throws IOException {
        //função para preencher os campos de acordo com o medico seleiconado na combobox

        clearFieldsAlterar();
        int index = cmbPacientes.getSelectionModel().getSelectedIndex();

        txtNome.setText(HomePageController.pacientes.get(index).getNomeCompleto());
        txtBairro.setText(HomePageController.pacientes.get(index).getEndereco().getBairro());
        txtCEP.setText(String.valueOf(HomePageController.pacientes.get(index).getEndereco().getCep()));
        txtCelular.setText(HomePageController.pacientes.get(index).getContato().getCelular());
        txtCelularResp.setText(HomePageController.pacientes.get(index).getContatoResponsavel().getCelular());
        txtCidade.setText(HomePageController.pacientes.get(index).getEndereco().getCidade());
        txtEmail.setText(HomePageController.pacientes.get(index).getContato().getEmail());
        txtEmailResp.setText(HomePageController.pacientes.get(index).getContatoResponsavel().getEmail());
        txtEstado.setText(HomePageController.pacientes.get(index).getEndereco().getEstado());
        txtIdade.setText(String.valueOf(HomePageController.pacientes.get(index).getIdade()));
        txtNomeResp.setText(HomePageController.pacientes.get(index).getContatoResponsavel().getNomeResponsavel());
        txtNumero.setText(String.valueOf(HomePageController.pacientes.get(index).getEndereco().getNumero()));
        txtComentarios.setText(HomePageController.pacientes.get(index).getObsGeral());
        txtRua.setText(HomePageController.pacientes.get(index).getEndereco().getRua());
        txtTelefone.setText(HomePageController.pacientes.get(index).getContato().getTelefone());
        txtTelefoneResp.setText(HomePageController.pacientes.get(index).getContatoResponsavel().getTelefone());
        dtNascimento.setValue(HomePageController.pacientes.get(index).getDataNascimento().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
        if (HomePageController.pacientes.get(index).getGenero() == Genero.masculino) {
            rdMasc.setSelected(true);
            rdFem.setSelected(false);
        } else {
            rdFem.setSelected(true);
            rdMasc.setSelected(false);
        }
    }

    @FXML
    private void clearFieldsAlterar() throws IOException {
        //função para limpar os campos de alteração
        txtNome.setText("");
        dtNascimento.setValue(null);
        rdMasc.setSelected(false);
        rdFem.setSelected(false);
        txtRua.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtCEP.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtNomeResp.setText("");
        txtCelularResp.setText("");
        txtEmailResp.setText("");
        txtTelefoneResp.setText("");
        txtComentarios.setText("");
        txtIdade.setText("");
    }

    @FXML
    private void clearFieldsCadastrar() throws IOException {
        //função para limpar os campos de cadastro
        txtNome1.setText("");
        dtNascimento1.setValue(null);
        rdMasc1.setSelected(false);
        rdFem1.setSelected(false);
        txtRua1.setText("");
        txtNumero1.setText("");
        txtBairro1.setText("");
        txtCidade1.setText("");
        txtEstado1.setText("");
        txtCEP1.setText("");
        txtTelefone1.setText("");
        txtCelular1.setText("");
        txtEmail1.setText("");
        txtNomeResp1.setText("");
        txtCelularResp1.setText("");
        txtEmailResp1.setText("");
        txtTelefoneResp1.setText("");
        txtComentarios1.setText("");
        txtIdade1.setText("");
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
    TextField txtNome1;

    @FXML
    DatePicker dtNascimento1;

    @FXML
    TextField txtIdade1;

    @FXML
    TextField txtRua1;

    @FXML
    TextField txtNumero1;

    @FXML
    TextField txtBairro1;

    @FXML
    TextField txtCidade1;

    @FXML
    TextField txtEstado1;

    @FXML
    TextField txtCEP1;

    @FXML
    TextField txtCelular1;

    @FXML
    TextField txtTelefone1;

    @FXML
    TextField txtEmail1;

    @FXML
    TextField txtNomeResp1;

    @FXML
    TextField txtCelularResp1;

    @FXML
    TextField txtTelefoneResp1;

    @FXML
    TextField txtEmailResp1;

    @FXML
    RadioButton rdMasc1;

    @FXML
    RadioButton rdFem1;

    @FXML
    TextField txtComentarios1;

    @FXML
    ComboBox<Paciente> cmbPacientes;

    @FXML
    TextField txtNome;

    @FXML
    DatePicker dtNascimento;

    @FXML
    TextField txtIdade;

    @FXML
    TextField txtRua;

    @FXML
    TextField txtNumero;

    @FXML
    TextField txtBairro;

    @FXML
    TextField txtCidade;

    @FXML
    TextField txtEstado;

    @FXML
    TextField txtCEP;

    @FXML
    TextField txtCelular;

    @FXML
    TextField txtTelefone;

    @FXML
    TextField txtEmail;

    @FXML
    TextField txtNomeResp;

    @FXML
    TextField txtCelularResp;

    @FXML
    TextField txtTelefoneResp;

    @FXML
    TextField txtEmailResp;

    @FXML
    RadioButton rdMasc;

    @FXML
    RadioButton rdFem;

    @FXML
    TextField txtComentarios;
}
