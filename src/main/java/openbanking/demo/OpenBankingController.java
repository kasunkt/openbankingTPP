package openbanking.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import openbanking.demo.dto.LoginRequest;
import openbanking.demo.dto.LoginResponse;

/**
 * Servlet implementation class OpenBankingController
 */
public class OpenBankingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenBankingController() {
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
		 if("openLogin".equals(sPage)) 
		 {
			 String sProvider=req.getParameter("provider");
			 
			 System.out.println("sProvider:" +sProvider);
			 if("metro".equals(sProvider))
			 {

				 LoginRequest loginReq=new LoginRequest();
				 String userName=req.getParameter("loginUsername"); 
				 String pwed=req.getParameter("loginPassword");
				 loginReq.setUserId(userName);
				 loginReq.setPassword(pwed);
				 LoginResponse loginRes= doLogin(loginReq);
				 
				 if ("00".equals(loginRes.getStatusCode())) 
				 {
					 String sLandingPage=sProvider+"_landingPage.jsp";
					 System.out.println("sLandingPage:" +sLandingPage);
					 HttpSession http =req.getSession();
					 http.setAttribute("metro_token", loginRes.getToken());
					 RequestDispatcher  dp=req.getRequestDispatcher("/MetroController?page=TrxnsList");
						  dp.forward(req, res);
					 return;
				 }else 
				 {
					
					 req.setAttribute("nextPage", " _mian_page");
					 RequestDispatcher  dp=req.getRequestDispatcher( "Msg.jsp" );
					 req.setAttribute("data", "Metro Login Unsuccessful");
					 dp.forward(req, res);
				 }

			 }else if(sProvider==null || sProvider.length()==0) 
			 {
				 System.out.println("no provider");
				 
				 req.setAttribute("data", "Please Select Provider");
				 RequestDispatcher  dp=req.getRequestDispatcher( "Msg.jsp" );
				 dp.forward(req, res);
				 return;
			 } else 
			 {
				 req.setAttribute("data", "Provider Not integrated");
				 RequestDispatcher  dp=req.getRequestDispatcher( "Msg.jsp" );
				 dp.forward(req, res);
				 return;
			 }
		 }
 
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
