package com.zetcode;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Dimension d;
	private Font smallFont; // = new Font("src/resources/font/PixeloidMono.ttf", Font.BOLD, 14);
	private Image ii;
	private final Color dotColor = new Color(192, 192, 0);
	private Color mazeColor;
	private JDialog creditDialog;
	// private Sound moveSound = new Sound("src/resources/sound/thief_eat.wav");

	private boolean inGame = false;
	private boolean dying = false;
	private boolean isCreditDialogOpen = false;

	private final int BLOCK_SIZE = 24;
	private final int N_BLOCKS = 20;
	private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
	private final int THIEF_ANIM_DELAY = 4;
	private final int THIEF_ANIM_COUNT = 4;
	private final int MAX_GHOSTS = 12;
	private final int THIEF_SPEED = 4;

	private int thiefAnimCount = THIEF_ANIM_DELAY;
	private int thiefAnimDir = 1;
	private int thiefAnimPos = 0;
	private int N_GHOSTS = 6;
	private int thiefLeft, score;
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

	private Image ghost;
	private Image thief1, thief2up, thief2left, thief2right, thief2down;
	private Image thief3up, thief3down, thief3left, thief3right;
	private Image thief4up, thief4down, thief4left, thief4right;
	private Image thief1up, thief1down, thief1left, thief1right;

	private int thief_x, thief_y, thiefd_x, thiefd_y;
	private int req_dx, req_dy, view_dx, view_dy;

	private final short levelData[] = {
			// ! 1
			19, 18, 26, 18, 18, 18, 18, 18, 18, 22, 0, 19, 18, 18, 26, 26, 18, 26, 26, 22,
			// ! 2
			17, 28, 0, 25, 16, 16, 16, 16, 16, 20, 0, 17, 16, 20, 0, 0, 21, 0, 0, 21,
			// ! 3
			21, 0, 0, 0, 17, 24, 24, 16, 16, 20, 0, 17, 16, 20, 0, 0, 21, 0, 0, 21,
			// ! 4
			17, 22, 0, 19, 20, 0, 0, 17, 16, 20, 0, 17, 16, 16, 26, 26, 16, 26, 26, 20,
			// ! 5
			17, 16, 26, 16, 16, 18, 18, 16, 16, 16, 18, 16, 16, 20, 0, 0, 21, 0, 0, 21,
			// ! 6
			17, 20, 0, 17, 16, 16, 16, 16, 16, 24, 24, 24, 16, 20, 0, 0, 21, 0, 0, 21,
			// ! 7
			17, 20, 0, 17, 16, 16, 16, 16, 20, 0, 0, 0, 17, 20, 0, 0, 21, 0, 0, 21,
			// ! 8
			17, 20, 0, 17, 16, 16, 16, 16, 16, 22, 0, 19, 16, 16, 18, 18, 16, 18, 18, 20,
			// ! 9
			17, 20, 0, 17, 16, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 16, 16, 16, 16, 20,
			// ! 10
			17, 20, 0, 25, 24, 16, 16, 16, 24, 24, 24, 24, 16, 16, 16, 24, 24, 24, 24, 28,
			// ! 11
			17, 20, 0, 0, 0, 17, 16, 20, 0, 0, 0, 0, 17, 16, 20, 0, 0, 0, 0, 4,
			// ! 12
			17, 16, 26, 26, 26, 16, 24, 24, 26, 18, 18, 18, 16, 16, 16, 26, 18, 18, 18, 22,
			// ! 13
			17, 20, 0, 0, 0, 21, 0, 0, 0, 17, 16, 16, 16, 16, 20, 0, 17, 24, 24, 20,
			// ! 14
			17, 20, 0, 19, 18, 16, 18, 22, 0, 17, 16, 16, 24, 16, 20, 0, 21, 0, 0, 21,
			// ! 15
			17, 20, 0, 17, 16, 16, 16, 20, 0, 17, 16, 28, 0, 25, 20, 0, 17, 26, 26, 20,
			// ! 16
			17, 20, 0, 17, 16, 16, 16, 20, 0, 17, 20, 0, 0, 0, 21, 0, 21, 0, 0, 21,
			// ! 17
			17, 16, 26, 16, 16, 16, 16, 16, 26, 16, 16, 18, 18, 18, 16, 26, 16, 18, 18, 20,
			// ! 18
			17, 20, 0, 25, 24, 16, 24, 28, 0, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 20,
			// ! 19
			17, 20, 0, 0, 0, 21, 0, 0, 0, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 20,
			// ! 20
			25, 24, 26, 26, 26, 24, 26, 26, 26, 24, 24, 24, 24, 24, 24, 26, 24, 24, 24, 28
	};

	private final int validSpeeds[] = { 1, 2, 3, 4, 6, 8 };
	private final int maxSpeed = 6;

	private int currentSpeed = 3;
	private short[] screenData;
	private Timer timer;

	private void initFont() {
		try {
			smallFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/PixeloidMono.ttf"))
					.deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(smallFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public Board() {
		loadImages();
		initVariables();
		initFont();
		initCreditDialog();
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.black);
	}

	private void initVariables() {
		screenData = new short[N_BLOCKS * N_BLOCKS];
		mazeColor = new Color(202, 150, 112);
		d = new Dimension(400, 400);
		ghost_x = new int[MAX_GHOSTS];
		ghost_dx = new int[MAX_GHOSTS];
		ghost_y = new int[MAX_GHOSTS];
		ghost_dy = new int[MAX_GHOSTS];
		ghostSpeed = new int[MAX_GHOSTS];
		dx = new int[4];
		dy = new int[4];

		timer = new Timer(40, this);
		timer.start();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		initGame();
	}

	private void doAnim() {
		thiefAnimCount--;
		if (thiefAnimCount <= 0) {
			thiefAnimCount = THIEF_ANIM_DELAY;
			thiefAnimPos = thiefAnimPos + thiefAnimDir;

			if (thiefAnimPos == (THIEF_ANIM_COUNT - 1) || thiefAnimPos == 0) {
				thiefAnimDir = -thiefAnimDir;
			}
		}
	}

	private void playGame(Graphics2D g2d) {
		if (dying) {
			death();
		} else {
			moveThief();
			drawThief(g2d);
			moveGhosts(g2d);
			checkMaze();
		}
	}

	private void showIntroScreen(Graphics2D g2d) {
		g2d.setColor(new Color(100, 130, 255));
		g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
		g2d.setColor(Color.white);
		g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

		String s = "Press 'S' to start";
		try {
			Font smallFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/PixeloidMono.ttf"))
					.deriveFont(14f);
			g2d.setColor(Color.white);
			g2d.setFont(smallFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		FontMetrics metr = g2d.getFontMetrics();
		g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
	}

	private void drawScore(Graphics2D g) {
		int i;
		String s;

		g.setFont(smallFont);
		g.setColor(new Color(96, 128, 255));
		s = "Score: " + score;
		g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

		for (i = 0; i < thiefLeft; i++) {
			g.drawImage(thief3left, i * 28 + 8, SCREEN_SIZE + 1, this);
		}
	}

	private void checkMaze() {
		short i = 0;
		boolean finished = true;

		while (i < N_BLOCKS * N_BLOCKS && finished) {
			if ((screenData[i] & 48) != 0) {
				finished = false;
			}
			i++;
		}

		if (finished) {
			score += 50;
			if (N_GHOSTS < MAX_GHOSTS) {
				N_GHOSTS++;
			}
			if (currentSpeed < maxSpeed) {
				currentSpeed++;
			}
			initLevel();
		}
	}

	private void death() {
		thiefLeft--;
		if (thiefLeft == 0) {
			inGame = false;
		}
		continueLevel();
	}

	private void moveGhosts(Graphics2D g2d) {
		short i;
		int pos;
		int count;

		for (i = 0; i < N_GHOSTS; i++) {
			if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
				pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

				count = 0;

				if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
					dx[count] = -1;
					dy[count] = 0;
					count++;
				}

				if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
					dx[count] = 0;
					dy[count] = -1;
					count++;
				}

				if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
					dx[count] = 1;
					dy[count] = 0;
					count++;
				}

				if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
					dx[count] = 0;
					dy[count] = 1;
					count++;
				}

				if (count == 0) {

					if ((screenData[pos] & 15) == 15) {
						ghost_dx[i] = 0;
						ghost_dy[i] = 0;
					} else {
						ghost_dx[i] = -ghost_dx[i];
						ghost_dy[i] = -ghost_dy[i];
					}

				} else {

					count = (int) (Math.random() * count);

					if (count > 3) {
						count = 3;
					}

					ghost_dx[i] = dx[count];
					ghost_dy[i] = dy[count];
				}
			}

			ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
			ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
			drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

			if (thief_x > (ghost_x[i] - 12) && thief_x < (ghost_x[i] + 12)
					&& thief_y > (ghost_y[i] - 12) && thief_y < (ghost_y[i] + 12)
					&& inGame) {

				dying = true;
			}
		}
	}

	private void drawGhost(Graphics2D g2d, int x, int y) {
		g2d.drawImage(ghost, x, y, this);
	}

	private void moveThief() {
		int pos;
		short ch;

		if (req_dx == -thiefd_x && req_dy == -thiefd_y) {
			thiefd_x = req_dx;
			thiefd_y = req_dy;
			view_dx = thiefd_x;
			view_dy = thiefd_y;
		}

		if (thief_x % BLOCK_SIZE == 0 && thief_y % BLOCK_SIZE == 0) {
			pos = thief_x / BLOCK_SIZE + N_BLOCKS * (int) (thief_y / BLOCK_SIZE);
			ch = screenData[pos];

			if ((ch & 16) != 0) {
				screenData[pos] = (short) (ch & 15);
				score++;
				// moveSound.play();
			}

			if (req_dx != 0 || req_dy != 0) {
				if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
						|| (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
						|| (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
						|| (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
					thiefd_x = req_dx;
					thiefd_y = req_dy;
					view_dx = thiefd_x;
					view_dy = thiefd_y;
				}
			}

			// Check for standstill
			if ((thiefd_x == -1 && thiefd_y == 0 && (ch & 1) != 0)
					|| (thiefd_x == 1 && thiefd_y == 0 && (ch & 4) != 0)
					|| (thiefd_x == 0 && thiefd_y == -1 && (ch & 2) != 0)
					|| (thiefd_x == 0 && thiefd_y == 1 && (ch & 8) != 0)) {
				thiefd_x = 0;
				thiefd_y = 0;
			}
		}
		thief_x = thief_x + THIEF_SPEED * thiefd_x;
		thief_y = thief_y + THIEF_SPEED * thiefd_y;
	}

	private void drawThief(Graphics2D g2d) {
		if (view_dx == -1) {
			drawThiefLeft(g2d);
		} else if (view_dx == 1) {
			drawThiefRight(g2d);
		} else if (view_dy == -1) {
			drawThiefUp(g2d);
		} else {
			drawThiefDown(g2d);
		}
	}

	private void drawThiefUp(Graphics2D g2d) {
		switch (thiefAnimPos) {
			case 1:
				g2d.drawImage(thief2up, thief_x + 1, thief_y + 1, this);
				break;
			case 2:
				g2d.drawImage(thief3up, thief_x + 1, thief_y + 1, this);
				break;
			case 3:
				g2d.drawImage(thief4up, thief_x + 1, thief_y + 1, this);
				break;
			default:
				g2d.drawImage(thief1up, thief_x + 1, thief_y + 1, this);
				break;
		}
	}

	private void drawThiefDown(Graphics2D g2d) {
		switch (thiefAnimPos) {
			case 1:
				g2d.drawImage(thief2down, thief_x + 1, thief_y + 1, this);
				break;
			case 2:
				g2d.drawImage(thief3down, thief_x + 1, thief_y + 1, this);
				break;
			case 3:
				g2d.drawImage(thief4down, thief_x + 1, thief_y + 1, this);
				break;
			default:
				g2d.drawImage(thief1down, thief_x + 1, thief_y + 1, this);
				break;
		}
	}

	private void drawThiefLeft(Graphics2D g2d) {
		switch (thiefAnimPos) {
			case 1:
				g2d.drawImage(thief2left, thief_x + 1, thief_y + 1, this);
				break;
			case 2:
				g2d.drawImage(thief3left, thief_x + 1, thief_y + 1, this);
				break;
			case 3:
				g2d.drawImage(thief4left, thief_x + 1, thief_y + 1, this);
				break;
			default:
				g2d.drawImage(thief1left, thief_x + 1, thief_y + 1, this);
				break;
		}
	}

	private void drawThiefRight(Graphics2D g2d) {
		switch (thiefAnimPos) {
			case 1:
				g2d.drawImage(thief2right, thief_x + 1, thief_y + 1, this);
				break;
			case 2:
				g2d.drawImage(thief3right, thief_x + 1, thief_y + 1, this);
				break;
			case 3:
				g2d.drawImage(thief4right, thief_x + 1, thief_y + 1, this);
				break;
			default:
				g2d.drawImage(thief1right, thief_x + 1, thief_y + 1, this);
				break;
		}
	}

	private void drawMaze(Graphics2D g2d) {
		short i = 0;
		int x, y;

		for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
			for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
				g2d.setColor(mazeColor);
				g2d.setStroke(new BasicStroke(2));
				if ((screenData[i] & 1) != 0) {
					g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 2) != 0) {
					g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
				}

				if ((screenData[i] & 4) != 0) {
					g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
							y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 8) != 0) {
					g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
							y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 16) != 0) {
					g2d.setColor(dotColor);
					g2d.fillRect(x + 11, y + 11, 2, 2);
				}

				i++;
			}
		}
	}

	private void initGame() {
		thiefLeft = 3;
		score = 0;
		initLevel();
		N_GHOSTS = 6;
		currentSpeed = 3;
	}

	private void initLevel() {
		int i;
		for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
			screenData[i] = levelData[i];
		}

		continueLevel();
	}

	private void continueLevel() {
		short i;
		int dx = 1;
		int random;

		for (i = 0; i < N_GHOSTS; i++) {
			ghost_y[i] = 10 * BLOCK_SIZE;
			ghost_x[i] = 10 * BLOCK_SIZE;
			ghost_dy[i] = 0;
			ghost_dx[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (currentSpeed + 1));

			if (random > currentSpeed) {
				random = currentSpeed;
			}
			ghostSpeed[i] = validSpeeds[random];
		}

		thief_x = 7 * BLOCK_SIZE;
		thief_y = 11 * BLOCK_SIZE;
		thiefd_x = 0;
		thiefd_y = 0;
		req_dx = 0;
		req_dy = 0;
		view_dx = -1;
		view_dy = 0;
		dying = false;
	}

	private void loadImages() {
		ghost = new ImageIcon("src/resources/images/Ghost/Ghost.gif").getImage();
		thief1 = new ImageIcon("src/resources/images/Thief/Down/ThiefDown1.png").getImage();
		thief1up = new ImageIcon("src/resources/images/Thief/Up/ThiefUp1.png").getImage();
		thief2up = new ImageIcon("src/resources/images/Thief/Up/ThiefUp2.png").getImage();
		thief3up = new ImageIcon("src/resources/images/Thief/Up/ThiefUp3.png").getImage();
		thief4up = new ImageIcon("src/resources/images/Thief/Up/ThiefUp4.png").getImage();
		thief1down = new ImageIcon("src/resources/images/Thief/Down/ThiefDown1.png").getImage();
		thief2down = new ImageIcon("src/resources/images/Thief/Down/ThiefDown2.png").getImage();
		thief3down = new ImageIcon("src/resources/images/Thief/Down/ThiefDown3.png").getImage();
		thief4down = new ImageIcon("src/resources/images/Thief/Down/ThiefDown4.png").getImage();
		thief1left = new ImageIcon("src/resources/images/Thief/Left/ThiefLeft1.png").getImage();
		thief2left = new ImageIcon("src/resources/images/Thief/Left/ThiefLeft2.png").getImage();
		thief3left = new ImageIcon("src/resources/images/Thief/Left/ThiefLeft3.png").getImage();
		thief4left = new ImageIcon("src/resources/images/Thief/Left/ThiefLeft4.png").getImage();
		thief1right = new ImageIcon("src/resources/images/Thief/Right/ThiefRight1.png").getImage();
		thief2right = new ImageIcon("src/resources/images/Thief/Right/ThiefRight2.png").getImage();
		thief3right = new ImageIcon("src/resources/images/Thief/Right/ThiefRight3.png").getImage();
		thief4right = new ImageIcon("src/resources/images/Thief/Right/ThiefRight4.png").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	private void initCreditDialog() {
		creditDialog = new JDialog();
		creditDialog.setTitle("Credits");
		creditDialog.setSize(890, 465);
		creditDialog.setLocationRelativeTo(null);

		JLabel creditLabel = new JLabel();
		ImageIcon creditImage = new ImageIcon("src/resources/images/Credit/Credit1.png");
		creditLabel.setIcon(creditImage);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleCreditDialogVisibility();
			}
		});

		JPanel creditPanel = new JPanel(new BorderLayout());
		creditPanel.add(creditLabel, BorderLayout.CENTER);
		creditPanel.add(closeButton, BorderLayout.SOUTH);

		creditDialog.add(creditPanel);
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, d.width, d.height);

		drawMaze(g2d);
		drawScore(g2d);
		doAnim();

		if (inGame) {
			playGame(g2d);
		} else {
			showIntroScreen(g2d);
		}

		// ? How to Play
		g2d.setColor(Color.white);
		g2d.setFont(smallFont);
		String howToPlayText = "! How to Play !";
		g2d.drawString(howToPlayText, SCREEN_SIZE + 55, 40);

		String upArrow = "Press Up arrow keys to up";
		g2d.drawString(upArrow, SCREEN_SIZE + 20, 60);
		String downArrow = "Press Down arrow keys to down";
		g2d.drawString(downArrow, SCREEN_SIZE + 20, 80);
		String leftArrow = "Press Left arrow keys to left";
		g2d.drawString(leftArrow, SCREEN_SIZE + 20, 100);
		String rightArrow = "Press Right arrow keys to right";
		g2d.drawString(rightArrow, SCREEN_SIZE + 20, 120);

		String lineCutter = "--------------------------------------";
		g2d.drawString(lineCutter, SCREEN_SIZE + 20, 140);

		String pauseString = "Press 'P' to Pause and Resume";
		g2d.drawString(pauseString, SCREEN_SIZE + 20, 220);

		String creditString = "Press 'C' to Show Credit";
		g2d.drawString(creditString, SCREEN_SIZE + 20, 180);
		String warningCredit = "Don't Press this If you are not a Developer";
		g2d.setColor(Color.RED);
		g2d.drawString(warningCredit, SCREEN_SIZE + 20, 200);

		g2d.drawImage(ii, 5, 5, this);
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}

	private void toggleCreditDialogVisibility() {
		isCreditDialogOpen = !isCreditDialogOpen;
		creditDialog.setVisible(isCreditDialogOpen);
	}

	class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (inGame) {
				if (key == 'C' || key == 'c') {
					toggleCreditDialogVisibility();
				}
				if (key == KeyEvent.VK_LEFT) {
					req_dx = -1;
					req_dy = 0;
				} else if (key == KeyEvent.VK_RIGHT) {
					req_dx = 1;
					req_dy = 0;
				} else if (key == KeyEvent.VK_UP) {
					req_dx = 0;
					req_dy = -1;
				} else if (key == KeyEvent.VK_DOWN) {
					req_dx = 0;
					req_dy = 1;
				} else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
					inGame = false;
				} else if (key == 'p' || key == 'P') {
					if (timer.isRunning()) {
						timer.stop();
					} else {
						timer.start();
					}
				}
			} else {
				if (key == 's' || key == 'S') {
					inGame = true;
					initGame();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == Event.LEFT || key == Event.RIGHT
					|| key == Event.UP || key == Event.DOWN) {
				req_dx = 0;
				req_dy = 0;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
