package tw.iii.qr.IndependentOrder.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		 
		List<IordersDetail> list = criteria.list();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				IordersDetail iod = list.get(i);
				//iod.setPicPath(GetPic(iod.getQrId()));
			}
		}
		 System.out.println("IordersDetailDAO.selectIordersDetailByQRId():finish");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<IordersDetail> getSeletedDetail(String qrid, Map<String, String> detailselector) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrid));
		setCriteriaSelector(detailselector, criteria);
		List<IordersDetail> list = criteria.list();
		List<IordersDetail> list2 = null;
		if (list.size() != 0) {
			Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(getEntityClass());

			criteria2.add(Restrictions.eq("qrId", qrid));
			list2 = criteria2.list();
			for (int i = 0; i < list2.size(); i++) {
				IordersDetail iod = (IordersDetail) list2.get(i);
				iod.setPicPath(GetPic(iod.getQrId()));
			}
		}
		return list2;
	}

	private void setCriteriaSelector(Map<String, String> selector, Criteria criteria) {

		if (selector == null) {
			return;
		}
		if (!isNullorEmpty(selector.get("SKU"))) {
			criteria.add(Restrictions.like("sku", "%" + selector.get("SKU") + "%"));
		}
		if (!isNullorEmpty(selector.get("productName"))) {
			criteria.add(Restrictions.like("productName", "%" + selector.get("productName") + "%"));
		}
	}

	private String GetPic(String qrid) throws Exception {
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

	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

}
