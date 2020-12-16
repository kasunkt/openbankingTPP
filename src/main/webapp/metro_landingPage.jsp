<%@page import="java.util.List"%>
<%@page import="openbanking.demo.dto.AccountAndTrxns"%>
<%@page import="openbanking.demo.dto.Trxn"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>metro</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Roboto -->
   <!--  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"> -->
    <link rel="stylesheet" href="fonts/googlefontrobotocss.css">
    <!-- jQuery Circle-->
    <link rel="stylesheet" href="css/grasp_mobile_progress_circle-1.0.0.min.css">
    <!-- Custom Scrollbar-->
    <link rel="stylesheet" href="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.blue.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <!-- Side Navbar -->
    <nav class="side-navbar">
      <div class="side-navbar-wrapper">
        <!-- Sidebar Header    -->
        <div class="sidenav-header d-flex align-items-center justify-content-center">
          <!-- User Info-->
          <div class="sidenav-header-inner text-center"><img src="img/avatar-7.jpg" alt="person" class="img-fluid rounded-circle">
            <h2 class="h5">KASADMIN</h2><span>Web Developer</span>
          </div>
          <!-- Small Brand information, appears on minimized sidebar-->
          <div class="sidenav-header-logo"><a href="index.jsp" class="brand-small text-center"> <strong>B</strong><strong class="text-primary">D</strong></a></div>
        </div>
        <!-- Sidebar Navigation Menus-->
        <div class="main-menu">
          <h5 class="sidenav-heading">Main</h5>
          <ul id="side-main-menu" class="side-menu list-unstyled">                  
            <li><a href="index.jsp"> <i class="icon-home"></i>Home                             </a></li>
            <!-- <li><a href="metro_landingPage.jsp" class="active"> <i class="icon-grid"></i>Metro Transactions    </a></li> -->
            <li><a href="MetroController?page=TrxnsList" class="active"> <i class="icon-grid"></i>Metro Transactions    </a></li>

           
             <!--  <li><a href="forms.html"> <i ></i></a></li>
                <li><a href="forms.html"> <i></i></a></li>
                <li><a href="forms.html"> <i ></i></a></li>
                <li><a href="forms.html"> <i ></i></a></li>
            
            
            <li><a href="login.html"> <i class="icon-interface-windows"></i>Login page                             </a></li>
            <li> <a href="#"> <i class="icon-mail"></i>Demo
                <div class="badge badge-warning">6 New</div></a></li> -->
          </ul>
        </div>
