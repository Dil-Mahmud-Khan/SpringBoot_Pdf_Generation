package com.pdf.pdfgeneration.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    private Logger logger= LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(){
        logger.info("Creating Pdf");

        String title="welcome ";
        String content="learn here how to do it";


        ByteArrayOutputStream out=new ByteArrayOutputStream();
        Document document=new Document();

        PdfWriter.getInstance(document,out);
        document.open();

        Font titleFont= FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
        Paragraph titlePara=new Paragraph(content);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        Font paraFont= FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
        Paragraph paragraph=new Paragraph(content);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(new Chunk("this is body "));
        document.add(paragraph);

        document.close();

        return new  ByteArrayInputStream(out.toByteArray());
    }

}
