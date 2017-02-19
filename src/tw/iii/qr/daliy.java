package tw.iii.qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

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
		GETIP();
		StartGetEbayOrderAndDailyBalance();
	}

	private static void StartGetEbayOrderAndDailyBalance() {

		Timer t1 = new Timer();
		TimerTask myTask = new TimerTask() {
			@Override
			public void run() {
				new CGetEbay().CGetEbay1();
				new DayliBalanceSheetFactory().dayliBalanceSheet();
				System.out.println("撈完訂單+上日結表");
			}
		};

		t1.scheduleAtFixedRate(myTask, 5000, 1800000);
		
	}

	public static void ClickGetEbayOrderAndDailyBalance(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out = response.getWriter();
		TimerTask myTask = new TimerTask() {
			@Override
			public void run() {
				new CGetEbay().CGetEbay1();
				new DayliBalanceSheetFactory().dayliBalanceSheet();
				System.out.println("da done");
				out.write("<script type='text/javascript'>");
				out.write("alert('Complete');");
				out.write("window.location = '../HomePage.jsp';");
				out.write("</script>");
			}
		};
		myTask.run();
		
	}

	private void GETIP() {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String iptxt = fsv.getHomeDirectory() + File.separator + "QRexcel" + File.separator + "Setting" + File.separator
				+ "IP.txt";
		Boolean isGETIP = false;
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(iptxt));
			String line = "";
			while ((line = in.readLine()) != null) {
				if (line.contains("ip=")) {
					ip = line.substring(3, line.length()).trim();
					if (ip == "") {
						throw new Exception("沒有輸入伺服器ip");
					}
					System.out.println(ip);
				} else {
					throw new Exception("沒有輸入 ip= 這一行 , ex: ip=1.1.1.1");
				}
			}
			isGETIP = true;
			in.close();
		} catch (Exception e1) {
			System.out.println(e1);
			System.out.println("使用本機IP");
		}
		if (!isGETIP) {
			InetAddress myComputer = null;
			try {
				myComputer = InetAddress.getLocalHost();
				System.out.println(myComputer.getHostAddress());
			} catch (UnknownHostException e) {
				System.out.println("取得本機IP失敗。");
			}
		}
	}
}
