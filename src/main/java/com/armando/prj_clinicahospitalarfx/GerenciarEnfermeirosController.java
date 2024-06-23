package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.model.ContatoTelEmail;
import com.armando.prj_clinicahospitalarfx.model.Endereco;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Genero;
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

public class GerenciarEnfermeirosController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombobox();

    }

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("HomePage");
    }

    private void preencherCombobox() {
        cmbEnfermeiros.getItems().removeAll(cmbEnfermeiros.getItems());
        for (int i = 0; i < HomePageController.enfermeiros.size(); i++) {
            cmbEnfermeiros.getItems().add(HomePageController.enfermeiros.get(i));
        }
    }

    @FXML
    private void cadastrarEnfermeiro() throws IOException {
        try {
            boolean oprx = false;
            Genero genero;
            Date dataNascimento = Date.from(this.dtNascimento1.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));

            if (rdMasc1.isSelected()) {
                genero = Genero.masculino;
            } else {
                genero = Genero.feminino;
            }

            if (rdSim1.isSelected()) {
                oprx = true;
            }

            //Instancia de endereco
            Endereco endereco = new Endereco(txtRua1.getText(), Integer.parseInt(txtNumero1.getText()),
                    txtBairro1.getText(), txtCidade1.getText(), txtEstado1.getText(), Integer.parseInt(txtCEP1.getText()));

            //Instancia de contato
            ContatoTelEmail contato = new ContatoTelEmail(txtTelefone1.getText(), txtCelular1.getText(), txtEmail1.getText());

            //Instancia usuario e adiciona na arraylist de controle
            Enfermeiro enfermeiro = new Enfermeiro(oprx, txtSetor1.getText(), Integer.parseInt(txtCH1.getText()), txtNome1.getText(), dataNascimento, endereco, contato, genero);
            HomePageController.enfermeiros.add(enfermeiro);

            //Atualiza combobox com o novo médico
            cmbEnfermeiros.getItems().add(enfermeiro);
            exibirMsgInfo(enfermeiro.getNomeCompleto());
            preencherCombobox();
            clearFieldsCadastrar();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " favor preencher corretamente os dados!");
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void salvarEnfermeiro() throws IOException {
        int index = cmbEnfermeiros.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.enfermeiros.get(index).setNomeCompleto(txtNome.getText());
            HomePageController.enfermeiros.get(index).setDataNascimento(Date.from(this.dtNascimento.getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
            HomePageController.enfermeiros.get(index).getEndereco().setRua(txtRua.getText());
            HomePageController.enfermeiros.get(index).getEndereco().setNumero(Integer.parseInt(txtNumero.getText()));
            HomePageController.enfermeiros.get(index).getEndereco().setBairro(txtBairro.getText());
            HomePageController.enfermeiros.get(index).getEndereco().setCidade(txtCidade.getText());
            HomePageController.enfermeiros.get(index).getEndereco().setEstado(txtEstado.getText());
            HomePageController.enfermeiros.get(index).getEndereco().setCep(Integer.parseInt(txtCEP.getText()));
            HomePageController.enfermeiros.get(index).getContato().setTelefone(txtTelefone.getText());
            HomePageController.enfermeiros.get(index).getContato().setCelular(txtCelular.getText());
            HomePageController.enfermeiros.get(index).getContato().setEmail(txtEmail.getText());
            if (rdMasc.isSelected()) {
                HomePageController.enfermeiros.get(index).setGenero(Genero.masculino);
            } else if (rdFem.isSelected()) {
                HomePageController.enfermeiros.get(index).setGenero(Genero.feminino);
            }
            HomePageController.enfermeiros.get(index).setSetor(txtSetor.getText());
            HomePageController.enfermeiros.get(index).setChSemanal(Integer.parseInt(txtCH.getText()));
            if (rdSim.isSelected()) {
                HomePageController.enfermeiros.get(index).setTreinadoOpRX(true);
            } else if (rdNao.isSelected()) {
                HomePageController.enfermeiros.get(index).setTreinadoOpRX(false);
            }

            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " favor preencher corretamente os dados!");
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void deletarEnfermeiro() throws IOException {
        int index = cmbEnfermeiros.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.enfermeiros.remove(index);
            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());
            //javax.swing.JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage() + " tente novamente.");
        }
    }

    @FXML
    private void onChangeCombo() throws IOException {
        clearFieldsAlterar();
        int index = cmbEnfermeiros.getSelectionModel().getSelectedIndex();

        txtNome.setText(HomePageController.enfermeiros.get(index).getNomeCompleto());
        txtBairro.setText(HomePageController.enfermeiros.get(index).getEndereco().getBairro());
        txtCEP.setText(String.valueOf(HomePageController.enfermeiros.get(index).getEndereco().getCep()));
        txtCelular.setText(HomePageController.enfermeiros.get(index).getContato().getCelular());
        txtCidade.setText(HomePageController.enfermeiros.get(index).getEndereco().getCidade());
        txtEmail.setText(HomePageController.enfermeiros.get(index).getContato().getEmail());
        txtEstado.setText(HomePageController.enfermeiros.get(index).getEndereco().getEstado());
        txtNumero.setText(String.valueOf(HomePageController.enfermeiros.get(index).getEndereco().getNumero()));
        txtRua.setText(HomePageController.enfermeiros.get(index).getEndereco().getRua());
        txtTelefone.setText(HomePageController.enfermeiros.get(index).getContato().getTelefone());
        dtNascimento.setValue(HomePageController.enfermeiros.get(index).getDataNascimento().toInstant().atZone(ZoneOffset.UTC).toLocalDate());

        if (HomePageController.enfermeiros.get(index).getGenero() == Genero.masculino) {
            rdMasc.setSelected(true);
            rdFem.setSelected(false);
        } else {
            rdFem.setSelected(true);
            rdMasc.setSelected(false);
        }
        if (HomePageController.enfermeiros.get(index).isTreinadoOpRX()) {
            rdSim.setSelected(true);
            rdNao.setSelected(false);
        } else {
            rdNao.setSelected(true);
            rdSim.setSelected(false);
        }

        txtSetor.setText(HomePageController.enfermeiros.get(index).getSetor());
        txtCH.setText(String.valueOf(HomePageController.enfermeiros.get(index).getChSemanal()));

    }

    @FXML
    private void clearFieldsAlterar() throws IOException {
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
        rdSim.setSelected(false);
        rdNao.setSelected(false);
        txtSetor.setText("");
        txtCH.setText("");
    }

    @FXML
    private void clearFieldsCadastrar() throws IOException {
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
        rdSim1.setSelected(false);
        rdNao1.setSelected(false);
        txtSetor1.setText("");
        txtCH1.setText("");
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
        msg_alerta.setContentText("Enfermeiro: " + msg);
        msg_alerta.showAndWait();
    }

    @FXML
    TextField txtNome1;

    @FXML
    DatePicker dtNascimento1;

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
    RadioButton rdMasc1;

    @FXML
    RadioButton rdFem1;

    @FXML
    RadioButton rdSim1;
    @FXML
    RadioButton rdNao1;
    @FXML
    TextField txtSetor1;
    @FXML
    TextField txtCH1;

    @FXML
    ComboBox<Enfermeiro> cmbEnfermeiros;

    @FXML
    TextField txtNome;

    @FXML
    DatePicker dtNascimento;

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
    RadioButton rdMasc;

    @FXML
    RadioButton rdFem;

    @FXML
    RadioButton rdSim;
    @FXML
    RadioButton rdNao;
    @FXML
    TextField txtSetor;
    @FXML
    TextField txtCH;

}
