package com.armando.prj_clinicahospitalarfx.data;

import static com.armando.prj_clinicahospitalarfx.HomePageController.consultas;
import static com.armando.prj_clinicahospitalarfx.HomePageController.enfermeiros;
import static com.armando.prj_clinicahospitalarfx.HomePageController.medicos;
import static com.armando.prj_clinicahospitalarfx.HomePageController.pacientes;
import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExportarJSON {

    public void exportarJSON(String nomeArq, String caminhoArq) throws IOException {

        try {
            //instancia da array de pacientes no formato JSON
            JSONArray pacientesArray = new JSONArray();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            //percorre a ArrayList de pacientes e converte cada Paciente em JSONObject
            JSONObject dataObject = new JSONObject();

            for (Paciente paciente : pacientes) {
                JSONObject pacienteObject = new JSONObject();
                pacienteObject.put("id", paciente.getIdPaciente());
                pacienteObject.put("idade", paciente.getIdade());
                pacienteObject.put("data_cadastro", dateFormat.format(paciente.getDataCadastro()));
                pacienteObject.put("obs", paciente.getObsGeral());
                pacienteObject.put("nome", paciente.getNomeCompleto());
                pacienteObject.put("data_nascimento", dateFormat.format(paciente.getDataNascimento()));
                pacienteObject.put("rua", paciente.getEndereco().getRua());
                pacienteObject.put("numero", paciente.getEndereco().getNumero());
                pacienteObject.put("bairro", paciente.getEndereco().getBairro());
                pacienteObject.put("cidade", paciente.getEndereco().getCidade());
                pacienteObject.put("estado", paciente.getEndereco().getEstado());
                pacienteObject.put("cep", paciente.getEndereco().getCep());
                pacienteObject.put("telefone", paciente.getContato().getTelefone());
                pacienteObject.put("celular", paciente.getContato().getCelular());
                pacienteObject.put("email", paciente.getContato().getEmail());
                pacienteObject.put("genero", paciente.getGenero().toString());
                pacienteObject.put("nome_resp", paciente.getContatoResponsavel().getTelefone());
                pacienteObject.put("telefone_resp", paciente.getContatoResponsavel().getTelefone());
                pacienteObject.put("celular_resp", paciente.getContatoResponsavel().getCelular());
                pacienteObject.put("email_resp", paciente.getContatoResponsavel().getEmail());

                //adiciona cada objeto na array de controle
                pacientesArray.put(pacienteObject);
            }
            //adiciona a array de pacientes no objeto json geral
            dataObject.put("pacientes", pacientesArray);

            //instancia da array de medicos no formato JSON
            JSONArray medicosArray = new JSONArray();

            //percorre a ArrayList de medicos e converte cada medico em JSONObject
            for (Medico medico : medicos) {
                JSONObject medicoObject = new JSONObject();

                medicoObject.put("id", medico.getIdMedico());
                medicoObject.put("crm", medico.getNumeroCRM());
                medicoObject.put("especialidades", medico.getAreasEspecialidade().toString());
                medicoObject.put("cirurgiao", medico.isCirurgiao());
                medicoObject.put("setor", medico.getSetor());
                medicoObject.put("ch_semanal", medico.getChSemanal());
                medicoObject.put("nome", medico.getNomeCompleto());
                medicoObject.put("data_nascimento", dateFormat.format(medico.getDataNascimento()));
                medicoObject.put("rua", medico.getEndereco().getRua());
                medicoObject.put("numero", medico.getEndereco().getNumero());
                medicoObject.put("bairro", medico.getEndereco().getBairro());
                medicoObject.put("cidade", medico.getEndereco().getCidade());
                medicoObject.put("estado", medico.getEndereco().getEstado());
                medicoObject.put("cep", medico.getEndereco().getCep());
                medicoObject.put("telefone", medico.getContato().getTelefone());
                medicoObject.put("celular", medico.getContato().getCelular());
                medicoObject.put("email", medico.getContato().getEmail());
                medicoObject.put("genero", medico.getGenero().toString());

                //adiciona cada objeto na array de controle
                medicosArray.put(medicoObject);
            }

            //adiciona a array de medicos no objeto json geral
            dataObject.put("medicos", medicosArray);

            //instancia da array de medicos no formato JSON
            JSONArray enfermeirosArray = new JSONArray();

            //percorre a ArrayList de medicos e converte cada medico em JSONObject
            for (Enfermeiro enfermeiro : enfermeiros) {

                JSONObject enfermeiroObject = new JSONObject();

                enfermeiroObject.put("id", enfermeiro.getIdEnfermeiro());
                enfermeiroObject.put("oprx", enfermeiro.isTreinadoOpRX());
                enfermeiroObject.put("setor", enfermeiro.getSetor());
                enfermeiroObject.put("ch_semanal", enfermeiro.getChSemanal());
                enfermeiroObject.put("nome", enfermeiro.getNomeCompleto());
                enfermeiroObject.put("data_nascimento", dateFormat.format(enfermeiro.getDataNascimento()));
                enfermeiroObject.put("rua", enfermeiro.getEndereco().getRua());
                enfermeiroObject.put("numero", enfermeiro.getEndereco().getNumero());
                enfermeiroObject.put("bairro", enfermeiro.getEndereco().getBairro());
                enfermeiroObject.put("cidade", enfermeiro.getEndereco().getCidade());
                enfermeiroObject.put("estado", enfermeiro.getEndereco().getEstado());
                enfermeiroObject.put("cep", enfermeiro.getEndereco().getCep());
                enfermeiroObject.put("telefone", enfermeiro.getContato().getTelefone());
                enfermeiroObject.put("celular", enfermeiro.getContato().getCelular());
                enfermeiroObject.put("email", enfermeiro.getContato().getEmail());
                enfermeiroObject.put("genero", enfermeiro.getGenero().toString());

                //adiciona cada objeto na array de controle
                enfermeirosArray.put(enfermeiroObject);
            }

            //adiciona a array de enfermeiros no objeto json geral
            dataObject.put("enfermeiros", enfermeirosArray);

            //instancia da array de consultas no formato JSON
            JSONArray consultasArray = new JSONArray();

            //percorre a ArrayList de medicos e converte cada medico em JSONObject
            for (ConsultaMedica consulta : consultas) {

                JSONObject consultaObject = new JSONObject();

                consultaObject.put("id", consulta.getIdConsulta());
                consultaObject.put("id_paciente", consulta.getIdPaciente());
                consultaObject.put("id_medico", consulta.getIdMedico());
                consultaObject.put("queixa", consulta.getExameQueixa());
                consultaObject.put("diagnostico", consulta.getDiagnostico());
                consultaObject.put("prescricao", consulta.getPrescricao());
                consultaObject.put("cirurgia", consulta.isIndicacaoCirurgica());

                //adiciona cada objeto na array de controle
                consultasArray.put(consultaObject);
            }
            
            //adiciona a array de consultas no objeto json geral
            dataObject.put("consultas", consultasArray);

            //cria arquivo com base no input do usuário
            FileWriter writer = new FileWriter(caminhoArq + nomeArq + ".json");
            writer.write(dataObject.toString(2)); 
            writer.close();

            exibirMsgInfo(nomeArq + ".json");

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

    private void exibirMsgInfo(String msg) {
        //função para exibir popup informando o resultado da operação
        Alert msg_alerta = new Alert(Alert.AlertType.INFORMATION);
        msg_alerta.setTitle("Informação");
        msg_alerta.setHeaderText("Operação realizada com sucesso!");
        msg_alerta.setContentText("Arquivo: " + msg);
        msg_alerta.showAndWait();

    }
}
