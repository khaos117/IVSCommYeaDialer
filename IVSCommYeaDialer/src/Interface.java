import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentListener;

public class Interface {
	public static String Url1 = "http://";
	public static String Url2 = "/servlet?key=number=";
	static DocumentListener ipListener;
	static DocumentListener numberListener;
	static JTextField ipAddressField;
	static JTextField numberField;
	static JTextField sNameField;
	static JTextField sNumberField;
	static String ipAddress = "";
	static String number = "";
	private static BufferedImage image;
	static ConcurrentMap<String, DialEntry> dialMap;


	private static class YeaDialer extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image,3,4,150,50,this);
			//g.drawString( "Work Already!", 20, 30 );
		}
	}
	
	private static class YeaDialerSpeed extends JPanel {
		
		
	}

	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ipAddress=ipAddressField.getText();
			number=numberField.getText();
			urlBuilderSender(ipAddress,number);
			//System.exit(0);
		}
	}

	public static void urlBuilderSender(String ipAddress, String number) {
		String url;
		String login = "admin" + ":" + "674872666";
		String base64login = new String(Base64.encodeBase64(login.getBytes()));
		try {
			url = new String(Url1+ipAddress+Url2+number);
			System.out.println(url);

			Connection.Response response = Jsoup
					.connect(url)
					.timeout(30000)
					.method(Connection.Method.GET)
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
					.header("Authorization", "Basic " + base64login)
					.execute();

			//Document document = response.parse();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			   
	}

	public static void main(String[] args) {

		YeaDialer displayPanel = new YeaDialer();
		JButton dialButton = new JButton("Dial");
		ButtonHandler listener = new ButtonHandler();
		dialButton.addActionListener(listener);
		ipAddressField = new JTextField("Enter phone IP Address",12);
		numberField = new JTextField("Enter number you wish to dial",17);
		sNameField = new JTextField("Speedial Name",10);
		sNumberField = new JTextField("Speedial Number",10);
		try {
			image = ImageIO.read(new File("IVSLOGOExt.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ipAddressField.getDocument().addDocumentListener(ipListener);
		//numberField.getDocument().addDocumentListener(numberListener);

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(displayPanel, BorderLayout.CENTER);
		content.add(ipAddressField, BorderLayout.NORTH);
		content.add(numberField, BorderLayout.WEST);
		content.add(dialButton, BorderLayout.EAST);

		JPanel speeddial = new JPanel();
		speeddial.setLayout(new BorderLayout());

		JFrame window = new JFrame("Dialer Interface");
		window.setContentPane(content);
		window.setSize(400,115);
		window.setLocation(890,100);
		window.setVisible(true);
		
		JFrame sWindow = new JFrame("Speeddials");
		sWindow.setContentPane(speeddial);
		sWindow.setSize(400,115);
		sWindow.setLocation(890,215);
		sWindow.setVisible(true);
	}				   
}


