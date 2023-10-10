
/**
 *
 * ? @author Tisarji
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class GameMapPanel extends JPanel {

	private GameTable gameTable;
	private JTable mainTable;
	private JTable menuTable;
	private JTable characterTable;
	private DefaultTableModel mainTableModel;
	private DefaultTableModel menuTableModel;
	private DefaultTableModel characterTableModel;

	private Image backgroundImage;

	private Character AdamCharacter;
	private Character BenCharacter;
	private Character CatCharacter;

	private int tileSize;

	public GameMapPanel(int rows, int columns) {
		/**********************
		 * MAIN TABLE FOR MAP *
		 **********************/

//		loadImage("../MvM-Game/pic/Map/Map6x9_V.1.png");
		mainTableModel = new DefaultTableModel(rows, columns);
		mainTable = new JTable(mainTableModel);
		mainTable = new BackgroundImageTable(mainTableModel);

		int newCellWidth = (int) (160 * 0.8);
		int newCellHeight = (int) (115 * 0.8);
		mainTable.setRowHeight(0, newCellHeight);
		mainTable.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

		mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainTable.setBorder(new LineBorder(Color.WHITE));
		mainTable.setShowGrid(true);
		mainTable.setBackground(Color.decode("#006400"));
		mainTable.setGridColor(Color.BLACK);

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			mainTable.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			mainTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		/*************************
		 * CHARACTER TABLE MODEL *
		 *************************/

		characterTableModel = new DefaultTableModel(1, 5);
		characterTable = new JTable(characterTableModel);

		characterTable.setBorder(new LineBorder(Color.WHITE));
		characterTable.setShowGrid(true);
		characterTable.setBackground(Color.BLACK);
		characterTable.setGridColor(Color.WHITE);

		for (int col = 0; col < characterTableModel.getColumnCount(); col++) {
			int newWidth = (int) (160 * 0.8);
			characterTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			characterTable.setRowHeight(row, newHeight);
		}

		/****************
		 * SET MENU BAR *
		 ****************/

		menuTableModel = new DefaultTableModel(1, 1);
		menuTable = new JTable(menuTableModel);

		menuTable.setBorder(new LineBorder(Color.WHITE));
		menuTable.setShowGrid(true);
		menuTable.setBackground(Color.BLACK);
		menuTable.setGridColor(Color.WHITE);

		for (int col = 0; col < menuTableModel.getColumnCount(); col++) {
			int newWidth = (int) (160 * 0.8);
			menuTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			menuTable.setRowHeight(row, newHeight);
		}

		/*****************
		 * SET CHARACTER *
		 *****************/

		AdamCharacter = new Character(this);
		BenCharacter = new Character(this);
		CatCharacter = new Character(this);

		AdamCharacter.adam(mainTable, 0, 4);
		BenCharacter.ben(mainTable, 0, 0);
		CatCharacter.cat(mainTable, 0, 2);

		/**************************
		 * SET ADD TABLE IN PANEL *
		 **************************/

		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(menuTable, BorderLayout.WEST);
		add(characterTable, BorderLayout.PAGE_START);
		add(mainTable, BorderLayout.CENTER);

		/***************************
		 * SET MOUSE IN MAIN TABLE *
		 ***************************/

		mainTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainTable.setBorder(new LineBorder(Color.BLUE));

				new Thread(() -> {
					try {
						Thread.sleep(200);
						mainTable.setBorder(new LineBorder(Color.WHITE));
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}).start();
			}
		});
	}

	/*********************************
	 * SET BACKGROUND IMAGE IN TABLE *
	 *********************************/

	private void loadImage(String imagePath) {
		try {
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private class BackgroundImageTable extends JTable {

		public BackgroundImageTable(DefaultTableModel model) {
			super(model);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
	}

	public int getTileSize() {
		return (tileSize);
	}

	public GameTable getGameTable() {
		return (gameTable);
	}

	public void setGameTable(GameTable gameTable) {
		this.gameTable = gameTable;
	}

	public JTable getMainTable() {
		return (mainTable);
	}

	public DefaultTableModel getMainTableModel() {
		return (mainTableModel);
	}

	public JTable getCharacterTable() {
		return (characterTable);
	}

	public DefaultTableModel getCharacterTableModel() {
		return (characterTableModel);
	}
}
