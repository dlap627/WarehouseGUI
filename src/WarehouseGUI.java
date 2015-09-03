import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;


public class WarehouseGUI extends JPanel {
	public JFrame frame;
//	public List<Area> areas;
	public Map<String, Area> routers;
	public static List<
	Location> nodes = new ArrayList<Location>();
	public static AtomicBoolean b;
	
	public WarehouseGUI(GridLayout g) {
		super(g);
		frame = new JFrame("Warehouse Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		areas = new ArrayList<Area>();
		routers = new HashMap<String, Area>();
	}
	
	public static void main(String[] args) {
		final WarehouseGUI main = new WarehouseGUI(new GridLayout(2,2));
		
		b = new AtomicBoolean(true);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	main.createAndShowGUI();
            }
        });
		while (!b.compareAndSet(false, true));
		
		System.out.println("yay");
		
//		main.areas.forEach(area -> area.add(new Location("localhost","localhost")));
		
		for (Area each : main.routers.values()) {
			each.add(new Location("localhost","localhost"));
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // for command line inputs
		
		while (true) {
			try {
				String str = inFromUser.readLine().toLowerCase();
				// Format of input is "Code *space* NodeID *space* Router"
				// Code 0 = Add
				// Code 1 = Remove
				
				String[] input = str.split("\\s+");
				
				
				
				
				if (Integer.parseInt(input[0]) == 0) {
					if (main.routers.containsKey(input[2])) {
//						main.routers.put(input[1],new Area(input[1],input[2]));
						main.routers.get(input[2]).add(new Location(input[1],input[2]));
					}
					else {
						System.out.println("Router doesn't exist");
						continue;
					}
				}
				else {
					if (main.routers.containsKey(input[2])) {						
						Location x = main.routers.get(input[2]).hosts.remove(input[1]);
						if (x == null) {
							System.out.println("nothing removed");
						}
					}
					else {
						System.out.println("Router doesn't exist");
						continue;
					}
				}

				main.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public synchronized void add(Area a) {
		super.add(a);
//		this.areas.add(a);
		this.routers.put(a.id, a);
	}
	
	private void createAndShowGUI() {
        //Add the ubiquitous "Hello World" label.
		Area a1 = new Area();
		a1.setBackground(new Color(37,144,148));
//		a1.setOpaque(true);
		this.add(a1);
		
		Area a2 = new Area();
		a2.setBackground(new Color(214,43,73));
//		a2.setOpaque(true);
		this.add(a2);
		
		Area a3 = new Area();
		a3.setBackground(new Color(238,165,42));
//		a3.setOpaque(true);
		this.add(a3);
		
		Area a4 = new Area();
		a4.setBackground(new Color(189,181,143));
//		a4.setOpaque(true);
		this.add(a4);
		
		this.frame.getContentPane().setSize(640 * 2, 480 * 2);
		this.setSize(640 * 2, 480 * 2);
		this.frame.add(this);
		this.frame.setSize(640, 480);

        //Display the window.
//        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        b.set(false);
    }
}