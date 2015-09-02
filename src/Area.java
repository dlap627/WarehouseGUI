import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Area extends JPanel{
	public List<Location> list;
	public Color c;
	public int ID;
	public InetAddress router;
	public static int numbers = 0;
	public final int size = 30;

	public Area() {
		super(new GridLayout(3,3));
		list = new ArrayList<Location>();
		ID = numbers;
		numbers++;
	}
	
	public void add(Location l) {
		this.list.add(l);
		WarehouseGUI.nodes.add(l);
		super.add(l);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 20;
		int y = 20;

		for (Location each : list) {
			g.setColor(each.c);
			g.fillOval(x, y, size, size);
			g.setColor(Color.white);
			g.drawString(String.valueOf(each.ID), x + size/2, y + size/2);
//			System.out.printf("Printing %d with x: %d and y: %d\n",each.ID,x,y);
			
			y = y + 30 + 20;
			
			if(y + size > this.getHeight()) {
				y = 20;
				x = x + 30 + 20;
			}
			
		}
	}
}
