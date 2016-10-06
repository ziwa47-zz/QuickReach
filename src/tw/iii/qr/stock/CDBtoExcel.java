package tw.iii.qr.stock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.iii.qr.DataBaseConn;

public class CDBtoExcel {
	public static void main(String[] args) {

	}

	public CDBtoExcel() {

	}

	public String[] 日結表() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("日結表");
		String strsql = "select distinct m.ebayNO, m.ebayItemNO , d.SKU, d.productName,d.qty, m.guestAccount,"
				+ " r.country, m.currency, m.ebayprice, m.ebayTotal, m.payDate,"
				+ " m.paypalmentId, m.totalPrice, m.paypalFees,m.paypalNet, "
				+ " p.cost, shippingdate, m.logistics, m.ebayFees, r.tel1, m.trackingCode,"
				+ " m.shippingFees, m.packageFees,d.comment, p.owner"

				+ " FROM  orders_master as m inner join" + " orders_detail as d on m.QR_id = d.QR_id inner join"
				+ " order_recieverinfo as r on m.QR_id = r.QR_id inner join"
				+ " shippinglog as s on m.QR_id = r.QR_id  inner join" + " product as p on d.SKU  = p.SKU "
				+ " where  shippingdate >=  convert(varchar,GETDATE(),111)";

		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		int index = 0;
		int index2 = 1;

		// 以下為每筆訂單的標頭
		XSSFRow myRow = sheet.createRow(index);
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

			// 以下為每筆訂單取到的值
			myRow = sheet.createRow(index + 1);
			myRow.createCell(0).setCellValue(index2);
			myRow.createCell(1).setCellValue(rs.getString(1));
			myRow.createCell(2).setCellValue(rs.getString(2));
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
			myRow.createCell(14).setCellValue(rs.getString(14));
			myRow.createCell(15).setCellValue(rs.getString(15));
			myRow.createCell(16).setCellValue(rs.getString(16));
			myRow.createCell(17).setCellValue(rs.getString(17));
			myRow.createCell(18).setCellValue(rs.getString(18));
			myRow.createCell(19).setCellValue(rs.getString(19));
			myRow.createCell(20).setCellValue(rs.getString(20));
			myRow.createCell(21).setCellValue(rs.getString(21));
			myRow.createCell(22).setCellValue(" ");
			myRow.createCell(23).setCellValue(rs.getString(22));
			myRow.createCell(24).setCellValue(rs.getString(23));
			myRow.createCell(25).setCellValue(rs.getString(24));
			myRow.createCell(26).setCellValue(rs.getString(25));

