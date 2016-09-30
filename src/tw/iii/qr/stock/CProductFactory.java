package tw.iii.qr.stock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.filechooser.FileSystemView;

import org.apache.jasper.tagplugins.jstl.core.Otherwise;

import tw.iii.qr.DataBaseConn;

public class CProductFactory extends CProduct {

	public CProductFactory() {

	}
	
	

	FileSystemView fsv = FileSystemView.getFileSystemView(); 
	String path = fsv.getDefaultDirectory().getAbsolutePath()+"\\QuickReach\\pics\\";
	
	
	
	public LinkedList<CProduct> searchProduct(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = " select distinct sku,brand,subbrand,p_name,spec,color from  product  where '1' = '1' ";
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
				+ "  weight , packageMatrial,  vilumetricWeight ,  createDate, volume, picturePath  FROM   product "
				+ " where 1 = 1 and sku = ? ";

		PreparedStatement ps = null;

		ps = conn.prepareStatement(strsql);

		ps.setString(1, sku);

		ResultSet rs = ps.executeQuery();
		CProduct product = new CProduct();
		while (rs.next()) {
			product.setSKU(rs.getString(1)); // sku
			product.setOwner(rs.getString(2)); // owner
			
			
			
			System.out.println("a3s2d3sdsdddddddddddda:"+rs.getString(21));
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
			product.setVolume(rs.getString(20));// 材積
			product.setPicturePath(rs.getString(21)); // 路徑
		}

		return product;

	}

	public void updateProduct(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = "UPDATE  product SET " + "owner  = ?," + "productType  = ?," + "brand  = ?," + "subBrand  = ?,"
				+ "EAN  = ?," + "productCode  = ?," + "P_name  = ?," + "spec  = ?," + "color  = ?," + "cost  = ?,"
				+ "comment  = ?," + "checkupdate  = ?," + "added  = ?," + "weight  = ?," + "packageMatrial  = ?,"
				+ "vilumetricWeight  = ? ," + " volume = ? ,"+"securedQty = ? ,"+"picturePath = ? " + " WHERE  sku  = ? ";
		CProduct cp = new CProduct();

		cp.setOwner(request.getParameter("owner"));
		

		System.out.println("request.getParameter('productType')"+request.getParameter("productType"));
		String productType ="" ;
		switch(request.getParameter("productType")){
	
		case "1":
			 productType = "單一商品";
			break;
		case "2":
			 productType = "清倉類";
			break;
		case "3":
			 productType = "調貨類";
			break;
		case "4":
			 productType = "組合商品";
			break;
			
		default:
			productType = "";
			
		}
		System.out.println("a3s2d3a:"+request.getParameter("picturePath"));
		cp.setProductType(productType);
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
		cp.setVolume(request.getParameter("volume"));
		cp.setSKU(request.getParameter("sku"));
		cp.setSecuredQty(Integer.valueOf(request.getParameter("securedqty")));
		cp.setPicturePath(request.getParameter("picturePath"));

		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);

		ps.setString(1, cp.getOwner()); // owner
		ps.setString(2, cp.getProductType()); // productType
		ps.setString(3, cp.getBrand()); // brand
		ps.setString(4, cp.getSubBrand()); // subbrand
		ps.setString(5, cp.getEAN()); // Ean
		ps.setString(6, cp.getProductCode()); // productcode
		ps.setString(7, cp.getP_name()); // pname
		ps.setString(8, cp.getSpec()); // spec
		ps.setString(9, cp.getColor()); // color
		ps.setDouble(10, cp.getCost()); // cost
		ps.setString(11, cp.getComment()); // comment
		ps.setDate(12, cp.getCheckupdate()); // checkupdate
		ps.setString(13, cp.getAdded()); // added
		ps.setDouble(14, cp.getWeight()); // weight
		ps.setString(15, cp.getPackageMatrial()); // package
		ps.setDouble(16, cp.getVilumetricWeight()); // vilu
		ps.setString(17, cp.getVolume()); // Volume
		ps.setInt(18, cp.getSecuredQty());
		ps.setString(19, cp.getPicturePath());
		ps.setString(20, cp.getSKU());
		
