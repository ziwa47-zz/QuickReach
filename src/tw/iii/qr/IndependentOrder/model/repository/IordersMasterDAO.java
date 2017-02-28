package tw.iii.qr.IndependentOrder.model.repository;


import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;

@Repository
public class IordersMasterDAO extends AbstractDAO<IordersMaster> {
	@Override
	protected Class<IordersMaster> getEntityClass() {
		return IordersMaster.class;
	}
	
	public int selectTodayCount(Date today) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.ge("orderDate", today));
		return criteria.list().size();
	}
	
	public IordersMaster selectIordersMasterByQRId(String qrId) throws Exception {
		System.out.println("IordersMasterDAO.selectIordersMasterByQRId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrId));
		IordersMaster iordersMaster = null;
		System.out.println("qrId = "+qrId);

		if (criteria.list().size() == 1) {
			iordersMaster = (IordersMaster) criteria.list().get(0);
			System.out.println(BeanUtils.describe(iordersMaster));
		} 
		System.out.println("IordersMasterDAO.selectIordersMasterByQRId():start");
		return iordersMaster;
	}
	
	@SuppressWarnings("unchecked")
	public List<IordersMaster> selectIordersMasterByStatus(String orderStatus) throws Exception {
		System.out.println("IordersMasterDAO.selectIordersMasterByStatus():start");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		if(orderStatus!=""&&orderStatus!=null){
			//找狀態
			criteria.add(Restrictions.eq("orderStatus", orderStatus));
		}//否則找全部
		List<IordersMaster> list = criteria.list();
		if(list.size()==0){
			System.out.println("找不到資料");
			return null;
		}
		for(int i =0;i<list.size();i++){
		System.out.println(BeanUtils.describe(criteria.list().get(i)));
		}
		return list;
	}
	
	
	
}
