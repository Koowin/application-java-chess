package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Game;
import io.growth.challenge.chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    private final int[] verticalMoves;
    private final int[][] diagonalMoves;
    public Pawn(boolean isBlack) {
        super(isBlack);
        type = PieceType.PAWN;
        if(isBlack){
            verticalMoves = new int[] {1, 2};
            diagonalMoves = new int[][] {{1,-1}, {1,1}};
        }
        else{
            verticalMoves = new int[] {-1, -2};
            diagonalMoves = new int[][] {{-1,-1}, {-1,1}};
        }
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        List<Position> ret = new ArrayList<>();
        int nowRow = nowPosition.getRow();
        int nowCol = nowPosition.getCol();

        int nextRow = nowRow + verticalMoves[0];
        int nextCol = nowCol;

        // 직선 경로에 상대 기물이 없을 경우 추가
        if(!isOutOfBounds(nextRow, nextCol)){
            Optional<Piece> oldPiece = positions[nextRow][nextCol].getPiece();
            if(oldPiece.isEmpty()){
                ret.add(positions[nextRow][nextCol]);

                // 시작 위치일 경우 2칸 전진 가능
                if(isStartPosition(nowRow)){
                    nextRow = nowRow + verticalMoves[1];
                    oldPiece = positions[nextRow][nextCol].getPiece();
                    if(oldPiece.isEmpty()){
                        ret.add(positions[nextRow][nextCol]);
                    }
                }
            }
        }

        // 대각선 경로에 상대 기물이 있을 경우 추가
        for(int[] oneMove : diagonalMoves){
            nextRow = nowRow + oneMove[0];
            nextCol = nowCol + oneMove[1];

            if(!isOutOfBounds(nextRow,nextCol)){
                Optional<Piece> oldPiece = positions[nextRow][nextCol].getPiece();
                if (oldPiece.isPresent() && oldPiece.get().isBlack != isBlack) {
                    ret.add(positions[nextRow][nextCol]);
                }
            }
        }
        return ret;
    }
    private boolean isStartPosition(int row){
        if((isBlack && row == 1) || (!isBlack && row == 6)){
            return true;
        }
        return false;
    }
}
