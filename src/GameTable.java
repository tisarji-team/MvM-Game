import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Tisarji
 */

public class GameTable {

	String path = "../tower-defend/pic/hero/StickBowMan.png";
	ImageIcon characterIcon = new ImageIcon(path);

	private char[][] table;
	private int	rows;
	private int	columns;
	private int[] characterCount;
	private JLabel[][] characterLabels;

	public GameTable(int rows, int colunms)
	{
		this.rows = rows;
		this.columns = colunms;
		this.characterLabels = new JLabel[rows][colunms];
		this.table = new char[rows][colunms];
		initializeTable();
		characterCount = new int[rows];
	}

	private void initializeTable() 
	{
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				table[row][col] = '.';
			}
		}
	}
	
	public void setCharacterLabel(int row, int col, JLabel label) 
	{
		characterLabels[row][col] = label;
	}
	
	public int getRowCount()
	{
		return (rows);
	}

	public int getColumnCount()
	{
		return (columns);
	}
}
