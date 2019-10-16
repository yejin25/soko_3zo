package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

/**
 *
 * @author Gabriel Massadas
 */
public class MinesFinder extends javax.swing.JFrame {

    private RecordTable recordEasy;
    private RecordTable recordMedium;
    private RecordTable recordHard;
    private RecordTable recordBattle;

    /**
     * Creates new form MinesFinder
     */
    public MinesFinder() {
        initComponents();
        recordEasy = new RecordTable();
        recordMedium = new RecordTable();
        recordHard = new RecordTable();
        recordBattle = new RecordTable();

        readGameRecords();

        labelEasyName.setText(recordEasy.getName());
        labelEasyPoints.setText(Long.toString(recordEasy.getScore()));
        labelMediumName.setText(recordMedium.getName());
        labelMediumPoints.setText(Long.toString(recordMedium.getScore()));
        labelHardName.setText(recordHard.getName());
        labelHardPoints.setText(Long.toString(recordHard.getScore()));
        labelBattleName.setText(recordBattle.getName());
        labelBattlePoints.setText(Long.toString(recordBattle.getScore()));



        recordEasy.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordEasyUpdated(record);
            }
        });

        recordMedium.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordMediumUpdated(record);
            }
        });

        recordHard.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordHardUpdated(record);
            }
        });
        recordBattle.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordBattleUpdated(record);
            }
        });
    }

    private void recordEasyUpdated(RecordTable record) {
        labelEasyName.setText(record.getName());
        labelEasyPoints.setText(Long.toString(record.getScore()));
        saveGameRecords();
    }

    private void recordMediumUpdated(RecordTable record) {
        labelMediumName.setText(record.getName());
        labelMediumPoints.setText(Long.toString(record.getScore()));
        saveGameRecords();
    }

    private void recordHardUpdated(RecordTable record) {
        labelHardName.setText(record.getName());
        labelHardPoints.setText(Long.toString(record.getScore()));
        saveGameRecords();
    }

    private void recordBattleUpdated(RecordTable record) {
        labelBattleName.setText(record.getName());
        labelBattlePoints.setText(Long.toString(record.getScore()/1000));
        saveGameRecords();
    }

    private void saveGameRecords() { 
    	File f = new File(System.getProperty("user.home") + File.separator + "minesfinder.txt");
  
		try (FileWriter writer = new FileWriter(f)) {
			// try - with - resource 구문  

			String contents = "";
			String easy = "EASY\n" + recordEasy.getName() + "\n" + recordEasy.getScore() + "\n";
			String med = "MED\n" + recordMedium.getName() + "\n" + recordMedium.getScore() + "\n";
			String hard = "HARD\n" + recordHard.getName() + "\n" + recordHard.getScore() + "\n";
			String battle = "BATTLE\n" + recordBattle.getName() + "\n" + recordBattle.getScore();
			contents = easy + med + hard + battle;

			writer.write(contents);
		} catch (IOException ex) {
			Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}// private void saveGameRecords() 주형 수정 

	private void readGameRecords() {
		File f = new File(System.getProperty("user.home") + File.separator + "minesfinder.txt");
		
		if (f.canRead()) {
			try(FileReader reader = new FileReader(f);
					BufferedReader buff = new BufferedReader(reader);) {
			
			String[] information = new String[2];
			ArrayList<String[]> list = new ArrayList<>();
			
			String line = "";
			for(int i = 1; (line=buff.readLine()) != null ; i++) {
				if((i+1) % 3 == 0) {
					// read name
					information[0] = line;
				}
				
				if(i % 3 == 0) {
					// read score
					information[1] = line;
					list.add(information);
					information = new String[2];
				}				
			} 
			
			recordEasy.setRecord(list.get(0)[0], Long.parseLong(list.get(0)[1]));
			recordMedium.setRecord(list.get(1)[0], Long.parseLong(list.get(1)[1]));
			recordHard.setRecord(list.get(2)[0], Long.parseLong(list.get(2)[1]));
			recordBattle.setRecord(list.get(3)[0], Long.parseLong(list.get(3)[1]));
			
			}catch(Throwable t) {
				t.printStackTrace();
			}
		} else {
			saveGameRecords();
			readGameRecords();
		}
	} // private void readGameRecords() 주형 수정 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelTitle = new javax.swing.JLabel();
        panelRecords = new javax.swing.JPanel();
        Records = new javax.swing.JLabel();

        labelEasy = new javax.swing.JLabel();
        labelEasyName = new javax.swing.JLabel();
        labelEasyPoints = new javax.swing.JLabel();
        labelMedium = new javax.swing.JLabel();
        labelMediumName = new javax.swing.JLabel();
        labelMediumPoints = new javax.swing.JLabel();
        labelHard = new javax.swing.JLabel();
        labelHardName = new javax.swing.JLabel();
        labelHardPoints = new javax.swing.JLabel();
        labelBattle = new javax.swing.JLabel();
        labelBattleName = new javax.swing.JLabel();
        labelBattlePoints = new javax.swing.JLabel();

        panelBtns = new javax.swing.JPanel();
        btnEasy = new javax.swing.JButton();
        btnMedium = new javax.swing.JButton();
        btnHard = new javax.swing.JButton();
        btnBattle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MinesFinder");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(600, 450));
        setResizable(false);

        panelTitle.setBackground(new java.awt.Color(136, 135, 217));
        panelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTitle.setText("Minesfinder");
        panelTitle.setOpaque(true);
        getContentPane().add(panelTitle, java.awt.BorderLayout.PAGE_START);
        
		JButton btnRankingChart = new JButton("Ranking Chart");
		getContentPane().add(btnRankingChart, BorderLayout.WEST); // 왼쪽에 "Ranking Chart" 버튼 추가

		btnRankingChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RankingChart();
			} // "Ranking Chart" 버튼을 클릭하면 새창을 띄운다.

		});

