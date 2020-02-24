package com.irs.pdfitextexample;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import org.apache.commons.lang.StringUtils;

public final class PdfItextUtil {

    private PdfItextUtil() {
    }
    
    public static float pointToInch(float point) {
        return (float) (point / 72.0);
    }
    
    public static float pointToCentimeter(float point) {
        //return Math.round((point * 2.54 / 72.0) * 100 / 100);
        return Math.round((point * 2.54 / 72.0) * 100 / 100);
        /*
        BigDecimal bd = new BigDecimal(Double.toString(point * 2.54 / 72.0));
        bd = bd.setScale(2, RoundingMode.UP);
        return bd.doubleValue();
        */
    }

    public static float pointToMilimeter(float point) {
        return (float) (point * 2.54 / 72.0 * 100);
    }
	
    public static float centimeterToPoint(float cm) {
        return Math.round((cm * 72.0 / 2.54) * 100 / 100);
    }
    
    public static Paragraph createParagraph(String text, Font font, float spacingAfter) {
		Paragraph paragraph = new Paragraph(text, font);
		paragraph.setSpacingAfter(spacingAfter);
        
		return paragraph;
    }
    
    public static PdfPCell createCellLabel(String label) {
        PdfPCell cell = new PdfPCell(new Phrase(label, PdfItextConst.FONT_8_NORMAL));
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
    }
    
    public static PdfPCell createCellValue(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(StringUtils.isNotEmpty(value) ? value : "-", PdfItextConst.FONT_8_BOLD));
        cell.setBorder(PdfPCell.NO_BORDER);
        
        return cell;
    }
    
    public static PdfPCell createCellHeader(String header) {
        PdfPCell cell = new PdfPCell(new Phrase(header, PdfItextConst.FONT_8_BOLD));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        return cell;
    }
    
    public static PdfPCell createCellBody(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, PdfItextConst.FONT_8_NORMAL));
        return cell;
    }
}
