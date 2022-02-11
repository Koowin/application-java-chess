package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isBlack) {
        super(isBlack);
        type = PieceType.BISHOP;
        moves = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        return getMovablePositionForQueenRookBishop(nowPosition, positions);
    }
}
