package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isBlack) {
        super(isBlack);
        type = PieceType.QUEEN;
        moves = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        return getMovablePositionForQueenRookBishop(nowPosition, positions);
    }
}
