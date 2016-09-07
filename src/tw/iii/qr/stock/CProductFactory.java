package tw.iii.qr.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CProductFactory extends CProduct {

	public CProductFactory() {

	}

	public LinkedList<CProduct> searchProduct(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = " select distinct sku,brand,subbrand,p_name,spec,color from  product inner join  storage using (sku) where '1' = '1' ";
		int param = 1;

		PreparedStatement ps = null;
		if (!isNullorEmpty(request.getParameter("pname"))) {
			strsql += " and p_name like ? ";
		}
		if (!isNullorEmpty(request.getParameter("brand"))) {
			strsql += " and brand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("subbrand"))) {
			strsql += " and subbrand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("sku"))) {
			strsql += " and sku like ? ";
		}
		if (!isNullorEmpty(request.getParameter("spec"))) {
			strsql += " and spec like ? ";
		}
		if (!isNullorEmpty(request.getParameter("color"))) {
			strsql += " and color like ? ";
		}
		if (!isNullorEmpty(request.getParameter("date1"))) {
			strsql += " and createDate  >= ? ";
		}
		if (!isNullorEmpty(request.getParameter("date2"))) {
			strsql += " and createDate  <= ? ";
		}
		if (!isNullorEmpty(request.getParameter("location1")) && !isNullorEmpty(request.getParameter("location2"))) {
			strsql += " and warehouseposition  <= ? ";
			strsql += " and warehouseposition  >= ? ";
		}
		System.out.println(strsql);
		ps = conn.prepareStatement(strsql);
		if (!isNullorEmpty(request.getParameter("pname"))) {
			ps.setString(param, "%" + request.getParameter("pname") + "%");
			param++;

		}
		if (!isNullorEmpty(request.getParameter("brand"))) {
			ps.setString(param, "%" + request.getParameter("brand") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("subbrand"))) {
			ps.setString(param, "%" + request.getParameter("subbrand") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("sku"))) {
			ps.setString(param, "%" + request.getParameter("sku") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("spec"))) {
			ps.setString(param, "%" + request.getParameter("spec") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("color"))) {
			ps.setString(param, "%" + request.getParameter("color") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("date1"))) {
			ps.setString(param, request.getParameter("date1"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("date2"))) {
			ps.setString(param, request.getParameter("date2"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("location1")) && !isNullorEmpty(request.getParameter("location2"))) {
			ps.setString(param, request.getParameter("location1"));
			param++;
			ps.setString(param, request.getParameter("location2"));
			param++;
		}
		ResultSet rs = ps.executeQuery();
		LinkedList<CProduct> productall = new LinkedList<CProduct>();
		CProduct product = new CProduct();
		int no = 1;

		while (rs.next()) {
			// sku,brand,subbrand,p_name,spec,color
			product = new CProduct();
			product.setItem(String.valueOf(no++));
			product.setSKU(rs.getString(1));// sku
			product.setBrand(rs.getString(2));// brand
			product.setSubBrand(rs.getString(3));// subbrand
			product.setP_name(rs.getString(4));// p_name
			product.setSpec(rs.getString(5));// spec
			product.setColor(rs.getString(6));// color
			productall.add(product);
		}

		System.out.print(product);

		return productall;

	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public CProduct searchDetail(String sku, Connection conn) throws SQLException {

		String strsql = "SELECT  SKU ,  owner ,  productType ,  brand ,  subBrand ,"
				+ "  EAN ,  productCode ,  P_name ,  spec ,  color ,"
				+ "  securedQty ,  cost ,  comment ,  checkupdate ,  added , "
				+ "  weight , packageMatrial,  vilumetricWeight ,  createDate  FROM   product "
				+ " where 1 = 1 and sku = ? ";

		PreparedStatement ps = null;

		ps = conn.prepareStatement(strsql);

		ps.setString(1, sku);

		ResultSet rs = ps.executeQuery();
		CProduct product = new CProduct();
		while (rs.next()) {
			product.setSKU(rs.getString(1)); // sku
			product.setOwner(rs.getString(2)); // owner
			product.setProductType(rs.getString(3)); // productType
			product.setBrand(rs.getString(4)); // 廠牌
			product.setSubBrand(rs.getString(5)); // 副廠牌
			product.setEAN(rs.getString(6)); // ean 國際標準碼
			product.setProductCode(rs.getString(7)); // productCode
			product.setP_name(rs.getString(8)); // 品名
			product.setSpec(rs.getString(9)); // 規格
			product.setColor(rs.getString(10)); // 顏色
			product.setSecuredQty(rs.getInt(11)); // 安全庫存
			product.setCost(rs.getDouble(12)); // 成本
			product.setComment(rs.getString(13));// 備註
			product.setCheckupdate(rs.getDate(14)); // 更新紀錄
			product.setAdded(rs.getString(15));
			product.setWeight(rs.getDouble(16)); // 重量
			product.setPackageMatrial(rs.getString(17));// 包材
			product.setVilumetricWeight(rs.getDouble(18));// 材積重
			product.setCreateDate(rs.getDate(19));// 建檔日
		}

		return product;

	}

	public void updateProduct(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = "UPDATE  product SET " 
				+ "owner  = ?," + "productType  = ?," + "brand  = ?,"
				+ "subBrand  = ?," + "EAN  = ?," + "productCode  = ?," + "P_name  = ?," + "spec  = ?," + "color  = ?,"
				+ "cost  = ?," + "comment  = ?," + "checkupdate  = ?," + "added  = ?," + "weight  = ?,"
				+ "packageMatrial  = ?," + "vilumetricWeight  = ? " + " WHERE  sku  = ? ";
		CProduct cp = new CProduct();
		cp.setSKU(request.getParameter("sku"));
		cp.setOwner(request.getParameter("owner"));
		cp.setProductType(request.getParameter("producttype"));
		cp.setBrand(request.getParameter("brand"));
		cp.setSubBrand(request.getParameter("subbrand"));
		cp.setEAN(request.getParameter("ean"));
		cp.setProductCode(request.getParameter("productcode"));
		cp.setP_name(request.getParameter("pname"));
		cp.setSpec(request.getParameter("spec"));
		cp.setColor(request.getParameter("color"));
		cp.setCost(Double.valueOf(request.getParameter("cost")));
		cp.setComment(request.getParameter("comment"));
		cp.setCheckupdate(Date.valueOf(request.getParameter("checkupdate")));
		cp.setAdded(request.getParameter("added"));
		cp.setWeight(Double.valueOf(request.getParameter("weight")));
		cp.setPackageMatrial(request.getParameter("package"));
		cp.setVilumetricWeight(Double.valueOf(request.getParameter("vilu")));
		
		PreparedStatement ps = null;
		System.out.print("rq"+request.getParameter("checkupdate"));
		ps = conn.prepareStatement(strsql);

		ps.setString(1, cp.getOwner()); //owner
		ps.setString(2, cp.getProductType()); // productType
		ps.setString(3, cp.getBrand()); //brand
		ps.setString(4, cp.getSubBrand()); //subbrand
		ps.setString(5, cp.getEAN()); //Ean
		ps.setString(6, cp.getProductCode()); //productcode
		ps.setString(7, cp.getP_name()); //pname
		ps.setString(8, cp.getSpec()); //spec
		ps.setString(9, cp.getColor()); //color
		ps.setDouble(10, cp.getCost()); //cost
		ps.setString(11, cp.getComment()); // comment
		ps.setDate(12, cp.getCheckupdate()); //checkupdate
		ps.setString(13, cp.getAdded()); //added
		ps.setDouble(14, cp.getWeight()); //weight
		ps.setString(15, cp.getPackageMatrial()); //package
		ps.setDouble(16, cp.getVilumetricWeight()); // vilu
		ps.setString(17,cp.getSKU());
		int i = ps.executeUpdate();
		
	}

}
