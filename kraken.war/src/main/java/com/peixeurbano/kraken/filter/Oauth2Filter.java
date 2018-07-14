package com.peixeurbano.kraken.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.peixeurbano.kraken.bean.SessionBean;
import com.peixeurbano.kraken.interfaces.UserDaoRemote;

/**
 * Servlet Filter implementation class Oauth2Filter
 */

public class Oauth2Filter implements Filter {

	
	@Inject
	private SessionBean sessionBean;
	
	@EJB(lookup = "java:global/kraken.sqlserver/UserDao")
	private UserDaoRemote userDao;
	
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		
		 if(this.sessionBean.getUser()==null){			
		 	this.sessionBean.setUser( this.userDao.getUserByMail(((HttpServletRequest)request).getHeader("x-forwarded-email")) );			
		 }	
		
		if((!((HttpServletRequest)request).getRequestURI().contains("acessblock"))&&(!this.sessionBean.getUser().getActive())){
		 	request.getRequestDispatcher("acessblock.html").forward(request,response);
		}else{			 
			chain.doFilter(request, response);
		}	
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
