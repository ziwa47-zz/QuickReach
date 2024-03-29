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
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.IndependentOrder.model.repository.BundlesDAO;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;
import tw.iii.qr.IndependentOrder.model.repository.IcomebineOrderDAO;
import tw.iii.qr.IndependentOrder.model.repository.IdpShippingLogDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogMasterDAO;
import tw.iii.qr.IndependentOrder.model.repository.StorageDAO;
import tw.iii.qr.IndependentOrder.service.StorageService;

public class CDBtoExcelIDP {
	
	@Autowired
	GuestDAO guestDAO;
	@Autowired
	IordersDetailDAO iordersDetailDAO;
	@Autowired
	IordersMasterDAO iordersMasterDAO;
	@Autowired
	IcomebineOrderDAO icomebineOrderDAO;
	@Autowired
	PurchaselogDetailDAO purchaseLogDetailDAO;
	@Autowired
	PurchaselogMasterDAO purchaseLogMasterDAO;
	@Autowired
	BundlesDAO bundlesDAO;
	@Autowired
	IdpShippingLogDAO idpShippingLogDAO;
	@Autowired
	StorageDAO storageDAO;
	@Autowired
	StorageService storageService;


	public CDBtoExcelIDP() {

	}

	public String[] 日結表() throws Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("大量出貨日結表");
		
		
		String strsql = "select distinct m.QR_id, d.SKU, d.productName,d.qty, m.guestid,"
				+ " r.country, m.currency,  m.payDate,"
				+ "  m.totalPrice, m.paypalFees,m.paypalNet, "
				+ " p.cost, m.shippingdate, m.logistics,  r.tel,r.phone, m.trackingCode,"
				+ " m.shippingFees,m.shippingFees, m.packageFees,d.comment, p.owner"

				+ " FROM  iorders_master as m inner join" + " iorders_detail as d on m.QR_id = d.QR_id inner join"
				+ " guest as r on m.guestid = r.guestid inner join"
				+ " IDPshippinglog as s on m.QR_id = s.qrId  inner join" + " product as p on d.SKU  = p.SKU "
				+ " where  m.shippingdate >=  convert(varchar,GETDATE(),111)";

		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		int index = 0;
		int index2 = 1;

		// 以下為每筆訂單的標頭
		XSSFRow myRow = sheet.createRow(index);
		myRow.createCell(0).setCellValue("項次");
		myRow.createCell(1).setCellValue("QRID");
		myRow.createCell(2).setCellValue("SKU");
		myRow.createCell(3).setCellValue("品名");
		myRow.createCell(4).setCellValue("數量");
		myRow.createCell(5).setCellValue("熟客ID");
		myRow.createCell(6).setCellValue("國家");
		myRow.createCell(7).setCellValue("幣別");
		myRow.createCell(8).setCellValue("付款日期");
		myRow.createCell(9).setCellValue("總價");
		myRow.createCell(10).setCellValue("P/P FEE");
		myRow.createCell(11).setCellValue("P/P NET");
		myRow.createCell(12).setCellValue("進貨成本 NTD");
		myRow.createCell(13).setCellValue("寄件日");
		myRow.createCell(14).setCellValue("遞交方式");
		myRow.createCell(15).setCellValue("TEL");
		myRow.createCell(16).setCellValue("MOBILE");
		myRow.createCell(17).setCellValue("TRACKING NO.");
		myRow.createCell(18).setCellValue("運費 USD");
		myRow.createCell(19).setCellValue("運費 NTD");
		myRow.createCell(20).setCellValue("包材 NTD");
		myRow.createCell(21).setCellValue("REMARK");
		myRow.createCell(22).setCellValue("商品持有人");
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
			myRow.createCell(22).setCellValue(rs.getString(22));
		

