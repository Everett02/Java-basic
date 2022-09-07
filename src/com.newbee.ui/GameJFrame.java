package com.newbee.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int [][] data = new int[4][4];

    int x = 0;
    int y = 0;
    String path = "image\\animal\\animal3\\";

    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    int step = 0;

    JMenuItem replayItem = new JMenuItem("Restart the game");
    JMenuItem reLoginItem = new JMenuItem("Quit and login again");
    JMenuItem closeItem = new JMenuItem("Close the game");

    JMenuItem accountItem = new JMenuItem("Our QR code");

    public GameJFrame(){
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        setVisible(true);
    }


    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }


    }

    private void initImage() {
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("D:\\happy\\puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }


        JLabel stepCount = new JLabel("Step:" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                int number = data [i][i1];
                JLabel jLabel = new JLabel(new ImageIcon(path + number +".jpg"));
                jLabel.setBounds(105 * i1, 105 * i, 105, 105);
                getContentPane().add(jLabel);
            }
        }

        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("Functions");
        JMenu aboutJMenu = new JMenu("About us");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        setSize(603,680);
        setTitle("Puzzle game version 1");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("Restart the game");
            step = 0;
            initData();
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("Quit and Login again");
            this.setVisible(false);
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("Close the game");
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("Our QR Code");

            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 37) {
            System.out.println("Move leftward");
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();

        } else if (code == 38) {
            System.out.println("Move upward");
            if(x == 3){
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("Move rightward");
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("Move downward");
            if(x == 0){
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }
}
