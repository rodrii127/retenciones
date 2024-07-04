package com.sevenb.retenciones.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.entity.Retention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
public class RetentionPdf {
    private static final Logger logger = LoggerFactory.getLogger(RetentionPdf.class);

    public ByteArrayInputStream generatePdf(Retention retention, Provider provider) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph fecha = new Paragraph("FECHA : " + retention.getDate().toString());
            fecha.setAlignment(Element.ALIGN_RIGHT);
            document.add(fecha);
            document.add(Chunk.NEWLINE);

            Paragraph number = new Paragraph("NUMERO :" + (retention.getNumber()));
            number.setAlignment(Element.ALIGN_RIGHT);
            document.add(number);
            document.add(Chunk.NEWLINE);


            Paragraph agenteRetention = new Paragraph("AGENTE DE RETENCION:");
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Razón Social : " + retention.getCompany().getCompanyName());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Cuit : " + retention.getCompany().getCuit() );
            agenteRetention.add(Chunk.NEWLINE);

            Paragraph title;
            if(retention.getRetentionType().getId() == 1L){
                 title = new Paragraph("CONSTANCIA DE RETENCION SUFRIDA RESOLUCION GENERAL Nº01/2012-MP");
                 agenteRetention.add("Habilitación :" + retention.getCompany().getHabilitationMun());
            }
            else {
                title = new Paragraph("CONSTANCIA DE RETENCION IIBB MISIONES");
                agenteRetention.add("Habilitación :" + retention.getCompany().getHabilitationDgr());
            }
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(Chunk.NEWLINE);
            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(agenteRetention);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph providerAgent = new Paragraph("AGENTE RETENIDO :");
            providerAgent.add(Chunk.NEWLINE);
            providerAgent.add("Razón Social : " + provider.getCompanyName());
            providerAgent.add(Chunk.NEWLINE);
            providerAgent.add("Cuit : " + provider.getCuit());
            document.add(providerAgent);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph ret = new Paragraph("RETENCION APLICADA:" );
            ret.add(Chunk.NEWLINE);
            ret.add("Descripción : " + retention.getRetentionType().getDescription());
            ret.add(Chunk.NEWLINE);
            ret.add("Alicuota : " + (retention.getProvider().getAgreement() ?  retention.getRetentionType().getReducedAliquot() : retention.getRetentionType().getAliquot()));
            ret.add(Chunk.NEWLINE);
            ret.add("Base imponible : " + String.format("%.2f", retention.getRetentionAmount() / (retention.getProvider().getAgreement() ?  retention.getRetentionType().getReducedAliquot() : retention.getRetentionType().getAliquot())));
            ret.add(Chunk.NEWLINE);
            ret.add("Importe retenido: " + String.format("%.2f",retention.getRetentionAmount()));
            document.add(ret);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph firma = new Paragraph("----------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            document.add(firma);
            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

}
