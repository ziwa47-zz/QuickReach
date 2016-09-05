package tw.iii.qr;
// Welcome1.java

// A first program in Java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import tw.iii.qr.order.COrderDetail;

import java.util.Calendar;

public class autoInsertData {
	public static void main(String args[]) throws Exception // the entry point
															// of program
	{
		Connection conn = new DataBaseConn().getConn();
		if(!isNullorEmpty(conn.toString()))
			System.out.println("conn ok");
		
		int forMin = 1;
		int forMax = 50;
		//parameter : conn, for loop i= ? , i <= ?)
			insertToMaster(conn, forMin, forMax);
			insertToDetail(conn, forMin, forMax);
			insertToGuestInfo(conn, forMin, forMax);
			insertToRecieverInfo(conn, forMin, forMax);
		//dateNow();
//		generateQR_Id();
//		getSKU("2",conn);
		conn.close();
	}
	
	public static String generateQR_Id() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select item, QR_id from quickreach.orders_master where QR_id like '%ebay%' order by item desc limit 0,1 ";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		String QR_idFromDatabase = null;
		while (rs.next()) {
			QR_idFromDatabase = rs.getString(2);
		}
		
		java.util.Date date = Calendar.getInstance().getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String formatted = formatter.format(date);
		System.out.println(formatted);

		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;
		