			index++;
			index2++;
		}
		String date = getDay();

		// FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/"
		// + date + "日出貨報表.xlsx");
		FileSystemView fsv = FileSystemView.getFileSystemView();

		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "Daily.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "Daily.xlsx" };
	}
	public String[] pickup(HttpServletRequest request, HttpServletResponse response){
		String[] pickup = null;
		try {
			String[] qrid = request.getParameterValues("QR_id");
			XSSFWorkbook wbpick = new XSSFWorkbook();
			pickup=new CDBtoExcel().get揀貨單(qrid, wbpick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pickup;
	}
	public String[] collect(HttpServletRequest request, HttpServletResponse response){
		String[] coll = null;
		try {
			XSSFWorkbook wbcoll = new XSSFWorkbook();
			coll = new CDBtoExcel().get集貨單(wbcoll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}
	public String[] logisticsselect(HttpServletRequest request, HttpServletResponse response) {
		String[] pa = null;
		try {
			// request.setCharacterEncoding("UTF-8");
			// response.setCharacterEncoding("text/html;charset=UTF-8");
			// PrintWriter out = response.getWriter();
			String[] qrid = request.getParameterValues("QR_id");

			Connection conn = new DataBaseConn().getConn();
			FileSystemView fsv = FileSystemView.getFileSystemView();
			String date = getDay();
			String path = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "sample"
					+ File.separator + "寄件單範本.xlsx";
			System.out.println(path);
			XSSFWorkbook wb = new XSSFWorkbook(path);
			for (int i = 0; i < qrid.length; i++) {

				String logis = "select logistics from orders_master where qr_id = ? ";
				PreparedStatement ps = conn.prepareStatement(logis);
				ps.setString(1, qrid[i]);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					if ("AP".equals(rs.getString(1))) {
						getAP(qrid[i], wb, i, conn);

					}
					if ("EMS".equals(rs.getString(1))) {
						getEMS(qrid[i], wb, i, conn);
					}
					if ("RA".equals(rs.getString(1))) {

					}

				}
			}
			pa = new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
					date + "Sent.xlsx" };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pa;

	}

	private void getEMS(String qrid, XSSFWorkbook wb, int i, Connection conn) {
		CopySheetStyle cs = new CopySheetStyle();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String date = getDay();
		try {

			XSSFSheet fromSheet = wb.getSheet("EMS");
			XSSFSheet toSheet = wb.createSheet("EMS" + i);
			cs.copySheet(wb, fromSheet, toSheet);
			String strsql = " select e.correspondCompany,e.companyAddress,e.companyPost,e.companyPhone,e.country,g.guestFirstName,g.guestLastName,r.address,r.country,d.invoiceName,d.invoicePrice,d.qty,convert(nvarchar,getDate(),106),m.currency "
					+ " from ebayaccount e inner join orders_master m on e.ebayid = m.ebayAccount "
					+ " inner join orders_guestinfo g on m.Qr_id = g.qr_id "
					+ " inner join order_recieverinfo r on r.qr_id= g.qr_id "
					+ " inner join orders_detail d on m.qr_id = d.qr_id " + " where m.qr_id= ?  ";

			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);
			ps.setString(1, qrid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				XSSFRow myRow4 = toSheet.getRow(4);
				// 收件人姓名
				myRow4.getCell(6).setCellValue(rs.getString(6) + " " + rs.getString(7));

				XSSFRow myRow5 = toSheet.getRow(5);
				String nameadd = rs.getString(1) + "\r\n" + rs.getString(2);
				XSSFCellStyle style5 = wb.createCellStyle();
				style5.setWrapText(true);

				myRow5.getCell(1).setCellStyle(style5);
				myRow5.getCell(1).setCellValue(nameadd);
				// 收件人地址
				myRow5.getCell(6).setCellStyle(style5);
				myRow5.getCell(6).setCellValue(rs.getString(8));

				XSSFRow myRow7 = toSheet.getRow(7);
				// myRow7.getCell(2).setCellValue("國家"); // 收件人國家
				myRow7.getCell(7).setCellValue(rs.getString(9));

				XSSFRow myRow8 = toSheet.getRow(8);
				// 寄件人郵遞區號
				myRow8.getCell(2).setCellValue(rs.getString(3));
				// 寄件人電話
				myRow8.getCell(3).setCellValue(rs.getString(4));

				// 字型設定
				Font font = wb.createFont();
				font.setFontName("新細明體"); // 設定字體
				font.setFontHeightInPoints((short) 9); // 設定字體大小
				// 設定儲存格格式，包含字體大小等
				CellStyle styleRow10 = wb.createCellStyle();
				styleRow10.setFont(font);
				styleRow10.setWrapText(true);
				// 設定字體
				XSSFRow myRow10 = toSheet.getRow(10);
				// InvoiceName
				myRow10.getCell(3).setCellValue(rs.getString(10));
				myRow10.getCell(3).setCellStyle(styleRow10);
				XSSFRow myRow11 = toSheet.getRow(11);
				// InvoicePrice
				myRow11.getCell(6).setCellValue(rs.getString(14) + " " + rs.getString(11));

				XSSFRow myRow14 = toSheet.getRow(14);
				myRow14.getCell(4).setCellValue(rs.getString(13).replace(" ", "-"));

			}

			FileOutputStream out = new FileOutputStream(
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "Sent.xlsx");
			wb.write(out);
			out.close();

		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	private void getAP(String qrid, XSSFWorkbook wb, int i, Connection conn) {
		CopySheetStyle cs = new CopySheetStyle();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String date = getDay();
		try {
			XSSFSheet fromSheet = wb.getSheet("A&P");
			XSSFSheet toSheet = wb.createSheet("A&P" + i);
			cs.copySheet(wb, fromSheet, toSheet);
			String strsql = " select e.correspondCompany,e.companyAddress,e.companyPost,e.companyPhone,e.country,g.guestFirstName,g.guestLastName,r.address,r.country,d.invoiceName,d.invoicePrice,d.qty,convert(nvarchar,getDate(),106),m.currency "
					+ " from ebayaccount e inner join orders_master m on e.ebayid = m.ebayAccount "
					+ " inner join orders_guestinfo g on m.Qr_id = g.qr_id "
					+ " inner join order_recieverinfo r on r.qr_id= g.qr_id "
					+ " inner join orders_detail d on m.qr_id = d.qr_id " + " where m.qr_id= ?  ";

			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);
			ps.setString(1, qrid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				XSSFRow myRow2 = toSheet.getRow(2);
				// myRow2.getCell(1).setCellValue("寄件人名字"); // 寄件人名字
				myRow2.getCell(1).setCellValue(rs.getString(1));

				XSSFRow myRow3 = toSheet.getRow(3);
				// myRow3.getCell(1).setCellValue("寄件人地址"); // 寄件人地址
				myRow3.getCell(1).setCellValue(rs.getString(2));

				XSSFRow myRow4 = toSheet.getRow(4);
				// myRow4.getCell(2).setCellValue("寄件人郵遞區號"); // 寄件人郵遞區號
				// myRow4.getCell(4).setCellValue("寄件人Mobile"); // 寄件人Mobile
				// myRow4.getCell(7).setCellValue("Check欄位"); // Check欄位
				myRow4.getCell(2).setCellValue(rs.getString(3));
				myRow4.getCell(4).setCellValue(rs.getString(4));

				XSSFRow myRow5 = toSheet.getRow(5);
				// myRow5.getCell(2).setCellValue("收件人"); // 收件人
				myRow5.getCell(1).setCellValue(rs.getString(6) + " " + rs.getString(7));

				XSSFRow myRow6 = toSheet.getRow(6);
				// myRow6.getCell(1).setCellValue("收件人地址"); // 收件人地址
				myRow6.getCell(1).setCellValue(rs.getString(8));

				XSSFRow myRow7 = toSheet.getRow(7);
				// myRow7.getCell(2).setCellValue("國家"); // 國家
				myRow7.getCell(2).setCellValue(rs.getString(9));

				// 字型設定
				Font font1 = wb.createFont();
				font1.setFontName("新細明體"); // 設定字體
				font1.setFontHeightInPoints((short) 9); // 設定字體大小
				// 設定儲存格格式，包含字體大小等
				CellStyle styleRow8 = wb.createCellStyle();
				styleRow8.setFont(font1);
				styleRow8.setWrapText(true);

				XSSFRow myRow8 = toSheet.getRow(8);
				// myRow8.getCell(1).setCellValue("品名"); // 品名
				// myRow8.getCell(3).setCellValue("數量"); // 數量
				// myRow8.getCell(4).setCellValue("寄件國家"); // 寄件國家
				// myRow8.getCell(7).setCellValue("價錢"); // 價錢
				myRow8.getCell(1).setCellStyle(styleRow8);
				myRow8.getCell(1).setCellValue(rs.getString(10));

				XSSFRow myRow8_1 = toSheet.getRow(9);
				myRow8_1.getCell(3).setCellValue(rs.getString(12));
				myRow8_1.getCell(4).setCellValue(rs.getString(5));
				myRow8_1.getCell(7).setCellValue(rs.getString(14) + " " + rs.getString(11));

				XSSFRow myRow11 = toSheet.getRow(17);
				// myRow11.getCell(5).setCellValue("日期"); // 日期
				myRow11.getCell(4).setCellValue(rs.getString(13).replace(" ", "-"));

				System.out.println(rs.getString(13).replace(" ", "-"));
			}

			FileOutputStream out = new FileOutputStream(
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "Sent.xlsx");
			wb.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void get揀貨單(String[] qrid, String path, Connection conn)
	// throws IllegalAccessException, ClassNotFoundException, SQLException,
	// Exception {
	// String date = getDay();
	// CopySheetStyle cs = new CopySheetStyle();
	// XSSFWorkbook wb = new XSSFWorkbook(path);
	// XSSFSheet fromSheet = wb.getSheet("撿貨單");
	// XSSFSheet toSheet = wb.createSheet("撿貨單" + date);
	//
	// System.out.println("qrid.length:" + qrid.length);
	//
	// for (int i = 0; i < qrid.length; i++) {
	// String strsql = " select order_id, o.SKU, productType, brand, subBrand,
	// productName, spec,qty"
	// + " from orders_detail o inner join product p on o.SKU = p.SKU" + " where
	// QR_id = ?";
	//
	// PreparedStatement ps = null;
	// System.out.println("strsql:" + strsql);
	//
	// System.out.println("QRID:" + qrid[i]);
	// ps = conn.prepareStatement(strsql);
	// ps.setString(1, qrid[i]);
	// ResultSet rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// cs.copySheet(wb, fromSheet, toSheet);
	//
	// System.out.println("SKU:" + rs.getString(2));
	//
	// XSSFRow myRow2 = toSheet.getRow(3);
	// myRow2.createCell(1).setCellValue("SKU:");
	// myRow2.getCell(2).setCellValue(rs.getString(2)); // SKU碼
	//
	// XSSFRow myRow3 = toSheet.getRow(4);
	// myRow3.getCell(1).setCellValue(rs.getString(3)); // 產品類型
	//
	// XSSFRow myRow4 = toSheet.getRow(5);
	// myRow4.getCell(1).setCellValue(rs.getString(4) + rs.getString(5)); //
	// 廠牌&副廠牌
	//
	// XSSFRow myRow5 = toSheet.getRow(6);
	// myRow5.getCell(1).setCellValue(rs.getString(6) + " / " +
	// rs.getString(7)); // 品名/規格
	//
	// XSSFRow myRow7 = toSheet.getRow(7);
	// myRow7.getCell(1).setCellValue(rs.getString(8)); // 數量
	// // index +=10;
	// }
	// }
	//
	// System.out.println("Done");
	// FileSystemView fsv = FileSystemView.getFileSystemView();
	// FileOutputStream out = new FileOutputStream(fsv.getHomeDirectory() + "\\"
	// + date + "揀貨單.xlsx");
	// wb.write(out);
	// out.close();
	// }

	// 揀貨單自行設計位置
	public String[] get揀貨單(String[] qrid, XSSFWorkbook wb)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		XSSFSheet sheet = wb.createSheet("揀貨單");
		String strsql = " select order_id, o.SKU, productType, brand, subBrand, productName, spec,qty"
				+ " from orders_detail o inner join product p on  o.SKU = p.SKU" + " where QR_id = ?";
		Connection conn = new DataBaseConn().getConn();
		int index = 0;
		for (int i = 0; i < qrid.length; i++) {

			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);
			ps.setString(1, qrid[i]);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				XSSFRow myRow = sheet.createRow(1 + (index * 8));
				myRow.createCell(0).setCellValue("訂單編號:");
				myRow.createCell(1).setCellValue(rs.getString(1));

				XSSFRow myRow2 = sheet.createRow(2 + (index * 8));
				myRow2.createCell(0).setCellValue("SKU:");
				myRow2.createCell(1).setCellValue(rs.getString(2));

				XSSFRow myRow3 = sheet.createRow(3 + (index * 8));
				myRow3.createCell(0).setCellValue("產品類型:");
				myRow3.createCell(1).setCellValue(rs.getString(3));

				XSSFRow myRow4 = sheet.createRow(4 + (index * 8));
				myRow4.createCell(0).setCellValue("廠牌:");
				myRow4.createCell(1).setCellValue(rs.getString(4));

				XSSFRow myRow5 = sheet.createRow(5 + (index * 8));
				myRow5.createCell(0).setCellValue("副廠牌:");
				myRow5.createCell(1).setCellValue(rs.getString(5));

				// XSSFRow myRow6 = sheet.createRow(6+(index*8));
				// myRow6.createCell(0).setCellValue("品名/規格:");
				// myRow6.createCell(1).setCellValue(rs.getString(6) + " / " +
				// rs.getString(7) );

				CellRangeAddress region = new CellRangeAddress(6 + index * 8, 6 + index * 8, 0, 17);
				XSSFCell cell = sheet.createRow(6 + index * 8).createCell(0);
				cell.setCellValue("品名/規格:" + rs.getString(6) + " / " + rs.getString(7));
				sheet.addMergedRegion(region);

				XSSFRow myRow7 = sheet.createRow(7 + (index * 8));
				myRow7.createCell(0).setCellValue("數量:");
				myRow7.createCell(1).setCellValue(rs.getString(8));

				index++;
			}
			rs.close();
			ps.close();
		}
		String date = getDay();

		FileSystemView fsv = FileSystemView.getFileSystemView();
		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "Pickup.xlsx");
		wb.write(out);

		conn.close();
		return new String[]{fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator ,date + "Pickup.xlsx"};
	}

	public String[] get集貨單(XSSFWorkbook wb) {
		XSSFSheet sheet = wb.createSheet("集貨單");
		String date = getDay();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String strsql = " select o.SKU,productType,brand, subBrand, productName, spec,count(*),s.warehouse,s.warehousePosition1,s.warehousePosition2 "
				+ " from orders_detail o inner join product p on  o.SKU = p.SKU "
				+ " inner join orders_master m on m.QR_id = o.QR_id "
				+ "	 inner join storage s on s.warehouse = o.warehouse and s.SKU = o.SKU"
				+ " group by o.SKU,productType,brand, subBrand,s.warehouse,s.warehousePosition1,s.warehousePosition2, productName, spec,m.orderStatus"
				+ " having m.orderStatus = N'揀貨中'";
		try {
			Connection conn = new DataBaseConn().getConn();
			int index = 1;

			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);

			ResultSet rs = ps.executeQuery();
			XSSFRow myRow = sheet.createRow(0);
			myRow.createCell(0).setCellValue("SKU");
			myRow.createCell(1).setCellValue("產品類型");
			myRow.createCell(2).setCellValue("廠牌");
			myRow.createCell(3).setCellValue("副廠牌");
			myRow.createCell(4).setCellValue("品名規格");
			myRow.createCell(5).setCellValue("數量");
			myRow.createCell(6).setCellValue("倉別/儲位");
			while (rs.next()) {
				XSSFRow myRow1 = sheet.createRow(index);
				myRow1.createCell(0).setCellValue(rs.getString(1));
				myRow1.createCell(1).setCellValue(rs.getString(2));
				myRow1.createCell(2).setCellValue(rs.getString(3));
				myRow1.createCell(3).setCellValue(rs.getString(4));
				myRow1.createCell(4).setCellValue(rs.getString(5) +" // "+ rs.getString(6));
				myRow1.createCell(5).setCellValue(rs.getString(7));
				myRow1.createCell(6).setCellValue(rs.getString(8) +" "+ rs.getString(9) +"-"+ rs.getString(10));
				index++;

			}
			rs.close();
			ps.close();

		
			FileOutputStream out = new FileOutputStream(
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "collect.xlsx");
			wb.write(out);

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[]{fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator , date + "collect.xlsx"};
	}

	// 日出貨報表
	public String[] dailyBalanceSheetExcel()
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
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

		// FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/"
		// + date + "日出貨報表.xlsx");
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "DailyReport.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "DailyReport.xlsx" };
	}

	public String[] 物流匯出格式() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("物流匯出報表");
		String strsql = "select s.date, s.type, s.QR_id, d.SKU, d.productName, d.qty, m.eBayAccount," + " r.country"
				+ " from orders_master as m left join shippinglog as s on m.QR_id = s.QR_id"
				+ " inner join order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " inner join orders_detail as d on m.QR_id = d.QR_id" + " where m.orderStatus = N'撿貨中'";
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
		myRow.createCell(6).setCellValue("寄件人資訊");// eBayAccount
		myRow.createCell(7).setCellValue("幣別"); // master currency
		myRow.createCell(8).setCellValue("E/B含運費");//
		myRow.createCell(9).setCellValue("寄件材積");
		myRow.createCell(10).setCellValue("寄件重量");
		myRow.createCell(11).setCellValue("Trqacking No."); // trackingCode
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
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "Logistic.xlsx");
		wb.write(out);
		wb.close();
		rs.close();
		ps.close();
		conn.close();
		String[] path = { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator, date + "Logistic.xlsx" };


		return path;
	}

	public static String getDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}
}
