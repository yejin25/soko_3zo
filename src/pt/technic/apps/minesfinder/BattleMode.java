package pt.technic.apps.minesfinder;

import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

//    private static int doublePressSpeed = 300; // double keypressed in ms
//    private static long timeKeyDown = 0;       // last keyperessed time
//    public static int lastKeyPressedCode;

public class BattleMode extends javax.swing.JFrame {
    private ButtonMinefield[][] buttons;
    private ButtonMinefield[][] buttons2;
    private Minefield minefield;
    private Minefield minefield2;
    private RecordTable record;
    private String id;
    private ActionListener actionListener;
    JPanel MAINPanel = new JPanel();
    JPanel mainpanel = new JPanel();
    JPanel mainstausbar = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel statuspanel = new JPanel();
    JPanel statuspanel2 = new JPanel();

    private final int[] sec = { 0 };// 시간 선언
    private boolean gameStart = false; // 게임이 시작 되었는지 판별

    Bgm battlebgm = new Bgm("mario.mp3",false);
    Bgm bgm = new Bgm("mariodie.mp3",false);

    BattleMode()
    {
        initComponents();
    }

    BattleMode(Minefield minefield, Minefield minefield2, RecordTable record) {
        initComponents();
        this.minefield = minefield;
        this.minefield2 = minefield2;
        this.record = record;
        initStatusBar();

        battlebgm.start();

        buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];
        buttons2 = new ButtonMinefield[minefield2.getWidth()][minefield2.getHeight()];

        MAINPanel.setLayout(new FlowLayout());
        MAINPanel.setPreferredSize(new Dimension(1010, 560));

        statuspanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statuspanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        mainpanel.setLayout(new FlowLayout());
        mainstausbar.setLayout(new FlowLayout());

        mainstausbar.setPreferredSize(new Dimension(700, 30));
        mainpanel.setPreferredSize(new Dimension(1000, 500));

