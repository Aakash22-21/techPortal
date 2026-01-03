package helper;

import java.util.List;

import dao.CourseDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListner implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		List<String> statusList = cdao.getDistinctStatus();
		ServletContext ctx = sce.getServletContext();
		ctx.setAttribute("statusList", statusList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	

}
