/**
 *
 * @author Tisarji
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class GameMapPanel extends JPanel {
	private int tileSize;
	private GameTable gameTable;

	private JTable table;
    private DefaultTableModel tableModel;

	
	public GameMapPanel(int rows, int columns)
	{
/* --------------------------- Set Scale Table --------------------------- */

		tableModel = new DefaultTableModel(rows, columns);
		table = new JTable(tableModel);

        int newCellWidth = (int) (160 * 0.8);
        int newCellHeight = (int) (115 * 0.8);
        table.setRowHeight(0, newCellHeight);
        table.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new LineBorder(Color.WHITE));
        table.setShowGrid(true);
        table.setBackground(Color.BLACK);
        table.setGridColor(Color.WHITE);
		
		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			table.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			table.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}
		
/* --------------------------- Set TopTable --------------------------- */


/* --------------------------- Set Center Monitor --------------------------- */

        setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));
        add(table, BorderLayout.CENTER);

/* -------------------------------------------------------------------------- */

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                table.setBorder(new LineBorder(Color.BLUE));

                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                        table.setBorder(new LineBorder(Color.WHITE));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
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

