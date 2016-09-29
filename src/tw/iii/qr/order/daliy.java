package tw.iii.qr.order;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.net.SecureNio2Channel.ApplicationBufferHandler;

/**
 * Application Lifecycle Listener implementation class daliy
 *
 */
@WebListener
public class daliy implements ServletContextListener {
	boolean isContinued = true;
	Timer t1 = new Timer();
	/**
	 * Default constructor.
	 */
	public daliy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent a) {

		getdailyandorders(a);

	}

	public void getdailyandorders(ServletContextEvent a) {
		
		t1.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					while (isContinued) {
						new Thread() {
							@Override
							public void run() {

								try {
									while (isContinued) {
										LinkedList<COrders> da = new DayliBalanceSheetFactory().dayliBalanceSheet();
										a.getServletContext().setAttribute("ndbs", da);
										System.out.println("da done");
									}
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}.start();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 900000);
	}

	public void terminate() {
		isContinued = false;
	}

	public void restart() {
		isContinued = true;

	}
}
