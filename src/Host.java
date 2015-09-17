import java.awt.Color;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JComponent;


public class Host extends JComponent {
	public String host, router;
	public int ID;
	private static int count = 0;
	public Color c;
	public int i;
	public static final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.MAGENTA}; 
	
	public Host(String h, String r) {
		host = h;
		router = r;
		ID = count;
		count++;
//		c = new Color(96,22,38);
		i = 0;
		c = colors[i];		
	}
}
