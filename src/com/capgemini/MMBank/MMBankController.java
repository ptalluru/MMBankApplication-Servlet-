package com.capgemini.MMBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.exception.AccountNotFoundException;


@WebServlet("*.mm")
public class MMBankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   @Override
	public void init() throws ServletException {
		super.init();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection
						("jdbc:mysql://localhost:3306/bankapp_db", "root", "root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String path = request.getServletPath();
		SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl();
		SavingsAccount savingsAccount = null,sendersSavingsAccount=null,receiversSavingsAccount=null;
		PrintWriter out = response.getWriter();
		switch(path){
			
			case "/newSA.mm":
				response.sendRedirect("NewSavingsAccount.html");
				break;
			case "/createAccount.mm":
					String name = request.getParameter("txtAccHn");
					double amount = Double.parseDouble(request.getParameter("txtBal"));
					boolean salary = request.getParameter("rdSal").equalsIgnoreCase("no")?false:true;
					try {
						savingsAccountService.createNewAccount(name, amount, salary);
						response.sendRedirect("index.html");
					} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					}
					break;
			case "/close.mm" :
				response.sendRedirect("CloseAccountForm.html");
				break;
			case "/closeAccount.mm" :
					int accountid = Integer.parseInt(request.getParameter("closeAcc"));
					try {
						savingsAccountService.deleteAccount(accountid);
						response.sendRedirect("index.html");
					} catch (ClassNotFoundException | SQLException| AccountNotFoundException e) {
					e.printStackTrace();
					}
					break;
			case "/withdraw.mm":
					response.sendRedirect("withdrawForm.html");
					break;
			case "/withdrawAmount.mm":
						int accountnumber = Integer.parseInt(request.getParameter("accId"));
						double amountWithdraw = Double.parseDouble(request.getParameter("amount"));
					try {
						savingsAccount = savingsAccountService.getAccountById(accountnumber);
					} catch (ClassNotFoundException | SQLException| AccountNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						savingsAccountService.withdraw(savingsAccount, amountWithdraw);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					try {
						DBUtil.commit();
						response.sendRedirect("index.html");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
			case "/deposit.mm":
				response.sendRedirect("depositForm.html");
				break;
			case"/depositAmount.mm":
					int depositAccountNumber = Integer.parseInt(request.getParameter("accId"));
					double depositAmount = Double.parseDouble(request.getParameter("amount"));
				try {
					savingsAccount = savingsAccountService.getAccountById(depositAccountNumber);
				} catch (ClassNotFoundException | SQLException| AccountNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					savingsAccountService.deposit(savingsAccount, depositAmount);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				try {
					DBUtil.commit();
					response.sendRedirect("index.html");
				} catch (SQLException e) {
					e.printStackTrace();
				}
					break;
			case "/fundTans.mm":
				response.sendRedirect("FundTransfer.html");
				break;
					
			case "/fundTransfer.mm":
					int sendersAccountNumber = Integer.parseInt(request.getParameter("sendersAccId"));
					int receiversAccountNumber = Integer.parseInt(request.getParameter("receiversAccId"));
					double transferAmount = Double.parseDouble(request.getParameter("amount"));
				
				try {
					sendersSavingsAccount = savingsAccountService.getAccountById(sendersAccountNumber);
				} catch (ClassNotFoundException | SQLException| AccountNotFoundException e) {
					e.printStackTrace();
				}
				try {
					receiversSavingsAccount = savingsAccountService.getAccountById(receiversAccountNumber);
				} catch (ClassNotFoundException | SQLException| AccountNotFoundException e) {
					e.printStackTrace();
				}
				try {
					savingsAccountService.fundTransfer(sendersSavingsAccount, receiversSavingsAccount, transferAmount);
					//DBUtil.commit();
					response.sendRedirect("index.html");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
					
						break;
			case"/curBal.mm":
				response.sendRedirect("currentBalance.html");
				break;
			case"/currentBal.mm":
					int accountnum = Integer.parseInt(request.getParameter("currentBal"));
				try {
					double currentBalance = savingsAccountService.checkBalance(accountnum);
					out.println("<h1>****HDFC Bank****</h1>\n"
							+ "<b>Your Current Balance is : <b>"+currentBalance);
				} catch (ClassNotFoundException | SQLException
						| AccountNotFoundException e) {
					e.printStackTrace();
				}
					
				break;
			}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}

}
