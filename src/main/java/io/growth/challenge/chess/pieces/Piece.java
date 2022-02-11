package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Game;
import io.growth.challenge.chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Piece {
    protected final boolean isBlack;
    protected PieceType type;
    protected int[][] moves;

    protected Piece(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isBlack() {
        return isBlack;
    }

    /**
     * 상속 받을 각 기물에서 이동가능한 위치들을 구하는 메서드
     *
     * @param nowPosition
     * @param positions
     * @return
     */
    public abstract List<Position> getMovablePosition(Position nowPosition, Position[][] positions);

    /**
     * Queen, Rook, Bishop 의 움직임을 구현. (중복되어 부모 메서드로 합침)
     * @param nowPosition
     * @param positions
     * @return
     */
    protected List<Position> getMovablePositionForQueenRookBishop(Position nowPosition, Position[][] positions) {
        List<Position> ret = new ArrayList<>();
        int nowRow = nowPosition.getRow();
        int nowCol = nowPosition.getCol();

        for (int[] move : moves) {
            int nextRow = nowRow;
            int nextCol = nowCol;
            for (int i = 0; i < 7; i++) {
                nextRow += move[0];
                nextCol += move[1];

                if (isOutOfBounds(nextRow, nextCol)) {
                    break;
                }
                Optional<Piece> oldPiece = positions[nextRow][nextCol].getPiece();
                if (oldPiece.isPresent()) {
                    if (oldPiece.get().isBlack != isBlack) {
                        ret.add(positions[nextRow][nextCol]);
                    }
                    break;
                }
                ret.add(positions[nextRow][nextCol]);
            }
        }
        return ret;
    }

    /**
     * King, Knight 의 움직임을 구현 (중복되어 부모 메서드로 합침)
     * @param nowPosition
     * @param positions
     * @return
     */
    protected List<Position> getMovablePositionForKingKnight(Position nowPosition, Position[][] positions) {
        List<Position> ret = new ArrayList<>();
        int nowRow = nowPosition.getRow();
        int nowCol = nowPosition.getCol();
        for (int[] move : moves) {
            int nextRow = nowRow + move[0];
            int nextCol = nowCol + move[1];
            if (!isOutOfBounds(nextRow, nextCol)) {
                Optional<Piece> oldPiece = positions[nextRow][nextCol].getPiece();
                if (oldPiece.isEmpty()) {
                    ret.add(positions[nextRow][nextCol]);
                } else if (oldPiece.get().isBlack != isBlack) {
                    ret.add(positions[nextRow][nextCol]);
                }
            }
        }
        return ret;
    }

    /**
     * 8 x 8 의 게임 판을 벗어났는지 검사하는 메서드
     *
     * @param row
     * @param col
     * @return 게임 판을 벗어났는지 여부
     */
    public static boolean isOutOfBounds(int row, int col) {
        final int LINE_SIZE = 8;
        if (0 <= row && row < LINE_SIZE && 0 <= col && col < LINE_SIZE) {
            return false;
        }
        return true;
    }

    /**
     * 현재 사용하는 곳은 없는 toString 메서드
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (isBlack) {
            ret.append("Black ");
        } else {
            ret.append("White ");
        }
        switch (type) {
            case PAWN:
                ret.append("Pawn");
                break;
            case ROOK:
                ret.append("Rook");
                break;
            case BISHOP:
                ret.append("Bishop");
                break;
            case KNIGHT:
                ret.append("Knight");
                break;
            case KING:
                ret.append("King");
                break;
            case QUEEN:
                ret.append("Queen");
                break;
            default:
                break;
        }
        return ret.toString();
    }
}
