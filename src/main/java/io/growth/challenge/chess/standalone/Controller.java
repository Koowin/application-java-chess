package io.growth.challenge.chess.standalone;

import io.growth.challenge.chess.Board;
import io.growth.challenge.chess.Game;
import io.growth.challenge.chess.pieces.Piece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    private final View v;
    private final Board board;
    private List<String> movablePositions = new ArrayList<>();
    private String lastSelected;

    Controller(View v, Board board) {
        this.v = v;
        this.board = board;
        addButtonActionListener();
    }

    void addButtonActionListener() {
        ActionListener[][] actionListeners = new MyActionListener[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                actionListeners[i][j] = new MyActionListener(i, j);
            }
        }
        v.setButtonListener(actionListeners);
    }

    void clickRowCol(String selected){
        v.clearMovable();

        if(movablePositions.contains(selected)){
            board.apply(lastSelected, selected);
            v.apply(lastSelected, selected);
            movablePositions = new ArrayList<>();
        }
        Optional<Piece> piece = board.peek(selected);
        if(piece.isPresent() && piece.get().isBlack() == board.isBlackTurn()){
            movablePositions =  board.getMovements(selected);
            lastSelected = selected;
            v.setMovable(movablePositions);
        }
    }

    private class MyActionListener implements ActionListener {
        private final int row, col;
        MyActionListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String rowCol = Integer.toString(row) + col;
            clickRowCol(rowCol);
        }
    }

    public static void main(String[] args) {
        View v = new View();
        Board board = new Game();
        new Controller(v, board);
    }
}
