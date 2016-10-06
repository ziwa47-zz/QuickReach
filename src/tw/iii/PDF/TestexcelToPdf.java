package tw.iii.PDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.C01E02_RickAstley;

public class TestexcelToPdf {
	public static final String DEST = "results/chapter01/testExcel.pdf";
	 
	public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TestexcelToPdf().createPdf(DEST);
	}
	
    public void createPdf(String dest) throws IOException {
		FileInputStream input_document = new FileInputStream(new File("C:/Users/Jenan/Desktop/Github/QuickReach/results/chapter01/2016-09-30AP寄件單範本AP.xlsx"));
        // Read workbook into HSSFWorkbook
		HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document); 
        // Read worksheet into HSSFSheet
        HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);
        
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf);
        //We will create output PDF document objects at this point
        Document iText_xls_2_pdf = new Document();
        PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("Excel2PDF_Output.pdf"));
        iText_xls_2_pdf.open();
        //we have two columns in the Excel sheet, so we create a PDF table with two columns
        //Note: There are ways to make this dynamic in nature, if you want to.
        PdfPTable my_table = new PdfPTable(2);
        //We will use the object below to dynamically add new data to the table
        PdfPCell table_cell;
        //Loop through rows.
        while(rowIterator.hasNext()) {
                Row row = rowIterator.next(); 
                Iterator<Cell> cellIterator = row.cellIterator();
                        while(cellIterator.hasNext()) {
                                Cell cell = cellIterator.next(); //Fetch CELL
                                switch(cell.getCellType()) { //Identify CELL type
                                        //you need to add more code here based on
                                        //your requirement / transformations
                                case Cell.CELL_TYPE_STRING:
                                        //Push the data from Excel to PDF Cell
                                         table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                                         //feel free to move the code below to suit to your needs
                                         my_table.addCell(table_cell);
                                        break;
                                }
                                //next line
                        }

        }
        //Finally add the table to PDF document
        iText_xls_2_pdf.add(my_table);                       
        iText_xls_2_pdf.close();                
        //we created our pdf file..
        input_document.close(); //close xls
		//FileOutputStream out = new FileOutputStream("C:/Users/iii/Documents/Excel_Data/" + date + "AP寄件單範本EMS.xlsx");
		FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/Github/QuickReach/results/chapter01/"+ date +"AP寄件單範本AP.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
    }


	private static String getDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}
	
	
	
}
