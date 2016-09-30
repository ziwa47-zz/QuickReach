package tw.iii.PDF;
import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
 
import java.io.File;

public class Barcodes {

	public static final String DEST = "results/chapter01/barcodes.pdf";
	 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Barcodes().manipulatePdf(DEST);
    }
 
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
 
        Table table = new Table(1);
        for (int i = 0; i < 4; i++) {
            table.addCell(createBarcode("201603ebay001", pdfDoc));
        }
        doc.add(table);
 
        doc.close();
    }
 
    public static Cell createBarcode(String code, PdfDocument pdfDoc) {
        BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
        barcode.setCodeType(BarcodeEAN.EAN8);
        barcode.setCode(code);
        Cell cell = new Cell().add(new Image(barcode.createFormXObject(null, null, pdfDoc)));
        cell.setPaddingTop(10);
        cell.setPaddingRight(10);
        cell.setPaddingBottom(10);
        cell.setPaddingLeft(10);
        return cell;
    }
}
