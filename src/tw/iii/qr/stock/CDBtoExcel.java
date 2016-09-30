package tw.iii.qr.stock;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public static void main(String[] args) {
		日結表();
	}
	public CDBtoExcel() {
	
	}
	public static void  日結表(){
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
	public static void 美日結表()throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("美日結表");
		String strsql = "select s.date, s.QR_id, m.eBayAccount, m.ebayNO, d.SKU, d.productName, d.qty,"
				+ " r.country, d.owner, d.warehouse, m.staffName, s.comment, s.trackingCode"
				+ " from orders_master as m inner join shippinglog as s on m.QR_id = s.QR_id"
				+ " inner join order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " inner join orders_detail as d on m.QR_id = d.QR_id"
				+ " where m.shippingDate >= (DATEADD(dd, DATEDIFF(dd, 0, GETDATE()), 0))";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		int index = 1;
		XSSFRow myRow = sheet.createRow(0);
		myRow.createCell(0).setCellValue("項次");
		myRow.createCell(1).setCellValue("E/B.NO");
		myRow.createCell(2).setCellValue("E/B ITEM NO.");
		myRow.createCell(3).setCellValue("SKU");
		myRow.createCell(4).setCellValue("品名");
		myRow.createCell(5).setCellValue("數量");
		myRow.createCell(6).setCellValue("E/B ID");
		myRow.createCell(7).setCellValue("國家");
		myRow.createCell(8).setCellValue("幣別");
		myRow.createCell(9).setCellValue("E/B 成交價");
		myRow.createCell(10).setCellValue("E/B 含運費");
		myRow.createCell(11).setCellValue("P/P DATE");
		myRow.createCell(12).setCellValue("P/P PAYMENT ID");
		myRow.createCell(13).setCellValue("P/P TOTAL");
		myRow.createCell(14).setCellValue("P/P FEE");
		myRow.createCell(15).setCellValue("P/P NET");
		myRow.createCell(16).setCellValue("進貨成本 NTD");
		myRow.createCell(17).setCellValue("寄件日");
		myRow.createCell(18).setCellValue("遞交方式");
		myRow.createCell(19).setCellValue("EBAY FEE (US)");
		myRow.createCell(20).setCellValue("TEL");
		myRow.createCell(21).setCellValue("TRACKING NO.");
		myRow.createCell(22).setCellValue("運費 USD");
		myRow.createCell(23).setCellValue("運費 NTD");
		myRow.createCell(24).setCellValue("包材 NTD");
		myRow.createCell(25).setCellValue("REMARK");
		myRow.createCell(26).setCellValue("商品持有人");
		while (rs.next()) {
			myRow = sheet.createRow(index);
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
			myRow.createCell(10).setCellValue(rs.getString(11));
			myRow.createCell(11).setCellValue(rs.getString(12));
			myRow.createCell(12).setCellValue(rs.getString(13));
			myRow.createCell(13).setCellValue(rs.getString(14));
			myRow.createCell(14).setCellValue(rs.getString(15));
			myRow.createCell(15).setCellValue(rs.getString(16));
			myRow.createCell(16).setCellValue(rs.getString(17));
			myRow.createCell(17).setCellValue(rs.getString(18));
			myRow.createCell(18).setCellValue(rs.getString(19));
			myRow.createCell(19).setCellValue(rs.getString(20));
			myRow.createCell(20).setCellValue(rs.getString(21));
			myRow.createCell(21).setCellValue(rs.getString(22));
			myRow.createCell(22).setCellValue(rs.getString(23));
			myRow.createCell(23).setCellValue(rs.getString(24));
			myRow.createCell(24).setCellValue(rs.getString(25));
			myRow.createCell(25).setCellValue(rs.getString(26));
		
			index++;
		}
		String date = getDay();
		
		//FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/" + date + "日出貨報表.xlsx");
		FileOutputStream out = new FileOutputStream("C:/Users/iii/Documents/Excel_Data/" + date + "美日結表.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
		
	}
	
	public void 進出貨紀錄(){
		
	}
	public void 揀貨單(){
		
	}
	//日出貨報表
	public void dailyBalanceSheetExcel() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("日出貨報表");
		String strsql = "select s.date, s.QR_id, m.eBayAccount, m.ebayNO, d.SKU, d.productName, d.qty,"
				+ " r.country, d.owner, d.warehouse, m.staffName, s.comment, s.trackingCode"
				+ " from orders_master as m inner join shippinglog as s on m.QR_id = s.QR_id"
				+ " inner join order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " inner join orders_detail as d on m.QR_id = d.QR_id"
				+ " where m.shippingDate >= (DATEADD(dd, DATEDIFF(dd, 0, GETDATE()), 0))";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		int index = 1;
		XSSFRow myRow = sheet.createRow(0);
		myRow.createCell(0).setCellValue("出貨日期");
		myRow.createCell(1).setCellValue("出貨編號");
		myRow.createCell(2).setCellValue("type");
		myRow.createCell(3).setCellValue("EbayAccount");
		myRow.createCell(4).setCellValue("訂單編號");
		myRow.createCell(5).setCellValue("SKU");
		myRow.createCell(6).setCellValue("產品名稱");
		myRow.createCell(7).setCellValue("數量");
		myRow.createCell(8).setCellValue("國家");
		myRow.createCell(9).setCellValue("Owner");
		myRow.createCell(10).setCellValue("倉別");
		myRow.createCell(11).setCellValue("員工姓名");
		myRow.createCell(12).setCellValue("備註");
		myRow.createCell(13).setCellValue("追蹤碼");
		while (rs.next()) {
			myRow = sheet.createRow(index);
			myRow.createCell(0).setCellValue(rs.getString(1));
			myRow.createCell(1).setCellValue(rs.getString(2));
			myRow.createCell(2).setCellValue("訂單出貨");
			myRow.createCell(3).setCellValue(rs.getString(3));
			myRow.createCell(4).setCellValue(rs.getString(4));
			myRow.createCell(5).setCellValue(rs.getString(5));
			myRow.createCell(6).setCellValue(rs.getString(6));
			myRow.createCell(7).setCellValue(rs.getString(7));
			myRow.createCell(8).setCellValue(rs.getString(8));
			myRow.createCell(9).setCellValue(rs.getString(9));
			myRow.createCell(10).setCellValue(rs.getString(10));
			myRow.createCell(11).setCellValue(rs.getString(11));
			myRow.createCell(12).setCellValue(rs.getString(12));
			myRow.createCell(13).setCellValue(rs.getString(13));
			index++;
		}
		String date = getDay();
		
		//FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/" + date + "日出貨報表.xlsx");
		FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/" + date + "日出貨報表.pdf");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
	}

	
	public void 物流匯出格式() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("物流匯出報表");
		String strsql = "select s.date, s.type, s.QR_id, d.SKU, d.productName, d.qty, m.eBayAccount,"
				+ " r.country"
				+ " from orders_master as m inner join shippinglog as s on m.QR_id = s.QR_id"
				+ " inner join order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " inner join orders_detail as d on m.QR_id = d.QR_id"
				+ " where m.orderStatus = N'撿貨中'";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		int index = 1;
		XSSFRow myRow = sheet.createRow(0);
		myRow.createCell(0).setCellValue("出貨日期");
		myRow.createCell(1).setCellValue("寄件類型");
		myRow.createCell(2).setCellValue("order no.");
		myRow.createCell(3).setCellValue("SKU");
		myRow.createCell(4).setCellValue("商品名稱");
		myRow.createCell(5).setCellValue("數量");
		myRow.createCell(6).setCellValue("寄件人資訊");//eBayAccount
		myRow.createCell(7).setCellValue("幣別");  //master currency
		myRow.createCell(8).setCellValue("E/B含運費");//
		myRow.createCell(9).setCellValue("寄件材積");
		myRow.createCell(10).setCellValue("寄件重量"); 
		myRow.createCell(11).setCellValue("Trqacking No."); //trackingCode
		myRow.createCell(12).setCellValue("運費(TWD)");
		myRow.createCell(13).setCellValue("運費(USD)");
		while (rs.next()) {
			myRow = sheet.createRow(index);
			myRow.createCell(0).setCellValue(rs.getString(1));
			myRow.createCell(1).setCellValue(rs.getString(2));
			myRow.createCell(2).setCellValue(rs.getString(3));
			myRow.createCell(3).setCellValue(rs.getString(4));
			myRow.createCell(4).setCellValue(rs.getString(5));
			myRow.createCell(5).setCellValue(rs.getString(6));
			myRow.createCell(6).setCellValue(rs.getString(7));
			myRow.createCell(7).setCellValue(rs.getString(8));
			
			index++;
		}
		String date = getDay();
		
		FileOutputStream out = new FileOutputStream("C:\\EC\\"+date+"物流匯出報表.xlsx");
		//FileOutputStream out = new FileOutputStream("C:/Users/iii/Documents/Excel_Data/" + date + "報表.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
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