		//System.out.println(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			}
			
		} else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}
	
	public static String generateQR_IdforDetail() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select item, QR_id from quickreach.orders_detail where QR_id like '%ebay%' order by item desc limit 0,1 ";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		String QR_idFromDatabase = null;
		while (rs.next()) {
			QR_idFromDatabase = rs.getString(2);
		}
		
		java.util.Date date = Calendar.getInstance().getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String formatted = formatter.format(date);
		System.out.println(formatted);

		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;
		
		//System.out.println(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			}
			
		} else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}
	
	public static String generateQR_IdforGuest() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select item, QR_id from quickreach.orders_guestinfo where QR_id like '%ebay%' order by item desc limit 0,1 ";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		String QR_idFromDatabase = null;
		while (rs.next()) {
			QR_idFromDatabase = rs.getString(2);
		}
		
		java.util.Date date = Calendar.getInstance().getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String formatted = formatter.format(date);
		System.out.println(formatted);

		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;
		
		//System.out.println(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			}
			
		} else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}
	
	public static String generateQR_IdforReciever() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select item, QR_id from quickreach.order_recieverinfo where QR_id like '%ebay%' order by item desc limit 0,1 ";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		String QR_idFromDatabase = null;
		while (rs.next()) {
			QR_idFromDatabase = rs.getString(2);
		}
		
		java.util.Date date = Calendar.getInstance().getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String formatted = formatter.format(date);
		System.out.println(formatted);

		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;
		
		//System.out.println(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			}
			
		} else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}

	public static void insertToRecieverInfo(Connection conn,int forMin, int forMax)
			throws IllegalAccessException, ClassNotFoundException, Exception {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.order_recieverinfo"
					+ " (QR_id, order_id, recieverFirstName, recieverLastName, tel1, tel2, address, country, postCode)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, generateQR_IdforReciever());
			ps.setInt(2, i);
			ps.setString(3, randomDownCaseString(6));
			ps.setString(4, randomDownCaseString(9));
			ps.setInt(5, randomNumber(9));
			ps.setInt(6, randomNumber(9));
			ps.setString(7, randomDownCaseString(20));
			ps.setString(8, randomDownCaseString(3));
			ps.setInt(9, randomNumber(4));
			
			ps.executeUpdate();
			
		}
	}

	public static void insertToGuestInfo(Connection conn, int forMin, int forMax)  throws IllegalAccessException, ClassNotFoundException, Exception {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_guestinfo (QR_id, order_id, guestFirstName, guestLastName, guestAccount, email)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, generateQR_IdforGuest());
			ps.setInt(2, i);
			ps.setString(3, randomDownCaseString(6));
			ps.setString(4, randomDownCaseString(9));
			ps.setString(5, randomDownCaseString(6));
			ps.setString(6, randomDownCaseString(9)+"@gmail.com");
			
			ps.executeUpdate();
			
		}
	}

	public static void insertToDetail(Connection conn, int forMin, int forMax) throws IllegalAccessException, ClassNotFoundException, Exception {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_detail (QR_id, order_id, SKU, productName, invoiceName, price,"
					+ " invoicePrice, qty, warehouse, comment, owner) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, generateQR_IdforDetail());
			ps.setInt(2, i);
			ps.setString(3, randomStringMixInt(6, 6));
			ps.setString(4, randomDownCaseString(12));
			ps.setString(5, randomDownCaseString(6));
			ps.setDouble(6, randomPrice(10000));
			ps.setDouble(7, randomPrice(100));
			ps.setInt(8, randomNumber(50));
			ps.setString(9, randomWarehouse());
			ps.setString(10, randomDownCaseString(9));
			ps.setString(11, randomOwner());
			
			ps.executeUpdate();
			System.out.println(strsql);
		}
	}

	public static void insertToMaster(Connection conn, int forMin, int forMax) 
			throws IllegalAccessException, ClassNotFoundException, Exception {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_master(QR_id, order_id, outsideCode, platform,"
					+ " company, eBayAccount, guestAccount, orderDate, payDate, logisticsId, logistics,"
					+ " orderStatus, paypal_id, payment, shippingDate, shippingFees,"
					+ " ebayFees, paypalFees, totalPrice)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, generateQR_Id()); //QR_id
			ps.setInt(2, i); //order_id
			ps.setString(3, randomStringMixInt(5, 5)); //outsideCode
			ps.setString(4, randomPlatform()); //platform
			ps.setString(5, randomDownCaseString(7)); //company
			ps.setString(6, randomEbayAccoount()); //eBayAccount
			ps.setString(7, randomDownCaseString(8)); //guestAccount
			ps.setString(8, randomDate(12,1,28)); //orderDate
			ps.setString(9, randomDate(12,1,28)); //payDate
			ps.setInt(10, randomNumber(10000)); //logisticsId
			
			ps.setString(11, randomLogistics()); //logistics
			ps.setString(12, randomOrderStatus()); //orderStatus
			ps.setInt(13, randomNumber(10000)); //paypal_id
			ps.setDouble(14, randomPrice(100)); //payment
			ps.setString(15, randomDate(12,1,28)); //shippingDate
			ps.setDouble(16, randomPrice(100)); //shippingFees
			ps.setDouble(17, randomPrice(10));  // ebayFees
			ps.setDouble(18, randomPrice(10));  //paypalFees
			ps.setDouble(19, randomPrice(1000)); //totalPrice
			ps.executeUpdate();
			
			
		}
	}

	public static boolean isNullorEmpty(String s) {

		if(s == null || s.length() == 0)
			return true;

		return false;
	}
	
	public void randomGo() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		Connection conn = new DataBaseConn().getConn();
		if(!isNullorEmpty(conn.toString()))
			System.out.println("conn ok");
		for(int i = 1; i <= 10; i++) {
			String strsql = "INSERT INTO quickreach.orders_master(order_id, QR_id, outsideCode, platform,"
					+ " company, eBayAccount, guestAccount, orderDate, payDate, logisticsId, logistics,"
					+ " orderStatus, paypal_id, payment, shippingDate, shippingFees, refundFees, otherFees,"
					+ " ebayFees, paypalFees, insurance, insurancePrice, insuranceTotal, currency, weight,"
					+ " totalWeight, FedexService, FedexPacking, staffName, size, totalPrice, trackingCode,"
					+ " comment, packageFees, ebayNO, ebayItemNO, ebayPrice, ebayTotal, paypalmentId,"
					+ " paypalTotal, paypalNet)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setInt(1, i);
		}
	}
	
	public static String randomDate(int mMax, int mMin, int dMax) throws ParseException {
		
		Integer MM =(int)(Math.random() *(mMax - mMin + 1) + mMin);
		Integer dd =(int)(Math.random() *(dMax - mMin + 1) + mMin);
		String input = "2016-"+ MM.toString() + "-" + dd.toString();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date t = null;
		t = formatter.parse(input);
		String formatted = formatter.format(t);
		System.out.println(formatted);
		  
		return formatted;
	}
	
	public static String dateNow() {
		//Calendar c=Calendar.getInstance();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		String formatted = formatter.format(today);
		System.out.println(formatted);
		return formatted;
	}
	
	private static void getNowFormatter(Calendar c) {
		 int[] a={c.get(Calendar.YEAR),
	             c.get(Calendar.MONTH),
	             c.get(Calendar.DAY_OF_MONTH),
	             c.get(Calendar.HOUR_OF_DAY),
	             c.get(Calendar.MINUTE),
	             c.get(Calendar.SECOND)};		
	}

	public static Float randomPrice(int pMax){
		
		
		Random random = new Random();
		Integer price1 = random.nextInt(pMax);
		Float price2 = random.nextFloat();
		Float price = price1 + price2;
		System.out.println(price);
		
		return price;
		
	}
	
	public static String randomDownCaseString(int sMax) {
		
		//Random r = new Random();
		//int upCase = r.nextInt(26)+65;//�o��65-90���H����
		//int downCase = r.nextInt(26)+97;//�o��97-122���H����
		//String up =String.valueOf((char)upCase);//�o��A-Z
		//String down =String.valueOf((char)downCase);
		String str = "";
		for(int i=0; i<= sMax; i++){
			Random r = new Random();
			int downCase = r.nextInt(26)+97;
			String down =String.valueOf((char)downCase);
			str += down;
		}
		System.out.println(str);
		return str;
	}
	
	public static String randomStringMixInt(int sMax, int iMax) {
		
		String str = "";
		for(int i=0; i<= sMax; i++){
			Random r = new Random();
			int upCase = r.nextInt(26)+65;
			String up =String.valueOf((char)upCase);
			str += up;
		}
		for(int i=0; i<= iMax; i++){
			Random r = new Random();
			Integer number = r.nextInt(10);
			str += number;
		}
		System.out.println(str);
		return str;
	}
	
	public static Integer randomNumber(int iMax) {
		
		Random r = new Random();
		Integer number = r.nextInt(iMax);
		return number;
		
	}
	
	public static String randomPlatform() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(1);
		if(i == 0)
			str = "ebay";
		if(i == 1)
			str = "amazon";
		return str;
	}
	
	public static String randomEbayAccoount() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(5);
		if(i == 0)
			str = "comenwin0903";
		if(i == 1)
			str = "cyclistbike";
		if(i == 2)
			str = "huangbowei";
		if(i == 3)
			str = "igrocery";
		if(i == 4)
			str = "magicbike";
		return str;
	}
	
	public static String randomLogistics() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(3);
		if(i == 0)
			str = "DHL";
		if(i == 1)
			str = "EMS";
		if(i == 2)
			str = "Fedex";
		return str;
	}
	
	public static String randomOrderStatus() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(3);
		if(i == 0)
			str = "待處理";
		if(i == 1)
			str = "處理中";
		if(i == 2)
			str = "揀貨中";
		if(i == 3)
			str = "已完成";
		return str;
	}
	
	public static String randomWarehouse() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(2);
		if(i == 0)
			str = "KHH";
		if(i == 1)
			str = "USA";
		return str;
	}
	
	public static String randomOwner() {
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(2);
		if(i == 0)
			str = "William";
		if(i == 1)
			str = "Eric";
		return str;
	}
}
