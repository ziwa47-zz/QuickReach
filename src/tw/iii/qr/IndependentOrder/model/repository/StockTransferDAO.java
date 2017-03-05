package tw.iii.qr.IndependentOrder.model.repository;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.StockTransferlogMaster;

@Repository
public class StockTransferDAO extends AbstractDAO<StockTransferlogMaster> { 
	@Override
	protected Class<StockTransferlogMaster> getEntityClass() {
		return StockTransferlogMaster.class;
	}
	
	public int selectTodayCount(Date today,String oldWarehouse) throws Exception{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today);
		String Strtoday = dateFormat.format(today);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		
		String StrStockTransferId="";
		
		StrStockTransferId="%"+Strtoday+"03"+oldWarehouse+"%";
		System.out.println("YO:"+StrStockTransferId);
		//criteria.add(Restrictions.ge("date", today));
		//criteria.add(Restrictions.eq("oldWarehouse", oldWarehouse));
		criteria.add(Restrictions.like("stockTransferId", StrStockTransferId));
		return criteria.list().size();
	}
	
	
}
