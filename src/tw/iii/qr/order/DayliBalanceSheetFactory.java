package tw.iii.qr.order;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.CProduct;

public class DayliBalanceSheetFactory extends COrders {

	public DayliBalanceSheetFactory() {

	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public LinkedList<COrders> dayliBalanceSheet(HttpServletRequest request, HttpServletResponse response,
			Connection conn) throws ClassNotFoundException, Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		conn = new DataBaseConn().getConn();

		String strSql = "select a.orderDate, a.order_id,b.SKU, b.productName,c.tel1, a.shippingFees,"
				+ " a.packageFees, a.comment,b.owner, a.totalWeight,a.ebayNO,a.ebayItemNO, b.qty, a.eBayAccount,c.country, a.currency,"
				+ " a.ebayprice,a.ebayTotal, a.payDate, a.paypalmentId, a.paypalTotal,a.paypalFees,"
				+ " a.paypalNet, b.price, a.shippingDate, a.ebayFees"
				+ " FROM quickreach.orders_master as a inner join quickreach.orders_detail as b inner join quickreach.orders_guestinfo as c "
				+ " where a.order_id=b.order_id and b.order_id=c.order_id and a.orderstatus = '待處理'";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		LinkedList<COrderDetail> orderDetails = new LinkedList<COrderDetail>();
		COrders order = new COrders();
		while (rs.next()) {
			System.out.println("ya");
			order = new COrders();
			order.COrderMaster.setOrderDate(rs.getDate(1));
			order.COrderMaster.setOrder_id(rs.getString(2));
			String strSql2 = "SELECT SKU, productName, owner, qty"
					+ " FROM quickreach.orders_detail"
					+ " where order_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(1));
			System.out.println(strSql2);
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while(rs2.next()){
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				orderDetails.add(COrderDetail);
			}
			order.setCOrderDetail(orderDetails);
			order.COrderGuestInfo.setTel1(rs.getString(5));
			order.COrderMaster.setShippingFees(rs.getDouble(6));
			order.COrderMaster.setPackageFees(rs.getDouble(7));
			order.COrderMaster.setComment(rs.getString(8));
			//order.COrderDetail.setOwner(rs.getString(9));
			order.COrderMaster.setTotalWeight(rs.getDouble(10));
			order.COrderMaster.setEbayNO(rs.getString(11));
			order.COrderMaster.setEbayItemNO(rs.getString(12));
			//order.COrderDetail.setQty(rs.getInt(13));

			order.COrderMaster.setEbayAccount(rs.getString(14));
			order.COrderGuestInfo.setCountry(rs.getString(15));
			order.COrderMaster.setCurrency(rs.getString(16));
			order.COrderMaster.setEbayPrice(rs.getDouble(17));
			order.COrderMaster.setEbayTotal(rs.getDouble(18));
			order.COrderMaster.setPayDate(rs.getDate(19));
			order.COrderMaster.setPaypalmentId(rs.getString(20));
			order.COrderMaster.setPaypalTotal(rs.getDouble(21));
			order.COrderMaster.setPaypalFees(rs.getDouble(22));
			order.COrderMaster.setPaypalNet(rs.getDouble(23));
			//order.COrderDetail.setPrice(rs.getDouble(24));
			order.COrderMaster.setShippingDate(rs.getDate(25));
			order.COrderMaster.setEbayFees(rs.getDouble(26));

			System.out.println(order);
			orderList.add(order);
		}
		System.out.println(orderList);
		rs.close();
		ps.close();
		conn.close();
		return orderList;

	}

}
