package tw.iii.PDF;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.test.annotations.type.SampleTest;
 
import java.io.File;


public class BarcodeBackground {

	public static final String DEST = "results/chapter01/barcode_background.pdf";
	 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BarcodeBackground().manipulatePdf(DEST);
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
        canvas.addXObject(xObject, 36, 750);
        canvas.moveTo(0, 0);
        pdfDoc.close();
    }
}