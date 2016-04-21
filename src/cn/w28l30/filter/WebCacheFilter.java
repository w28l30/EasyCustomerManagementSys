package cn.w28l30.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import cn.w28l30.filter.GzipFilter.MyServletOutputStream;

/**
 * Servlet Filter implementation class WebCacheFilter
 */
@WebFilter("/WebCacheFilter")
public class WebCacheFilter implements Filter {
	private Map<String, byte[]> map = new HashMap<String, byte[]>();

    /**
     * Default constructor. 
     */
    public WebCacheFilter() {
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
		
		//1.get the request uri
		String uri = req.getRequestURI();
		//2.check whether the resource exist in map, if yes return the source
		byte[] buffer = map.get(uri);
		System.out.println("filter");
		if (buffer != null) {
			resp.getOutputStream().write(buffer);
			chain.doFilter(request, response);
			return;
		}
		//3.if not, get the resource and get a response wrapper and write the resource into the buffer array
		MyResponse myResponse = new MyResponse(resp); 
		chain.doFilter(request, myResponse);
		
		buffer = myResponse.getBuffer();
		map.put(uri, buffer);
		resp.getOutputStream().write(buffer);
	}
	
	class MyResponse extends HttpServletResponseWrapper {
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;
		private HttpServletResponse response;

		public MyResponse(HttpServletResponse response) {
			super(response);
			this.response = response;
			// TODO Auto-generated constructor stub
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {

			// TODO Auto-generated method stub
			return new MyServletOutputStream(bout);
		}
		
		
		@Override
		public PrintWriter getWriter() throws IOException {
			// TODO Auto-generated method stub
			pw = new PrintWriter(new OutputStreamWriter(bout,this.response.getCharacterEncoding()));
			return pw;
		}

		public byte[] getBuffer() {
			if (pw != null) {
				pw.close();
			}
			return bout.toByteArray();
		}

	}

	class MyServletOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream bout;

		public MyServletOutputStream(ByteArrayOutputStream bout) {
			super();
			this.bout = bout;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			bout.write(b);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
