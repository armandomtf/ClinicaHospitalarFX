package com.armando.prj_clinicahospitalarfx.data;

import com.armando.prj_clinicahospitalarfx.HomePageController;
import com.armando.prj_clinicahospitalarfx.model.ConsultaMedica;
import com.armando.prj_clinicahospitalarfx.model.ContatoTelEmail;
import com.armando.prj_clinicahospitalarfx.model.Endereco;
import com.armando.prj_clinicahospitalarfx.model.Enfermeiro;
import com.armando.prj_clinicahospitalarfx.model.Genero;
import com.armando.prj_clinicahospitalarfx.model.Medico;
import com.armando.prj_clinicahospitalarfx.model.Paciente;
import com.armando.prj_clinicahospitalarfx.model.Responsavel;
import static com.armando.prj_clinicahospitalarfx.HomePageController.consultas;
import static com.armando.prj_clinicahospitalarfx.HomePageController.enfermeiros;
import static com.armando.prj_clinicahospitalarfx.HomePageController.medicos;
import static com.armando.prj_clinicahospitalarfx.HomePageController.pacientes;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportarExcel {

    public void ReadDataFromExcel(String path) {

        HomePageController.pacientes = new ArrayList<>();
        HomePageController.medicos = new ArrayList<>();
        HomePageController.enfermeiros = new ArrayList<>();
        HomePageController.consultas = new ArrayList<>();

        try {
            //XSSFWorkbook workbook = new XSSFWorkbook(path);
            XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\Armando\\Desktop\\Video.xlsx");

            //Abre aba Pacientes
            Sheet sheet = workbook.getSheetAt(0);

            //Coletando pacientes
            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);

                Genero gp = Genero.feminino;
                if (row.getCell(15).getStringCellValue().contains("masculino")) {
                    gp = Genero.masculino;
                }
                Endereco ep = new Endereco(row.getCell(6).getStringCellValue(), (int) row.getCell(7).getNumericCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue(), row.getCell(10).getStringCellValue(), (int) (row.getCell(11).getNumericCellValue()));
                ContatoTelEmail cp = new ContatoTelEmail(row.getCell(12).getStringCellValue(), row.getCell(13).getStringCellValue(), row.getCell(14).getStringCellValue());
                Responsavel rp = new Responsavel(row.getCell(16).getStringCellValue(), row.getCell(17).getStringCellValue(), row.getCell(18).getStringCellValue(), row.getCell(19).getStringCellValue());
                Paciente paciente = new Paciente((int) row.getCell(1).getNumericCellValue(), row.getCell(2).getDateCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getDateCellValue(), ep, cp, gp, rp);
                pacientes.add(paciente);
            }

            //Coletando medicos
            sheet = workbook.getSheetAt(1);
            rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                boolean cirurgiao = false;
                if (row.getCell(2).getStringCellValue().contains("TRUE")) {
                    cirurgiao = true;
                }
                Genero gm = Genero.feminino;
                if (row.getCell(17).getStringCellValue().contains("masculino")) {
                    gm = Genero.masculino;
                }
                String input = row.getCell(2).getStringCellValue();
                String content = input.substring(1, input.length() - 1);
                String[] items = content.split(",\\s*");
                ArrayList<String> EspTemp = new ArrayList<>(Arrays.asList(items));
                Endereco em = new Endereco(row.getCell(8).getStringCellValue(), (int) row.getCell(9).getNumericCellValue(), row.getCell(10).getStringCellValue(), row.getCell(11).getStringCellValue(), row.getCell(12).getStringCellValue(), (int) (row.getCell(13).getNumericCellValue()));
                ContatoTelEmail cm = new ContatoTelEmail(row.getCell(14).getStringCellValue(), row.getCell(15).getStringCellValue(), row.getCell(16).getStringCellValue());
                Medico medico = new Medico((int) row.getCell(1).getNumericCellValue(), cirurgiao, row.getCell(4).getStringCellValue(), (int) row.getCell(5).getNumericCellValue(), row.getCell(6).getStringCellValue(), row.getCell(7).getDateCellValue(), em, cm, gm, EspTemp);
                medico.setAreasEspecialidade(EspTemp);
                medicos.add(medico);
            }

            //Coletando enfermeiros
            sheet = workbook.getSheetAt(2);
            rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                boolean rx = false;
                if (row.getCell(2).getStringCellValue().contains("TRUE")) {
                    rx = true;
                }
                Genero ge = Genero.feminino;
                if (row.getCell(15).getStringCellValue().contains("masculino")) {
                    ge = Genero.masculino;
                }

                Endereco ee = new Endereco(row.getCell(6).getStringCellValue(), (int) row.getCell(7).getNumericCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue(), row.getCell(10).getStringCellValue(), (int) (row.getCell(11).getNumericCellValue()));
                ContatoTelEmail ce = new ContatoTelEmail(row.getCell(12).getStringCellValue(), row.getCell(13).getStringCellValue(), row.getCell(14).getStringCellValue());
                Enfermeiro enfermeiro = new Enfermeiro(rx, row.getCell(2).getStringCellValue(), (int) row.getCell(3).getNumericCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getDateCellValue(), ee, ce, ge);
                enfermeiros.add(enfermeiro);
            }

            //Coletando consultas
            sheet = workbook.getSheetAt(3);
            rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                boolean ic = false;
                if (row.getCell(6).getBooleanCellValue()) {
                    ic = true;
                }
                ConsultaMedica consulta = new ConsultaMedica((long) row.getCell(1).getNumericCellValue(), (long) row.getCell(2).getNumericCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(), ic);
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
        }

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
