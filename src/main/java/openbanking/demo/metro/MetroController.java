package openbanking.demo.metro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import jersey.repackaged.com.google.common.collect.Lists;
import openbanking.demo.Const;
import openbanking.demo.dto.Account;
import openbanking.demo.dto.AccountAndTrxns;
import openbanking.demo.dto.AccountListResp;
import openbanking.demo.dto.FundTransferRequest;
import openbanking.demo.dto.LoginRequest;
import openbanking.demo.dto.LoginResponse;
import openbanking.demo.dto.Trxn;
import openbanking.demo.dto.TrxnListResponse;

/**
 * Servlet implementation class MetroController
 */
public class MetroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String sPage=req.getParameter("page");
		 System.out.println("Page:" +sPage);
		 if("TrxnsList".equals(sPage)  ) 
		 {
		 
			 List<AccountAndTrxns> acAndTrxn=new ArrayList<AccountAndTrxns>();
			 AccountAndTrxns obj=null;
			 String fromAccount=req.getParameter("provider"); 
			 String benificiryAccount=req.getParameter("provider");
			 String cif=req.getParameter("cif");
			 cif="100";
			 List<Account> accountList=null;
			 AccountListResp accListResp= getAccountList(cif, "s");
			 if (accListResp!=null) {
			 accountList =accListResp.getAccountList();
			 if(accListResp!=null && accountList!=null) 
			 {
				 
				 for(Account acc:accountList) 
				 {
					 obj=new AccountAndTrxns();
					 obj.setAcc(acc);
					 TrxnListResponse resp =getTrxnsList(acc.getAccountNumber(),acc.getAccountNumber());
					 obj.setBenificaryTrxns(resp.getBenificaryTrxns());
					 obj.setPayeeTrxns(resp.getPayeeTrxns());
					 acAndTrxn.add(obj);
				 }
			 }
			 }
 
			 req.setAttribute("data", acAndTrxn);
			 RequestDispatcher  dp=req.getRequestDispatcher( "metro_landingPage.jsp" );
			 dp.forward(req, res);
		 }else if ("trxn".equals(sPage) )
		 {
			 FundTransferRequest trxnReq=new FundTransferRequest ();
			 trxnReq.setAmount(req.getParameter("amount"));
			 trxnReq.setFromAccount(req.getParameter("fromAccount").split("\\|")[0]);
			 trxnReq.setBenificiryAccount(req.getParameter("benAccount"));
			 trxnReq.setBenificiryBank(req.getParameter("benBank"));
			 trxnReq.setBenificiryName(req.getParameter("benName"));
			 trxnReq.setCif(req.getParameter("000"));
			 trxnReq.setCurrencyCode(req.getParameter("LKR"));
			 trxnReq.setPayeeName(req.getParameter("fromAccount").split("\\|")[1]);
			 
			 Trxn trxnRes= doTrxns(trxnReq);
			 String displayMsg="";
			 if("SUCCESS".equals( trxnRes.getStatus()))
			 {
				 displayMsg="Transaction Successul.";
			 }else 
			 {
				 displayMsg="Transaction Unsuccessul.";
			 }
			 req.setAttribute("data", displayMsg);
			 RequestDispatcher  dp=req.getRequestDispatcher( "Msg.jsp" );
			 dp.forward(req, res);
			
		 }else if ("login".equals(sPage)) 
		 {
			 LoginRequest loginReq=new LoginRequest();
			 String userName=req.getParameter("loginUsername"); 
			 String pwed=req.getParameter("loginPassword");
			 loginReq.setUserId(userName);
			 loginReq.setPassword(pwed);
			 LoginResponse loginRes= doLogin(loginReq);
			 
			 if ("00".equals(loginRes.getStatusCode())) 
			 {
				 RequestDispatcher  dp=req.getRequestDispatcher( "index.jsp" );
				 dp.forward(req, res);
			 }else 
			 {
				 RequestDispatcher  dp=req.getRequestDispatcher( "login.jsp" );
				 req.setAttribute("data", "Login Unsuccessful");
				 dp.forward(req, res);
			 }
			
		 }else if ("logout".equals(sPage)) 
		 {
			 
			 	try{req.getSession().invalidate();}catch(Exception e)
			 	{
			 		e.printStackTrace();
			 	}
				 RequestDispatcher  dp=req.getRequestDispatcher( "login.jsp" );
				 dp.forward(req, res);
			 
			
		 }
	}

	public AccountListResp  getAccountList(String cif, String accountType) //throws ClientProtocolException, IOException
	{
			System.out.println("getAccountList");
			AccountListResp trnListRes=null;
			CloseableHttpClient client =null;
		    try {

				Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, Const.ZCASH_TOKEN);
				List<Header> headers = Lists.newArrayList(header);
				
		    	client = HttpClients.custom().setDefaultHeaders(headers).build();
			    HttpPost httpPost = new HttpPost("http://localhost:8090/api/Account/list");
			 
			    List<NameValuePair> params = new ArrayList<NameValuePair>();
			    params.add(new BasicNameValuePair("cif", cif));
			    params.add(new BasicNameValuePair("accountType", accountType));

				httpPost.setEntity(new UrlEncodedFormEntity(params));

			    CloseableHttpResponse response = client.execute(httpPost);
			    HttpEntity entity = response.getEntity();
			    String result = EntityUtils.toString(entity);
			    
			    System.out.println("result" +result);
			    System.out.println("end" +response.getStatusLine());
			    System.out.println("end2" +response.toString());
			    if (response.getStatusLine().getStatusCode() == 200) 
			    {	
			    	System.out.println("success" );	
			    	Gson gson = new Gson();
			    	trnListRes =gson.fromJson(result,AccountListResp.class);
			        System.out.println("res="+trnListRes); 
			    }
			    
				client.close();
				 

		    } catch  (Exception e) {
				e.printStackTrace();
				
			}finally 
		    {
				
		    }
		    
		    return trnListRes;
	}
	
	
	public TrxnListResponse  getTrxnsList(String fromAccount, String benificiryAccount) //throws ClientProtocolException, IOException
	{
			System.out.println("getTrxnsList");
			TrxnListResponse trnListRes=null;
			CloseableHttpClient client =null;
		    try {
				Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, Const.ZCASH_TOKEN);
				List<Header> headers = Lists.newArrayList(header);
				
		    	client = HttpClients.custom().setDefaultHeaders(headers).build();
			    HttpPost httpPost = new HttpPost("http://localhost:8090/api/transaction/list");
			 
			    List<NameValuePair> params = new ArrayList<NameValuePair>();
			    params.add(new BasicNameValuePair("fromAccount", fromAccount));
			    params.add(new BasicNameValuePair("benificiryAccount", benificiryAccount));
			     
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			    CloseableHttpResponse response = client.execute(httpPost);
			    HttpEntity entity = response.getEntity();
			    String result = EntityUtils.toString(entity);
			    
			    System.out.println("result" +result);
			    System.out.println("end" +response.getStatusLine());
			    System.out.println("end2" +response.toString());
			    if (response.getStatusLine().getStatusCode() == 200) 
			    {	
			    	System.out.println("success" );	
			    	Gson gson = new Gson();
			    	trnListRes =gson.fromJson(result,TrxnListResponse.class);
			        System.out.println("res="+trnListRes); 
			    }
			    
				client.close();
				 

		    } catch  (Exception e) {
				e.printStackTrace();
				
			}finally 
		    {
				
		    }
		    
		    return trnListRes;
	}
	
	public Trxn  doTrxns(FundTransferRequest req)
	{
			System.out.println("doTrxns");
			Trxn trxn=null;
			CloseableHttpClient client =null;
		    try {

				Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, Const.ZCASH_TOKEN);
				List<Header> headers = Lists.newArrayList(header);
				
		    	client = HttpClients.custom().setDefaultHeaders(headers).build();
			    HttpPost httpPost = new HttpPost("http://localhost:8090/api/transaction/fundTransfer");
			 
			    List<NameValuePair> params = new ArrayList<NameValuePair>();
			    params.add(new BasicNameValuePair("cif", req.getCif()));
			    params.add(new BasicNameValuePair("fromAccount", req.getFromAccount()));
			    params.add(new BasicNameValuePair("benificiryBank", req.getBenificiryBank()));
			    params.add(new BasicNameValuePair("benificiryAccount", req.getBenificiryAccount()));
			    params.add(new BasicNameValuePair("benificiryName", req.getBenificiryName()));
			    params.add(new BasicNameValuePair("amount", req.getAmount()));
			    params.add(new BasicNameValuePair("currencyCode", req.getCurrencyCode()));


				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			    CloseableHttpResponse response = client.execute(httpPost);
			    HttpEntity entity = response.getEntity();
			    String result = EntityUtils.toString(entity);
			    
			    System.out.println("result" +result);
			    System.out.println("end" +response.getStatusLine());
			    System.out.println("end2" +response.toString());
			    if (response.getStatusLine().getStatusCode() == 200) 
			    {	
			    	System.out.println("success" );	
			    	Gson gson = new Gson();
			    	trxn =gson.fromJson(result,Trxn.class);
			        System.out.println("res="+trxn); 
			    }
			    
				client.close();
				 

		    } catch  (Exception e) {
				e.printStackTrace();
				
			}finally 
		    {
				
		    }
		    
		    return trxn;
	}
	
	
	public LoginResponse  doLogin(LoginRequest req)
	{
			System.out.println("doTrxns");
			LoginResponse loginRes=null;
			CloseableHttpClient client =null;
		    try {
				Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, Const.ZCASH_TOKEN);
				List<Header> headers = Lists.newArrayList(header);
				
		    	client = HttpClients.custom().setDefaultHeaders(headers).build();
			    HttpPost httpPost = new HttpPost("http://localhost:8090/api/auth/login");
			 
			    List<NameValuePair> params = new ArrayList<NameValuePair>();
 
			    params.add(new BasicNameValuePair("userId", req.getUserId()));
			    params.add(new BasicNameValuePair("password", req.getPassword()));
 
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
			    CloseableHttpResponse response = client.execute(httpPost);
			    HttpEntity entity = response.getEntity();
			    String result = EntityUtils.toString(entity);
			    
			    System.out.println("result" +result);
			    System.out.println("end" +response.getStatusLine());
			    System.out.println("end2" +response.toString());
			    if (response.getStatusLine().getStatusCode() == 200) 
			    {	
			    	System.out.println("success" );	
			    	Gson gson = new Gson();
			    	loginRes =gson.fromJson(result,LoginResponse.class);
			        System.out.println("res="+loginRes); 
			    }
			    
				client.close();
				 

		    } catch  (Exception e) {
				e.printStackTrace();
				
			}finally 
		    {
		    }
		    
		    return loginRes;
	}

}
