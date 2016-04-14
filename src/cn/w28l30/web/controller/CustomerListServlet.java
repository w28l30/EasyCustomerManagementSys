package cn.w28l30.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.w28l30.domain.PageBean;
import cn.w28l30.domain.QueryInfo;
import cn.w28l30.service.BusinessServiceImpl;
import cn.w28l30.utils.WebUtils;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerListServlet() {
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
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			BusinessServiceImpl service = new BusinessServiceImpl();
			PageBean pageBean = service.pageQuery(info);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("WEB-INF/jsp/customerlist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Failed!");
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
		doGet(request, response);
	}

}
