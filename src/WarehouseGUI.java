import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;


public class WarehouseGUI extends JPanel {
	public JFrame frame;
	public List<Area> areas;
	public static AtomicBoolean b;
	
	public WarehouseGUI(GridLayout g) {
		super(g);
		frame = new JFrame("Warehouse Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		areas = new ArrayList<Area>();
	}
	
	public static void main(String[] args) {
		WarehouseGUI main = new WarehouseGUI(new GridLayout(2,2));
		
		b = new AtomicBoolean(true);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main.createAndShowGUI();
            }
        });
		while (!b.compareAndSet(false, true));
		
		System.out.println("yay");
		
		main.areas.forEach(area -> area.add(new Location("localhost","localhost")));
		main.repaint();
	}
	
	public synchronized void add(Area a) {
		super.add(a);
		this.areas.add(a);
	}
	
	private void createAndShowGUI() {
        //Add the ubiquitous "Hello World" label.
		Area a1 = new Area();
		a1.setBackground(Color.BLUE);
//		a1.setOpaque(true);
		this.add(a1);
		
		Area a2 = new Area();
		a2.setBackground(Color.RED);
//		a2.setOpaque(true);
		this.add(a2);
		
		Area a3 = new Area();
		a3.setBackground(Color.GREEN);
//		a3.setOpaque(true);
		this.add(a3);
		
		Area a4 = new Area();
		a4.setBackground(Color.YELLOW);
//		a4.setOpaque(true);
		this.add(a4);
		
		this.frame.getContentPane().setSize(640 * 2, 480 * 2);
		this.setSize(640 * 2, 480 * 2);
		this.frame.add(this);
		this.frame.setSize(640, 480);

        //Display the window.
//        this.frame.pack();
        this.frame.setVisible(true);
//        this.frame.setResizable(false);
        b.set(false);
    }
}