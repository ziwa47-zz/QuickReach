package tw.iii.qr.IndependentOrder.model.repository;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;

@Repository
public class AllIdpOrderDAO extends AbstractDAO<IDPorderAll> {



	@Override
	protected Class<IDPorderAll> getEntityClass() {
		return IDPorderAll.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<IordersDetail> selectIordersDetailByQRId(String qrId, Map<String, String> selector) throws Exception {
		//System.out.println("IordersDetailDAO.selectIordersDetailByQRId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrId));
		setCriteriaSelector(selector,criteria);
		//System.out.println("IordersDetailDAO.selectIordersDetailByQRId():finish");
		
		for(int i =0;i<criteria.list().size();i++){
			IordersDetail iod = (IordersDetail) criteria.list().get(i);
			if(iod.getPicPath()==null||iod.getPicPath()==""){
				iod.setPicPath(GetPic(iod.getQrId()));
			}
		}
		return criteria.list();
	}

	private String GetPic(String qrid) throws Exception{
		String strsql = "select p.picturePath from product p inner join iorders_detail d on p.SKU=d.SKU"
				+ " where  d.QR_id =  ?";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, qrid);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		return rs.getString(1);
		}
		return null;
		
	}
	private void setCriteriaSelector(Map<String, String> selector, Criteria criteria) {

		if(selector==null){
			return;
		}
		if(!isNullorEmpty(selector.get("SKU"))){
			criteria.add(Restrictions.like("sku", selector.get("SKU")));
		}
		if(!isNullorEmpty(selector.get("productName"))){
			criteria.add(Restrictions.like("productName", selector.get("productName")));
		}
	}

	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}
	
}
