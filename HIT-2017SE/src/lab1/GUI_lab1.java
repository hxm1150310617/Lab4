package lab1;

//Edit on B1

import java.awt.Dimension;
import java.io.File;

import javax.swing.*;

import com.sun.prism.Image;

public class GUI_lab1 extends JFrame {
	public GUI_lab1(String picPath) {
		super("Show Graph ");
		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel pane = new JPanel();
		ImageIcon pic=new ImageIcon(picPath);
		JLabel label = new JLabel(pic);
		//label.setPreferredSize(new Dimension(2500,6000));
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(label, v, h);
		//pane.add(label);
		//add(scrollPane);
		//JScrollPane scrollPane = new JScrollPane(pane);
//		scrollPane.add(pane);
		//add(scrollPane);
		add(jsp);
		setLookAndFeel();
		setVisible(true);
		System.out.println(picPath);
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf." + "nimbus.NimbusLookAndFeel");
		} catch (Exception exc) {

		}
	}

	public static void main(String[] arguments) {
		GUI_lab1.showPic("C:\\Users\\10297\\SoftwareEngineering\\HIT-2017SE\\data\\graph_out.png");
	}

	public static void showPic(String picPath) {
		new GUI_lab1(picPath);
	}

}
