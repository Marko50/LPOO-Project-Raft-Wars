package dkeep.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import dkeep.logic.GameMap;
import dkeep.logic.GameState;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MapEditor {
	private GraphicalInterface gi;
	//private GameState game;
	public GraphicsPanelMapEditor gpme;
	public JFrame frame;
	public JButton btnStartGame;
	public JButton btnExitGame;
	public JButton btnDoorClosed;
	public JButton btnWall;
	public JButton btnFloor;
	public JButton btnHero;
	public JButton btnOgre;
	public JButton btnKey;
	public JButton btnLever;
	private JTextField textField;
	private JTextField textField_1;
	public JButton btnDoorOpened;
	public JButton btnGenerate;
	public boolean HeroSelected;
	public boolean OgreSelected;
	public boolean DoorOpenedSelected;
	public boolean DoorClosedSelected;
	public boolean KeySelected;
	public boolean LeverSelected;
	public boolean WallSelected;
	public boolean FloorSelected;
	
	public boolean ogreUsed = false;
	public boolean heroUsed = false;
	public boolean keyUsed = false;
	public boolean leverUsed = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapEditor window = new MapEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void enableButtons()
	{
		btnStartGame.setEnabled(true);
		btnExitGame.setEnabled(true);
		btnDoorClosed.setEnabled(true);
		btnDoorOpened.setEnabled(true);
		btnWall.setEnabled(true);
		btnFloor.setEnabled(true);
		btnHero.setEnabled(true);
		btnOgre.setEnabled(true);
		btnKey.setEnabled(true);
		btnLever.setEnabled(true);
	}
	
	public void disableButtons()
	{
		btnStartGame.setEnabled(false);
		btnExitGame.setEnabled(false);
		btnDoorClosed.setEnabled(false);
		btnDoorOpened.setEnabled(false);
		btnWall.setEnabled(false);
		btnFloor.setEnabled(false);
		btnHero.setEnabled(false);
		btnOgre.setEnabled(false);
		btnKey.setEnabled(false);
		btnLever.setEnabled(false);
	}
	
	public int parseInt(String text)
	{
		int num;
		
		try
		{
			num = Integer.parseInt(text);
			return num;
		}
		
		catch(NumberFormatException e){
			return 11;
		}
	}
	
	
	public void generateEmptyMap(int y, int x)
	{
		char[][] aux = new char[y][x];
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				if(j == 0 || i == 0 || j == x-1 || i == y-1)
					aux[i][j] = 'X';
				else
					aux[i][j] = ' ';
			}
		}
		
		getGi().getGame().setMapa(new GameMap(aux));
	}	
	/**
	 * Create the application.
	 */	
	public MapEditor(GraphicalInterface gg)
	{
		this.setGi(gg);
		initialize(); 	
	}
	
	public MapEditor() {
		initialize();
	}

	public void init()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.pack();
		frame.setBounds(100, 100, 492, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setPreferredSize(new Dimension(450, 450));
		btnDoorClosed = new JButton("Door Closed");
		springLayout.putConstraint(SpringLayout.EAST, btnDoorClosed, 420, SpringLayout.WEST, frame.getContentPane());
		btnDoorClosed.setEnabled(false);
		btnDoorClosed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = true;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		frame.getContentPane().add(btnDoorClosed);
		
		btnWall = new JButton("Wall");
		springLayout.putConstraint(SpringLayout.NORTH, btnWall, 5, SpringLayout.SOUTH, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.SOUTH, btnWall, 35, SpringLayout.SOUTH, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.EAST, btnWall, 0, SpringLayout.EAST, btnDoorClosed);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = true;
				FloorSelected = false;
			}
		});
		btnWall.setEnabled(false);
		frame.getContentPane().add(btnWall);
		
		btnFloor = new JButton("Floor");
		springLayout.putConstraint(SpringLayout.EAST, btnFloor, 0, SpringLayout.EAST, btnDoorClosed);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = true;
			}
		});
		btnFloor.setEnabled(false);
		frame.getContentPane().add(btnFloor);
		
		btnLever = new JButton("Lever");
		springLayout.putConstraint(SpringLayout.SOUTH, btnFloor, 35, SpringLayout.SOUTH, btnLever);
		springLayout.putConstraint(SpringLayout.EAST, btnLever, 0, SpringLayout.EAST, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.NORTH, btnFloor, 5, SpringLayout.SOUTH, btnLever);
		btnLever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = true;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnLever.setEnabled(false);
		frame.getContentPane().add(btnLever);
		
		btnKey = new JButton("Key");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLever, 35, SpringLayout.SOUTH, btnKey);
		springLayout.putConstraint(SpringLayout.EAST, btnKey, 0, SpringLayout.EAST, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.NORTH, btnLever, 5, SpringLayout.SOUTH, btnKey);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = true;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnKey.setEnabled(false);
		frame.getContentPane().add(btnKey);
		
		btnOgre = new JButton("Ogre");
		springLayout.putConstraint(SpringLayout.NORTH, btnKey, 5, SpringLayout.SOUTH, btnOgre);
		springLayout.putConstraint(SpringLayout.SOUTH, btnKey, 35, SpringLayout.SOUTH, btnOgre);
		springLayout.putConstraint(SpringLayout.EAST, btnOgre, 0, SpringLayout.EAST, btnDoorClosed);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HeroSelected = false;
				OgreSelected = true;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		btnOgre.setEnabled(false);
		frame.getContentPane().add(btnOgre);
		
		btnHero = new JButton("Hero");
		springLayout.putConstraint(SpringLayout.SOUTH, btnOgre, 35, SpringLayout.SOUTH, btnHero);
		springLayout.putConstraint(SpringLayout.SOUTH, btnHero, 85, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnWall, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.NORTH, btnHero, 55, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnHero, 300, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnHero, 0, SpringLayout.EAST, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.WEST, btnDoorClosed, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.WEST, btnFloor, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.WEST, btnLever, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.WEST, btnKey, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.WEST, btnOgre, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.NORTH, btnOgre, 5, SpringLayout.SOUTH, btnHero);
		btnHero.setEnabled(false);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroSelected = true;
				OgreSelected = false;
				DoorOpenedSelected = false;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		frame.getContentPane().add(btnHero);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ogreUsed && leverUsed && keyUsed && heroUsed)
				{
					getGi().frame.setVisible(true);
					getGi().numOgres = getGi().getGame().getOgres().size();
					frame.setVisible(false);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnStartGame, 48, SpringLayout.NORTH, frame.getContentPane());
		btnStartGame.setEnabled(false);
		frame.getContentPane().add(btnStartGame);
		
		btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 19, SpringLayout.SOUTH, btnWall);
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, 0, SpringLayout.WEST, btnDoorClosed);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 56, SpringLayout.SOUTH, btnWall);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, 0, SpringLayout.EAST, btnDoorClosed);
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		frame.getContentPane().add(btnExitGame);	
		
		JLabel lblHeight = new JLabel("Height");
		springLayout.putConstraint(SpringLayout.NORTH, lblHeight, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblHeight, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width");
		springLayout.putConstraint(SpringLayout.NORTH, lblWidth, 5, SpringLayout.SOUTH, lblHeight);
		springLayout.putConstraint(SpringLayout.WEST, lblWidth, 0, SpringLayout.WEST, lblHeight);
		springLayout.putConstraint(SpringLayout.EAST, lblWidth, 0, SpringLayout.EAST, lblHeight);
		frame.getContentPane().add(lblWidth);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblHeight);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblHeight);
		springLayout.putConstraint(SpringLayout.EAST, textField, -200, SpringLayout.WEST, btnHero);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblWidth);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblWidth);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnDoorOpened = new JButton("Door Open");
		btnDoorOpened.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HeroSelected = false;
				OgreSelected = false;
				DoorOpenedSelected = true;
				DoorClosedSelected = false;
				KeySelected = false;
				LeverSelected = false;
				WallSelected = false;
				FloorSelected = false;
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnDoorClosed, 35, SpringLayout.SOUTH, btnDoorOpened);
		springLayout.putConstraint(SpringLayout.WEST, btnDoorOpened, 0, SpringLayout.WEST, btnHero);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDoorOpened, 35, SpringLayout.SOUTH, btnFloor);
		springLayout.putConstraint(SpringLayout.NORTH, btnDoorClosed, 5, SpringLayout.SOUTH, btnDoorOpened);
		springLayout.putConstraint(SpringLayout.NORTH, btnDoorOpened, 5, SpringLayout.SOUTH, btnFloor);
		springLayout.putConstraint(SpringLayout.EAST, btnDoorOpened, 0, SpringLayout.EAST, btnDoorClosed);
		btnDoorOpened.setEnabled(false);
		frame.getContentPane().add(btnDoorOpened);
		MapEditor i = this;
		btnGenerate = new JButton("Generate");
		springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 6, SpringLayout.EAST, btnGenerate);
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, 113, SpringLayout.EAST, btnGenerate);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -6, SpringLayout.WEST, btnGenerate);
		springLayout.putConstraint(SpringLayout.WEST, btnGenerate, 106, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnGenerate, 48, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGenerate, 213, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 10, SpringLayout.NORTH, frame.getContentPane());
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int height = parseInt(textField.getText());
				int width = parseInt(textField_1.getText());
				if(width > 10 || height > 10 )
				{
					actionPerformed(e);
				}
				enableButtons();
				getGi().setGame(new GameState());
				generateEmptyMap(height ,width);
				gpme = new GraphicsPanelMapEditor(i);
				frame.getContentPane().add(gpme);
				gpme.requestFocusInWindow();
				frame.pack();
				frame.repaint();
			}
		});
		frame.getContentPane().add(btnGenerate);
	}

	public GraphicalInterface getGi() {
		return gi;
	}

	public void setGi(GraphicalInterface gi) {
		this.gi = gi;
	}

}
