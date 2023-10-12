
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tisarji
 */

public class CharacterClass {

	private JTable table;
	private DefaultTableModel tableModel;
	private Timer animationTimer;
	private int currentFrame;

	private List<ImageIcon> frames;

	private int rows;
	private int cols;

	private void loadFramesFromGif(String gifFilePath) {
		frames = new ArrayList<>();

		try {
			BufferedImage sourceImage = ImageIO.read(new File(gifFilePath));
			int frameWidth = sourceImage.getWidth() / 4; // 4 เฟรม
			int frameHeight = sourceImage.getHeight();

			for (int i = 0; i < 4; i++) {
				int x = i * frameWidth;
				BufferedImage frameImage = sourceImage.getSubimage(x, 0, frameWidth, frameHeight);
				ImageIcon frameIcon = new ImageIcon(frameImage);
				frames.add(frameIcon);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void animateCharacter(JTable tableToUse, int row, int col, String gifFilePath) {
		loadFramesFromGif(gifFilePath);
		animateCharacter(tableToUse, row, col);
	}

	private void animateCharacter(JTable tableToUse, int row, int col) {
		int frameDelay = 50; // ปรับตามความเร็วที่คุณต้องการ

		animationTimer = new Timer(frameDelay, e -> toggleFrame(tableToUse, row, col));
		animationTimer.start();
	}

	private void toggleFrame(JTable tableToUse, int row, int col) {
		currentFrame = (currentFrame + 1) % frames.size();
		ImageIcon frameIcon = frames.get(currentFrame);
		tableToUse.setValueAt(frameIcon, row, col);
	}

	public void startAnimation(JTable tableToUse, int row, int col, String gifFilePath) {
		loadFramesFromGif(gifFilePath);
		animateCharacter(tableToUse, row, col);
	}
}