//        panelRecords.setBackground(new java.awt.Color(118, 206, 108));
//
//        Records.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
//        Records.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        Records.setText("Records");
//
//        labelEasy.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
//        labelEasy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        labelEasy.setText("Easy");
//
//        labelEasyName.setText("Player");
//
//        labelEasyPoints.setText("9999");
//
//        labelMedium.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
//        labelMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        labelMedium.setText("Medium");
//
//        labelMediumName.setText("Player");
//
//        labelMediumPoints.setText("9999");
//
//        labelHard.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
//        labelHard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        labelHard.setText("Hard");
//
//        labelHardName.setText("Player");
//
//        labelHardPoints.setText("9999");
//
//        labelBattle.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
//        labelBattle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        labelBattle.setText("Battle");
//
//        labelBattleName.setText("Winner");
//
//        labelBattlePoints.setText("9999");
//
//        javax.swing.GroupLayout panelRecordsLayout = new javax.swing.GroupLayout(panelRecords);
//        panelRecords.setLayout(panelRecordsLayout);
//        panelRecordsLayout.setHorizontalGroup(
//            panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(labelEasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(Records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addGroup(panelRecordsLayout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(panelRecordsLayout.createSequentialGroup()
//                        .addComponent(labelEasyName)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(labelEasyPoints))
//                    .addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .addGroup(panelRecordsLayout.createSequentialGroup()
//                        .addComponent(labelMediumName)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(labelMediumPoints))
//                    .addComponent(labelHard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .addGroup(panelRecordsLayout.createSequentialGroup()
//                            .addComponent(labelHardName)
//                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(labelHardPoints))
//                        .addComponent(labelBattle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addGroup(panelRecordsLayout.createSequentialGroup()
//                                .addComponent(labelBattleName)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(labelBattlePoints)))
//
//                .addContainerGap())
//        );
//        panelRecordsLayout.setVerticalGroup(
//            panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(panelRecordsLayout.createSequentialGroup()
//                .addComponent(Records)
//                .addGap(18, 18, 18)
//                .addComponent(labelEasy)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(labelEasyPoints)
//                    .addComponent(labelEasyName))
//                .addGap(18, 18, 18)
//                .addComponent(labelMedium)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(labelMediumPoints)
//                    .addComponent(labelMediumName))
//                .addGap(18, 18, 18)
//                .addComponent(labelHard)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(labelHardPoints)
//                    .addComponent(labelHardName))
//                .addGap(18, 18, 18)
//                    .addComponent(labelBattle)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                            .addComponent(labelBattlePoints)
//                            .addComponent(labelBattleName))
//                    .addGap(0, 169, Short.MAX_VALUE))
//        );
//
//        getContentPane().add(panelRecords, java.awt.BorderLayout.LINE_START);

        panelBtns.setLayout(new java.awt.GridLayout(2, 0));

        btnEasy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/easy.png"))); // NOI18N
        btnEasy.setText("Easy");
        btnEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEasyActionPerformed(evt);
            }
        });
        panelBtns.add(btnEasy);

        btnMedium.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/medium.png"))); // NOI18N
        btnMedium.setText("Medium");
        btnMedium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMediumActionPerformed(evt);
            }
        });
        panelBtns.add(btnMedium);

        btnHard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/hard.png"))); // NOI18N
        btnHard.setText("Hard");
        btnHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHardActionPerformed(evt);
            }
        });
        panelBtns.add(btnHard);

//        btnBattle.setText("Battle");    //btnExit -> btnBattle
//        btnBattle.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnBattleActionPerformed(evt);
//            }
//        });
//        panelBtns.add(btnBattle);

        getContentPane().add(panelBtns, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEasyActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(9, 9, 10), recordEasy);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnEasyActionPerformed

//    private void btnBattleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBattleActionPerformed
//        //System.exit(0);
//        BattleMode battlewindow = new BattleMode(new Minefield(9,9,10),recordBattle);
//        battlewindow.setVisible(true);
//    }//GEN-LAST:event_btnBattleActionPerformed

    private void btnMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMediumActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(16, 16, 40), recordMedium);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnMediumActionPerformed

    private void btnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(16, 30, 90), recordHard);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnHardActionPerformed

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
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinesFinder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Records;
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnBattle;
    private javax.swing.JButton btnHard;
    private javax.swing.JButton btnMedium;
    private javax.swing.JLabel labelEasy;
    private javax.swing.JLabel labelEasyName;
    private javax.swing.JLabel labelEasyPoints;
    private javax.swing.JLabel labelHard;
    private javax.swing.JLabel labelHardName;
    private javax.swing.JLabel labelHardPoints;
    private javax.swing.JLabel labelMedium;
    private javax.swing.JLabel labelMediumName;
    private javax.swing.JLabel labelMediumPoints;
    private javax.swing.JLabel labelBattle;
    private javax.swing.JLabel labelBattleName;
    private javax.swing.JLabel labelBattlePoints;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JPanel panelRecords;
    private javax.swing.JLabel panelTitle;
    // End of variables declaration//GEN-END:variables
}
