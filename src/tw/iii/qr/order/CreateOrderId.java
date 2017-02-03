package tw.iii.qr.order;
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

import tw.iii.qr.DataBaseConn;

import java.util.Calendar;

public class CreateOrderId {

	public static String generateQR_Id() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select Top 1  item, QR_id from  orders_master where QR_id like '%03ebay%' order by item desc ";
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

		
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			} else {
				QR_id = formatted + "03" + "ebay" + "001";
			}
		}else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}

	public String generateQR_Id04() throws IllegalAccessException, ClassNotFoundException, Exception {

		String strsql = " select Top 1  item, QR_id from  orders_master where QR_id like '%04ebay%' order by item desc ";
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

		// System.out.println(QR_idFromDatabase.substring(14,
		// QR_idFromDatabase.length()));
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(14, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "04" + "ebay" + df.format(serailNumber);
			} else {
				QR_id = formatted + "04" + "ebay" + "001";
			}
		}
		System.out.println(QR_id);
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

	public static String generateCQR_Id() throws Exception{
		String strsql = " select Top 1  item, combinesku from  orders_master   order by combinesku desc ";
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

		
		String QR_id = "";
		if (QR_idFromDatabase != null) {
			if (serailNumber <= Integer.valueOf(QR_idFromDatabase.substring(15, QR_idFromDatabase.length()))) {
				int getSerailNumber = Integer.valueOf(QR_idFromDatabase.substring(15, QR_idFromDatabase.length()));
				serailNumber = getSerailNumber + 1;
				QR_id = formatted + "03" + "ebay" + df.format(serailNumber);
			} else {
				QR_id = formatted + "03" + "ebay" + "001";
			}
		}else {
			QR_id = formatted + "03" + "ebay" + "001";
		}
		System.out.println(QR_id);
		return QR_id;
	}

}
