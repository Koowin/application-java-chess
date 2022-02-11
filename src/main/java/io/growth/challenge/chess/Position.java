package io.growth.challenge.chess;

import io.growth.challenge.chess.pieces.Piece;

import java.util.Optional;

public class Position {
    private final int col, row;
    private Piece piece;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        piece = null;
    }

    public Optional<Piece> getPiece(){
        return Optional.ofNullable(piece);
    }

    /**
     * 현재 위치에 기물을 놓고 원래 놓여있던 기물을 Optional 로 boxing 하여 반환
     * @param piece
     * @return
     */
    public Optional<Piece> setPiece(Piece piece){
        Piece oldPiece = this.piece;
        this.piece = piece;
        return Optional.ofNullable(oldPiece);
    }

    /**
     * 위치의 포맷을 AlgebraicNotation -> int 배열 형태로 바꿈
     * e.g. "f5" -> {3,5}
     * @param position
     * @return
     */
    public static int[] parseToIntArr(String position){
        int[] ret = new int[2];
        ret[1] = position.charAt(0) - 'a';
        ret[0] = '8' - position.charAt(1);
        return ret;
    }

    /**
     * 위치 정보를 외부 interface 로 전송할 프로토콜에 맞춰 변환
     * e.g. {3,5} -> "35"
     * @return
     */
    @Override
    public String toString(){
        return Integer.toString(row) + col;
    }

    /**
     * 위치 정보를 Algebraic Notation 으로 반환
     * e.g. {3,5} -> "f5"
     * @return
     */
    public String toAlgebraicNotation(){
        char[] ret = new char[2];
        ret[0] = (char)(col + 'a');
        ret[1] = (char)('8' - row);
        return new String(ret);
    }
    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }
}
