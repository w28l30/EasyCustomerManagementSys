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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		try {
			BusinessServiceImpl service = new BusinessServiceImpl();
			String id = request.getParameter("id");
			
			request.setAttribute("genders", Globals.genders);
			request.setAttribute("preferences", Globals.preferences);
			request.setAttribute("types", Globals.types);
			
			Customer c = service.findCustomer(id);
			request.setAttribute("customer", c);
			request.getRequestDispatcher("WEB-INF/jsp/updatecustomer.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Find failed");
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			BusinessServiceImpl service = new BusinessServiceImpl();
			Customer c = WebUtils.request2Bean(request, Customer.class);
			
			service.updateCustomer(c);
			request.setAttribute("message", "Update Successfully!");
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Update failed");
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
		}
	}

}
