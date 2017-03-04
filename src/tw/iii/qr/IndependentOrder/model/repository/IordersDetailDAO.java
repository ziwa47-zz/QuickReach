package tw.iii.qr.IndependentOrder.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;

@Repository
public class IordersDetailDAO extends AbstractDAO<IordersDetail> {
	@Override
	protected Class<IordersDetail> getEntityClass() {
		return IordersDetail.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<IordersDetail> selectIordersDetailByQRId(String qrId) throws Exception {
		System.out.println("IordersDetailDAO.selectIordersDetailByQRId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrId));
		System.out.println("IordersDetailDAO.selectIordersDetailByQRId():finish");
		
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
	
}
