package tw.iii.qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.filechooser.FileSystemView;

import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.net.SecureNio2Channel.ApplicationBufferHandler;

import tw.iii.qr.order.CGetEbay;
import tw.iii.qr.order.DayliBalanceSheetFactory;

/**
 * Application Lifecycle Listener implementation class daliy
 *
 */
@WebListener
public class daliy implements ServletContextListener {
	public static ServletContext servletContext;
	public static String ip;

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

		servletContext = a.getServletContext();
		try {
			FileSystemView fsv = FileSystemView.getFileSystemView();
			String iptxt = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "Setting"
					+ File.separator + "IP.txt";
			BufferedReader in;

			in = new BufferedReader(new FileReader(iptxt));

			String line = "";

			while ((line = in.readLine()) != null) {
				if (line.contains("ip=")) {
					ip = line.substring(3, line.length()).trim();
					System.out.println(ip);
				}
			}
			in.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Timer t1 = new Timer();
		t1.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {

					new Thread() {
						@Override
						public void run() {

							
								new CGetEbay().CGetEbay1();
								new DayliBalanceSheetFactory().dayliBalanceSheet();
								System.out.println("da done");
							

						}
					}.start();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 1800000);

	}

}
