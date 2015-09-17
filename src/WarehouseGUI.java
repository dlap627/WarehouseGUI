import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;


public class WarehouseGUI extends JPanel {
	public JFrame frame;
	//	public List<Area> areas;
	public Map<String, Area> routers;
	public static List<
	Host> nodes = new ArrayList<Host>();
	public static AtomicBoolean b;
	public static AtomicInteger flag;
	public static DatagramSocket serverSocket;
	public static byte[] receiveData = new byte[1280];
	public static String str;
	public static Inet6Address ip;

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
		flag = new AtomicInteger(0);
		try {
			serverSocket = new DatagramSocket(61619);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				main.createAndShowGUI();
			}
		});
		while (!b.compareAndSet(false, true));

		System.out.println("yay");

		//		main.areas.forEach(area -> area.add(new Location("localhost","localhost")));

		//		for (Area each : main.routers.values()) {
		//			each.add(new Location("localhost","localhost"));
		//		}

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // for command line inputs

		while (true) {
			flag.set(0);
			Thread userInput = (new Thread(new Runnable() {
				public void run() {
					try {
						// Format of input is "Code *space* NodeID *space* Router"
						// Code 0 = Add
						// Code 1 = Remove
						str = inFromUser.readLine().toLowerCase();
						flag.set(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}));

			Thread serverInput = (new Thread(new Runnable() {
				public void run() {
					try {
						serverSocket.receive(receivePacket);
						flag.set(2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}));

			userInput.start();
			serverInput.start();

			while(flag.get() == 0);
			if (flag.get() == 1) {
				serverInput.interrupt();

				processing(main, str);
				
//				String[] input = str.split("\\s+");		

//				if (Integer.parseInt(input[0]) == 0) {
//					if (main.routers.containsKey(input[2])) {
//						main.routers.get(input[2]).add(new Location(input[1],input[2]));
//					}
//					else {
//						System.out.println("Router doesn't exist");
//						continue;
//					}
//				}
//				else if (Integer.parseInt(input[0]) == 1){
//					if (main.routers.containsKey(input[2])) {						
//						Location x = main.routers.get(input[2]).hosts.remove(input[1]);
//						if (x == null) {
//							System.out.println("nothing removed");
//						}
//					}
//					else {
//						System.out.println("Router doesn't exist");
//						continue;
//					}
//				}
//				else {
//					if (main.routers.containsKey(input[2])) {
//						
//					}
//				}

//				main.repaint();
			}
			else {
				userInput.interrupt();
				System.out.println("Packet received");
				ByteBuffer buf = ByteBuffer.wrap(receiveData);
				
				int payload_len = receivePacket.getLength();
				
				byte b;
				str = "";
				
				System.out.println("Payload received:");
				while ((b = buf.get()) != 0x00) {
					str += (char) b;
					System.out.printf("%c ", b);
				}
				System.out.println();
				
				processing(main, str);
				
			}
			main.repaint();
		}

	}
	
	public static void processing(WarehouseGUI main, String str) {
		String[] input = str.split("\\s+");		

		if (Integer.parseInt(input[0]) == 0) {
			if (main.routers.containsKey(input[2])) {
				main.routers.get(input[2]).add(new Host(input[1],input[2]));
			}
			else {
				System.out.println("Router doesn't exist");
				return;
			}
		}
		else if (Integer.parseInt(input[0]) == 1){
			if (main.routers.containsKey(input[2])) {						
				Host x = main.routers.get(input[2]).hosts.remove(input[1]);
				if (x == null) {
					System.out.println("nothing removed");
				}
			}
			else {
				System.out.println("Router doesn't exist");
				return;
			}
		}
		else {
			if (main.routers.containsKey(input[2])) {
				Host x = main.routers.get(input[2]).hosts.get(input[1]);
				if (x == null) {
					System.out.println("Host doesn't exist");
				}
				x.i++;
				x.c = Host.colors[x.i % 7];
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
		Area a1 = new Area(Integer.toString(3));
		a1.setBackground(new Color(37,144,148));
		//		a1.setOpaque(true);
		this.add(a1);

		Area a2 = new Area(Integer.toString(4));
		a2.setBackground(new Color(214,43,73));
		//		a2.setOpaque(true);
		this.add(a2);

		Area a3 = new Area(Integer.toString(5));
		a3.setBackground(new Color(238,165,42));
		//		a3.setOpaque(true);
		this.add(a3);

		Area a4 = new Area(Integer.toString(6));
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