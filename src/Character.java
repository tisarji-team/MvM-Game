/**
 *
 * @author Tisarji
 */

import javax.swing.Timer;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class Character {
    private GameMapPanel gameMapPanel;
    private Timer animationTimer;
    private ImageIcon[] images;
    private int currentImageIndex;
    private DefaultTableModel tableModel;

    public Character(GameMapPanel gameMapPanel) {
        this.gameMapPanel = gameMapPanel;
        this.tableModel = gameMapPanel.getMainTableModel();
    }

    private ImageIcon scaleImage(ImageIcon originalIcon, int width, int height) {
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }

    public void adam(int row, int col) {
        int width = 80;
        int height = 80;

        gameMapPanel.getMainTable().setDefaultRenderer(Object.class, new ImageRenderer());
        images = new ImageIcon[4];
        images[0] = new ImageIcon("../MvM-Game/pic/Hero/Adam/Adam-1.png");
        images[1] = new ImageIcon("../MvM-Game/pic/Hero/Adam/Adam-2.png");
        images[2] = new ImageIcon("../MvM-Game/pic/Hero/Adam/Adam-3.png");
        images[3] = new ImageIcon("../MvM-Game/pic/Hero/Adam/Adam-4.png");

        currentImageIndex = 0;
        tableModel.setValueAt(scaleImage(images[currentImageIndex], width, height), row, col);

        animationTimer = new Timer(50, e -> toggleImage());
        animationTimer.start();
    }
	
    public void ben(int row, int col) {
        int width = 80;
        int height = 80;

        gameMapPanel.getMainTable().setDefaultRenderer(Object.class, new ImageRenderer());
        images = new ImageIcon[4];
        images[0] = new ImageIcon("../MvM-Game/pic/Hero/Ben/Ben-1.png");
        images[1] = new ImageIcon("../MvM-Game/pic/Hero/Ben/Ben-2.png");
        images[2] = new ImageIcon("../MvM-Game/pic/Hero/Ben/Ben-3.png");
        images[3] = new ImageIcon("../MvM-Game/pic/Hero/Ben/Ben-4.png");

        currentImageIndex = 0;
        tableModel.setValueAt(scaleImage(images[currentImageIndex], width, height), row, col);

        animationTimer = new Timer(50, e -> toggleImage());
        animationTimer.start();
    }

	private void toggleImage() {
		currentImageIndex = (currentImageIndex + 1) % images.length;
		int width = 80;
		int height = 80;
		tableModel.setValueAt(scaleImage(images[currentImageIndex], width, height), 1, 1);
	}
}

