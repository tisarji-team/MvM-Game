
import java.awt.Color;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tisarji
 */
public class TableClass {

	private DefaultTableModel mapTableModel;
	private DefaultTableModel menuTableModel;
	private DefaultTableModel characterTableModel;

	private JTable mapTable;
	private JTable menuTable;
	private JTable characterTable;

	private Image backgroundImage;

	public TableClass(int rows, int columns) {
		mapTableModel = new DefaultTableModel(rows, columns);
		mapTable = new JTable(mapTableModel);

		createMapTable(rows, columns);
		createMenuTable(rows, columns);
		createCharacterTable(rows, columns);
	}

	public JTable createMapTable(int rows, int columns) {
		mapTableModel = new DefaultTableModel(rows, columns);
		mapTable = new JTable(mapTableModel);

		int newCellWidth = (int) (160 * 0.8);
		int newCellHeight = (int) (115 * 0.8);
		mapTable.setRowHeight(0, newCellHeight);
		mapTable.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

		mapTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapTable.setBorder(new LineBorder(Color.WHITE));
		mapTable.setShowGrid(true);
		mapTable.setBackground(Color.decode("#006400"));
		mapTable.setGridColor(Color.BLACK);

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			mapTable.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			mapTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		return (mapTable);
	}

	public JTable createMenuTable(int rows, int columns) {
		menuTableModel = new DefaultTableModel(rows, columns);
		menuTable = new JTable(menuTableModel);

		int newCellWidth = (int) (160 * 0.8);
		int newCellHeight = (int) (115 * 0.8);
		menuTable.setRowHeight(0, newCellHeight);
		menuTable.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

		menuTable.setBorder(new LineBorder(Color.WHITE));
		menuTable.setShowGrid(true);
		menuTable.setBackground(Color.BLACK);
		menuTable.setGridColor(Color.WHITE);

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			menuTable.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			menuTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		return (menuTable);
	}

	public JTable createCharacterTable(int rows, int columns) {
		characterTableModel = new DefaultTableModel(rows, columns);
		characterTable = new JTable(characterTableModel);

		int newCellWidth = (int) (160 * 0.8);
		int newCellHeight = (int) (115 * 0.8);
		characterTable.setRowHeight(0, newCellHeight);
		characterTable.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

		characterTable.setBorder(new LineBorder(Color.WHITE));
		characterTable.setShowGrid(true);
		characterTable.setBackground(Color.BLACK);
		characterTable.setGridColor(Color.WHITE);

		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			characterTable.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			characterTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

		return (characterTable);
	}
}
