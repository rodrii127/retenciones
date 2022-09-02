package com.sevenb.retenciones.utils;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Retention;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RetentionATMCsvUtil {

    public File fileAtmCsv(List<Retention> retentionList, Company company) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = retentionList.get(0).getDate().format(formatter);
        String fileName = "RetentionATM.txt";
        File file = new File(fileName);
        // default, create, truncate and write to it.
        try (FileWriter writer = new FileWriter(file)) {
         retentionList.forEach(r->{
                try {
                    writer.write(r.getDate().toString()+","+r.getNumber()+","+
                            r.getProvider().getCompanyName()+","+"p,"+r.getProvider().getCuit()+","+
                            String.format("%.2f",r.getRetentionAmount() / r.getRetentionType().getAliquot())
                            +","+r.getRetentionType().getAliquot()+"\r\n");
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
