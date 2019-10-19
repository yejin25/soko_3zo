package pt.technic.apps.minesfinder;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Gabriel Massadas
 */
public class GameWindow extends javax.swing.JFrame {
	private ButtonMinefield[][] buttons;
	private Minefield minefield;
	private RecordTable record;
	private final int[] sec = { 0 };// 시간 선언
	private boolean gameStart = false; // 게임이 시작 되었는지 판별


	/**
	 * Creates new form GameWindow
	 */
	private Bgm bgm = new Bgm("boom.mp3",false);
	private Bgm mainbgm = new Bgm("젤다 테트리스.mp3",true);

	public GameWindow() {
		initComponents();
	}

	public GameWindow(Minefield minefield, RecordTable record) {

		initComponents();
		this.minefield = minefield;
		this.record = record;

		initStatusBar();

		mainbgm.start();

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonMinefield botao = (ButtonMinefield) e.getSource();
				int x = botao.getCol();
				int y = botao.getLine();
				minefield.revealGrid(x, y);
				updateButtonsStates();
				if (minefield.isGameFinished()) {
					gameStart = false; // 끝나면 게임 시작 false 표시
					bgm.start();
					bgm.close();

					mainbgm.close();
					if (minefield.isPlayerDefeated()) {
						JOptionPane.showMessageDialog(null, "Oh, a mine broke", "Lost!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Congratulations. You managed to discover all the mines in " + (sec[0]) + " seconds",
								"victory", JOptionPane.INFORMATION_MESSAGE);
						long a = sec[0];
						long b = record.getScore();
						boolean newRecord = sec[0] < record.getScore();

						if (newRecord) {
							String name = JOptionPane.showInputDialog("Enter your name");
							if (name != "")
								record.setRecord(name, sec[0]);
						}
					}
					setVisible(false);
				}
			}
		};

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					ButtonMinefield botao = (ButtonMinefield) e.getSource();
					int x = botao.getCol();
					int y = botao.getLine();
					if (minefield.getGridState(x, y) == minefield.COVERED) {
						minefield.setMineMarked(x, y);
					} else if (minefield.getGridState(x, y) == minefield.MARKED) {
						minefield.setMineQuestion(x, y);
					} else if (minefield.getGridState(x, y) == minefield.QUESTION) {
						minefield.setMineCovered(x, y);
					}
					updateButtonsStates();
				}
			}

			@Override
			public void mouseClicked(MouseEvent me) {
			}

			@Override
			public void mouseReleased(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
			}

			@Override
			public void mouseExited(MouseEvent me) {
			}
		};

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};

		// Create buttons for the player
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y] = new ButtonMinefield(x, y, 2);
				buttons[x][y].addActionListener(action);
				buttons[x][y].addMouseListener(mouseListener);
				buttons[x][y].addKeyListener(keyListener);
				buttons[x][y].addKeyListener(keyListener);
				getContentPane().add(buttons[x][y]);
			}
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gameStart = false;
				mainbgm.close();
			}
		});

	}


	private void initStatusBar() {
		JMenuBar statusBar = new JMenuBar(); // 상태바 생성
		JPanel panel = new JPanel(); // 패널 생성
		JLabel timeLabel = new JLabel(
				"Time : " + String.valueOf(sec[0]) + " /  Mark Chances : " + this.minefield.getNumMarkChances()); // 레이블
																													// 생성

		ThreadPool.timeThreadPool.submit(() -> {
			while (gameStart) {
				sec[0] += 1; // 1증가
				try {
					TimeUnit.SECONDS.sleep(1); // 1초 쉬고
					timeLabel.setText("Time : " + String.valueOf(sec[0]) + " / Mark Chances : "
							+ this.minefield.getNumMarkChances()); // 레이블 생성
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		panel.add(timeLabel);
		statusBar.add(panel);
		setJMenuBar(statusBar);
	}

	private void updateButtonsStates() {
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y].setEstado(minefield.getGridState(x, y));
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		gameStart = true; // 게임을 재시작할 때 게임 시작됨을 알림
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		setTitle("Game");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1094, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 553, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
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
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
