package tw.iii.qr.stock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xpath.operations.Equals;

import tw.iii.qr.DataBaseConn;

public class CExceltoDB {

	public CExceltoDB() throws IOException {

	}

	public void addProduct() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		FileSystemView fsv = FileSystemView.getFileSystemView();

		String path = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "sample" + File.separator
				+ "庫存表.xlsx";
		System.out.println(path);
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet sheet = wb.getSheetAt(0);
		String[] strData = new String[26];
		XSSFCell cell;
		Connection conn = new DataBaseConn().getConn();
		ArrayList<String> allsku = new ArrayList<>();
		String strsku = "select sku from product";
		PreparedStatement ps1 = conn.prepareStatement(strsku);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			allsku.add(rs1.getString(1));
		}
		
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			boolean isContinue =true;
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < 26; j++) {
				cell = row.getCell(j);
				strData[j] = cell.toString();
			}
			
			for(int k =0;k<allsku.size();k++){
				if(strData[5].equals(allsku.get(k))){
					System.out.println("sku already");
					isContinue=false;
					continue;
				}
			}
			if(!isContinue)
				continue;
			if (strData[5] == null || "".equals(strData[5])){
				System.out.println("sku null");
				return;
			}
			CProduct p = new CProduct();
			p.setOwner(strData[0]);
			p.setAdded("false");
			// p.setPicturePath(strData[4]);
			p.setSKU(strData[5]);
			// p.setPosition1(strData[6]);
			// p.setPosition2(strData[7]);
			p.setEAN(strData[8]);

			//
			String x = strData[5].substring(0, 3);

			System.out.println(x);

			switch (x) {
			case "B00":
				p.setProductType("組合商品");
				break;

			case "ZZZ":
				p.setProductType("調貨類");
				break;

			default:
				p.setProductType("單一商品");
				break;
			}

			//

			p.setBrand(strData[11]);
			p.setSubBrand(strData[12]);
			p.setProductCode(strData[13]);
			p.setSecuredQty(0);

			p.setP_name(strData[14]);
			p.setSpec(strData[15]);
			p.setVolume(strData[16]);
			p.setColor(strData[17]);
			// p.setQTY(strData[18]);

			System.out.println("Sku:" + strData[5] + " Brand:" + strData[11]);
			String toComment = "";
			try {

				// 請先確認excel裡cost欄位裡為USD幣別的cell皆是"USDXX.XX" 而非USD$XX.XX
				String y = strData[19].substring(0, 3);
				System.out.println("cost 前3碼:"+y);
				switch (y) {
				case "USD":
					String z = strData[19].substring(3);
					p.setCost(Double.parseDouble(z));
					toComment += "進貨成本為USD  ";

					break;

				default:
					p.setCost(Double.parseDouble(strData[19]));

					break;
				}

			} catch (Exception e) {
				p.setCost(0);
			}
			toComment += strData[20].toString();
			p.setComment(toComment);
			// p.setWareHouse(strData[21]);
			try {
				p.setWeight(Double.parseDouble(strData[23]));
			} catch (Exception e) {
				p.setWeight(0);
			}
			if ("".equals(strData[24])) {
				p.setPackageMatrial("");
			} else {
				p.setPackageMatrial(strData[24]);
			}
			try {
				if ("".equals(strData[23])) {
					p.setVilumetricWeight(0);
				} else {
					p.setVilumetricWeight(Double.parseDouble(strData[25]));
				}
			} catch (Exception e) {
				p.setVilumetricWeight(0);
			}

			p.setPicturePath("");

			try {
				String strsql = "INSERT INTO product(SKU,owner,productType,brand,subbrand,ean,productCode,p_name,spec"
						+ ",color,securedQty,cost,comment,checkupdate,added,weight,packageMatrial,volume,vilumetricWeight,createDate,picturePath) "
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,getdate(),?)"; // (21個)，還未加入barCode
				// ,add
				// volume

				PreparedStatement ps = null;
				// System.out.print(strsql);

				ps = conn.prepareStatement(strsql);

				ps.setString(1, p.getSKU());
				// ps.setString(1, request.getParameter("barCode")); //此行未加入
				ps.setString(2, p.getOwner());
				ps.setString(3, p.getProductType());
				ps.setString(4, p.getBrand());
				ps.setString(5, p.getSubBrand());
				ps.setString(6, p.getEAN());// (6)
				ps.setString(7, p.getProductCode());
				ps.setString(8, p.getP_name());
				ps.setString(9, p.getSpec());
				ps.setString(10, p.getColor());
				ps.setInt(11, p.getSecuredQty());// (11)
				ps.setDouble(12, p.getCost());
				ps.setString(13, p.getComment());
				ps.setInt(14, 0);
				ps.setString(15, p.getAdded());
				ps.setDouble(16, p.getWeight());// (16)
				ps.setString(17, p.getPackageMatrial());
				ps.setString(18, p.getVolume());
				ps.setDouble(19, p.getVilumetricWeight());
				// ps.setDate(20, p.getCreateDate());
				ps.setString(20, p.getPicturePath()); // picturePath(20)

				int j = ps.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("product done");
	}

	public void addStorage() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		Connection conn = new DataBaseConn().getConn();

		FileSystemView fsv = FileSystemView.getFileSystemView();
		String path = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "sample" + File.separator
				+ "庫存表.xlsx";
		XSSFWorkbook wb = new XSSFWorkbook(path);

		XSSFSheet sheet = wb.getSheetAt(0);
		String[] strData = new String[26];
		XSSFCell cell;
		ArrayList<String> allsku = new ArrayList<>();
		String strsku = "select sku from storage";
		PreparedStatement ps1 = conn.prepareStatement(strsku);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			allsku.add(rs1.getString(1));
		}
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			boolean isContinue =true;
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < 26; j++) {
				cell = row.getCell(j);
				strData[j] = cell.toString();
			}
			
			for(int k =0;k<allsku.size();k++){
				if(strData[5].equals(allsku.get(k))){
					System.out.println("sku already");
					isContinue=false;
					continue;
				}
			}
			if(!isContinue)
				continue;
			if (strData[5] == null || "".equals(strData[5])){
				System.out.println("sku null");
				return;
			}
			CStock s = new CStock();
			CProduct p = new CProduct();
			p.setOwner(strData[0]);
			p.setAdded(strData[3]);
			// p.setPicturePath(strData[4]);
			p.setSKU(strData[5]);
			if ("".equals(strData[6])) {
				s.setPosition1("");
			} else if ("0".equals(strData[6])) {
				s.setPosition1("0");
			} else if ("AM".equals(strData[6])) {
				s.setPosition1("AM");
			} else if ("ZZZ".equals(strData[6])) {
				s.setPosition1("ZZZ");
			} else {
				s.setPosition1(String.valueOf((int) Math.floor(Double.valueOf(strData[6]))));
			}

			s.setPosition2(strData[7]);
			p.setEAN(strData[8]);
			p.setProductType(strData[9]);
			p.setBrand(strData[11]);
			p.setSubBrand(strData[12]);
			p.setProductCode(strData[13]);
			p.setSecuredQty(10);

			p.setP_name(strData[14]);
			p.setSpec(strData[15]);
			p.setVolume(strData[16]);
			p.setColor(strData[17]);
			System.out.println(strData[5] + strData[18]);
			if ("".equals(strData[18]) || "0.0".equals(strData[18])) {
				s.setQty(0);
			} else {
				s.setQty((int) Math.floor(Double.valueOf(strData[18])));
			}

			// System.out.println("Sku:"+strData[5]+"
			// warehouse:"+strData[21]+":"+String.valueOf((int)Math.floor(Double.valueOf(strData[6])))+"-"+strData[7]);

			try {

				p.setCost(Double.parseDouble(strData[19]));

			} catch (Exception e) {
				p.setCost(0);
			}
			p.setComment(strData[20]);
			s.setWareHouse(strData[21]);
			try {
				p.setWeight(Double.parseDouble(strData[23]));
			} catch (Exception e) {
				p.setWeight(0);
			}
			if ("".equals(strData[24])) {
				p.setPackageMatrial("");
			} else {
				p.setPackageMatrial(strData[24]);
			}
			try {
				if ("".equals(strData[23])) {
					p.setVilumetricWeight(0);
				} else {
					p.setVilumetricWeight(Double.parseDouble(strData[25]));
				}
			} catch (Exception e) {
				p.setVilumetricWeight(0);
			}

			p.setCompany(" ");
			try {
				String strsql = "Insert into storage(SKU,warehouse,warehousePosition1,warehousePosition2,qty,comment,purchaseDate,company) "
						+ "values(?,?,?,?,?,?,getdate(),?)";
				PreparedStatement ps = null;
				// System.out.print(strsql);

				ps = conn.prepareStatement(strsql);

				ps.setString(1, p.getSKU());
				// ps.setString(1, request.getParameter("barCode")); //此行未加入
				ps.setString(2, s.getWareHouse());
				ps.setString(3, s.getPosition1());
				ps.setString(4, s.getPosition2());
				ps.setInt(5, s.getQty());
				ps.setString(6, p.getComment());// (6)
				ps.setString(7, "");
				int j = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("storage done");
	}
}
