package com.sevenb.retenciones.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sevenb.retenciones.entity.Retention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

public class RetentionPdf {
    private static final Logger logger = LoggerFactory.getLogger(RetentionPdf.class);
    public ByteArrayInputStream generatePdf(Retention retention) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable table = new PdfPTable(3);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 5, 5});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Fecha", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Numero de factura", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Base Imponible", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            retention.getInvoice().forEach(i -> {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(i.getDate().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(i.getPointSale()+"-"+ i.getNumber()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.format("%.2f",i.getEngraved())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

            });

            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph fecha = new Paragraph("FECHA : " +  LocalDate.now());
            fecha.setAlignment(Element.ALIGN_RIGHT);
            document.add(fecha);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph("COMPROBANTE DE RENTION NRO : " + retention.getId());
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(Chunk.NEWLINE);
            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph agenteRetention = new Paragraph("AGENTE DE RETENCION:");
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Razón Social : " + retention.getCompany().getCompanyName());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Cuit : " + retention.getCompany().getCuit() );
            document.add(agenteRetention);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph provider = new Paragraph("AGENTE DE RETENIDO :");
            provider.add(Chunk.NEWLINE);
            provider.add("Razón Social : " + "tumina");
            provider.add(Chunk.NEWLINE);
            provider.add("Cuit : " + "154584515");
            document.add(provider);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph ret = new Paragraph("RETENCION APLICADA:" );
            ret.add(Chunk.NEWLINE);
            ret.add("Descripción : " + retention.getRetentionType().getDescription());
            ret.add(Chunk.NEWLINE);
            ret.add("Alicuota : " + retention.getRetentionType().getAliquot() );
            ret.add(Chunk.NEWLINE);
            ret.add("Base imponible : " + String.format("%.2f", retention.calculateBase()) );
            ret.add(Chunk.NEWLINE);
            ret.add("Importe retenido: " + String.format("%.2f",retention.calculateRet()));
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
