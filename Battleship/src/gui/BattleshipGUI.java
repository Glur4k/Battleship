package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Field.state;
import observer.IObserver;
import controller.Controller;

public class BattleshipGUI extends JFrame implements IObserver {

	public static JPanel mainPanel;
	// action: 0 = eigenes feld nur anzeigen; 1 = ruderboot setzen
	private int action = 0;
	private JPanel fieldsPanel;
	private JButton JBsize;
	private JLabelE[][] labelField;
    private JTextField JTFsize;
	BattleshipInfos infoPanel;
	private int fieldsize;
	private static final int HEX = 65;
	private JPanel fieldPanel;
	private Controller controller;
	BattleshipDialogs dialogs;
	int i, j;
	URL resource = BattleshipGUI.class.getResource("/images/Field.jpg");
    URL resourceSelected = BattleshipGUI.class.getResource("/images/FieldS.jpg");
    URL resourcePattern = BattleshipGUI.class.getResource("/images/Pattern.jpg");
    URL resourceRowboatNormal = BattleshipGUI.class.getResource("/images/RowboatNormal.jpg");
    Icon icon = new ImageIcon(resource);
    Icon icon2 = new ImageIcon(resourceSelected);
    Icon pattern = new ImageIcon(resourcePattern);
    Icon rowboatNormal = new ImageIcon(resourceRowboatNormal);
 	
	
	public BattleshipGUI(Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		dialogs = new BattleshipDialogs(this);
	}
	
	public void printMainFrame() {
		infoPanel = new BattleshipInfos();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
	    showPlayboards();
	    mainPanel.add(infoPanel, BorderLayout.NORTH);
	    mainPanel.add(fieldsPanel, BorderLayout.SOUTH);

	    Dimension d = new Dimension(285, 118);
        mainPanel.setMaximumSize(d);
        mainPanel.setMinimumSize(d);
	    this.setContentPane(mainPanel);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	public boolean onSetFieldsize() {
		dialogs.setFieldsize();
		fieldsize = controller.getFieldsize();
		printMainFrame();
		return true;
	}
	
	public int getFieldsize() {
		return controller.getFieldsize();
	}
	
	public boolean onSetRowboat () {
		dialogs.setShip(0);
		return true;
	}

	public boolean onSetDestructor() {
		dialogs.setShip(1);
		return true;
	}

	public boolean onSetFlattop() {
		dialogs.setShip(2);
		return true;
	}

	public void onShowMenu() {
		// TODO Auto-generated method stub
		
	}

	public void onAction() {
		// TODO Auto-generated method stub
		
	}

	public void onShowPlayersField() {
		// TODO Auto-generated method stub
		
	}

	public void onShowBotsField(boolean withShip) {
		// TODO Auto-generated method stub
		
	}

	public void onShootOnBot() {
		// TODO Auto-generated method stub
		
	}

	public void onStatus() {
		// TODO Auto-generated method stub
		
	}
	
	public void showPlayboards() {
		fieldsPanel = new JPanel();
		//fieldsPanel.setMinimumSize(new Dimension(304, 313));
		fieldsPanel.setLayout(new BorderLayout(0, 0));
		fieldsPanel.add(printPlayboard(), BorderLayout.WEST);
		fieldsPanel.add(printPlayboard(), BorderLayout.EAST);
	}
	
	private JPanel printPlayboard() {
		JPanel playboardPanel = new JPanel();
		int pattern = 25;
		int width = 25 * fieldsize + pattern;
		Border borderLT = BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(135, 180, 255));
		Border borderTop = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(135, 180, 255));
		Border borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(135, 180, 255));
		Border borderRB = BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(135, 180, 255));
	    playboardPanel.setLayout(new GridLayout(fieldsize+1, fieldsize+1));
	    playboardPanel.setSize(width, width);
		JLabelE[][] fields = new JLabelE[fieldsize + 2][fieldsize + 2];
		for (int i = 0; i < fieldsize + 1; i++) {
			for (int j = 0; j < fieldsize + 1; j++) {
				if (i == 0) {
					if (j == 0) {
						fields[i][j] = new JLabelE(this.pattern);
						playboardPanel.add(fields[i][j]);
					} else {
					fields[i][j + 1] = new JLabelE(this.pattern);
					fields[i][j + 1].setText("<html><font style=\"font-size:14px; font-family: " +
							"trebuchet ms,helvetica,sans-serif; color:#3f87ff;\">" + 
							String.valueOf((char) (j - 1 + HEX)) + "</font></html>");
					fields[i][j + 1].setHorizontalTextPosition(JLabel.CENTER);
					fields[i][j + 1].setVerticalTextPosition(JLabel.CENTER);
					fields[i][j + 1].setBorder(borderLeft);
					playboardPanel.add(fields[i][j + 1]);
					}
				} else if (j == 0) {
					fields[i][j] = new JLabelE(this.pattern);
					fields[i][j].setText("<html><font style=\"font-size:14px; font-family: " +
							"trebuchet ms,helvetica,sans-serif; color:#3f87ff;\">" + 
							String.valueOf(i) + "</font></html>");
					fields[i][j].setHorizontalTextPosition(JLabel.CENTER);
					fields[i][j].setVerticalTextPosition(JLabel.CENTER);
					fields[i][j].setBorder(borderTop);
					playboardPanel.add(fields[i][j]);
				} else {
					fields[i][j] = new JLabelE(icon);
					//checkStateHuman(fields, i, j);
					fields[i][j].setSize(25, 25);
					fields[i][j].setPosition(i, j);
					fields[i][j].setBorder(borderLT);
					fields[i][j].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
							lbl.setIcon(icon2);
						}
						@Override
						public void mouseExited(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
							lbl.setIcon(icon);
						}
						@Override
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							JLabelE lbl = (JLabelE)evt.getSource();
						}
					});
					playboardPanel.add(fields[i][j]);
				}
			}
		}
		return playboardPanel;
	}
	
	private void checkStateHuman(JLabelE[][] fields, int i, int j) {
		state stat = controller.getState(controller.getPlayer().getPlayboard().getField()[i][j]);
		if (stat == state.empty) {
			fields[i][j] = new JLabelE(icon);
		} else if (stat == state.rowboat) {
			fields[i][j] = new JLabelE(rowboatNormal);
		}
	}
}