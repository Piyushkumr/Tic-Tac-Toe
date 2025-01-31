package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myGame extends JFrame implements ActionListener{

    JLabel heading;
    Font font = new Font("", Font.PLAIN, 30);
    JPanel mainPanel;

    JButton []btns = new JButton[9];

//    game instances variable
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    int winner = 2;

//    postions jin par winner honge
    int wps[][] = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    boolean gameOver = false;

    myGame(){
        System.out.println("Creating game");
        setTitle("Tic Tac Toe");
        setSize(650,650);
        ImageIcon icon = new ImageIcon("src/main/java/Img/TTT.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#1e4d5c"));
        this.setLayout(new BorderLayout());

        heading = new JLabel("Tic Tac Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
//        heading.setIcon(new ImageIcon("src/main/java/Img/TTT.png"));

        this.add(heading, BorderLayout.NORTH);

//     Panel section
        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(3,3));

        for(int i=1; i<=9; i++){
            JButton btn = new JButton();
//            btn.setIcon(new ImageIcon("src/main/java/Img/ooo.png"));
            btn.setBackground(Color.decode("#d5dce0"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1] = btn;

//            agar kisi bhi button pe click karenge to ye chl jayega
            btn.addActionListener(this);
//            identify karne k liye ki konsa button chl rha hai
            btn.setName(String.valueOf(i-1));
        }

        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        e.getSource()  ye hume de dega jo button click hua hoga
        JButton currentButton = (JButton) e.getSource();
        String nameStr = currentButton.getName();
        int name = Integer.parseInt(nameStr.trim());

        if(gameOver){
            JOptionPane.showMessageDialog(this, "Game Over");
            return;
        }

        if(gameChances[name] == 2){
            if(activePlayer == 1){
                currentButton.setIcon(new ImageIcon("src/main/java/Img/x.png"));

                gameChances[name] = activePlayer;
                activePlayer = 0;
            }else{
                currentButton.setIcon(new ImageIcon("src/main/java/Img/ooo.png"));

                gameChances[name] = activePlayer;
                activePlayer = 1;
            }

//        Finding winner
            for(int []temp : wps){
                if((gameChances[temp[0]] == gameChances[temp[1]]) &&
                        (gameChances[temp[1]] == gameChances[temp[2]]) &&
                        (gameChances[temp[2]]!= 2)){
                    winner = gameChances[temp[0]];

                    gameOver = true;

                    JOptionPane.showMessageDialog(null, "Player " + winner + " has won the game");
                    int i = JOptionPane.showConfirmDialog(this, "Play again");

                    if(i == 0){
//                        Current game will be closed
                        this.setVisible(false);

//                        Fresh game will start
                        new myGame();
                    }else if(i == 1){
                        System.exit(400);
                    }else{

                    }
                    System.out.println(i);
                    break;
                }
            }
//            For DRAW
            int c = 0;
            for(int x:gameChances){
                if(x == 2){
                    c++;
                    break;
                }
            }
            if(c == 0 && gameOver == false){
                JOptionPane.showMessageDialog(null, "Its a draw");

                int i = JOptionPane.showConfirmDialog(this, "More game?");
                if(i == 0){
                    this.setVisible(false);
                    new myGame();
                }else if(i == 1){
//                    System.exit(Integer.parseInt(" Closed"));
                    System.exit(400);

                }else{

                }
                gameOver = true;
            }

        }else{
            JOptionPane.showMessageDialog(this,"Postion already occupied");
        }

    }

    public static void main(String[] args) {
        myGame game = new myGame();
    }
}
