package tw.iii.qr.IndependentOrder.model.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;

@Repository
public class IordersMasterDAO extends AbstractDAO<IordersMaster> {
	@Override
	protected Class<IordersMaster> getEntityClass() {
		return IordersMaster.class;
	}

	public int selectTodayCount(Date today) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.ge("orderDate", today));
		return criteria.list().size();
	}

	public IordersMaster selectIordersMasterByQRId(String qrId) throws Exception {
		// System.out.println("IordersMasterDAO.selectIordersMasterByQRId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrId));
		IordersMaster iordersMaster = new IordersMaster();
		// System.out.println("qrId = "+qrId);

		if (criteria.list().size() == 1) {
			iordersMaster = (IordersMaster) criteria.list().get(0);
			// System.out.println(BeanUtils.describe(iordersMaster));
		}
		// System.out.println("IordersMasterDAO.selectIordersMasterByQRId():start");
		return iordersMaster;
	}

	@SuppressWarnings("unchecked")
	public List<IordersMaster> selectIordersMasterByStatus(Map<String, String> selector, String orderStatus) throws Exception {
		 System.out.println("IordersMasterDAO.selectIordersMasterByStatus():start");
		 System.out.println("orderStatus:"+orderStatus);

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		setCriteriaSelector(selector, criteria);
		if (StringUtils.hasText(orderStatus)) {
			// 找狀態
			criteria.add(Restrictions.eq("orderStatus", orderStatus));
		} // 否則找全部
		if (criteria.list().size() == 0) {
			System.out.println("找不到資料");
		}
		// 找搜尋條件

		return criteria.list();
	}

	private void setCriteriaSelector(Map<String, String> selector, Criteria criteria) throws Exception {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (selector != null) {

			if (!isNullorEmpty(selector.get("guestId")))
				criteria.add(Restrictions.like("guestId", "%" + selector.get("guestId") + "%"));

			if (!isNullorEmpty(selector.get("QR_id")))
				criteria.add(Restrictions.like("qrId", "%" + selector.get("QR_id") + "%"));

			String logistics = selector.get("logistics");
			if ("ALL".equals(logistics) || isNullorEmpty(logistics)) {

			} else if ("lothers".equals(logistics)) {
				criteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("logistics", "DHL"),
						Restrictions.eq("logistics", "EMS"), Restrictions.eq("logistics", "AP"),
						Restrictions.eq("logistics", "RA"), Restrictions.eq("logistics", "Fedex"),
						Restrictions.eq("logistics", "USPS寄倉"), Restrictions.eq("logistics", "USPS集運"))));

			} else if (!"ALL".equals(logistics)) {
				criteria.add(Restrictions.eq("logistics", selector.get("logistics")));
			}

			// if(!isNullorEmpty(selector.get("SKU")))
			// criteria.add(Restrictions.like("SKU", selector.get("SKU")));

			Date payDateMin = null;
			if (!isNullorEmpty(selector.get("payDateMin"))) {
				payDateMin = formatter.parse(selector.get("payDateMin")+" 00:00:00");
				criteria.add(Restrictions.ge("payDate", payDateMin));
			}
			Date payDateMax = null;
			if (!isNullorEmpty(selector.get("payDateMax"))) {
				payDateMax = formatter.parse(selector.get("payDateMax")+ " 23:59:59");
				criteria.add(Restrictions.le("payDate", payDateMax));
			}
			// if(!isNullorEmpty(selector.get("productName")))
			// criteria.add(Restrictions.like("productName",
			// selector.get("productName")));
			Date shippingDateMin = null;
			if (!isNullorEmpty(selector.get("shippingDateMin"))) {
				shippingDateMin = formatter.parse(selector.get("shippingDateMin")+" 00:00:00");
				criteria.add(Restrictions.ge("shippingDate", shippingDateMin));
			}
			Date shippingDateMax = null;
			if (!isNullorEmpty(selector.get("shippingDateMax"))) {
				shippingDateMax = formatter.parse(selector.get("shippingDateMax")+ " 23:59:59");
				criteria.add(Restrictions.le("shippingDate", shippingDateMax));
			}
		}

	}

	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

}
