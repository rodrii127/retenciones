package com.sevenb.retenciones.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import com.sevenb.retenciones.entity.Invoice;
import com.sevenb.retenciones.entity.PayOrder;
import com.sevenb.retenciones.entity.Retention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayOrderPdf {

    private static final Logger logger = LoggerFactory.getLogger(PayOrderPdf.class);

    public ByteArrayInputStream generatePdfPayOrder(PayOrder payOrder) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // insertar imagen en pfg
        FileInputStream fis = null;
        File file = new File("/home/fernando/Downloads/farmacom.jpeg");
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




        try {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 5, 5,5});

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

            List<Retention> retentionList = payOrder.getRetentionList();
            List<Invoice> invoiceList = retentionList.get(0).getInvoice();

            payOrder.getRetentionList().forEach(r -> {
                r.getInvoice().forEach(i -> {
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

                    cell = new PdfPCell(new Phrase(String.format("%.2f", i.getEngraved())));
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


            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("ORDEN DE PAGO NUMERO : " + payOrder.getId());
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(Chunk.NEWLINE);
            document.add(title);
            document.add(Chunk.NEWLINE);
            // Creating a Document object

            Image img = Image.getInstance("/home/fernando/Downloads/farmacom.jpeg");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scalePercent(25f,25f);


            Paragraph agenteRetention = new Paragraph("FECHA EMISION: " + payOrder.getDate());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("AGENTE DE RETENCION:");
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Razón Social : " + payOrder.getCompany().getCompanyName());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Cuit : " + payOrder.getCompany().getCuit());
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Habilitación : 839505/00");
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add("Teléfono :" + payOrder.getCompany().getPhone());
            agenteRetention.setAlignment(Element.ALIGN_LEFT);
            agenteRetention.add(Chunk.NEWLINE);
            agenteRetention.add(Chunk.NEWLINE);

            Paragraph provider = new Paragraph("AGENTE DE RETENIDO:");
            provider.add(Chunk.NEWLINE);
            provider.add("Razón Social : "+payOrder.getProvider().getCompanyName());
            provider.add(Chunk.NEWLINE);
            provider.add("Cuit : " + payOrder.getProvider().getCuit());
            provider.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tableInfo = new PdfPTable(1);
            tableInfo.setWidthPercentage(100);
            tableInfo.setWidths(new int[]{10});


            PdfPTable encabezado = new PdfPTable(2);
            encabezado.setWidthPercentage(100);
            encabezado.setWidths(new int[]{10, 10});

            PdfPCell imaCell;
            imaCell = new PdfPCell(img);
            imaCell.setPadding(2);
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

            Paragraph ret = new Paragraph("RETENCION APLICADA:");
            ret.add(Chunk.NEWLINE);
            ret.add("Base imponible : " + String.format("%.2f", payOrder.calculateBase()));
            ret.add(Chunk.NEWLINE);
            ret.add("Retención municipal (0.007%) : " + String.format("%.2f", payOrder.calculateMunicipality()));
            ret.add(Chunk.NEWLINE);
            document.add(ret);
            document.add(Chunk.NEWLINE);
            Paragraph pay = new Paragraph("MONTO DEl CHEQUE O TRANSFERENCIA : " + String.format("%.2f",payOrder.calculateTotal() - payOrder.calculateMunicipality()));
            pay.add(Chunk.NEWLINE);
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
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph firma = new Paragraph("-------------------------------");
            firma.add(Chunk.NEWLINE);
            firma.add("FIRMA Y SELLO");
            firma.setAlignment(Element.ALIGN_CENTER);
            document.add(firma);


            document.close();document.add(Chunk.NEWLINE);

        } catch (DocumentException | IOException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
