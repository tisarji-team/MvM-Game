package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
	public Main() {
		initUI();
	}
	private void initUI() {
		add(new Board());

		setTitle("The Thief Escape");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1150, 780);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			var ex = new Main();
			ex.setVisible(true);
		});
	}
}
