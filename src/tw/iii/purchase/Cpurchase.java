package tw.iii.purchase;

import java.util.LinkedList;


public class Cpurchase {

	Cpurchase_master CPurchase_master = new Cpurchase_master();
	LinkedList<Cpurchase_detail> CPurchase_details = new LinkedList<Cpurchase_detail>();
	
	Cpurchase_detail CPurchase_detailsSingle = new Cpurchase_detail();

	

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
	
	public LinkedList<Cpurchase_detail> getCPurchase_detail() {
		return CPurchase_details;
	}
	public void setCPpurchase_detail(LinkedList<Cpurchase_detail> Cpurchase_detail) {
		CPurchase_details = Cpurchase_detail;
	}
}
