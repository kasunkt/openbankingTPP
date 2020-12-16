package openbanking.demo.dto;

import java.util.List;

public class AccountListResp {

	@Override
	public String toString() {
		return "AccountListResp [accountList=" + accountList + ", status=" + status + ", error=" + error + "]";
	}
	private List<Account> accountList=null;
	private String status;
	private String error;
	
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
