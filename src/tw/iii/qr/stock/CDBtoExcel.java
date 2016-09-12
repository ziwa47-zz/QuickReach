package tw.iii.qr.stock;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import tw.iii.qr.DataBaseConn;

public class CDBtoExcel {
	public CDBtoExcel() {
	
	}
	public void  日結表(){
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("日結表");
			String strsql = "select * from product";
			Connection conn = new DataBaseConn().getConn();
			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();
			int index = 0;
			while (rs.next()) {
				XSSFRow myRow = sheet.createRow(index);
				myRow.createCell(0).setCellValue(rs.getString(1));
				myRow.createCell(1).setCellValue(rs.getString(2));
				myRow.createCell(2).setCellValue(rs.getString(3));
				myRow.createCell(3).setCellValue(rs.getString(4));
				myRow.createCell(4).setCellValue(rs.getString(5));
				myRow.createCell(5).setCellValue(rs.getString(6));
				myRow.createCell(6).setCellValue(rs.getString(7));
				myRow.createCell(7).setCellValue(rs.getString(8));
				myRow.createCell(8).setCellValue(rs.getString(9));
				myRow.createCell(9).setCellValue(rs.getString(10));
				index++;
			}

			// row.createCell(1).setCellValue("ID");
			// row.createCell(2).setCellValue("YA");
			String date = getDay();

			FileOutputStream out;

			out = new FileOutputStream(date + "日結表.xlsx");

			wb.write(out);
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void 進出貨紀錄(){
		
	}
	public void 揀貨單(){
		
	}
public void 出貨單(){
		
	}
	public static String getDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}
}
