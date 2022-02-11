package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;

import java.util.List;

public class King extends Piece {
    public King(boolean isBlack) {
        super(isBlack);
        type = PieceType.KING;
        moves = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        return getMovablePositionForKingKnight(nowPosition, positions);
    }
}
