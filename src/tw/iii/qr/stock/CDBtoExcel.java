package tw.iii.qr.stock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CDBtoExcel {
	public static void main(String[] args) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("第一頁");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		
		
		cell.setCellValue(1);
		row.createCell(1).setCellValue("ID");
		row.createCell(2).setCellValue("YA");
		
		FileOutputStream out = new FileOutputStream("test.xlsx");
		wb.write(out);
		out.close();
				
				
	}
}
