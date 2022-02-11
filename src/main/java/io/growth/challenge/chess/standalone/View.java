package io.growth.challenge.chess.standalone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class View extends JFrame{
    GridLayout gridLayout = new GridLayout(8,8);
    JButton[][] buttons = new JButton[8][8];
    Color lightBrown = new Color(0xac6730);
    Color darkBrown = new Color(0xc08457);
    Color green = new Color(0x9acd32);
    List<String> movablePosition = new ArrayList<>();

    final String blackKingSymbol = "♚";
    final String whiteKingSymbol = "♔";
    final String blackQueenSymbol = "♛";
    final String whiteQueenSymbol = "♕";
    final String blackBishopSymbol = "♝";
    final String whiteBishopSymbol = "♗";
    final String blackKnightSymbol = "♞";
    final String whiteKnightSymbol = "♘";
    final String blackRookSymbol = "♜";
    final String whiteRookSymbol = "♖";
    final String blackPawnSymbol = "♟";
    final String whitePawnSymbol = "♙";

    public View(){
        setLayout(gridLayout);

        setButtonsColor();
        setPiece();

        setSize(500, 500);
        setVisible(true);
    }

    private void setButtonsColor(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                buttons[i][j] = new JButton();
                if((i+j)%2 == 0) {
                    buttons[i][j].setBackground(lightBrown);
                }
                else{
                    buttons[i][j].setBackground(darkBrown);
                }
                buttons[i][j].setFont(new Font("고딕",Font.PLAIN, 25));
                add(buttons[i][j]);
            }
        }
    }

    private void setPiece(){
        buttons[0][0].setText(blackRookSymbol);
        buttons[0][7].setText(blackRookSymbol);
        buttons[7][0].setText(whiteRookSymbol);
        buttons[7][7].setText(whiteRookSymbol);

        buttons[0][1].setText(blackKnightSymbol);
        buttons[0][6].setText(blackKnightSymbol);
        buttons[7][1].setText(whiteKnightSymbol);
        buttons[7][6].setText(whiteKnightSymbol);

        buttons[0][2].setText(blackBishopSymbol);
        buttons[0][5].setText(blackBishopSymbol);
        buttons[7][2].setText(whiteBishopSymbol);
        buttons[7][5].setText(whiteBishopSymbol);

        buttons[0][4].setText(blackQueenSymbol);
        buttons[7][4].setText(whiteQueenSymbol);

        buttons[0][3].setText(blackKingSymbol);
        buttons[7][3].setText(whiteKingSymbol);

        for(int i=0;i<8;i++){
            buttons[1][i].setText(blackPawnSymbol);
        }
        for(int i=0;i<8;i++){
            buttons[6][i].setText(whitePawnSymbol);
        }
    }

    public void apply(String fromStr, String toStr){
        int fromInt = Integer.parseInt(fromStr);
        int toInt = Integer.parseInt(toStr);
        JButton from = buttons[fromInt/10][fromInt%10];
        JButton to = buttons[toInt/10][toInt%10];
        to.setText(from.getText());
        from.setText("");
    }

    public void setButtonListener(ActionListener[][] actionListeners){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                buttons[i][j].addActionListener(actionListeners[i][j]);
            }
        }
    }

    public void clearMovable(){
        for(String s : movablePosition){
            int row = Integer.parseInt(s) / 10;
            int col = Integer.parseInt(s) % 10;

            if((row+col) % 2 == 0){
                buttons[row][col].setBackground(lightBrown);
            }
            else{
                buttons[row][col].setBackground(darkBrown);
            }
        }
    }

    public void setMovable(List<String> movable){
        movablePosition = movable;
        for(String s : movablePosition){
            int row = Integer.parseInt(s) / 10;
            int col = Integer.parseInt(s) % 10;

            buttons[row][col].setBackground(green);
        }
    }
}
