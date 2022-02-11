package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isBlack) {
        super(isBlack);
        type = PieceType.KNIGHT;
        moves = new int[][]{{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    }

    @Override
    public List<Position> getMovablePosition(Position nowPosition, Position[][] positions) {
        return getMovablePositionForKingKnight(nowPosition, positions);
    }
}
