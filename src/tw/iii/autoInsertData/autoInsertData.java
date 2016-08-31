package tw.iii.autoInsertData;
// Welcome1.java

// A first program in Java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import tw.iii.qr.DataBaseConn;

public class autoInsertData {
	public static void main(String args[]) throws Exception // the entry point
															// of program
	{
		Connection conn = new DataBaseConn().getConn();
		if(!isNullorEmpty(conn.toString()))
			System.out.println("conn ok");
		
		int forMin = 10;
		int forMax = 20;
		//parameter : conn, for loop i= ? , i <= ?)
		for(int i = forMin; i <= forMax; i++) {
			insertToMaster(conn, forMin, forMax);
			insertToDetail(conn, forMin, forMax);
			insertToGuestInfo(conn, forMin, forMax);
			insertToRecieverInfo(conn, forMin, forMax);
		}
		conn.close();
	}

	public static void insertToRecieverInfo(Connection conn,int forMin, int forMax)
			throws SQLException {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.order_recieverinfo"
					+ " (order_id, recieverFirstName, recieverLastName, tel1, tel2, address, country, postCode)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setInt(1, i);
			ps.setString(2, randomDownCaseString(6));
			ps.setString(3, randomDownCaseString(9));
			ps.setInt(4, randomNumber(9));
			ps.setInt(5, randomNumber(9));
			ps.setString(6, randomDownCaseString(20));
			ps.setString(7, randomDownCaseString(3));
			ps.setInt(8, randomNumber(4));
			ps.executeUpdate();
			
		}
	}

	public static void insertToGuestInfo(Connection conn, int forMin, int forMax)  throws SQLException {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_guestinfo (order_id, guestFirstName, guestLastName, guestAccount, email)"
					+ " VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setInt(1, i);
			ps.setString(2, randomDownCaseString(6));
			ps.setString(3, randomDownCaseString(9));
			ps.setString(4, randomDownCaseString(6));
			ps.setString(5, randomDownCaseString(9)+"@gmail.com");
			ps.executeUpdate();
			
		}
	}

	public static void insertToDetail(Connection conn, int forMin, int forMax) throws SQLException {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_detail (order_id, SKU, productName, invoiceName, price,"
					+ " invoicePrice, qty, warehouse, comment, owner) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setInt(1, i);
			ps.setString(2, randomStringMixInt(6, 6));
			ps.setString(3, randomDownCaseString(12));
			ps.setString(4, randomDownCaseString(6));
			ps.setDouble(5, randomPrice(10000));
			ps.setDouble(6, randomPrice(100));
			ps.setInt(7, randomNumber(50));
			ps.setString(8, randomWarehouse());
			ps.setString(9, randomDownCaseString(9));
			ps.setString(10, randomOwner());
			
			ps.executeUpdate();
			System.out.println(strsql);
		}
	}

	public static void insertToMaster(Connection conn, int forMin, int forMax) 
			throws SQLException, ParseException {
		for(int i = forMin; i <= forMax; i++) {
			String strsql = "INSERT INTO quickreach.orders_master(order_id, QR_id, outsideCode, platform,"
					+ " company, eBayAccount, guestAccount, orderDate, payDate, logisticsId, logistics,"
					+ " orderStatus, paypal_id, payment, shippingDate, shippingFees)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setInt(1, i); //order_id
			ps.setInt(2, randomNumber(100)); //QR_id
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
		Integer i = r.nextInt(4);
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
		Integer i = r.nextInt(2);
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
			str = "�ݳB�z";
		if(i == 1)
			str = "�B�z��";
		if(i == 2)
			str = "�z�f��";
		if(i == 3)
			str = "�w����";
		return str;
	}
	
	public static String randomWarehouse() {
		
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(1);
		if(i == 0)
			str = "KH";
		if(i == 1)
			str = "US";
		return str;
	}
	
	public static String randomOwner() {
		String str = "";
		Random r = new Random();
		Integer i = r.nextInt(1);
		if(i == 0)
			str = "William";
		if(i == 1)
			str = "Eric";
		return str;
	}
}
