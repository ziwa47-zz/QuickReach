package tw.iii.PDF;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.test.annotations.type.SampleTest;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;

public class myPdf {
	
	public static final String DEST = "results/chapter01/myPDF.pdf";
	 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new myPdf().manipulatePdf(DEST);
    }
    
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        Barcode128 code128 = new Barcode128(pdfDoc);
        code128.setCode("2016093003eaby001");
        code128.setCodeType(Barcode128.CODE128);
        PdfFormXObject xObject = code128.createFormXObject(null, null, pdfDoc);
        float x = 36;
        float y = 750;
        float w = xObject.getWidth();
        float h = xObject.getHeight();
        canvas.saveState();
        canvas.rectangle(x, y, w, h);
        canvas.restoreState();
        canvas.addXObject(xObject, 100, 770);
        
        
        // Initialize document
        Document document = new Document(pdfDoc);
 
        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("123").setFont(font));
        document.add(new Paragraph("To:").setFont(font));
        document.add(new Paragraph("ATTN:").setFont(font));
        document.add(new Paragraph("ADD:").setFont(font));
        document.add(new Paragraph("    ").setFont(font));
        document.add(new Paragraph("    ").setFont(font));
        document.add(new Paragraph("TEL:").setFont(font));

 
        //Close document
        
        pdfDoc.close();
        document.close();
        
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);
 
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf);
 
        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
        // Create a List
        List list = new List()
            .setSymbolIndent(12)
            .setListSymbol("\u2022")
            .setFont(font);
        // Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
            .add(new ListItem("Never gonna let you down"))
            .add(new ListItem("Never gonna run around and desert you"))
            .add(new ListItem("Never gonna make you cry"))
            .add(new ListItem("Never gonna say goodbye"))
            .add(new ListItem("Never gonna tell a lie and hurt you"));
        // Add the list
        document.add(list);
 
        //Close document
        document.close();
    }
    
}
