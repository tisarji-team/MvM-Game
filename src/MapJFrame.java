import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Tisarji, Ndod
 */

public class MapJFrame extends javax.swing.JFrame {

private GameMapPanel mapPanel;

public MapJFrame() {
	int	rows;
	int	columns;

	initComponents();
	setTitle("Paper Game! - Map");
	
	rows = 6;
	columns = 9;
	
	mapPanel = new GameMapPanel(rows, columns);
	MapjPanel.setLayout(new GridLayout(1, 1));
	MapjPanel.add(mapPanel);

	System.out.println("Rows = " + rows);
	System.out.println("Cols = " + columns);
	
	setSize(1280, 720);
	setResizable(false);
}

@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MapjPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));

        MapjPanel.setSize(new java.awt.Dimension(1280, 720));
        MapjPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapjPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MapjPanelLayout = new javax.swing.GroupLayout(MapjPanel);
        MapjPanel.setLayout(MapjPanelLayout);
        MapjPanelLayout.setHorizontalGroup(
            MapjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        MapjPanelLayout.setVerticalGroup(
            MapjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MapjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MapjPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapjPanelMouseClicked

    }//GEN-LAST:event_MapjPanelMouseClicked

public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
	} catch (ClassNotFoundException ex) {
		java.util.logging.Logger.getLogger(MapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
		java.util.logging.Logger.getLogger(MapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
		java.util.logging.Logger.getLogger(MapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
		java.util.logging.Logger.getLogger(MapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	public void run() {
		new MapJFrame().setVisible(true);
	}
	});
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MapjPanel;
    // End of variables declaration//GEN-END:variables
}