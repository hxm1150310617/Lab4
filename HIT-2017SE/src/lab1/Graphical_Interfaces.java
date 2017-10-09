package lab1;

// on B2
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Graphical_Interfaces extends JFrame implements ActionListener {
	JTextField filepath = new JTextField(60);
	JTextField filename = new JTextField(60);
	JButton generate = new JButton("Generate");
	JButton show = new JButton("Show Graph");

	JTextField word1 = new JTextField(10);
	JTextField word2 = new JTextField(10);
	JButton query = new JButton("Query");
	JTextArea bridgeWords = new JTextArea(10, 40);

	JTextArea sentence = new JTextArea(10, 20);
	JTextArea newSentence = new JTextArea(10, 20);
	JButton newText = new JButton("New Text");

	JTextField word1sp = new JTextField(10);
	JTextField word2sp = new JTextField(10);
	JButton shortpathbutton = new JButton("Shortest Path");
	JTextField wordss = new JTextField(10);
	JButton wordssbutton = new JButton("Shortest Path Single Source");
	JTextArea sgshortpath = new JTextArea(10, 40);
	JScrollPane scroll = new JScrollPane(sgshortpath);
	
	JButton randomWalkButton = new JButton("Start Random Walk");
	JTextArea randomWalkResult = new JTextArea(10,50);
	JScrollPane randomWalkScroll = new JScrollPane(randomWalkResult);
	
	public Graphical_Interfaces() {
		super("Text Execute");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		// pack();
		this.setSize(800, 400);
//		FlowLayout lm= new FlowLayout(FlowLayout.LEFT);
//		setLayout(lm);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel pane1 = new JPanel();
		JPanel pane2 = new JPanel();
		JPanel pane3 = new JPanel();
		JPanel pane4 = new JPanel();
		JPanel pane5 = new JPanel();
		JLabel filepathLabel = new JLabel("Filepath:");
		JLabel filenameLabel = new JLabel("Filename:");
		JLabel word1label = new JLabel("Input word1:");
		JLabel word2label = new JLabel("Input word2:");
		JLabel bridgeResultLabel = new JLabel("Query Result:");
		JLabel sentenceLabel = new JLabel("Input your text:");
		JLabel newSentenceLabel = new JLabel("The new text is:");
		JLabel word1spLabel = new JLabel("Input word1:");
		JLabel word2spLabel = new JLabel("Input word2:");
		JLabel wordssLabel = new JLabel("Input word (Single source) :");
		JLabel sgshortpathLabel = new JLabel("The paths are:");
		JLabel randomWalkLabel = new JLabel("The random-walk path is:");
		generate.addActionListener(this);
		generate.setSize(10, 5);
		show.addActionListener(this);
		show.setSize(10, 5);
		JTabbedPane tabs = new JTabbedPane();
		pane1.add(filepathLabel);
		pane1.add(filepath);
		pane1.add(filenameLabel);
		pane1.add(filename);
		pane1.add(generate);
		pane1.add(show);
		tabs.addTab("Generate", pane1);
		query.addActionListener(this);
		bridgeWords.setEditable(false);
		pane2.add(word1label);
		pane2.add(word1);
		pane2.add(word2label);
		pane2.add(word2);
		pane2.add(query);
		pane2.add(bridgeResultLabel);
		pane2.add(bridgeWords);
		tabs.addTab("Bridge Words", pane2);
		newText.addActionListener(this);
		pane3.add(sentenceLabel);
		pane3.add(sentence);
		pane3.add(newSentenceLabel);
		pane3.add(newSentence);
		pane3.add(newText);
		tabs.addTab("New Text", pane3);
		shortpathbutton.addActionListener(this);
		wordssbutton.addActionListener(this);
		sgshortpath.setEditable(false);
		pane4.add(word1spLabel);
		pane4.add(word1sp);
		pane4.add(word2spLabel);
		pane4.add(word2sp);
		pane4.add(shortpathbutton);
		pane4.add(wordssLabel);
		pane4.add(wordss);
		pane4.add(wordssbutton);
		pane4.add(sgshortpathLabel);
		//pane4.add(sgshortpath);
		pane4.add(scroll);
		tabs.addTab("Shortest Path", pane4);
		randomWalkButton.addActionListener(this);
		pane5.add(randomWalkButton);
		pane5.add(randomWalkLabel);
		//pane5.add(randomWalkResult);
		pane5.add(randomWalkScroll);
		tabs.addTab("Random Walk", pane5);
		add(tabs);
		
		setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			setUIFont();
			SwingUtilities.updateComponentTreeUI(this);

		} catch (Exception exc) {
			System.err.println("Couldn't use the system " + "look and feel: " + exc);
		}
	}

	public static void main(String arguments[]) {
		Graphical_Interfaces GUI = new Graphical_Interfaces();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == generate) {
			Main3.setdataPath(filepath.getText());
			try {
				Main3.EWD = Main3.createDirectedGraph(filename.getText());
				EdgeWeightedDigraph.setPath(filepath.getText());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (source == show) {
			try {

				Main3.showDirectedGraph(Main3.EWD);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (source == query) {
			bridgeWords.setText(
					Main3.queryBridgeWords(Main3.EWD, word1.getText().toLowerCase(), word2.getText().toLowerCase()));

		}
		if (source == newText) {
			newSentence.setText(Main3.generateNewText(Main3.EWD, sentence.getText().toLowerCase()));
		}
		if (source == shortpathbutton) {
			try {
				Main3.calcShortestPath(Main3.EWD, word1sp.getText().toLowerCase(), word2sp.getText().toLowerCase());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (source == wordssbutton) {
			try {
				sgshortpath.setText(Main3.calcShortestPathss(Main3.EWD, wordss.getText().toLowerCase()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(source==randomWalkButton) {
			try {
				randomWalkResult.setText(Main3.randomWalk(Main3.EWD));
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		repaint();
	}

	public static void setUIFont() {
		Font f = new Font("consola", Font.PLAIN, 24);
		String names[] = { "Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem", "JRadioButtonMenuItem",
				"ComboBox", "Button", "Tree", "ScrollPane", "TabbedPane", "EditorPane", "TitledBorder", "Menu",
				"TextArea", "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip", "ProgressBar", "TableHeader",
				"Panel", "List", "ColorChooser", "PasswordField", "TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame" };
		for (String item : names) {
			UIManager.put(item + ".font", f);
		}
	}
}
