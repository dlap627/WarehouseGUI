import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;


public class Area extends JPanel{
	public List<Location> list;
	public Map<String, Location> hosts;
	public Color c;
	public int ID;
	public String id;
	public InetAddress router;
	public static int numbers = 0;
	public final int size = 30;

	public Area() {
		super(new GridLayout(3,3));
		list = new ArrayList<Location>();
		hosts = new HashMap<String, Location>();
		ID = numbers;
		id = String.valueOf(ID);
		numbers++;
	}
	public Area(String r) {
		this();
		id = r;
	}
	
	public void add(Location l) {
		this.list.add(l);
		WarehouseGUI.nodes.add(l);
		this.hosts.put(l.host, l);
		super.add(l);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 20;
		int y = 20;
		g.drawString(this.id, this.getWidth()/2, this.getHeight()/2);

		for (Location each : hosts.values()) {
			g.setColor(each.c);
			g.fillOval(x, y, size, size);
			g.setColor(Color.white);
			g.drawString(/*String.valueOf(each.ID)*/ each.host, x + size/2, y + size/2);
//			System.out.printf("Printing %d with x: %d and y: %d\n",each.ID,x,y);
			
			y = y + 30 + 20;
			
			if(y + size > this.getHeight()) {
				y = 20;
				x = x + 30 + 20;
			}
		}
	}
}
