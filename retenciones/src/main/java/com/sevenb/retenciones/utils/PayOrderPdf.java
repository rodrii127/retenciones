package com.sevenb.retenciones.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.pdf.*;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.ss.usermodel.Footer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Provider;
import com.sevenb.retenciones.entity.Retention;

public class PayOrderPdf {
    private static final Logger logger = LoggerFactory.getLogger(PayOrderPdf.class);

    public ByteArrayInputStream generatePdfPayOrder(PayOrder payOrder) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 5, 5, 5});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Fecha", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Número de factura", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Base Imponible", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            payOrder.getInvoice().forEach(i -> {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(i.getDate().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(i.getPointSale() + "-" + i.getNumber()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.format("%.2f", i.getEngraved() + i.getExempt())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.format("%.2f", i.calculateTotal())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

            });

            PdfPCell cellTotal;
            cellTotal = new PdfPCell(new Phrase("TOTAL", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellTotal);

            cellTotal = new PdfPCell(new Phrase(""));
            cellTotal.setPaddingLeft(5);
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellTotal);

            cellTotal = new PdfPCell(new Phrase(""));
            cellTotal.setPaddingLeft(5);
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellTotal);

            cellTotal = new PdfPCell(new Phrase(String.format("%.2f", payOrder.calculateTotal()), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTotal.setPaddingRight(5);
            table.addCell(cellTotal);

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("ORDEN DE PAGO NUMERO : " + payOrder.getPayOrderNumber());
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(Chunk.NEWLINE);
            document.add(title);
            document.add(Chunk.NEWLINE);
            // Creating a Document object

            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.BLACK);
            Chunk c = new Chunk(payOrder.getCompany().getFantasyName(), f);
            Paragraph fantasyName = new Paragraph(c);

            Paragraph agenteRetention = new Paragraph("FECHA EMISION: " + payOrder.getDate());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("AGENTE DE RETENCION:");
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Razón Social : " + payOrder.getCompany().getCompanyName());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Cuit : " + payOrder.getCompany().getCuit());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Teléfono :" + payOrder.getCompany().getPhone());
            agenteRetention.setAlignment(Element.ALIGN_LEFT);
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add(Chunk.NEWLINE);

            Paragraph provider = new Paragraph("AGENTE RETENIDO:");
            provider.add(Chunk.NEWLINE);
            provider.add("Razón Social : " + payOrder.getProvider().getCompanyName());
            provider.add(Chunk.NEWLINE);
            provider.add("Cuit : " + payOrder.getProvider().getCuit());
            provider.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tableInfo = new PdfPTable(1);
            tableInfo.setWidthPercentage(100);
            tableInfo.setWidths(new int[]{10});

            PdfPTable encabezado = new PdfPTable(2);
            encabezado.setWidthPercentage(100);
            encabezado.setWidths(new int[]{10, 10});

            // TODO images will be inserted when they are added to database
            PdfPCell imaCell;
            imaCell = new PdfPCell(fantasyName);
            imaCell.setPadding(2);
            imaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            imaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezado.addCell(imaCell);


            PdfPCell hcellInfo;
            hcellInfo = new PdfPCell(agenteRetention);
            hcellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcellInfo.setBorder(0);
            tableInfo.addCell(hcellInfo);

            hcellInfo = new PdfPCell(provider);
            hcellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcellInfo.setBorder(0);
            tableInfo.addCell(hcellInfo);

            encabezado.addCell(tableInfo);

            document.add(encabezado);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph retentionParagraph = new Paragraph("RETENCION APLICADA:");
            retentionParagraph.add(Chunk.NEWLINE);
            retentionParagraph.add("Base imponible : " + String.format("%.2f", payOrder.calculateBase()));
            retentionParagraph.add(Chunk.NEWLINE);
            payOrder.getRetentionList().forEach(retention -> {
                retentionParagraph.add(retention.getRetentionType().getDescription()
                    + String.format(" (%,.4f) : ", payOrder.getProvider().getAgreement() ? retention.getRetentionType().getReducedAliquot() : retention.getRetentionType().getAliquot())
                    + String.format("%.2f", retention.getRetentionAmount()));
                retentionParagraph.add(Chunk.NEWLINE);
            });
            document.add(retentionParagraph);
            document.add(Chunk.NEWLINE);
            Paragraph pay = new Paragraph("MONTO DEL CHEQUE O TRANSFERENCIA : " + String.format("%.2f", payOrder.calculateTotalWithRetentions()));
            pay.add(Chunk.NEWLINE);
            document.add(pay);

            Paragraph payMode = new Paragraph("-------MODO DE PAGO-------");
            payMode.add(Chunk.NEWLINE);
            payMode.add(Chunk.NEWLINE);
            payMode.add("FORMA DE PAGO : - TRANFERENCIA  - CHEQUE - OTROS");
            payMode.add(Chunk.NEWLINE);
            payMode.add(Chunk.NEWLINE);
            payMode.add("NUMERO DE REFERENCIA: ");
            payMode.add(Chunk.NEWLINE);
            payMode.add(Chunk.NEWLINE);
            document.add(payMode);
            document.add(Chunk.NEWLINE);

            Paragraph firma = new Paragraph("-------------------------------");
            firma.add(Chunk.NEWLINE);
            firma.add("FIRMA Y SELLO");
            firma.setAlignment(Element.ALIGN_CENTER);
            document.add(firma);
            Font fontFooter = new Font(Font.FontFamily.TIMES_ROMAN, .50f, Font.BOLD, BaseColor.BLACK);
            Phrase phraseFooter = new Phrase("Comprobante emitido por Seven b SRL Retenciones Cel:3764-900722 ");
            phraseFooter.setFont(fontFooter);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,phraseFooter,300,20,0);
            document.add(Chunk.NEXTPAGE);
            document.close();
            return mergePdf(payOrder.getRetentionList(), payOrder.getProvider(), out);
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
            throw new RuntimeException(ex);
        }
    }


    //Une el PDF de pay order y las retencions
    public ByteArrayInputStream mergePdf(List<Retention> retentionLis, Provider provider, ByteArrayOutputStream outputStreamPayOrder) {
        try {
            Document doc = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, outputStream);
            //Open document.
            doc.open();
            //Contain the pdf data.
            PdfContentByte pageContentByte = writer.getDirectContent();
            PdfReader pdfReader1 = new PdfReader(new ByteArrayInputStream(outputStreamPayOrder.toByteArray()));
            PdfImportedPage pdfImportedPage = writer.getImportedPage(pdfReader1, 1);
            pageContentByte.addTemplate(pdfImportedPage, 0, 0);
            retentionLis.forEach(r -> {
                doc.newPage();
                ByteArrayInputStream byteArrayInputStream = new RetentionPdf().generatePdf(r, provider);
                PdfReader pdfReader = null;
                try {
                    pdfReader = new PdfReader(byteArrayInputStream);
                    PdfImportedPage pdfImportedPage1 = writer.getImportedPage(pdfReader, 1);
                    pageContentByte.addTemplate(pdfImportedPage1, 0, 0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            doc.close();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


     public void onEndPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Fin de Pagina - El lado oscuro de Java"),200,20,0);
    }
}