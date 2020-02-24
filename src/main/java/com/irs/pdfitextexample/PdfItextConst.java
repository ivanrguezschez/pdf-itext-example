package com.irs.pdfitextexample;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

public final class PdfItextConst {

    private PdfItextConst() {
    }

    public static final Font FONT_2_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 2, Font.NORMAL);
    public static final Font FONT_3_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 3, Font.NORMAL);
    public static final Font FONT_4_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 4, Font.NORMAL);
    public static final Font FONT_6_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 6, Font.NORMAL);
    public static final Font FONT_8_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 8, Font.NORMAL);
    public static final Font FONT_8_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 8, Font.BOLD);
    public static final Font FONT_10_NORMAL = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 10, Font.NORMAL);
    public static final Font FONT_10_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 10, Font.BOLD);
    public static final Font FONT_12_BOLD = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1252, 12, Font.BOLD);
    
    public static final float CM_17 = PdfItextUtil.centimeterToPoint(17.0f);
    public static final float CM_380 = PdfItextUtil.centimeterToPoint(3.80f);
    public static final float CM_1 = PdfItextUtil.centimeterToPoint(1.0f);
    public static final float CM_2 = PdfItextUtil.centimeterToPoint(2.0f);
    public static final float CM_05 = PdfItextUtil.centimeterToPoint(0.5f);
    public static final float CM_02 = PdfItextUtil.centimeterToPoint(0.2f);
    
}
