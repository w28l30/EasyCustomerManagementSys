package cn.w28l30.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

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

/**
 * Servlet Filter implementation class GzipFilter
 */
@WebFilter("/GzipFilter")
public class GzipFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public GzipFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// pass the request along the filter chain
		MyResponse myResponse = new MyResponse(resp);
		chain.doFilter(request, myResponse);
		
		byte[] buffer = myResponse.getBuffer();
		byte[] out = gZip(buffer);
		
		resp.setHeader("content-encoding", "gzip");
		resp.setHeader("content-length", out.length + "");
		resp.getOutputStream().write(out);
	}
	
	public byte[] gZip(byte[] buffer) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream(); 
			GZIPOutputStream gout = new GZIPOutputStream(bout);
			gout.write(buffer);
			gout.close();
			return bout.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}

	class MyResponse extends HttpServletResponseWrapper {
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;

		public MyResponse(HttpServletResponse response) {
			super(response);
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
			pw = new PrintWriter(bout);
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
