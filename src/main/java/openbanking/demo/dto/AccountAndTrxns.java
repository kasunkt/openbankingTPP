package openbanking.demo.dto;

import java.util.List;

public class AccountAndTrxns {
	
	private Account acc;
	List<Trxn> benificaryTrxns;
	List<Trxn> payeeTrxns;
	
	
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}

	public List<Trxn> getBenificaryTrxns() {
		return benificaryTrxns;
	}
	public void setBenificaryTrxns(List<Trxn> benificaryTrxns) {
		this.benificaryTrxns = benificaryTrxns;
	}
	public List<Trxn> getPayeeTrxns() {
		return payeeTrxns;
	}
	public void setPayeeTrxns(List<Trxn> payeeTrxns) {
		this.payeeTrxns = payeeTrxns;
	}



}
