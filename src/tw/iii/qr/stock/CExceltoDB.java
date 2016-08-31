package tw.iii.qr.stock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CExceltoDB {
	
	
	public CExceltoDB() throws IOException {
		
	}
	public static void main(String[] args) throws IOException{
		String path = "/Users/Ziwa/Desktop/進貨單範本.xlsx"; 
		
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] strData= new String[4];
		XSSFCell cell;
		
		for(int i = 3 ; i<sheet.getPhysicalNumberOfRows();i++){
			
			XSSFRow row = sheet.getRow(i);
			for(int j = 0 ;j<=3;j++ ){
				cell = row.getCell(j);
				strData[j]=cell.toString();
			}
			System.out.println("ID:" +strData[0]+" Sku:"+strData[1]+" Name:"+strData[2]+" Spec:"+strData[3]);
		}
		
	}

}
