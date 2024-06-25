package com.armando.prj_clinicahospitalarfx.data;

import static com.armando.prj_clinicahospitalarfx.HomePageController.consultas;
import static com.armando.prj_clinicahospitalarfx.HomePageController.enfermeiros;
import static com.armando.prj_clinicahospitalarfx.HomePageController.medicos;
import static com.armando.prj_clinicahospitalarfx.HomePageController.pacientes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarExcel {

    public void exportarExcel(String nomeArq, String caminhoArq) throws IOException {
        //Instancia da planilha
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Criando aba Paciente
        String[] headerPaciente = {"Id paciente", "Idade", "Data de Cadastro", "Observações", "Nome", "Data de Nascimento", "Rua", "Número", "Bairro", "Cidade", "Estado", "CEP", "Telefone", "Celular", "Email", "Genero", "Nome Responsável", "Tel. Responsável", "Cel Responsável", "Email Responsável"};
        XSSFSheet abaPaciente = workbook.createSheet("Paciente");
        Row headerRowPaciente = abaPaciente.createRow(0);

        //Criando cabeçalho
        for (int i = 0; i < headerPaciente.length; i++) {
            Cell cell = headerRowPaciente.createCell(i);
            cell.setCellValue(headerPaciente[i]);
        }

        // Criando as linhas para cada paciente
        for (int i = 0; i < pacientes.size(); i++) {
            Row dataRow = abaPaciente.createRow(i + 1);
            dataRow.createCell(0).setCellValue(pacientes.get(i).getIdPaciente());
            dataRow.createCell(1).setCellValue(pacientes.get(i).getIdade());
            dataRow.createCell(2).setCellValue(pacientes.get(i).getDataCadastro());
            dataRow.createCell(3).setCellValue(pacientes.get(i).getObsGeral());
            dataRow.createCell(4).setCellValue(pacientes.get(i).getNomeCompleto());
            dataRow.createCell(5).setCellValue(pacientes.get(i).getDataNascimento());
            dataRow.createCell(6).setCellValue(pacientes.get(i).getEndereco().getRua());
            dataRow.createCell(7).setCellValue(pacientes.get(i).getEndereco().getNumero());
            dataRow.createCell(8).setCellValue(pacientes.get(i).getEndereco().getBairro());
            dataRow.createCell(9).setCellValue(pacientes.get(i).getEndereco().getCidade());
            dataRow.createCell(10).setCellValue(pacientes.get(i).getEndereco().getEstado());
            dataRow.createCell(11).setCellValue(pacientes.get(i).getEndereco().getCep());
            dataRow.createCell(12).setCellValue(pacientes.get(i).getContatoResponsavel().getTelefone());
            dataRow.createCell(13).setCellValue(pacientes.get(i).getContatoResponsavel().getCelular());
            dataRow.createCell(14).setCellValue(pacientes.get(i).getContatoResponsavel().getEmail());
            dataRow.createCell(15).setCellValue(pacientes.get(i).getGenero().toString());
            dataRow.createCell(16).setCellValue(pacientes.get(i).getContatoResponsavel().getNomeResponsavel());
            dataRow.createCell(17).setCellValue(pacientes.get(i).getContatoResponsavel().getTelefone());
            dataRow.createCell(18).setCellValue(pacientes.get(i).getContatoResponsavel().getCelular());
            dataRow.createCell(19).setCellValue(pacientes.get(i).getContatoResponsavel().getEmail());
        }

        //Criando aba Médico
        XSSFSheet abaMedico = workbook.createSheet("Médico");
        Row headerRowMedico = abaMedico.createRow(0);
        String[] headerMedico = {"Id medico", "NumeroCRM", "Especialidades", "Cirurgiao", "Setor", "Carga Horária", "Nome", "Data de Nascimento", "Rua", "Número", "Bairro", "Cidade", "Estado", "CEP", "Telefone", "Celular", "Email", "Genero"};

        // Criando cabeçalho
        for (int i = 0; i < headerMedico.length; i++) {
            Cell cell = headerRowMedico.createCell(i);
            cell.setCellValue(headerMedico[i]);
        }

        // Criando as linhas para cada médico
        for (int i = 0; i < medicos.size(); i++) {
            Row dataRow = abaMedico.createRow(i + 1);
            dataRow.createCell(0).setCellValue(medicos.get(i).getIdMedico());
            dataRow.createCell(1).setCellValue(medicos.get(i).getNumeroCRM());
            dataRow.createCell(2).setCellValue(medicos.get(i).getAreasEspecialidade().toString());
            dataRow.createCell(3).setCellValue(medicos.get(i).isCirurgiao());
            dataRow.createCell(4).setCellValue(medicos.get(i).getSetor());
            dataRow.createCell(5).setCellValue(medicos.get(i).getChSemanal());
            dataRow.createCell(6).setCellValue(medicos.get(i).getNomeCompleto());
            dataRow.createCell(7).setCellValue(medicos.get(i).getDataNascimento());
            dataRow.createCell(8).setCellValue(medicos.get(i).getEndereco().getRua());
            dataRow.createCell(9).setCellValue(medicos.get(i).getEndereco().getNumero());
            dataRow.createCell(10).setCellValue(medicos.get(i).getEndereco().getBairro());
            dataRow.createCell(11).setCellValue(medicos.get(i).getEndereco().getCidade());
            dataRow.createCell(12).setCellValue(medicos.get(i).getEndereco().getEstado());
            dataRow.createCell(13).setCellValue(medicos.get(i).getEndereco().getCep());
            dataRow.createCell(14).setCellValue(medicos.get(i).getContato().getTelefone());
            dataRow.createCell(15).setCellValue(medicos.get(i).getContato().getCelular());
            dataRow.createCell(16).setCellValue(medicos.get(i).getContato().getEmail());
            dataRow.createCell(17).setCellValue(medicos.get(i).getGenero().toString());
        }

        //Criando aba de enfermeiros
        XSSFSheet abaEnfermeiro = workbook.createSheet("Enfermeiro");
        Row headerRowEnfermeiro = abaEnfermeiro.createRow(0);
        String[] headerEnfermeiro = {"Id enfermeiro", "Treinado Op Rx", "Setor", "Carga Horária", "Nome", "Data de Nascimento", "Rua", "Número", "Bairro", "Cidade", "Estado", "CEP", "Telefone", "Celular", "Email", "Genero"};

        // Criando cabeçalho
        for (int i = 0; i < headerEnfermeiro.length; i++) {
            Cell cell = headerRowEnfermeiro.createCell(i);
            cell.setCellValue(headerEnfermeiro[i]);
        }

        // Criando as linhas para cada enfermeiro
        for (int i = 0; i < enfermeiros.size(); i++) {
            Row dataRow = abaEnfermeiro.createRow(i + 1);
            dataRow.createCell(0).setCellValue(enfermeiros.get(i).getIdEnfermeiro());
            dataRow.createCell(1).setCellValue(enfermeiros.get(i).isTreinadoOpRX());
            dataRow.createCell(2).setCellValue(enfermeiros.get(i).getSetor());
            dataRow.createCell(3).setCellValue(enfermeiros.get(i).getChSemanal());
            dataRow.createCell(4).setCellValue(enfermeiros.get(i).getNomeCompleto());
            dataRow.createCell(5).setCellValue(enfermeiros.get(i).getDataNascimento());
            dataRow.createCell(6).setCellValue(enfermeiros.get(i).getEndereco().getRua());
            dataRow.createCell(7).setCellValue(enfermeiros.get(i).getEndereco().getNumero());
            dataRow.createCell(8).setCellValue(enfermeiros.get(i).getEndereco().getBairro());
            dataRow.createCell(9).setCellValue(enfermeiros.get(i).getEndereco().getCidade());
            dataRow.createCell(10).setCellValue(enfermeiros.get(i).getEndereco().getEstado());
            dataRow.createCell(11).setCellValue(enfermeiros.get(i).getEndereco().getCep());
            dataRow.createCell(12).setCellValue(enfermeiros.get(i).getContato().getTelefone());
            dataRow.createCell(13).setCellValue(enfermeiros.get(i).getContato().getCelular());
            dataRow.createCell(14).setCellValue(enfermeiros.get(i).getContato().getEmail());
            dataRow.createCell(15).setCellValue(enfermeiros.get(i).getGenero().toString());
        }

        //Criando aba Consulta-Médica
        XSSFSheet abaConsultas = workbook.createSheet("Consulta-Médica");
        Row headerRowConsultas = abaConsultas.createRow(0);
        String[] headerConsultas = {"Id consulta", "Id paciente", "Id médico", "Queixa", "Diagnóstico", "Prescrição", "Indicação Cirurgica"};

        // Criando cabeçalho
        for (int i = 0; i < headerConsultas.length; i++) {
            Cell cell = headerRowConsultas.createCell(i);
            cell.setCellValue(headerConsultas[i]);
        }

        // Criando as linhas para cada consulta
        for (int i = 0; i < consultas.size(); i++) {
            Row dataRow = abaConsultas.createRow(i + 1);
            dataRow.createCell(0).setCellValue(consultas.get(i).getIdConsulta());
            dataRow.createCell(1).setCellValue(consultas.get(i).getIdPaciente());
            dataRow.createCell(2).setCellValue(consultas.get(i).getIdMedico());
            dataRow.createCell(3).setCellValue(consultas.get(i).getExameQueixa());
            dataRow.createCell(4).setCellValue(consultas.get(i).getDiagnostico());
            dataRow.createCell(5).setCellValue(consultas.get(i).getPrescricao());
            dataRow.createCell(6).setCellValue(consultas.get(i).isIndicacaoCirurgica());

        }

        //Escrita da planilha
        FileOutputStream out;

        try {
            //Salvando no caminho e com o nome digitado pelo usuário
            out = new FileOutputStream(new File(caminhoArq + nomeArq + ".xlsx"));

            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            exibirMsgErroNull(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            exibirMsgErroNull(e.getMessage());
        }

        exibirMsgInfo(nomeArq+".xlsx");

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
