import java.awt.Color;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JComponent;


public class Location extends JComponent {
	private InetAddress host, router;
	public int ID;
	private static int count = 0;
	public Color c;
	
	public Location(String h, String r) {
		try {
			setHost(Inet6Address.getByName(h));
			setRouter(Inet6Address.getByName(r));
			ID = count;
			count++;
			c = new Color(96,22,38);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public InetAddress getHost() {
		return host;
	}

	public void setHost(InetAddress host) {
		this.host = host;
	}

	public InetAddress getRouter() {
		return router;
	}

	public void setRouter(InetAddress router) {
		this.router = router;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public boolean equals(Object n) {
    	if (n == null) {
    		return false;
    	}
    	
    	Location temp = (Location) n;
    	
    	if (this.ID == temp.ID) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
