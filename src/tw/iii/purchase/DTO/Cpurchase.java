package tw.iii.purchase.DTO;

import java.util.LinkedList;


public class Cpurchase {

	public Cpurchase_master CPurchase_master = new Cpurchase_master();
	
	public Cpurchase_detail CPurchase_detailsSingle = new Cpurchase_detail();

	

	public Cpurchase_master getCPurchase_master() {
		return CPurchase_master;
	}
	public void setCPpurchase_master(Cpurchase_master Cpurchase_master) {
		CPurchase_master = Cpurchase_master;
	}
	
	public Cpurchase_detail getCPurchase_detailsSingle() {
		return CPurchase_detailsSingle;
	}
	public void setCPurchase_detailsSingle(Cpurchase_detail Cpurchase_detailsSingle) {
		CPurchase_detailsSingle = Cpurchase_detailsSingle;
	}
	
}
