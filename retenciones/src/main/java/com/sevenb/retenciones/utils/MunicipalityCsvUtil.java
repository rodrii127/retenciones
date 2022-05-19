package com.sevenb.retenciones.utils;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MunicipalityCsvUtil {


        public File generaFileMunicipality(List<Retention> retentionList, Company company){
            String fileName = "Municipalidad.txt";
            File file =new File(fileName);

            // default, create, truncate and write to it.
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("numero_habilitacion,cuit,agente,periodo,tipo_declaracion,operaciones_excluidas\r\n");
                writer.write(company.getHabilitationMun()+","+company.getCuit()+","+company.getCompanyName()+","+"04/2022"+","+"retencion"+","+0+"\r\n"+"\r\n");
                writer.write("cuit,contribuyente,comprobante_numero,comprobante_tipo,comprobante_fecha_emision,importe_operacion,alicuota,importe_a_depositar" );

                retentionList.forEach(r->{
                    try {
                        writer.write(r.getInvoice().get(0).getProvider().getCuit()+","+ r.getId()+" " + r.getRetentionAmount()+ "\r\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                return file;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        return null;
        }

}
