package tw.iii.qr.IndependentOrder.service;

import java.math.BigDecimal;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;

@Service
@Transactional
public class IordersDetailService extends AbstractService<IordersDetail> {
	@Autowired
	private IordersDetailDAO iordersDetailDAO;


	@Override
	protected AbstractDAO<IordersDetail> getDAO() {
		return iordersDetailDAO;
	}

	
	/**儲存獨立訂單明細<br/>
	 * @throws Exception */
	public void processPurchaseDetail(HttpServletRequest request) throws Exception {
		
		String qrId 			= request.getParameter("iorderMasterId");
		String transactionId 	= request.getParameter("transactionId");
		int count = Integer.parseInt(request.getParameter("count"));
		
		LinkedList<Integer> times = new LinkedList<>();
		for (String s : request.getParameterValues("times")) {
			times.add(Integer.parseInt(s));
		}

		for (int i = 1; i <= count; i++) {
			if (times.indexOf(i) == -1)
				continue;

			IordersDetail iordersDetail = new IordersDetail();
			iordersDetail.setQrId(qrId);
			iordersDetail.setTansactionId(transactionId);
			iordersDetail.setSku(request.getParameter(("sku" + i)));
			iordersDetail.setQty(Integer.valueOf(request.getParameter(("qty" + i))));
			iordersDetail.setPrice(new BigDecimal(request.getParameter(("Price" + i))));
			//iordersDetail.setWarehousePosition(request.getParameter(("warehousePositionOne" + i)));
			//iordersDetail.setWarehousePosition2(request.getParameter(("warehousePositionTwo" + i)));
			iordersDetail.setComment(request.getParameter(("comment" + i)));
			iordersDetail.setWarehouse(request.getParameter("warehouse"));
			iordersDetail.setProductName(request.getParameter("pName"+i));
			iordersDetail.setOwner(request.getParameter("owner"+i));
			
			if(request.getParameter("weight_g"+i)!=null&&request.getParameter("weight_g"+i)!=""){
			iordersDetail.setWeight_g(new BigDecimal(request.getParameter("weight_g"+i)));
			}
			if(request.getParameter("weight_oz"+i)!=null&&request.getParameter("weight_oz"+i)!=""){
			iordersDetail.setWeight_oz(new BigDecimal(request.getParameter("weight_oz"+i)));
			}
			//前端撈商品明細  要增加 owner，ajax也要改寫~
			
			persist(iordersDetail);
			

		}

		// insert data
	}

	
}
