package com.irs.pdfitextexample;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderAndFooterPdfPageEvent extends PdfPageEventHelper {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderAndFooterPdfPageEvent.class);

    private Map<String, String> params;

    /**
     * Tabla del encabezado.
     */
    private PdfPTable tableHeader;

    /**
     * Tabla de la dirección del pie.
     */
    private PdfPTable tableDireccion;

    public HeaderAndFooterPdfPageEvent(Map<String, String> params) throws IOException, DocumentException {
        super();
        this.params = params;
        this.tableHeader = createTableHeader();
        this.tableDireccion = createTableDireccion();
    }

    public PdfPTable getTableHeader() {
        return tableHeader;
    }

    public PdfPTable getTableDireccion() {
        return tableDireccion;
    }

    /*
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        LOG.debug("leftMargin {}, rightMargin {},  topMargin {}, bottomMargin {}", document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
        LOG.debug("Page Size: {}", document.getPageSize().toString());
        LOG.debug("Page Size Inch: {} - {}", PdfItextUtil.pointToInch(document.getPageSize().getWidth()), PdfItextUtil.pointToInch(document.getPageSize().getHeight()));
        LOG.debug("Page Size cm: {} - {}", PdfItextUtil.pointToCentimeter(document.getPageSize().getWidth()), PdfItextUtil.pointToCentimeter(document.getPageSize().getHeight()));
        LOG.debug("Page Size mm: {} - {}", PdfItextUtil.pointToMilimeter(document.getPageSize().getWidth()), PdfItextUtil.pointToMilimeter(document.getPageSize().getHeight()));
        LOG.debug("1 cm = {} point (dpi=dot per inch)", centimeterToPoint(1.0f));
                
        float margin2Cm = PdfItextUtil.centimeterToPoint(2.0f);
	//document.setMargins(margin2Cm, margin2Cm, margin2Cm, margin2Cm);
	try {
            document.add(tableHeader);	
	} catch (Exception e) {
            LOG.warn(e.getMessage(), e);
	}
    }
     */
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        try {
            document.add(tableHeader);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        /*
	PdfPCell cell = tableFooter.getRow(0).getCells()[0];
	cell.setPhrase(null);
        cell.setVerticalAlignment(Element.ALIGN_BASELINE);
        //cell.setRowspan(4);
	//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	//cell.addElement(createTablePagina(writer));
        cell.addElement(new Phrase("Página " + writer.getPageNumber(), FONT_4_NORMAL));
                
	//tableFooter.setTotalWidth(document.right() - document.left());
	//tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
        tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() - centimeterToPoint(0.5f), writer.getDirectContent());
         */
        try {
            PdfPTable tableFooter = new PdfPTable(2);
            tableFooter.setWidths(new float[]{
                PdfItextUtil.centimeterToPoint(13.20f),
                PdfItextUtil.centimeterToPoint(3.80f)
            });
            //tableFooter.setSpacingBefore(centimeterToPoint(1.0f));
            //tableFooter.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tableFooter.setTotalWidth(PdfItextConst.CM_17);
            tableFooter.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase("Página " + writer.getPageNumber(), PdfItextConst.FONT_6_NORMAL));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            tableFooter.addCell(cell);

            cell = new PdfPCell(this.tableDireccion);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidthLeft(0.2f);
            cell.setPaddingLeft(PdfItextUtil.centimeterToPoint(0.20f));
            tableFooter.addCell(cell);

            //tableFooter.setTotalWidth(document.right() - document.left());
            //tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
            tableFooter.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() - PdfItextConst.CM_05, writer.getDirectContent());
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }

    }

    private PdfPTable createTableHeader() throws IOException, DocumentException {
        /*
        PdfPTable tableHeader = new PdfPTable(2);
	tableHeader.setWidths(new float[] {1f, 0.6f});
	tableHeader.setWidthPercentage(100);
	tableHeader.setSpacingAfter(20);
	//tableHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        tableHeader.addCell(createTableOrganismoNivel1("escudo.gif", 
                StringEscapeUtils.unescapeJava(params.get("organismo.nivel.1.nombre")),
                50));
        
        tableHeader.addCell(createTableSubsecretaria(
        		"DENOMINACI\\u00D3N ORGANISMO DE SEGUNDO NIVEL",
                        "DENOMINACI\u00D3N ORGANISMO DE TERCER NIVEL")); 				
		
	return tableHeader;
         */

        PdfPTable tableHeader = new PdfPTable(3);
        tableHeader.setWidths(new float[]{
            PdfItextUtil.centimeterToPoint(2.0f),
            PdfItextUtil.centimeterToPoint(11.20f),
            PdfItextUtil.centimeterToPoint(3.8f)
        });
        tableHeader.setSpacingAfter(PdfItextConst.CM_05);
        tableHeader.setTotalWidth(PdfItextConst.CM_17);
        tableHeader.setLockedWidth(true);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(getClass().getClassLoader().getResourceAsStream("escudo.gif"), output);

        Image image = Image.getInstance(output.toByteArray());
        image.scalePercent(75);

        // Añado el logotipo
        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(image, 0, 0)));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setRowspan(2);
        tableHeader.addCell(cell);

        cell = new PdfPCell(new Phrase(StringEscapeUtils.unescapeJava(params.get("organismo.nivel.1.nombre")), PdfItextConst.FONT_8_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setRowspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPaddingTop(PdfItextUtil.centimeterToPoint(0.4f));
        tableHeader.addCell(cell);

        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.2.nombre"), PdfItextConst.FONT_6_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(new Color(240, 240, 240));
        cell.setPadding(PdfItextConst.CM_02);
        tableHeader.addCell(cell);

        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.3.nombre"), PdfItextConst.FONT_6_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(PdfItextConst.CM_02);
        tableHeader.addCell(cell);

        return tableHeader;
    }

    private PdfPTable createTableFooter() throws IOException, DocumentException {
        /*
	PdfPTable t = new PdfPTable(2);
	//t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	t.addCell("");
		
	PdfPCell cell = new PdfPCell();
	//cell.setBorder(PdfPCell.NO_BORDER);
	cell.addElement(createTableDireccion());
	t.addCell(cell);
	
	return t;
         */

        PdfPTable tableFooter = new PdfPTable(2);
        tableFooter.setWidths(new float[]{
            PdfItextUtil.centimeterToPoint(13.20f),
            PdfItextUtil.centimeterToPoint(3.80f)
        });
        //tableFooter.setSpacingBefore(centimeterToPoint(1.0f));
        //tableFooter.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableFooter.setTotalWidth(PdfItextConst.CM_17);
        tableFooter.setLockedWidth(true);

        PdfPCell cell = new PdfPCell(new Phrase("", PdfItextConst.FONT_6_NORMAL));
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableFooter.addCell(cell);

        cell = new PdfPCell(this.tableDireccion);
        cell.setPaddingLeft(PdfItextUtil.centimeterToPoint(0.20f));
        tableFooter.addCell(cell);

        return tableFooter;
    }

    /*
    private PdfPTable createTablePagina(PdfWriter writer) {
	PdfPTable t = new PdfPTable(1);
	t.setWidthPercentage(40);
	t.setHorizontalAlignment(Element.ALIGN_LEFT);
	t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

	t.addCell(new Phrase("Página " + writer.getPageNumber(), FONT_4_NORMAL));
		
	return t;
    }
     */
    private PdfPTable createTableDireccion() throws IOException, DocumentException {
        PdfPTable tableDireccion = new PdfPTable(1);
        tableDireccion.getDefaultCell().setPaddingLeft(0);
        tableDireccion.getDefaultCell().setPaddingTop(1);
        tableDireccion.getDefaultCell().setPaddingBottom(1);
        tableDireccion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        tableDireccion.setWidths(new float[]{PdfItextConst.CM_380});
        tableDireccion.setTotalWidth(PdfItextConst.CM_380);
        tableDireccion.setLockedWidth(true);

        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.nombre"), PdfItextConst.FONT_6_NORMAL));
        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.via.cp") + " " + params.get("organismo.nivel.1.via.provincia"), PdfItextConst.FONT_6_NORMAL));
        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.telefono"), PdfItextConst.FONT_6_NORMAL));
        tableDireccion.addCell(new Phrase(params.get("organismo.nivel.1.fax"), PdfItextConst.FONT_6_NORMAL));

        /*
        PdfPCell cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.via.nombre"), PdfItextConst.FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.via.cp") + " " + params.get("organismo.nivel.1.via.provincia"), PdfItextConst.FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.telefono"), PdfItextConst.FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        tableDireccion.addCell(cell);
        
        cell = new PdfPCell(new Phrase(params.get("organismo.nivel.1.fax")", PdfItextConst.FONT_6_NORMAL));        
        cell.setBorder(PdfPCell.NO_BORDER);
        tableDireccion.addCell(cell);
         */
        return tableDireccion;
    }
}
