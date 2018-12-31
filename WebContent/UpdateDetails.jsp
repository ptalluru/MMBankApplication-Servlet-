<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body style="overflow: hidden;">
        <header>
               <h1 class="topright" style="font-size:80px;line-height:23px">HDFC BANK</h1>
                <img src="images/HDFC.jpg" style="width:25%;height:150px">
                </header> 
                 <nav>   
					<a href="newSA.mm">	Open New Savings Account</a> 
					<a href="close.mm"> Close Account</a>
					<a href="withdraw.mm"> Withdraw</a>
					<a href="deposit.mm"> Deposit</a>
					<a href="fundTans.mm"> Fund Transfer</a>
					<a href="curBal.mm"> Get Current Balance</a>
                </nav> <br><br>
        <form action ="updateAccount.mm">
	           	<center><h1>UPDATE AN ACCOUNT</h1>
	           	<label>Account Number : <br><input type="text" name="txtNum" readonly="readonly" value="${requestScope.accounts.bankAccount.accountNumber}"></label><br><br></label> 
	           	<label>Name :<br><input type="text" name="txtAccHn" value="${requestScope.accounts.bankAccount.accountHolderName}"/></label><br><br>
				<label>AccountBalance :<br><input type="text" name="txtBal" readonly="readonly" value="${requestScope.accounts.bankAccount.accountBalance}"></label><br><br>
				<label>Salaried :</label>
				<label><input type="radio" name="rdSal" ${requestScope.accounts.salary==true?"checked":""}>YES</label>
				<label><input type="radio" name="rdSal" ${requestScope.accounts.salary==true?"":"checked"}>NO</label><br><br>
				<label><input type="submit" name="submit" value="Submit"></label>
				<label><input type="reset" name="reset" value="Reset"></label>
   
        </form>
        <div><footer id="footer">
                <marquee>
                    <img class="imgfooter" src="images/securebanking.png">
                    <img class="imgfooter" src="images/verisign.gif">
                    <p>@HDFC All Rights Reserved 2018</p>
                </marquee>
            </footer></div>
</body>
</html>