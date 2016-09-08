package tw.iii.qr.stock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tw.iii.qr.DataBaseConn;

public class CExceltoDB {
	
	
	public CExceltoDB() throws IOException {
		addBundle();
	}
	
	private static void addBundle() {
String path = "/Users/Ziwa/Desktop/庫存表1.xlsx"; 
		
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] strData= new String[26];
		XSSFCell cell;
		Connection conn = new DataBaseConn().getConn();
		for(int i = 2 ; i<sheet.getPhysicalNumberOfRows();i++){
			
			XSSFRow row = sheet.getRow(i);
			for(int j = 0 ;j<26;j++ ){
				cell = row.getCell(j);
				strData[j]=cell.toString();
			}
			CProduct p = new CProduct();
			p.setOwner(strData[0]);
			p.setAdded(strData[3]);
			//p.setPicturePath(strData[4]);
			p.setSKU(strData[5]);
//			p.setPosition1(strData[6]);
//			p.setPosition2(strData[7]);
			p.setEAN(strData[8]);
			p.setProductType(strData[9]);
			p.setBrand(strData[11]);
			p.setSubBrand(strData[12]);
			p.setProductCode(strData[13]);
			p.setSecuredQty(10);
			
			p.setP_name(strData[14]);
			p.setSpec(strData[15]);
			p.setColor(strData[17]);
//			p.setQTY(strData[18]);
			
			
			
			
			
			try{
			
			p.setCost(Double.parseDouble(strData[19]));
			
			
			}catch(Exception e){
				p.setCost(0);
			}
			p.setComment(strData[20]);
//			p.setWareHouse(strData[21]);
			try{
			p.setWeight(Double.parseDouble(strData[23]));
			}catch(Exception e){
				p.setWeight(0);
			}
			if("".equals(strData[24])){
				p.setPackageMatrial("");
			}else{
				p.setPackageMatrial(strData[24]);
			}
			try{
			if("".equals(strData[23])){
				p.setVilumetricWeight(0);
			}else{
				p.setVilumetricWeight(Double.parseDouble(strData[25]));
			}
			}catch(Exception e){
				p.setVilumetricWeight(0);
			}
			try{
			String strsql = "INSERT INTO product(SKU,owner,productType,brand,subbrand,ean,productCode,p_name,spec"
					+ ",color,securedQty,cost,comment,checkupdate,added,weight,packageMatrial,vilumetricWeight,createDate,picturePath) "+
				 " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //(20個)，還未加入barCode
		
			PreparedStatement ps = null;
			//System.out.print(strsql); 
			
			ps = conn.prepareStatement(strsql);
			
			ps.setString(1, p.getSKU());
			//ps.setString(1, request.getParameter("barCode")); //此行未加入
			ps.setString(2,p.getOwner());
			ps.setString(3, p.getProductType());
			ps.setString(4, p.getBrand());
			ps.setString(5, p.getSubBrand());
			ps.setString(6, p.getEAN());//(6)
			ps.setString(7, p.getProductCode());
			ps.setString(8, p.getP_name());
			ps.setString(9, p.getSpec());
			ps.setString(10, p.getColor());
			ps.setInt(11, p.getSecuredQty());//(11)
			ps.setDouble(12, p.getCost());
			ps.setString(13, p.getComment());
			ps.setDate(14, p.getCheckupdate());
			ps.setString(15, p.getAdded());
			ps.setDouble(16, p.getWeight());//(16)
			ps.setString(17, p.getPackageMatrial());
			ps.setDouble(18, p.getVilumetricWeight());
			ps.setDate(19, p.getCreateDate());
			ps.setString(20, p.getPicturePath()); //picturePath(20)
			
			int j =ps.executeUpdate();
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	public static void addProduct()
			throws IOException, SQLException, Exception, IllegalAccessException, ClassNotFoundException {
		String path = "/Users/Ziwa/Desktop/庫存表1.xlsx"; 
		
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] strData= new String[26];
		XSSFCell cell;
		Connection conn = new DataBaseConn().getConn();
		for(int i = 2 ; i<sheet.getPhysicalNumberOfRows();i++){
			
			XSSFRow row = sheet.getRow(i);
			for(int j = 0 ;j<26;j++ ){
				cell = row.getCell(j);
				strData[j]=cell.toString();
			}
			CProduct p = new CProduct();
			p.setOwner(strData[0]);
			p.setAdded(strData[3]);
			//p.setPicturePath(strData[4]);
			p.setSKU(strData[5]);
//			p.setPosition1(strData[6]);
//			p.setPosition2(strData[7]);
			p.setEAN(strData[8]);
			p.setProductType(strData[9]);
			p.setBrand(strData[11]);
			p.setSubBrand(strData[12]);
			p.setProductCode(strData[13]);
			p.setSecuredQty(10);
			
			p.setP_name(strData[14]);
			p.setSpec(strData[15]);
			p.setColor(strData[17]);
//			p.setQTY(strData[18]);
			
			
			
			
			
			try{
			
			p.setCost(Double.parseDouble(strData[19]));
			
			
			}catch(Exception e){
				p.setCost(0);
			}
			p.setComment(strData[20]);
//			p.setWareHouse(strData[21]);
			try{
			p.setWeight(Double.parseDouble(strData[23]));
			}catch(Exception e){
				p.setWeight(0);
			}
			if("".equals(strData[24])){
				p.setPackageMatrial("");
			}else{
				p.setPackageMatrial(strData[24]);
			}
			try{
			if("".equals(strData[23])){
				p.setVilumetricWeight(0);
			}else{
				p.setVilumetricWeight(Double.parseDouble(strData[25]));
			}
			}catch(Exception e){
				p.setVilumetricWeight(0);
			}
			try{
			String strsql = "INSERT INTO bundles(m_SKU,bundles,qty,eap_name,spec"
					+ ",color,securedQty,cost,comment,checkupdate,added,weight,packageMatrial,vilumetricWeight,createDate,picturePath) "+
				 " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where Sku like 'B01%'"; //(20個)，還未加入barCode
		
			PreparedStatement ps = null;
			//System.out.print(strsql); 
			
			ps = conn.prepareStatement(strsql);
			
			ps.setString(1, p.getSKU());
			//ps.setString(1, request.getParameter("barCode")); //此行未加入
			ps.setString(2,p.getOwner());
			ps.setString(3, p.getProductType());
			ps.setString(4, p.getBrand());
			ps.setString(5, p.getSubBrand());
			ps.setString(6, p.getEAN());//(6)
			ps.setString(7, p.getProductCode());
			ps.setString(8, p.getP_name());
			ps.setString(9, p.getSpec());
			ps.setString(10, p.getColor());
			ps.setInt(11, p.getSecuredQty());//(11)
			ps.setDouble(12, p.getCost());
			ps.setString(13, p.getComment());
			ps.setDate(14, p.getCheckupdate());
			ps.setString(15, p.getAdded());
			ps.setDouble(16, p.getWeight());//(16)
			ps.setString(17, p.getPackageMatrial());
			ps.setDouble(18, p.getVilumetricWeight());
			ps.setDate(19, p.getCreateDate());
			ps.setString(20, p.getPicturePath()); //picturePath(20)
			
			int j =ps.executeUpdate();
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
