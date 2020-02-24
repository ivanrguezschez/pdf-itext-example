package com.irs.pdfitextexample;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlantillaApp {

    private static final Logger LOG = LoggerFactory.getLogger(PlantillaApp.class);
      
    private static final String NOMBRE_PDF = "plantilla.pdf";
    
    private static final DateFormat DATE_FORMAT_DEFAULT = new SimpleDateFormat("dd/MM/yyyy");
        
    public static void main(String[] args) {
        LOG.info("BEGIN");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("organismo.nivel.1.nombre", "DENOMINACIÓN\nORGANISMO\nDE PRIMER NIVEL");
        params.put("organismo.nivel.2.nombre", "DENOMINACIÓN ORGANISMO DE SEGUNDO NIVEL");
        params.put("organismo.nivel.3.nombre", "DENOMINACIÓN ORGANISMO DE TERCER NIVEL");
        params.put("organismo.nivel.1.via.nombre", "AAAAA AA AA AAAAAAAAAA, 99");        
        params.put("organismo.nivel.1.via.cp", "99999");
        params.put("organismo.nivel.1.via.provincia", "AAAAAA");
        params.put("organismo.nivel.1.telefono", "TEL.: 99 999 99 99");
        params.put("organismo.nivel.1.fax", "FAX: 99 999 99 99");
            
        try {
            Document document = new Document(PageSize.A4, 
                    PdfItextConst.CM_2, PdfItextConst.CM_2, PdfItextConst.CM_1, PdfItextConst.CM_2);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(NOMBRE_PDF));
            
            HeaderAndFooterPdfPageEvent events = new HeaderAndFooterPdfPageEvent(params);
            writer.setPageEvent(events);
            //events.onOpenDocument(writer, document);
            
            document.open();
            
            document.add(PdfItextUtil.createParagraph("LISTADO DE USUARIOS", PdfItextConst.FONT_12_BOLD, PdfItextConst.CM_05));
            
            document.add(PdfItextUtil.createParagraph("Criterios de Búsqueda", PdfItextConst.FONT_10_BOLD, PdfItextConst.CM_02));
            document.add(createTableSearch());
              
            document.add(PdfItextUtil.createParagraph("Resultado de la Búsqueda", PdfItextConst.FONT_10_BOLD, PdfItextConst.CM_02));
            document.add(createTableResult());
                        
            document.add(PdfItextUtil.createParagraph("", PdfItextConst.FONT_10_BOLD, PdfItextConst.CM_02));
            
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
	    table.addCell("celda del 100%");
            document.add(table);
            
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
	    table.addCell("celda del 50%");
            table.addCell("celda del 50%");
            document.add(table);
            
            table = new PdfPTable(4);
            table.setWidthPercentage(100);
            for (int i = 0; i < 4; i++) {
                table.addCell(new Phrase("celda del 25%"));
            }
            document.add(table);
            
            table = new PdfPTable(5);
            table.setWidthPercentage(100);
            for (int i = 0; i < 5; i++) {
                table.addCell(new Phrase("20%"));
            }
            document.add(table);
            
            table = new PdfPTable(10);
            table.setWidthPercentage(100);
            for (int i = 0; i < 10; i++) {
                table.addCell(new Phrase("10%"));
            }
            document.add(table);
            
            table = new PdfPTable(20);
            table.setWidthPercentage(100);
            for (int i = 0; i < 20; i++) {
                table.addCell(new Phrase("5%", PdfItextConst.FONT_4_NORMAL));
            }
            document.add(table);
            
            table = new PdfPTable(100);
            table.setWidthPercentage(100);
            for (int i = 0; i < 100; i++) {
                table.addCell(new Phrase("1%", PdfItextConst.FONT_2_NORMAL));
            }
            document.add(table);
                                
            document.add(PdfItextUtil.createParagraph("", PdfItextConst.FONT_10_BOLD, PdfItextConst.CM_02));
            
            PdfPTable table1cm = new PdfPTable(1);
            table1cm.setWidths(new float[] { PdfItextConst.CM_1 });
            table1cm.setTotalWidth(PdfItextConst.CM_1);
            table1cm.setLockedWidth(true);
	    table1cm.addCell(new Phrase("1cm"));
            document.add(table1cm);
            
            document.close();
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
        LOG.info("END");
    }
        
    private static PdfPTable createTableSearch() throws DocumentException {
        float[] TABLE_SEARCH_WDITHS = new float[] {
            PdfItextUtil.centimeterToPoint(3.0f), 
            PdfItextUtil.centimeterToPoint(14.0f)
        };
                
        PdfPTable table = new PdfPTable(TABLE_SEARCH_WDITHS.length);
	table.setWidths(TABLE_SEARCH_WDITHS);
	table.setTotalWidth(PdfItextConst.CM_17);
        table.setLockedWidth(true);
        			
	table.addCell(PdfItextUtil.createCellLabel("NIF:"));
	table.addCell(PdfItextUtil.createCellValue("00000001R"));
				
	table.addCell(PdfItextUtil.createCellLabel("Nombre:"));
	table.addCell(PdfItextUtil.createCellValue("nombre-01"));
		
	table.addCell(PdfItextUtil.createCellLabel("1º Apellido:"));
	table.addCell(PdfItextUtil.createCellValue("apellido1-01"));
		
	table.addCell(PdfItextUtil.createCellLabel("2º Apellido:"));
	table.addCell(PdfItextUtil.createCellValue("apellido2-01"));
		
	table.addCell(PdfItextUtil.createCellLabel("Rol:"));
	table.addCell(PdfItextUtil.createCellValue("ADMINISTRADOR"));
				
	return table;
    }
    
    private static PdfPTable createTableResult() throws DocumentException {
        float[] TABLE_RESULT_WDITHS = new float[] {
            PdfItextUtil.centimeterToPoint(2.0f),
            PdfItextUtil.centimeterToPoint(3.0f),
            PdfItextUtil.centimeterToPoint(3.0f),
            PdfItextUtil.centimeterToPoint(3.0f),
            PdfItextUtil.centimeterToPoint(4.0f),
            PdfItextUtil.centimeterToPoint(2.0f)
        };
        
        PdfPTable table = new PdfPTable(TABLE_RESULT_WDITHS.length);
        table.setHeaderRows(1);
	table.setWidths(TABLE_RESULT_WDITHS);
	table.setTotalWidth(PdfItextConst.CM_17);
        table.setLockedWidth(true);
        	
	table.addCell(PdfItextUtil.createCellHeader("NIF"));
	table.addCell(PdfItextUtil.createCellHeader("NOMBRE"));
	table.addCell(PdfItextUtil.createCellHeader("1º APELLIDO"));
	table.addCell(PdfItextUtil.createCellHeader("2º APELLIDO"));
	table.addCell(PdfItextUtil.createCellHeader("ROL"));
	table.addCell(PdfItextUtil.createCellHeader("F. ALTA"));

        Date hoy = new Date();
        for (int i = 1; i <= 50; i++) {
            table.addCell(PdfItextUtil.createCellBody("nif-" + i));
            table.addCell(PdfItextUtil.createCellBody("nombre-" + i));
            table.addCell(PdfItextUtil.createCellBody("apellido1-" + i));
            table.addCell(PdfItextUtil.createCellBody("apellido2-" + i));
            table.addCell(PdfItextUtil.createCellBody("ADMINISTRADOR"));
            table.addCell(PdfItextUtil.createCellBody(DATE_FORMAT_DEFAULT.format(hoy)));
        }	

        return table;
    }
}
