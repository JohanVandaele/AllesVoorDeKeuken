package be.vdab.filters;

import java.io.IOException;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.htm")
public class JPAFilter implements Filter
{
	private static final EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("allesvoordekeuken");
	private static final ThreadLocal<EntityManager> entityManagers=new ThreadLocal<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		ServletContext context = filterConfig.getServletContext();
		context.setAttribute("contextPath", context.getContextPath());
	}

	public static EntityManager getEntityManager()
	{
		return entityManagers.get();
	}	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws ServletException, IOException
	{
		entityManagers.set(entityManagerFactory.createEntityManager());
		
		try
		{
			request.setCharacterEncoding("UTF-8");
			// Je geeft de request door aan de servlet waarvoor hij bestemd is.
			chain.doFilter(request, response);
		}
		finally
		{
			EntityManager entityManager = entityManagers.get();
			
			if (entityManager.getTransaction().isActive())
			{
				entityManager.getTransaction().rollback();
			}
			
			entityManager.close();
			entityManagers.remove();
		}	
	}
	
	@Override
	public void destroy()
	{
		entityManagerFactory.close();
	}
}
