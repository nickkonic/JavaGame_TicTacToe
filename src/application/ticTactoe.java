package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ticTactoe {
    int boardwidth = 600;
    int boardheight = 650;
    
    JFrame frame = new JFrame("Activity One");
    
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    
    String player1 = "X";
    String player2 = "0";
    String currentplayer = player1;
    
    boolean GameOver = false;
    
    int turns = 0;
    
    ticTactoe(){
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textLabel.setBackground(Color.LIGHT_GRAY);
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(boardPanel);
        
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.LIGHT_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(GameOver) return;
                        JButton tile =(JButton) e.getSource();
                        
                        /*tile.setText(currentplayer);
                        currentplayer = currentplayer == player1 ? player2 : player1;
                        textLabel.setText(currentplayer + " 's Turn");*/
                        
                        if(tile.getText() == ""){
                            tile.setText(currentplayer);
                            turns++;
                            checkWinner();
                            if(!GameOver){
                                currentplayer = currentplayer == player1 ? player2 : player1;
                                textLabel.setText(currentplayer + " 's Turn");
                            }
                        }
                    }
                });
            }
        }
  }
void checkWinner(){
        //horizontal
        for(int r = 0; r < 3; r++){
            if(board[r][0].getText() == "") continue;
        
            if(board[r][0].getText() == board[r][1].getText() &&
               board[r][1].getText() == board[r][2].getText()){
               for(int i = 0; i < 3; i++){
                   setWinner(board[r][i]);
               }
               GameOver = true;
               return;
            }
        }
        //vertical
        for(int c = 0; c < 3; c++){
            if(board[0][c].getText() == "") continue;
        
            if(board[0][c].getText() == board[1][c].getText() &&
               board[1][c].getText() == board[2][c].getText()){
               for(int i = 0; i < 3; i++){
                   setWinner(board[i][c]);
               }
               GameOver = true;
               return;
            }
        }
        //diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][2].getText() &&
           board[0][0].getText()!= ""){
            for(int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            GameOver =true;
            return;
        }
        //antidiagonal
        if(board[0][2].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][0].getText() &&
           board[0][2].getText()!= ""){
           setWinner(board[2][0]);
           setWinner(board[1][1]);
           setWinner(board[0][2]);
            GameOver =true;
            return;
        }
        if (turns == 9){
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    setTie(board[r][c]);
                }
            }
            GameOver = true;
        }
    }
void setWinner(JButton tile){
    tile.setForeground(Color.ORANGE);
    tile.setBackground(Color.GRAY);
    textLabel.setText(currentplayer + " is the WINNER!");
}
void setTie(JButton tile){
    tile.setForeground(Color.red);
    tile.setBackground(Color.GRAY);
    textLabel.setText("Its A TIE");
}
}
