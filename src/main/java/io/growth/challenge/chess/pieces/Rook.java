package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;

import java.util.List;

public class Rook extends Piece {
    public Rook(boolean isBlack) {
        super(isBlack);
        type = PieceType.ROOK;
        moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        return getMovablePositionForQueenRookBishop(nowPosition, positions);
    }
}
