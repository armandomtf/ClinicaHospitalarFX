package com.armando.prj_clinicahospitalarfx;

import com.armando.prj_clinicahospitalarfx.model.ContatoTelEmail;
import com.armando.prj_clinicahospitalarfx.model.Endereco;
import com.armando.prj_clinicahospitalarfx.model.Genero;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class GerenciarMedicosController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombobox();

    }

    @FXML
    private void switchToHomePage() throws IOException {
        //redireciona para a homepage
        App.setRoot("HomePage");
    }

    private void preencherCombobox() {
        //função para preencher a combobox com os médicos cadastrados
        cmbMedicos.getItems().removeAll(cmbMedicos.getItems());
        for (int i = 0; i < HomePageController.medicos.size(); i++) {
            cmbMedicos.getItems().add(HomePageController.medicos.get(i));
        }
    }

    public ArrayList<String> preencherEspecialidadesCadastro(ArrayList<String> list) {
        //Função para preencher array de especialidades de acordo com a seleção (cadastrar)
        if (chkCardio1.isSelected()) {
            list.add(chkCardio.getText());
        }
        if (chkGineco1.isSelected()) {
            list.add(chkGineco.getText());
        }
        if (chkNeuro1.isSelected()) {
            list.add(chkNeuro.getText());
        }
        if (chkOftalmo1.isSelected()) {
            list.add(chkOftalmo.getText());
        }
        if (chkOrto1.isSelected()) {
            list.add(chkOrto.getText());
        }
        if (chkPediatria1.isSelected()) {
            list.add(chkPediatria.getText());
        }
        if (chkPsi1.isSelected()) {
            list.add(chkPsi.getText());
        }
        if (chkUrologia1.isSelected()) {
            list.add(chkUrologia.getText());
        }

        return list;
    }

    public ArrayList<String> preencherEspecialidadesAlterar(ArrayList<String> list) {
        //Função para preencher array de especialidades de acordo com a seleção (cadastrar)
        if (chkCardio.isSelected()) {
            list.add(chkCardio.getText());
        }
        if (chkGineco.isSelected()) {
            list.add(chkGineco.getText());
        }
        if (chkNeuro.isSelected()) {
            list.add(chkNeuro.getText());
        }
        if (chkOftalmo.isSelected()) {
            list.add(chkOftalmo.getText());
        }
        if (chkOrto.isSelected()) {
            list.add(chkOrto.getText());
        }
        if (chkPediatria.isSelected()) {
            list.add(chkPediatria.getText());
        }
        if (chkPsi.isSelected()) {
            list.add(chkPsi.getText());
        }
        if (chkUrologia.isSelected()) {
            list.add(chkUrologia.getText());
        }

        return list;
    }

    @FXML
    private void cadastrarMedico() throws IOException {
        //função para cadastrar novo médico

        ArrayList<String> especialidades = new ArrayList<String>();
        try {
            boolean cirurgiao = false;
            especialidades = preencherEspecialidadesCadastro(especialidades);
            Genero genero;
            Date dataNascimento = Date.from(this.dtNascimento1.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));

            if (rdMasc1.isSelected()) {
                genero = Genero.masculino;
            } else {
                genero = Genero.feminino;
            }

            if (rdSim1.isSelected()) {
                cirurgiao = true;
            }

            //Instancia de endereco
            Endereco endereco = new Endereco(txtRua1.getText(), Integer.parseInt(txtNumero1.getText()),
                    txtBairro1.getText(), txtCidade1.getText(), txtEstado1.getText(), Integer.parseInt(txtCEP1.getText()));

            //Instancia de contato
            ContatoTelEmail contato = new ContatoTelEmail(txtTelefone1.getText(), txtCelular1.getText(), txtEmail1.getText());

            //Instancia usuario e adiciona na arraylist de controle
            Medico medico = new Medico(Integer.parseInt(txtCRM1.getText()), cirurgiao, txtSetor1.getText(), Integer.parseInt(txtCH1.getText()), txtNome1.getText(), dataNascimento, endereco, contato, genero, especialidades);
            HomePageController.medicos.add(medico);

            //Atualiza combobox com o novo médico
            cmbMedicos.getItems().add(medico);
            exibirMsgInfo(medico.getNomeCompleto());
            preencherCombobox();
            clearFieldsCadastrar();
        } catch (Exception e) {

            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void salvarMedico() throws IOException {
        //função para salvar novos dados adicionados ao médico
        
        int index = cmbMedicos.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.medicos.get(index).setNomeCompleto(txtNome.getText());
            HomePageController.medicos.get(index).setDataNascimento(Date.from(this.dtNascimento.getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
            HomePageController.medicos.get(index).getEndereco().setRua(txtRua.getText());
            HomePageController.medicos.get(index).getEndereco().setNumero(Integer.parseInt(txtNumero.getText()));
            HomePageController.medicos.get(index).getEndereco().setBairro(txtBairro.getText());
            HomePageController.medicos.get(index).getEndereco().setCidade(txtCidade.getText());
            HomePageController.medicos.get(index).getEndereco().setEstado(txtEstado.getText());
            HomePageController.medicos.get(index).getEndereco().setCep(Integer.parseInt(txtCEP.getText()));
            HomePageController.medicos.get(index).getContato().setTelefone(txtTelefone.getText());
            HomePageController.medicos.get(index).getContato().setCelular(txtCelular.getText());
            HomePageController.medicos.get(index).getContato().setEmail(txtEmail.getText());
            if (rdMasc.isSelected()) {
                HomePageController.medicos.get(index).setGenero(Genero.masculino);
            } else if (rdFem.isSelected()) {
                HomePageController.medicos.get(index).setGenero(Genero.feminino);
            }
            HomePageController.medicos.get(index).setNumeroCRM(Integer.parseInt(txtCRM.getText()));
            HomePageController.medicos.get(index).setSetor(txtSetor.getText());
            HomePageController.medicos.get(index).setChSemanal(Integer.parseInt(txtCH.getText()));
            if (rdSim.isSelected()) {
                HomePageController.medicos.get(index).setCirurgiao(true);
            } else if (rdNao.isSelected()) {
                HomePageController.medicos.get(index).setCirurgiao(false);
            }
            ArrayList<String> especialidades2 = new ArrayList<String>();
            especialidades2 = preencherEspecialidadesAlterar(especialidades2);
            HomePageController.medicos.get(index).setAreasEspecialidade(especialidades2);

            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {
            
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void deletarMedico() throws IOException {
        //função para remover um médico
        
        int index = cmbMedicos.getSelectionModel().getSelectedIndex();

        try {
            HomePageController.medicos.remove(index);
            exibirMsgInfo(txtNome.getText());
            preencherCombobox();
            clearFieldsAlterar();
        } catch (Exception e) {
            exibirMsgErroNull(e.getMessage());
        }
    }

    @FXML
    private void onChangeCombo() throws IOException {
         //função para preencher os campos de acordo com o paciente seleiconado na combobox
        
        clearFieldsAlterar();
        int index = cmbMedicos.getSelectionModel().getSelectedIndex();

        txtNome.setText(HomePageController.medicos.get(index).getNomeCompleto());
        txtBairro.setText(HomePageController.medicos.get(index).getEndereco().getBairro());
        txtCEP.setText(String.valueOf(HomePageController.medicos.get(index).getEndereco().getCep()));
        txtCelular.setText(HomePageController.medicos.get(index).getContato().getCelular());
        txtCidade.setText(HomePageController.medicos.get(index).getEndereco().getCidade());
        txtEmail.setText(HomePageController.medicos.get(index).getContato().getEmail());
        txtEstado.setText(HomePageController.medicos.get(index).getEndereco().getEstado());
        txtCRM.setText(String.valueOf(HomePageController.medicos.get(index).getNumeroCRM()));
        txtNumero.setText(String.valueOf(HomePageController.medicos.get(index).getEndereco().getNumero()));
        txtRua.setText(HomePageController.medicos.get(index).getEndereco().getRua());
        txtTelefone.setText(HomePageController.medicos.get(index).getContato().getTelefone());
        dtNascimento.setValue(HomePageController.medicos.get(index).getDataNascimento().toInstant().atZone(ZoneOffset.UTC).toLocalDate());

        if (HomePageController.medicos.get(index).getGenero() == Genero.masculino) {
            rdMasc.setSelected(true);
            rdFem.setSelected(false);
        } else {
            rdFem.setSelected(true);
            rdMasc.setSelected(false);
        }
        if (HomePageController.medicos.get(index).isCirurgiao()) {
            rdSim.setSelected(true);
            rdNao.setSelected(false);
        } else {
            rdNao.setSelected(true);
            rdSim.setSelected(false);
        }

        txtSetor.setText(HomePageController.medicos.get(index).getSetor());
        txtCH.setText(String.valueOf(HomePageController.medicos.get(index).getChSemanal()));

        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Cardiologia")) {
            chkCardio.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Neurologia")) {
            chkNeuro.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Pediatria")) {
            chkPediatria.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Urologia")) {
            chkUrologia.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Ginecologia")) {
            chkGineco.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Psicologia")) {
            chkPsi.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Ortopedia")) {
            chkOrto.setSelected(true);
        }
        if (HomePageController.medicos.get(index).getAreasEspecialidade().contains("Oftalmologia")) {
            chkOftalmo.setSelected(true);
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
        chkCardio.setSelected(false);
        chkGineco.setSelected(false);
        chkNeuro.setSelected(false);
        chkOftalmo.setSelected(false);
        chkOrto.setSelected(false);
        chkPediatria.setSelected(false);
        chkPsi.setSelected(false);
        chkUrologia.setSelected(false);
        txtCRM.setText("");
        rdSim.setSelected(false);
        rdNao.setSelected(false);
        txtSetor.setText("");
        txtCH.setText("");
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
        chkCardio1.setSelected(false);
        chkGineco1.setSelected(false);
        chkNeuro1.setSelected(false);
        chkOftalmo1.setSelected(false);
        chkOrto1.setSelected(false);
        chkPediatria1.setSelected(false);
        chkPsi1.setSelected(false);
        chkUrologia1.setSelected(false);
        txtCRM1.setText("");
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
        msg_alerta.setContentText("Médico: " + msg);
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
    TextField txtCRM1;
    @FXML
    TextField txtSetor1;
    @FXML
    TextField txtCH1;
    @FXML
    CheckBox chkCardio1;
    @FXML
    CheckBox chkGineco1;
    @FXML
    CheckBox chkNeuro1;
    @FXML
    CheckBox chkOftalmo1;
    @FXML
    CheckBox chkOrto1;
    @FXML
    CheckBox chkPediatria1;
    @FXML
    CheckBox chkPsi1;
    @FXML
    CheckBox chkUrologia1;

    @FXML
    ComboBox<Medico> cmbMedicos;

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
    TextField txtCRM;
    @FXML
    TextField txtSetor;
    @FXML
    TextField txtCH;
    @FXML
    CheckBox chkCardio;
    @FXML
    CheckBox chkGineco;
    @FXML
    CheckBox chkNeuro;
    @FXML
    CheckBox chkOftalmo;
    @FXML
    CheckBox chkOrto;
    @FXML
    CheckBox chkPediatria;
    @FXML
    CheckBox chkPsi;
    @FXML
    CheckBox chkUrologia;

}
