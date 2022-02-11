package io.growth.challenge.chess;

import io.growth.challenge.chess.pieces.Piece;

import java.util.List;
import java.util.Optional;

/**
 * 체스 보드 https://ko.wikipedia.org/wiki/%EC%B2%B4%EC%8A%A4%ED%8C%90
 */
public interface Board {
    /**
     * 해당 칸의 기물을 확인한다.
     * @param rowCol 확인할 칸 위치
     * @return 체스 기물
     */
    Optional<Piece> peek(String rowCol);
    //Optional<Piece> peek(int row, int col);

    //List<String> getMovements(int row, int col);
    List<String> getMovements(String rowCol);

    void apply(String fromStr, String toStr);

    boolean isBlackWin();
    boolean isGameEnd();
    boolean isBlackTurn();
}
