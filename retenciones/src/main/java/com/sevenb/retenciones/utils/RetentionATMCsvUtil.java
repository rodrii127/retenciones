package com.sevenb.retenciones.utils;

import com.sevenb.retenciones.entity.Company;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.entity.Retention;
import com.sevenb.retenciones.entity.RetentionType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class RetentionATMCsvUtil {

    public File fileAtmCsv(List<Retention> retentionList, Company company) throws Exception {


        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fileName = "RetentionATM.txt";
        File file = new File(fileName);
        // default, create, truncate and write to it.
        try (FileWriter writer = new FileWriter(file)) {
         retentionList.forEach(r->{
             Double aliquot = validateAliquot(r.getRetentionType(),r.getProvider());
             String date = r.getDate().format(formatDate);
                try {
                    writer.write(date+","+r.getNumber()+","+
                            r.getProvider().getCompanyName()+","+"p,"+r.getProvider().getCuit()+","+
                            String.format("%.2f",r.getRetentionAmount() / aliquot)
                            +","+String.format("%.2f",aliquot*100)+"\r\n");
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

    public Double validateAliquot(RetentionType retentionType, Provider provider){
                return  (provider.getAgreement() ? retentionType.getReducedAliquot() : retentionType.getAliquot());
    }



}
