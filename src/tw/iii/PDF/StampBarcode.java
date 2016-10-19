package tw.iii.PDF;

import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.test.annotations.type.SampleTest;
 
import java.io.File;
 
public class StampBarcode {

	 public static final String DEST = "results/chapter01/stamp_barcode.pdf";
	    public static final String SRC = "results/chapter01/quick_brown_fox.pdf";
	 
	    public static void main(String[] args) throws Exception {
	        File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new StampBarcode().manipulatePdf(DEST);
	    }
	 
	    protected void manipulatePdf(String dest) throws Exception {
	        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
	        int n = pdfDoc.getNumberOfPages();
	        Rectangle pagesize;
	        for (int i = 1; i <= n; i++) {
	            PdfCanvas over = new PdfCanvas(pdfDoc.getPage(i));
	            pagesize = pdfDoc.getPage(i).getPageSize();
	            float x = pagesize.getLeft() + 10;
	            float y = pagesize.getTop() - 50;
	            BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
	            barcode.setCodeType(BarcodeEAN.EAN8);
	            String s = String.valueOf(i);
	            s = "00000000".substring(s.length()) + s;
	            barcode.setCode(s);
	            PdfFormXObject barcodeXObject = barcode.createFormXObject(Color.BLACK, Color.BLACK, pdfDoc);
	            over.addXObject(barcodeXObject, x, y);
	            System.out.println(over.toString());
	        }
	        pdfDoc.close();
	    }
	}