        panel1.setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));
        panel2.setLayout(new GridLayout(minefield2.getWidth(), minefield2.getHeight()));

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                ButtonMinefield botao = (ButtonMinefield) e.getSource();
                int x = botao.getCol();
                int y = botao.getLine();

                if (e.getKeyCode() == KeyEvent.VK_W && x > 0) {
                    buttons[x - 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_A && y > 0) {
                    buttons[x][y - 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_S && x < minefield.getHeight() - 1) {
                    buttons[x + 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_D && y < minefield.getWidth() - 1) {
                    buttons[x][y + 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_F && minefield.getGridState(x, y) == minefield2.COVERED) {
                    if(!minefield.isBattleDefeated()){
                        Battlebtn(x, y, 0);
                        buttons2[x][y].setFocusable(true);
                        buttons2[x][y].requestFocus();
                        for(x = 0; x<minefield.getWidth();x++) {
                            for (y = 0; y < minefield.getHeight(); y++) {
                                buttons[x][y].setFocusable(false);
                            }
                        }
                        for(x = 0; x<minefield.getWidth();x++) {
                            for (y = 0; y < minefield.getHeight(); y++) {
                                buttons2[x][y].setFocusable(true);
                            }
                        }
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_UP && x > 0) {
                    buttons2[x - 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && y > 0) {
                    buttons2[x][y - 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && x < minefield2.getHeight() - 1) {
                    buttons2[x + 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && y < minefield2.getWidth() - 1) {
                    buttons2[x][y + 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER && (minefield2.getGridState(x, y) == minefield2.COVERED)) {
                     if(!minefield2.isBattleDefeated()){
                        Battlebtn(x, y, 1);
                        buttons[x][y].setFocusable(true);
                        buttons[x][y].requestFocus();
                         for(x = 0; x<minefield.getWidth();x++) {
                             for (y = 0; y < minefield.getHeight(); y++) {
                                 buttons2[x][y].setFocusable(false);
                             }
                         }
                         for(x = 0; x<minefield.getWidth();x++) {
                             for (y = 0; y < minefield.getHeight(); y++) {
                                 buttons[x][y].setFocusable(true);
                             }
                         }
                    }
                }
            }

                @Override
                public void keyTyped (KeyEvent ke){
                }

                @Override
                public void keyReleased (KeyEvent ke){
                }
            }

            ;
        for(
            int x = 0; x<minefield.getWidth();x++)

            {
                for (int y = 0; y < minefield.getHeight(); y++) {
                    buttons[x][y] = new ButtonMinefield(x, y, 1);
                    buttons2[x][y] = new ButtonMinefield(x, y, 0);
                    buttons[x][y].setPreferredSize(new Dimension(55, 55));
                    buttons2[x][y].setPreferredSize(new Dimension(55, 55));
                    panel1.add(buttons[x][y]);
                    panel2.add(buttons2[x][y]);
                    buttons[x][y].addKeyListener(keyListener);
                    buttons2[x][y].addKeyListener(keyListener);
                }
            }

        mainstausbar.add(statuspanel);
        mainstausbar.add(statuspanel2);

        mainpanel.add(panel1);
        mainpanel.add(panel2);

        MAINPanel.add(mainstausbar);
        MAINPanel.add(mainpanel);

            setContentPane(MAINPanel);

            pack();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                battlebgm.close();
            }
        });

        }


    private void initStatusBar() {
        JLabel timeLabel = new JLabel(
                "Player1 Mark Chances : " + this.minefield.getnumlife() + " / LeftMines : " + this.minefield.getleft()+"         "
        + "Player2 Mark Chances : " + this.minefield2.getnumlife() + " / LeftMines : " + this.minefield2.getleft()); // 레이블
        // 생성

        ThreadPool.timeThreadPool.submit(() -> {
            while (gameStart) {
                try {
                    TimeUnit.SECONDS.sleep(1); // 1초 쉬고
                    timeLabel.setText("Player1 Mark Chances : " + this.minefield.getnumlife() + " / LeftMines : " + this.minefield.getleft()+"         "
                            + "Player2 Mark Chances : " + this.minefield2.getnumlife() + " / LeftMines : " + this.minefield2.getleft()); // 레이블 생성
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

//        JLabel timeLabel2 = new JLabel(
//                "Player2   " + "Time : " + String.valueOf(sec[0]) + " /  Mark Chances : " + this.minefield2.getnumlife() + " / LeftMines : " + this.minefield2.getleft()); // 레이블
//        // 생성
//
//        ThreadPool.timeThreadPool.submit(() -> {
//            while (gameStart) {
//                sec[0] += 1; // 1증가
//                try {
//                    TimeUnit.SECONDS.sleep(1); // 1초 쉬고
//                    timeLabel2.setText("Player2   "+"Time : " + String.valueOf(sec[0]) + " / Mark Chances : "
//                            + this.minefield2.getnumlife()+" / LeftMines  : "+this.minefield2.getleft()); // 레이블 생성
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });


        statuspanel.add(timeLabel);
//        statuspanel2.add(timeLabel2);
    }

    private void Battlebtn(int x, int y, int i) {//지뢰 다 찾으면 승리
        if (i == 0) {
            minefield.BattlerevealGrid(x, y);
            updateButtonsStates();
            if (minefield.isBattleFinished()) {
                gameStart = false;

                if (minefield.isBattleWin()) {
                    battlebgm.close();
                    JOptionPane.showMessageDialog(null, "COGRATULATIONS, WIN! Player1 Find All Mines",
                            "WIN!", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                } else if (minefield.isBattleDefeated()) {
                    battlebgm.close();
                    bgm.start();
                    bgm.close();
                    for (x = 0; x < minefield.getWidth(); x++) {
                        for (y = 0; y < minefield.getHeight(); y++) {
                            buttons[x][y].setBackground(Color.RED);
                            buttons[x][y].setText("END");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Player2 Win! Player1 try harder",
                            "Player2 win", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }

            }
        }


        else{
            minefield2.BattlerevealGrid(x, y);
            updateButtonsStates2();

            if (minefield2.isBattleFinished()) {
                gameStart = false;
                if (minefield2.isBattleWin()) {
                    battlebgm.close();
                    JOptionPane.showMessageDialog(null, "COGRATULATIONS, WIN! Player2 Find All Mines",
                            "WIN!", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                } else if (minefield2.isBattleDefeated()) {
                    battlebgm.close();
                    bgm.start();
                    bgm.close();
                    for (x = 0; x < minefield2.getWidth(); x++) {
                        for (y = 0; y < minefield2.getHeight(); y++) {
                            buttons2[x][y].setBackground(Color.RED);
                            buttons2[x][y].setText("END");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Player1 Win! Player2 try harder",
                            "Player1 win", JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);
                }
            }

            }
        }

    private void updateButtonsStates() {
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                buttons[x][y].setEstado(minefield.getGridState(x, y));
            }
        }
    }

    private void updateButtonsStates2() {
        for (int x = 0; x < minefield2.getWidth(); x++) {
            for (int y = 0; y < minefield2.getHeight(); y++) {
                buttons2[x][y].setEstado(minefield2.getGridState(x, y));
            }
        }
    }

    private void initComponents() {
        gameStart = true;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Game");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1094, Short.MAX_VALUE)



        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 553, Short.MAX_VALUE)

        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
}





//포커싱된 버튼의 행렬 어찌 받아올까요..
//int x = ?
//int y = ?

//        ButtonMinefield botao = (ButtonMinefield) e.getSource();


//        addKeyBinding(buttons[x][y], KeyEvent.VK_D, "RIGHT");
//        addKeyBinding(buttons[x][y], KeyEvent.VK_A, "LEFT");
//        addKeyBinding(buttons[x][y], KeyEvent.VK_W, "UP");
//        addKeyBinding(buttons[x][y], KeyEvent.VK_S, "DOWN");
//        addKeyBinding(buttons[x][y], KeyEvent.VK_SPACE, "FIND");
//
//        addKeyBinding(buttons2[x][y], KeyEvent.VK_RIGHT, "RIGHT");
//        addKeyBinding(buttons2[x][y], KeyEvent.VK_LEFT, "LEFT");
//        addKeyBinding(buttons2[x][y], KeyEvent.VK_UP, "UP");
//        addKeyBinding(buttons2[x][y], KeyEvent.VK_DOWN, "DOWN");
//        addKeyBinding(buttons2[x][y], KeyEvent.VK_SHIFT, "FIND");
//
//
//
//        }
//
//        public void Right(int x, int y, int i){
//            if(i==0 && y<8){
//                buttons[x][y+1].requestFocus();
//                y++;
//            }
//            else
//                buttons2[x][y+1].requestFocus();
//                y++;
//        }
//
//    public void Left(int x, int y, int i){
//        if(i==0 && y > 0){
//            buttons[x][y-1].requestFocus();
//            y--;
//        }
//        else
//            buttons2[x][y-1].requestFocus();
//            y--;
//    }
//    public void Down(int x, int y, int i){
//        if(i==0&&x<8){
//            buttons[x+1][y].requestFocus();
//            x++;
//        }
//        else
//            buttons2[x+1][y].requestFocus();
//            x++;
//    }
//    public void Up(int x, int y, int i){
//        if(i==0&&x>0){
//            buttons[x-1][y].requestFocus();
//            x--;
//        }
//        else
//            buttons2[x-1][y].requestFocus();
//            x--;
//    }
//
//
//    public void addKeyBinding(JComponent buttons, int KeyCode, String id){
//
//        InputMap im = buttons.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//        ActionMap ap = buttons.getActionMap();
//
//        im.put(KeyStroke.getKeyStroke(KeyCode, 0, false), id);
//
//        ap.put(id, new AbstractAction(){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ButtonMinefield botao = (ButtonMinefield) e.getSource();
//                int x = botao.getCol();
//                int y = botao.getLine();
//
//                if (id=="UP" && x > 0) {
//                    Up(x,y,0);
//                } else if (id == "LEFT" && y > 0) {
//                    Left(x,y,0);
//                } else if (id == "DOWN" && x < minefield.getHeight() - 1) {
//                    Down(x,y,0);
//                } else if (id == "RIGHT" && y < minefield.getWidth() - 1) {
//                    Right(x,y,0);
//                } else if(id == "FIND"){
//                    Battlebtn(x,y);
//                }
//            }
//        });
//    }

//



//                addKeyListener(new KeyAdapter() {
//                    public void keyPressed(KeyEvent e) {
//                        super.keyPressed(e);
//                        ButtonMinefield botao = (ButtonMinefield) e.getSource();
//                        int x = botao.getCol();
//                        int y = botao.getLine();
//                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                            buttons2[x][y].requestFocus();
//                        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && y > 0) {
//                            buttons[x][y - 1].requestFocus();
//                        } else if (e.getKeyCode() == KeyEvent.VK_UP && x > 8) {
//                            buttons[x - 1][y].requestFocus();
//                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && x < 8) {
//                            buttons[x + 1][y].requestFocus();
//                        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                            Battlebtn(x, y);
//                        }
//                    }
//                });
//            }


//    public static  boolean isDoublePress(KeyEvent e) {
//        if ((e.getWhen() - timeKeyDown) < doublePressSpeed) {
//            return true;
//        } else {
//            timeKeyDown = e.getWhen();
//        }
//        lastKeyPressedCode = e.getKeyCode();
//        return false;
//    }