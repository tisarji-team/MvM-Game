

/**
 *
 * @author Tisarji
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GameMapPanel extends JPanel {
	private int tileSize;
	private GameTable gameTable;
	private int[] columnTileSizes;
	private int rows;
	private int columns;

	private JTable table;
    private DefaultTableModel tableModel;

	
	public GameMapPanel(int rows, int columns)
	{
		this.rows = rows;
        this.columns = columns;
		gameTable = new GameTable(rows, columns);

        setBackground(Color.BLACK);
		
		columnTileSizes = new int[columns];
        for (int col = 0; col < columns; col++) {
            columnTileSizes[col] = tileSize * 8;
        }
		columnTileSizes[0] = tileSize * 6; 

		addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			int row = e.getY() / tileSize;
			int col = e.getX() / tileSize;
		}
        });

//		table.setRowHeight(115);
//		table.getColumnModel().getColumn(0).setPreferredWidth(160);

//		tableModel = new DefaultTableModel(rows, columns);
//		table = new JTable(tableModel);
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		setLayout(new BorderLayout());
//
//		add(scrollPane, BorderLayout.CENTER);
		
//		table.setBackground(Color.black);
//		table.setGridColor(Color.white);
//		setPreferredSize(new Dimension(1280, 720));
		
//        JTableHeader header = table.getTableHeader();
//        header.setPreferredSize(new Dimension(0, 0));
//        header.setMinimumSize(new Dimension(0, 0));
//        header.setMaximumSize(new Dimension(0, 0));
//
//
//
//		for (int row = 0; row < rows; row++) 
//		{
//			if (row == 0) {
//				table.setRowHeight(row, 115);
//			} else {
//                table.setRowHeight(row, 115);
//            }
//        }
//
//        for (int col = 0; col < columns; col++) 
//		{
//			if (col == 0) {
//				table.getColumnModel().getColumn(col).setPreferredWidth(160);
//			} else {
//				table.getColumnModel().getColumn(col).setPreferredWidth(160);
//            }
//        }

//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int row = table.rowAtPoint(e.getPoint());
//                int col = table.columnAtPoint(e.getPoint());
//                // Handle the click event here
//            }
//        });

	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);

		int cellWidth = 160;
		int cellHeight = 115;

		for (int row = 0; row < gameTable.getRowCount(); row++) {
			for (int col = 0; col < gameTable.getColumnCount(); col++) {
				int x = col * cellWidth;
				int y = row * cellHeight;
				g.drawRect(x, y, cellWidth, cellHeight);
			}
		}
	}
	
	public int getTileSize() {
        return tileSize;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

}

