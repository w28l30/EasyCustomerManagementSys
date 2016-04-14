package cn.w28l30.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.w28l30.domain.Customer;
import cn.w28l30.service.BusinessServiceImpl;
import cn.w28l30.utils.Globals;
import cn.w28l30.utils.WebUtils;

/**
 * Servlet implementation class AddCustomerServlet
 */
@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("genders", Globals.genders);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);
		request.getRequestDispatcher("WEB-INF/jsp/addcustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Customer c = WebUtils.request2Bean(request, Customer.class);
			c.setId(WebUtils.generateID());
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.addCustomer(c);
			request.setAttribute("message", "Success!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Failed!");
		}
		request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
	}

}