			index++;
			index2++;
		}
		String date = getDay();

		// FileOutputStream out = new FileOutputStream("C:/Users/Jenan/Desktop/"
		// + date + "日出貨報表.xlsx");
		FileSystemView fsv = FileSystemView.getFileSystemView();

		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPDaily.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPDaily.xlsx" };
	}

	public String[] pickup(HttpServletRequest request, HttpServletResponse response) {
		String[] pickup = null;
		String date = getDay();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		try {
			String[] qrid = request.getParameterValues("QR_id");
			XSSFWorkbook wbpick = new XSSFWorkbook();
			XSSFSheet sheet = wbpick.createSheet("揀貨單");
			String strsql = " select o.qrId, o.SKU, productType, brand, subBrand, productName, spec,qty"
					+ " from iorders_detail o inner join product p on  o.SKU = p.SKU" + " where QR_id = ?";
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
					// myRow6.createCell(1).setCellValue(rs.getString(6) + " / "
					// +
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

			FileOutputStream out = new FileOutputStream(
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPPickup.xlsx");
			wbpick.write(out);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPPickup.xlsx" };
	}

	public String[] collect(HttpServletRequest request, HttpServletResponse response) {
		String[] coll = null;
		String date = getDay();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		try {
			XSSFWorkbook wbcoll = new XSSFWorkbook();
			String[] qrid = request.getParameterValues("QR_id");
			XSSFSheet sheet = wbcoll.createSheet("集貨單");

			String strsql = " select o.SKU,productType,brand, subBrand, productName, spec,count(*),s.warehouse,s.warehousePosition1,s.warehousePosition2 "
					+ " from iorders_detail o inner join product p on  o.SKU = p.SKU "
					+ " inner join iorders_master m on m.QR_id = o.QR_id "
					+ "	 inner join storage s on s.warehouse = o.warehouse and s.SKU = o.SKU"
					+ " group by m.qr_id,o.SKU,productType,brand, subBrand,s.warehouse,s.warehousePosition1,s.warehousePosition2, productName, spec,m.orderStatus"
					+ " having m.orderStatus = N'揀貨中' and m.qr_id = ?";

			Connection conn = new DataBaseConn().getConn();
			int index = 1;

			XSSFRow myRow = sheet.createRow(0);
			myRow.createCell(0).setCellValue("SKU");
			myRow.createCell(1).setCellValue("產品類型");
			myRow.createCell(2).setCellValue("廠牌");
			myRow.createCell(3).setCellValue("副廠牌");
			myRow.createCell(4).setCellValue("品名規格");
			myRow.createCell(5).setCellValue("數量");
			myRow.createCell(6).setCellValue("倉別/儲位");

			for (int i = 0; i < qrid.length; i++) {
				PreparedStatement ps = null;
				ps = conn.prepareStatement(strsql);
				ps.setString(1, qrid[i]);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					XSSFRow myRow1 = sheet.createRow(index);
					myRow1.createCell(0).setCellValue(rs.getString(1));
					myRow1.createCell(1).setCellValue(rs.getString(2));
					myRow1.createCell(2).setCellValue(rs.getString(3));
					myRow1.createCell(3).setCellValue(rs.getString(4));
					myRow1.createCell(4).setCellValue(rs.getString(5) + " // " + rs.getString(6));
					myRow1.createCell(5).setCellValue(rs.getString(7));
					myRow1.createCell(6).setCellValue(rs.getString(8) + " " + rs.getString(9) + "-" + rs.getString(10));
					index++;

				}
				rs.close();
				ps.close();
			}
			FileOutputStream out = new FileOutputStream(
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPcollect.xlsx");
			wbcoll.write(out);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPcollect.xlsx" };
	}

	public String[] logisticsselect(HttpServletRequest request, HttpServletResponse response) {
		String[] pa = null;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String date = getDay();
		try {
			// request.setCharacterEncoding("UTF-8");
			// response.setCharacterEncoding("text/html;charset=UTF-8");
			// PrintWriter out = response.getWriter();
			String[] qrid = request.getParameterValues("QR_id");

			Connection conn = new DataBaseConn().getConn();

			String path = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "sample"
					+ File.separator + "寄件單範本.xlsx";
			
			System.out.println(path);
			XSSFWorkbook wb = new XSSFWorkbook(path);
			for (int i = 0; i < qrid.length; i++) {

				String logis = "select logistics from iorders_master where qr_id = ? ";
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pa = new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPSent.xlsx" };

	}

	private void getEMS(String qrid, XSSFWorkbook wb, int i, Connection conn) {
		CopySheetStyle cs = new CopySheetStyle();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String date = getDay();
		try {

			XSSFSheet fromSheet = wb.getSheet("EMS");
			XSSFSheet toSheet = wb.createSheet("EMS" + i);
			cs.copySheet(wb, fromSheet, toSheet);
			String strsql = " select e.name,e.Address,e.Post,e.Phone,e.country,g.guestName,g.address,g.country,m.invoiceName,m.invoicePrice,d.qty,convert(nvarchar,getDate(),106),m.currency "
					+ " from idpmasteraccount e inner join iorders_master m on e.id = m.masteraccount "
					+ " inner join guest g on m.guestid = g.guestid "
					+ " inner join iorders_detail d on m.qr_id = d.qr_id " + " where m.qr_id= ?  ";

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
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPSent.xlsx");
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
			String strsql = " select e.name,e.Address,e.Post,e.Phone,e.country,g.guestName,g.address,g.country,m.invoiceName,m.invoicePrice,d.qty,convert(nvarchar,getDate(),106),m.currency "
					+ " from idpmasteraccount e inner join iorders_master m on e.id = m.masteraccount "
					+ " inner join guest g on m.guestid = g.guestid "
					+ " inner join iorders_detail d on m.qr_id = d.qr_id " + " where m.qr_id= ?  ";

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
					fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPSent.xlsx");
			wb.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 日出貨報表
	public String[] dailyBalanceSheetExcel()
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		String date = getDay();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("IDP日出貨報表");
		String strsql = "select s.shippingdate, s.qrId, d.SKU, d.productName, d.qty,"
				+ " r.country, d.owner, d.warehouse, m.staffName, s.comment, s.trackingCode"
				+ " from iorders_master as m inner join IDPshippinglog as s on m.QR_id = s.qrId"
				+ " inner join guest as r on m.guestid = r.guestid"
				+ " inner join iorders_detail as d on m.QR_id = d.QR_id"
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
		myRow.createCell(3).setCellValue("SKU");
		myRow.createCell(4).setCellValue("產品名稱");
		myRow.createCell(5).setCellValue("數量");
		myRow.createCell(6).setCellValue("國家");
		myRow.createCell(7).setCellValue("Owner");
		myRow.createCell(8).setCellValue("倉別");
		myRow.createCell(9).setCellValue("員工姓名");
		myRow.createCell(10).setCellValue("備註");
		myRow.createCell(11).setCellValue("追蹤碼");
		while (rs.next()) {
			myRow = sheet.createRow(index);
			myRow.createCell(0).setCellValue(rs.getString(1));
			myRow.createCell(1).setCellValue(rs.getString(2));
			myRow.createCell(2).setCellValue("大量出貨");
			myRow.createCell(3).setCellValue(rs.getString(3));
			myRow.createCell(4).setCellValue(rs.getString(4));
			myRow.createCell(5).setCellValue(rs.getString(5));
			myRow.createCell(6).setCellValue(rs.getString(6));
			myRow.createCell(7).setCellValue(rs.getString(7));
			myRow.createCell(8).setCellValue(rs.getString(8));
			myRow.createCell(9).setCellValue(rs.getString(9));
			myRow.createCell(10).setCellValue(rs.getString(10));
			myRow.createCell(11).setCellValue(rs.getString(11));

			index++;
		}

		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPDailyReport.xlsx");
		wb.write(out);
		rs.close();
		ps.close();
		conn.close();
		return new String[] { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPDailyReport.xlsx" };
	}

	public String[] 物流匯出格式() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("物流匯出報表");
		String strsql = "select s.shippingdate, s.type, s.qrId, d.SKU, d.productName, d.qty, m.currency ,d.weight_g,d.weight_oz,d.comment"
				+ " from iorders_master as m left join IDPshippinglog as s on m.QR_id = s.qrId"
				+ " inner join guest as r on m.guestid = r.guestid"
				+ " inner join iorders_detail as d on m.QR_id = d.QR_id" + " where m.orderStatus = N'揀貨中'";
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
		myRow.createCell(6).setCellValue("幣別"); // master currency
		myRow.createCell(7).setCellValue("寄件材積");
		myRow.createCell(8).setCellValue("寄件重量(克)");
		myRow.createCell(9).setCellValue("寄件重量(盎司)");
		myRow.createCell(10).setCellValue("Tracking No."); // trackingCode
		myRow.createCell(11).setCellValue("運費(TWD)");
		myRow.createCell(12).setCellValue("運費(USD)");
		myRow.createCell(13).setCellValue("備註");
		while (rs.next()) {
			myRow = sheet.createRow(index);
			myRow.createCell(0).setCellValue(rs.getString(1));
			myRow.createCell(1).setCellValue(rs.getString(2));
			myRow.createCell(2).setCellValue(rs.getString(3));
			myRow.createCell(3).setCellValue(rs.getString(4));
			myRow.createCell(4).setCellValue(rs.getString(5));
			myRow.createCell(5).setCellValue(rs.getString(6));
			myRow.createCell(6).setCellValue(rs.getString(7));
			myRow.createCell(7).setCellValue("");
			myRow.createCell(8).setCellValue(rs.getString(8));
			myRow.createCell(9).setCellValue(rs.getString(9));
			myRow.createCell(10).setCellValue("");
			myRow.createCell(11).setCellValue("");
			myRow.createCell(12).setCellValue("");
			myRow.createCell(13).setCellValue(rs.getString(10));

			index++;
		}

		String date = getDay();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FileOutputStream out = new FileOutputStream(
				fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + date + "IDPLogistic.xlsx");
		wb.write(out);
		wb.close();
		rs.close();
		ps.close();
		conn.close();
		String[] path = { fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator,
				date + "IDPLogistic.xlsx" };

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
