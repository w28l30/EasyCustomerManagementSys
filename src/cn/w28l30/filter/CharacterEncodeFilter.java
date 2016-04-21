package cn.w28l30.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterEncodeFilter
 */
@WebFilter("/CharacterEncodeFilter")
public class CharacterEncodeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CharacterEncodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset="+"UTF-8");
		
		// pass the request along the filter chain
		chain.doFilter(new MyRequest(req), resp);
	}
	
	class MyRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			this.request = request;
		}
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			String val = request.getParameter(name);
			if (request.getMethod().equals("get")) {
				return val;
			}
			if (val == null) {
				return null;
			}
			try {
				return new String(val.getBytes("ISO8859-1"), request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
