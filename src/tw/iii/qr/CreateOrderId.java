package tw.iii.qr;
// Welcome1.java

// A first program in Java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateOrderId {

	public static String generateQR_Id() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select Top 1  item, QR_id from  orders_master where QR_id not like 'C%03ebay%' order by item desc ";
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
		Boolean isQRidValid = false;
		Boolean isToday = false;
		if(QR_idFromDatabase!="" && QR_idFromDatabase!=null){
			isQRidValid =true;
			isToday = formatted.equals(QR_idFromDatabase.substring(0, 8));
		}
		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;

		String QR_id = "";
		if (isQRidValid) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))
					&& isToday) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			} else {
				QR_id = formatted + "03" + "ebay" + "001";
			}
		} else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}

	public String generateQR_Id04() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select Top 1  item, QR_id from  orders_master where QR_id  like '%04ebay%' order by item desc ";
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
		//System.out.println(formatted);
		Boolean isQRidValid = false;
		Boolean isToday = false;
		if(QR_idFromDatabase!="" && QR_idFromDatabase!=null){
			isQRidValid =true;
			isToday = formatted.equals(QR_idFromDatabase.substring(0, 8));
		}

		DecimalFormat df = new DecimalFormat("000");
		int serailNumber = 1;

		// System.out.println(QR_idFromDatabase.substring(14,
		// QR_idFromDatabase.length()));
		String QR_id = "";
		if (isQRidValid) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))
					&& isToday) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "04" + "ebay" + df.format(serailNumber);
			} else {
				QR_id = formatted + "04" + "ebay" + "001";
			}
		} else {
			QR_id = formatted + "04" + "ebay" + "001";
		}
		//System.out.println(QR_id);
		return QR_id;
	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public String dateNow() {
		// Calendar c=Calendar.getInstance();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		String formatted = formatter.format(today);
		System.out.println(formatted);
		return formatted;
	}

	public static String generateCQR_Id() throws Exception {
		String QR_id = "";
		try {
			String strsql = " select  top 1 combinesku from  orders_master order by combinesku desc  ";
			Connection conn = new DataBaseConn().getConn();
			PreparedStatement ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();

			String QR_idFromDatabase = null;
			while (rs.next()) {
				QR_idFromDatabase = rs.getString(1);
			}
			
			java.util.Date date = Calendar.getInstance().getTime();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String formatted = formatter.format(date);
			System.out.println(formatted);
			Boolean isQRidValid = false;
			Boolean isToday = false;
			if(QR_idFromDatabase!="" && QR_idFromDatabase!=null){
				isQRidValid =true;
				isToday = formatted.equals(QR_idFromDatabase.substring(1, 9));
			}
		

			DecimalFormat df = new DecimalFormat("000");
			int serailNumber = 1;
			
				
			
			
			if (isQRidValid) {
				if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(15, QR_idFromDatabase.length()))
						&& isToday) {
					int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(15, QR_idFromDatabase.length()));
					serailNumber = getSerailNumber + 1;
					QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
				} else {
					QR_id = formatted + "03" + "ebay" + "001";
				}
			} else {
				QR_id = formatted + "03" + "ebay" + "001";
			}
			System.out.println(QR_id);
			return QR_id;
		} catch (

		Exception e) {
			e.printStackTrace();
			throw new Exception("創立訂單號失敗");
		}

	}

}