<!--         <div class="admin-menu">
          <h5 class="sidenav-heading">Second menu</h5>
          <ul id="side-admin-menu" class="side-menu list-unstyled"> 
            <li> <a href="#"> <i class="icon-screen"> </i>Demo</a></li>
            <li> <a href="#"> <i class="icon-flask"> </i>Demo
                <div class="badge badge-info">Special</div></a></li>
            <li> <a href=""> <i class="icon-flask"> </i>Demo</a></li>
            <li> <a href=""> <i class="icon-picture"> </i>Demo</a></li>
          </ul>
        </div> -->
      </div>
    </nav>
    <div class="page">
      <!-- navbar-->
          <%
    List<AccountAndTrxns> acAndTrxn =request.getAttribute("data")==null?null: (List<AccountAndTrxns>)request.getAttribute("data");
    
    
    
    %>
      
      <header class="header">
        <nav class="navbar">
          <div class="container-fluid">
            <div class="navbar-holder d-flex align-items-center justify-content-between">
              <div class="navbar-header"><a id="toggle-btn" href="#" class="menu-btn"><i class="icon-bars"> </i></a><a href="index.jsp" class="navbar-brand">
                  <div class="brand-text d-none d-md-inline-block"><span>Metro </span><strong class="text-primary">Transactions</strong></div></a></div>
              <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                <!-- Notifications dropdown-->
                <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell"></i><span class="badge badge-warning">12</span></a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
                    <li><a rel="nofollow" href="#" class="dropdown-item"> 
                        <div class="notification d-flex justify-content-between">
                          <div class="notification-content"><i class="fa fa-envelope"></i>You have 6 new messages </div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item"> 
                        <div class="notification d-flex justify-content-between">
                          <div class="notification-content"><i class="fa fa-twitter"></i>You have 2 followers</div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item"> 
                        <div class="notification d-flex justify-content-between">
                          <div class="notification-content"><i class="fa fa-upload"></i>Server Rebooted</div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item"> 
                        <div class="notification d-flex justify-content-between">
                          <div class="notification-content"><i class="fa fa-twitter"></i>You have 2 followers</div>
                          <div class="notification-time"><small>10 minutes ago</small></div>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong> <i class="fa fa-bell"></i>view all notifications                                            </strong></a></li>
                  </ul>
                </li>
                <!-- Messages dropdown-->
                <li class="nav-item dropdown"> <a id="messages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope"></i><span class="badge badge-info">10</span></a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
                    <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">Jason Doe</h3><span>sent you a direct message</span><small>3 days ago at 7:58 pm - 10.06.2014</small>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">KASADMIN</h3><span>sent you a direct message</span><small>3 days ago at 7:58 pm - 10.06.2014</small>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">Ashley Wood</h3><span>sent you a direct message</span><small>3 days ago at 7:58 pm - 10.06.2014</small>
                        </div></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong> <i class="fa fa-envelope"></i>Read all messages    </strong></a></li>
                  </ul>
                </li>
                <!-- Languages dropdown    -->
                <li class="nav-item dropdown"><a id="languages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle"><img src="img/flags/16/GB.png" alt="English"><span class="d-none d-sm-inline-block">English</span></a>
                  <ul aria-labelledby="languages" class="dropdown-menu">
                    <li><a rel="nofollow" href="#" class="dropdown-item"> <img src="img/flags/16/DE.png" alt="English" class="mr-2"><span>German</span></a></li>
                    <li><a rel="nofollow" href="#" class="dropdown-item"> <img src="img/flags/16/FR.png" alt="English" class="mr-2"><span>French                                                         </span></a></li>
                  </ul>
                </li>
                <!-- Log out-->
                <li class="nav-item"><a href="login.html" class="nav-link logout"> <span class="d-none d-sm-inline-block">Logout</span><i class="fa fa-sign-out"></i></a></li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      <!-- Breadcrumb-->
      <div class="breadcrumb-holder">
        <div class="container-fluid">
          <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
            <li class="breadcrumb-item active">Metro Transactions</li>
          </ul>
        </div>
      </div>
      <section>
        <div class="container-fluid">
          <!-- Page Header-->
          <header> 
            <h1 class="h3 display">Metro Transactions</h1>
          </header>
          <div class="row">
            <div class="col-lg-6">
 
              <div class="card">
                <div class="card-header d-flex align-items-center">
                  <h4>Transfers and Payments</h4>
                </div>
                <div class="card-body">
                 <!--  <p>Lorem ipsum dolor sit amet consectetur.</p> -->
                  <form action="MetroController" method="post">
                  <input type="hidden" name="page" value="trxn" />
                    <div class="form-group">
                      <label>From Account</label>
                     
                       <select name="fromAccount" class="form-control" >  <!--  -->
                       	  <option value="" selected>Select Account</option>
                       	  <% 
                       	  for (int i=0;i<acAndTrxn.size();i++)
                          {
                       	  %>
                          <option value="<%=acAndTrxn.get(i).getAcc().getAccountNumber()+"|"+acAndTrxn.get(i).getAcc().getAccountName()%>"><%=acAndTrxn.get(i).getAcc().getAccountName() %></option>
 
                         <% 
                          }
                       	  %>
                        </select>
                    </div>
                    <div class="form-group">
                      <label>Select Benificiary's Bank</label>
                     <!--  <input type="text" name="benBank"  placeholder="Select Bank" class="form-control"> -->
                       <select name="benBank" class="form-control" >  <!--  -->
                       	  <option value="" selected>Select Bank</option>
                          <option value="metro">Metro Bank</option>
                          <option value="anz" >ANZ</option>
                          <option value="hsbc" >HSBC</option>
                         
                        </select>
                      
                    </div>
                    <div class="form-group">       
                      <label>Benificiary's account No.</label>
                      <input type="text" name="benAccount" class="form-control">
                    </div>
                     <div class="form-group">       
                      <label>Benificiary's Name.</label>
                      <input type="text" name="benName"   class="form-control">
                    </div>
                     <div class="form-group">       
                      <label>Amount</label>
                      <input type="text" name="amount"   class="form-control">
                    </div>
                    <div class="form-group">       
                      <input type="submit" value="Submit" class="btn btn-primary">
                    </div>
                  </form>
                </div>
              </div>
            </div>
  
 
            <div class="col-lg-6">
            
             <div class="card">
                <div class="card-header">
                  <h4>Your Metro Accounts</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Account Name</th>
                          <th>Account Number</th>
                          <th>Balance</th>
                        </tr>
                      </thead>
                   <%
                   for (int i=0;i<acAndTrxn.size();i++)
                   {
                   %>   
                      
                      <tbody>
                        <tr>
                          <th scope="row"><%=i+1 %></th>
                          <td><%= acAndTrxn.get(i).getAcc().getAccountName() %></td>
                          <td><%= acAndTrxn.get(i).getAcc().getAccountNumber() %></td>
                          <td><%= acAndTrxn.get(i).getAcc().getAccountBalance() %></td>
                        </tr>
 
                      </tbody>
                      
                      <%} %>
                      
                      
                    </table>
                  </div>
                </div>
              </div>
                          <div class="card">
                <div class="card-header">
                  <h4>Your Recent Metro Transactions</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped table-sm">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Trxn Channel</th>
                          <th>Trxn Type</th>
                          <th>Bene/Payee</th>
                           <th>Credit/Debit</th>
                          <th>Amount</th>
                          <th>Date</th>
                        </tr>
                      </thead>
                      <tbody>
                      
                  <%
                  	int rowNumber=0;
                   for (int i=0;i<acAndTrxn.size();i++)
                   {
                	   	List<Trxn> trxns= acAndTrxn.get(i).getBenificaryTrxns();
                	   	rowNumber=0;
                	   	%>
                	   	<tr>
                		<td colspan="6"><%=acAndTrxn.get(i).getAcc().getAccountNumber() +" - "+acAndTrxn.get(i).getAcc().getAccountName() %></td>
                	   	 	</tr>
                	   	<%
                	   	
                	   	
                   		for (int j=0;j<trxns.size();j++)
                   		{
                   %> 
                        <tr style="background-color:lightgreen ">
                          <th scope="row"><%=++rowNumber %></th>
                          <td><%=trxns.get(j).getChannel() %></td>
                          <td><%=trxns.get(j).getTrxnType()  %></td>
                         <td><%=trxns.get(j).getPayeeName() %></td>
                          <td><%="C"  %></td>
                          <td><%=trxns.get(j).getAmount()  %></td>
                          <td><%=trxns.get(j).getTrxnTime()  %></td>
                            </tr>
                          
                 <%
                   		}
                   		
                   		List<Trxn> payeetrxns= acAndTrxn.get(i).getPayeeTrxns();
                   		for (int j=0;j<payeetrxns.size();j++)
                   		{
                  %> 
                         <tr style="background-color: #ff00004f">
                           <th scope="row"><%=++rowNumber%></th>
                            <td><%=payeetrxns.get(j).getChannel() %></td>
                           <td><%=payeetrxns.get(j).getTrxnType()  %></td>
                            <td><%=payeetrxns.get(j).getBenificiryName()  %></td>
                           <td><%="D"  %></td>
                           <td><%=payeetrxns.get(j).getAmount()  %></td>
                           <td><%=payeetrxns.get(j).getTrxnTime()  %></td> 
                             </tr>
                  <%
                    	}
                   	}
                  %>

 
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
 
            </div>
            <div class="col-lg-6">

            </div>
            <div class="col-lg-6">

            </div>
          </div>
        </div>
      </section>
      <footer class="main-footer">
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-6">
              <p>Metro Cash &copy; 2017-2020</p>
            </div>
           <!--  <div class="col-sm-6 text-right">
              <p>Design by <a href="https://bootstrapious.com/p/bootstrap-4-Transactions" class="external">Bootstrapious</a></p>
              Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions and it helps me to run Bootstrapious. Thank you for understanding :)
            </div> -->
          </div>
        </div>
      </footer>
    </div>
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="js/grasp_mobile_progress_circle-1.0.0.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
  </body>
</html>