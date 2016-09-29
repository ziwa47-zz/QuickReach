package tw.iii.qr.order;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.CProduct;
import tw.iii.qr.stock.CProductFactory;

public class DayliBalanceSheetFactory extends COrders {

	public DayliBalanceSheetFactory() {

	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public LinkedList<COrders> dayliBalanceSheet() throws ClassNotFoundException, Exception {
		
		
		
		
		
		Connection conn = new DataBaseConn().getConn();

		String strSql = "select m.orderDate, m.QR_id, r.tel1, m.shippingFees,"
				+ " m.packageFees, m.comment, m.totalWeight,m.ebayNO,m.ebayItemNO, m.eBayAccount,r.country, m.currency,"
				+ " m.ebayprice,m.ebayTotal, m.payDate, m.paypalmentId, m.totalPrice,m.paypalFees,"
				+ " m.paypalNet, m.shippingDate, m.ebayFees, m.order_id,"
				+ " d.sku, d.productName, d.qty, d.owner, d.price,"
				+ " r.country"
				+ " FROM  orders_master as m inner join"
				+ " orders_detail as d  on m.QR_id = d.QR_id left join"
				+ " order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " where m.orderstatus = N'待處理' order by m.QR_id"
				+ " order by payDate desc";
		System.out.println(strSql);
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		LinkedList<COrderDetail> orderDetails = new LinkedList<COrderDetail>();
		COrders order = new COrders();
		while (rs.next()) {
			order = new COrders();
			order.COrderMaster.setOrderDate(rs.getDate(1));
			order.COrderMaster.setQR_id(rs.getString(2));
			String strSql2 = "SELECT SKU, productName, owner, qty, price"
					+ " FROM  orders_detail"
					+ " where QR_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(2));
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while(rs2.next()){
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				COrderDetail.setOwner(rs2.getString(3));
				COrderDetail.setQty(rs2.getInt(4));
				COrderDetail.setPrice(rs.getDouble(5));
				orderDetails.add(COrderDetail);
			}
			order.setCOrderDetail(orderDetails);
			order.COrderGuestInfo.setTel1(rs.getString(3));
			order.COrderMaster.setShippingFees(rs.getDouble(4));
			order.COrderMaster.setPackageFees(rs.getDouble(5));
			order.COrderMaster.setComment(rs.getString(6));
			//order.COrderDetail.setOwner(rs.getString(9));
			order.COrderMaster.setTotalWeight(rs.getDouble(7));
			order.COrderMaster.setEbayNO(rs.getString(8));
			order.COrderMaster.setEbayItemNO(rs.getString(9));
			//order.COrderDetail.setQty(rs.getInt(13));

			order.COrderMaster.setEbayAccount(rs.getString(10));
			order.COrderGuestInfo.setCountry(rs.getString(11));
			order.COrderMaster.setCurrency(rs.getString(12));
			order.COrderMaster.setEbayPrice(rs.getDouble(13));
			order.COrderMaster.setEbayTotal(rs.getDouble(14));
			order.COrderMaster.setPayDate(rs.getDate(15));
			order.COrderMaster.setPaypalmentId(rs.getString(16));
			order.COrderMaster.setTotalPrice(rs.getDouble(17));
			order.COrderMaster.setPaypalFees(rs.getDouble(18));
			order.COrderMaster.setPaypalNet(rs.getDouble(19));
			//order.COrderDetail.setPrice(rs.getDouble(24));
			order.COrderMaster.setShippingDate(rs.getDate(20));
			order.COrderMaster.setEbayFees(rs.getDouble(21));
			order.COrderMaster.setOrder_id(rs.getString(22));
			
			order.COrderDetailSingle.setSKU(rs.getString(23));
			order.COrderDetailSingle.setProductName(rs.getString(24));
			order.COrderDetailSingle.setQty(rs.getInt(25));
			order.COrderDetailSingle.setOwner(rs.getString(26));
			order.COrderDetailSingle.setPrice(rs.getDouble(27));
			
			order.COrderReciever.setCountry(rs.getString(28));
			
			CProductFactory myCProductFactory = new CProductFactory();

			order.COrderMaster.setPurchaseCost(myCProductFactory.isBundle(rs.getString(23)));

			orderList.add(order);
		}
		rs.close();
		ps.close();
		conn.close();
		return orderList;

	}

}
