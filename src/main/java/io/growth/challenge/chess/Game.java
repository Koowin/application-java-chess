package io.growth.challenge.chess;

import io.growth.challenge.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game implements Board {
    private static final int LINE_SIZE = 8;
    private boolean isEnd = false;
    private boolean isBlackWin = false;
    private boolean isBlackTurn = false;
    private Position[][] positions;

    public Game() {
        positions = new Position[LINE_SIZE][LINE_SIZE];
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = 0; j < LINE_SIZE; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
        setPiecesDefault();
    }

    /**
     * 새로운 게임 시작 시 초기 위치로 기물을 놓는 메서드
     */
    private void setPiecesDefault() {
        Piece blackKing = new King(true);
        Piece blackQueen = new Queen(true);
        Piece blackBishop1 = new Bishop(true);
        Piece blackBishop2 = new Bishop(true);
        Piece blackKnight1 = new Knight(true);
        Piece blackKnight2 = new Knight(true);
        Piece blackRook1 = new Rook(true);
        Piece blackRook2 = new Rook(true);

        positions[0][3].setPiece(blackKing);
        positions[0][4].setPiece(blackQueen);
        positions[0][2].setPiece(blackBishop1);
        positions[0][5].setPiece(blackBishop2);
        positions[0][1].setPiece(blackKnight1);
        positions[0][6].setPiece(blackKnight2);
        positions[0][0].setPiece(blackRook1);
        positions[0][7].setPiece(blackRook2);

        for (int i = 0; i < LINE_SIZE; i++) {
            Piece blackPawn = new Pawn(true);
            positions[1][i].setPiece(blackPawn);
        }

        Piece whiteKing = new King(false);
        Piece whiteQueen = new Queen(false);
        Piece whiteBishop1 = new Bishop(false);
        Piece whiteBishop2 = new Bishop(false);
        Piece whiteKnight1 = new Knight(false);
        Piece whiteKnight2 = new Knight(false);
        Piece whiteRook1 = new Rook(false);
        Piece whiteRook2 = new Rook(false);

        positions[7][3].setPiece(whiteKing);
        positions[7][4].setPiece(whiteQueen);
        positions[7][2].setPiece(whiteBishop1);
        positions[7][5].setPiece(whiteBishop2);
        positions[7][1].setPiece(whiteKnight1);
        positions[7][6].setPiece(whiteKnight2);
        positions[7][0].setPiece(whiteRook1);
        positions[7][7].setPiece(whiteRook2);

        for (int i = 0; i < LINE_SIZE; i++) {
            Piece whitePawn = new Pawn(false);
            positions[6][i].setPiece(whitePawn);
        }
    }

    /**
     * 해당 칸의 기물을 확인하는 메서드
     *
     * @param rowCol 확인할 칸 위치
     * @return 기물의 Optional 래핑
     */
    @Override
    public Optional<Piece> peek(String rowCol) {
        int input = Integer.parseInt(rowCol);
        int row = input / 10;
        int col = input % 10;
        return positions[row][col].getPiece();
    }

    /**
     * 해당 위치의 말의 이동 가능한 경로의 리스트를 반환하는 메서드
     *
     * @param rowColString
     * @return
     */
    @Override
    public List<String> getMovements(String rowColString) {
        int[] rowColInt = positionFormatParseStringToIntArr(rowColString);
        Position position = positions[rowColInt[0]][rowColInt[1]];
        Optional<Piece> piece = position.getPiece();
        if (isPieceCanMove(piece)) {
            List<Position> positionList = piece.get().getMovablePosition(position, positions);
            return positionList.stream().map(Position::toString).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 외부로 부터 받은 위치 정보를 행과 열로 바꿔 반환하는 메서드
     * e.g. "35" -> {3,5}
     *
     * @param rowCol
     * @return
     */
    private int[] positionFormatParseStringToIntArr(String rowCol) {
        int input = Integer.parseInt(rowCol);
        int row = input / 10;
        int col = input % 10;
        return new int[]{row, col};
    }

    /**
     * 해당 기물이 자신 진영의 차례여서 움직일 수 있는지 검사하는 메서드
     *
     * @param piece
     * @return
     */
    private boolean isPieceCanMove(Optional<Piece> piece) {
        if (piece.isPresent() && piece.get().isBlack() == isBlackTurn) {
            return true;
        }
        return false;
    }

    /**
     * 기물의 움직임을 적용하는 메서드
     * e.g. "35" -> "25"
     *
     * @param fromStr
     * @param toStr
     */
    @Override
    public void apply(String fromStr, String toStr) {
        int fromInt = Integer.parseInt(fromStr);
        int toInt = Integer.parseInt(toStr);
        Position from = positions[fromInt / 10][fromInt % 10];
        Position to = positions[toInt / 10][toInt % 10];

        isBlackTurn = !isBlackTurn;
        Optional<Piece> target = from.getPiece();
        from.setPiece(null);
        if (target.isPresent()) {
            Optional<Piece> fallenPiece = to.setPiece(target.get());
            if (fallenPiece.isPresent() && fallenPiece.get().getType() == PieceType.KING) {
                isEnd = true;
                isBlackWin = !fallenPiece.get().isBlack();
            }
        }
    }

    @Override
    public boolean isBlackWin() {
        return isBlackWin;
    }

    @Override
    public boolean isGameEnd() {
        return isEnd;
    }

    @Override
    public boolean isBlackTurn() {
        return isBlackTurn;
    }
}