		int i = ps.executeUpdate();

	}

	public void InsertNewProduct(HttpServletRequest request, Connection conn) throws SQLException, IOException, ServletException {
		String strsql = "INSERT INTO product(SKU,owner,productType,brand,subbrand,ean,productCode,p_name,spec"
				+ ",color,securedQty,cost,comment,checkupdate,added,weight,packageMatrial,vilumetricWeight,createDate,picturePath,volume) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // (20個)，還未加入barCode
		String productType ="" ;
		
		System.out.println("request.getParameter('productType')"+request.getParameter("productType"));
		switch(request.getParameter("productType")){
	
		case "1":
			 productType = "單一商品";
			break;
		case "2":
			 productType = "清倉類";
			break;
		case "3":
			 productType = "調貨類";
			break;
		default:
			productType = "";
		}
		
		PreparedStatement ps = null;
		// System.out.print(strsql);
		ps = conn.prepareStatement(strsql);

		ps.setString(1, request.getParameter("SKU"));
		// ps.setString(1, request.getParameter("barCode")); //此行未加入
		ps.setString(2, request.getParameter("owner"));
		
		
		System.out.println("產品類別~"+productType);
		ps.setString(3, productType);
		ps.setString(4, request.getParameter("brand"));
		ps.setString(5, request.getParameter("subBrand"));
		ps.setString(6, request.getParameter("EAN"));// (6)
		ps.setString(7, request.getParameter("productCode"));
		ps.setString(8, request.getParameter("P_name"));
		ps.setString(9, request.getParameter("spec"));
		ps.setString(10, request.getParameter("color"));
		ps.setInt(11, Integer.valueOf(request.getParameter("securedQty")));// (11)
		ps.setDouble(12, Double.valueOf(request.getParameter("cost")));
		ps.setString(13, request.getParameter("comment"));
		ps.setDate(14, Date.valueOf(request.getParameter("checkupdate")));
		ps.setString(15, request.getParameter("added"));
		ps.setDouble(16, Double.valueOf(request.getParameter("weight")));// (16)
		ps.setString(17, request.getParameter("packageMatrial"));
		ps.setDouble(18, Double.valueOf(request.getParameter("vilumetricWeight")));
		ps.setDate(19, Date.valueOf(request.getParameter("createDate")));
		
		
		ps.setString(20, request.getParameter("picturePath")); // picturePath(20)
		ps.setString(21, request.getParameter("volume"));
		int i = ps.executeUpdate();
		
		//上傳圖片
		
		
		//			Part part = request.getPart("multimedia");
		//
		//			if(!"".equals(request.getParameter("picturePath"))&& part.getContentType()!=null){
		//			part.write(request.getParameter("picturePath"));
		//			
		//			}
		//			
		//
		//			
//		   FileInputStream fileInputStream = new FileInputStream(new File(request.getParameter("picturePath"))); 
//	        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\iii\\Documents\\QuickReach\\pics\\"+request.getParameter("picturePath"))); 
//	        byte[] buffer = new byte[1024]; 
//	        int idx = 0; 
//	        while ((idx = fileInputStream.read(buffer)) != -1) { 
//	                fileOutputStream.write(buffer, 0, idx); 
//	        } 
//	        fileInputStream.close(); 
//	        fileOutputStream.close();
				     
		
	}
	
	public Double isBundle(String sku){
		Double cost = 0.0 ;
		try{
		if(sku.substring(0,3).equals("B00")){
		cost =	plusBundleCost(sku);
		}else{
		cost =	plusSingleCost(sku);
		}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return cost;
	}
	

	public Double plusBundleCost(String sku) throws SQLException {
		Connection conn;
		Double cost = 0.0;
		LinkedList<String> skulist = new LinkedList<String>();
		try {
			conn = new DataBaseConn().getConn();

			String strsql = " select p_sku from bundles where '1' = '1' and m_sku = ? ";
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, sku);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				skulist.add(rs.getString(1));
			}
			rs = null;
			ps = null;
			
			
			
			for (int i = 0; i < skulist.size(); i++) {
				String strsql2 = "select cost from product where sku = ?";
				ps = conn.prepareStatement(strsql2);
				ps.setString(1, skulist.get(i));

				rs = ps.executeQuery();
				if(rs.next()) {
					cost += rs.getDouble(1);
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return cost;
	}

	public Double plusSingleCost(String sku) throws SQLException {
		Connection conn;
		Double cost = 0.0;
		PreparedStatement ps;
		ResultSet rs;
		try {
			conn = new DataBaseConn().getConn();

			String strsql = "select cost from product where sku = ?";
			ps = conn.prepareStatement(strsql);
			ps.setString(1, sku);

			rs = ps.executeQuery();
			while (rs.next()) {
				cost += rs.getDouble(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cost;
	}
	
	

}
