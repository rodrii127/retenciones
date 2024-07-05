package com.sevenb.retenciones.utils;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MunicipalityCsvUtil {


        public File generaFileMunicipality(List<Retention> retentionList, Company company) throws Exception {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = retentionList.get(0).getDate().format(formatter);
            String fileName = "Municipalidad.txt";
            File file = new File(fileName);
            // default, create, truncate and write to it.
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("numero_habilitacion,cuit,agente,periodo,tipo_declaracion,operaciones_excluidas\r\n");
                writer.write(company.getHabilitationMun()+","+company.getCuit()+","+company.getCompanyName()+","+date+","+"retencion"+","+0+"\r\n"+"\r\n");
                writer.write("cuit,contribuyente,comprobante_numero,comprobante_tipo,comprobante_fecha_emision,importe_operacion,alicuota,importe_a_depositar"+"\r\n");
                retentionList.forEach(r->{
                    try {
                        writer.write(r.getProvider().getCuit() +","+ r.getProvider().getCompanyName()+","+ r.getNumber()+","+"CONSTANCIA"+","+r.getDate().format(formatDate)+ ","+
                                String.format("%.2f",r.getRetentionAmount() / r.getRetentionType().getAliquot()) +","+"7"+","+String.format("%.2f",r.getRetentionAmount())+"\r\n");
                    } catch (IOException e) {
                       throw new RuntimeException(e);
                    }
                });
                return file;
            }
            catch (IOException e) {
              throw new Exception(e.getMessage());
            }

        }

}
