/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.data;

import com.armando.prj_clinicahospitalarfx.HomePageController;
import static com.armando.prj_clinicahospitalarfx.HomePageController.consultas;
import static com.armando.prj_clinicahospitalarfx.HomePageController.enfermeiros;
import static com.armando.prj_clinicahospitalarfx.HomePageController.medicos;
import static com.armando.prj_clinicahospitalarfx.HomePageController.pacientes;
import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.ContatoTelEmail;
import com.armando.prj_clinicahospitalarfx.model.Endereco;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Genero;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.armando.prj_clinicahospitalarfx.model.Responsavel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javafx.scene.control.Alert;

public class ImportarJSON {

    public void importarJSON(String path) throws IOException, ParseException {

        //zera as arraylists de controle    
        HomePageController.pacientes = new ArrayList<>();
        HomePageController.medicos = new ArrayList<>();
        HomePageController.enfermeiros = new ArrayList<>();
        HomePageController.consultas = new ArrayList<>();

        try {

            // cria o objeto JSON com o arquivo inputado
            JSONObject dataObject = new JSONObject(readFile(path));

            // pegando o JSON de pacientes
            JSONArray pacientesArray = dataObject.getJSONArray("pacientes");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // percorre cada paciente no JSON e instancia objetos correspondentes
            for (int i = 0; i < pacientesArray.length(); i++) {
                JSONObject pacienteObject = pacientesArray.getJSONObject(i);

                Genero gp = Genero.feminino;
                if (pacienteObject.getString("genero").equals("masculino")) {
                    gp = Genero.masculino;
                }

                Date dtCadastro = dateFormat.parse(pacienteObject.getString("data_cadastro"));
                Date dtNasc = dateFormat.parse(pacienteObject.getString("data_nascimento"));

                Endereco ep = new Endereco(pacienteObject.getString("rua"), pacienteObject.getInt("numero"), pacienteObject.getString("bairro"), pacienteObject.getString("cidade"), pacienteObject.getString("estado"), pacienteObject.getInt("cep"));
                ContatoTelEmail cp = new ContatoTelEmail(pacienteObject.getString("telefone"), pacienteObject.getString("celular"), pacienteObject.getString("email"));
                Responsavel rp = new Responsavel(pacienteObject.getString("nome_resp"), pacienteObject.getString("telefone_resp"), pacienteObject.getString("celular_resp"), pacienteObject.getString("email_resp"));
                Paciente paciente = new Paciente(pacienteObject.getInt("idade"), dtCadastro, pacienteObject.getString("obs"), pacienteObject.getString("nome"), dtNasc, ep, cp, gp, rp);

                pacientes.add(paciente);
            }

            // instancia de medico
            JSONArray medicosArray = dataObject.getJSONArray("medicos");

            // percorre cada medico no JSON e instancia objetos correspondentes
            for (int i = 0; i < medicosArray.length(); i++) {
                JSONObject medicoObject = medicosArray.getJSONObject(i);

                boolean cirurgiao = false;
                if (medicoObject.getBoolean("cirurgiao")) {
                    cirurgiao = true;
                }
                Genero gm = Genero.feminino;
                if (medicoObject.getString("genero").equals("masculino")) {
                    gm = Genero.masculino;
                }

                Date dtNasc = dateFormat.parse(medicoObject.getString("data_nascimento"));

                String input = medicoObject.getString("especialidades");
                String content = input.substring(1, input.length() - 1);
                String[] items = content.split(",\\s*");
                ArrayList<String> EspTemp = new ArrayList<>(Arrays.asList(items));

                Endereco em = new Endereco(medicoObject.getString("rua"), medicoObject.getInt("numero"), medicoObject.getString("bairro"), medicoObject.getString("cidade"), medicoObject.getString("estado"), medicoObject.getInt("cep"));
                ContatoTelEmail cm = new ContatoTelEmail(medicoObject.getString("telefone"), medicoObject.getString("celular"), medicoObject.getString("email"));
                Medico medico = new Medico(medicoObject.getInt("crm"), cirurgiao, medicoObject.getString("setor"), medicoObject.getInt("ch_semanal"), medicoObject.getString("nome"), dtNasc, em, cm, gm, EspTemp);
                medico.setAreasEspecialidade(EspTemp);

                medicos.add(medico);
            }

            //instancia de enfermeiros
            JSONArray enfermeirosArray = dataObject.getJSONArray("enfermeiros");

            // percorre cada enfermeiro no JSON e instancia objetos correspondentes
            for (int i = 0; i < enfermeirosArray.length(); i++) {
                JSONObject enfermeiroObject = enfermeirosArray.getJSONObject(i);

                boolean rx = false;
                if (enfermeiroObject.getBoolean("oprx")) {
                    rx = true;
                }
                Genero ge = Genero.feminino;
                if (enfermeiroObject.getString("genero").equals("masculino")) {
                    ge = Genero.masculino;
                }

                Date dtNasc = dateFormat.parse(enfermeiroObject.getString("data_nascimento"));

                Endereco ee = new Endereco(enfermeiroObject.getString("rua"), enfermeiroObject.getInt("numero"), enfermeiroObject.getString("bairro"), enfermeiroObject.getString("cidade"), enfermeiroObject.getString("estado"), enfermeiroObject.getInt("cep"));
                ContatoTelEmail ce = new ContatoTelEmail(enfermeiroObject.getString("telefone"), enfermeiroObject.getString("celular"), enfermeiroObject.getString("email"));
                Enfermeiro enfermeiro = new Enfermeiro(rx, enfermeiroObject.getString("setor"), enfermeiroObject.getInt("ch_semanal"), enfermeiroObject.getString("nome"), dtNasc, ee, ce, ge);
                enfermeiros.add(enfermeiro);

            }

            //instancia de consultas
            JSONArray consultasArray = dataObject.getJSONArray("consultas");

            // percorre cada paciente no JSON e instancia objetos correspondentes
            for (int i = 0; i < consultasArray.length(); i++) {
                JSONObject consultaObject = consultasArray.getJSONObject(i);

                boolean ic = false;
                if (consultaObject.getBoolean("cirurgia")) {
                    ic = true;
                }
                ConsultaMedica consulta = new ConsultaMedica(consultaObject.getLong("id_paciente"), consultaObject.getLong("id_medico"), consultaObject.getString("queixa"), consultaObject.getString("diagnostico"), consultaObject.getString("prescricao"), ic);
                consultas.add(consulta);
            }

            //Constroi o historico de consultas na classe pacientes
            for (int i = 0; i < pacientes.size(); i++) {
                ArrayList<ConsultaMedica> consultasTemp = new ArrayList<ConsultaMedica>();
                for (int j = 0; j < consultas.size(); j++) {
                    if (pacientes.get(i).getIdPaciente() == consultas.get(j).getIdPaciente()) {
                        ConsultaMedica consulta = new ConsultaMedica(consultas.get(j).getIdPaciente(), consultas.get(j).getIdMedico(), consultas.get(j).getExameQueixa(), consultas.get(j).getExameQueixa(), consultas.get(j).getPrescricao(), consultas.get(j).isIndicacaoCirurgica());
                        consultasTemp.add(consulta);
                        pacientes.get(i).setHistoricoConsultasMedicas(consultasTemp);
                    }
                }

            }
            exibirMsgInfo(path);

        } catch (Exception e) {
            e.printStackTrace();
            exibirMsgErroNull(e.getMessage());
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(filePath)) {
            int c;
            while ((c = fr.read()) != -1) {
                sb.append((char) c);
            }
        }
        return sb.toString();
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
