import java.awt.Color;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JComponent;


public class Location extends JComponent {
	public String host, router;
	public int ID;
	private static int count = 0;
	public Color c;
	
	public Location(String h, String r) {
		host = h;
		router = r;
		ID = count;
		count++;
		c = new Color(96,22,38);
	}
}
