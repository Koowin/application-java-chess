package io.growth.challenge.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("기물들의 기본 메서드 테스트")
class PieceTest {

    @Test
    @DisplayName("toString 메서드 테스트")
    void toStringTest(){
        Piece blackKing = new King(true);
        assert blackKing != null;
        assertEquals("Black King",blackKing.toString());

        Piece whiteKing = new King(false);
        assert whiteKing != null;
        assertEquals("White King",whiteKing.toString());


        Piece blackQueen = new Queen(true);
        assert blackQueen != null;
        assertEquals("Black Queen",blackQueen.toString());

        Piece whiteQueen = new Queen(false);
        assert whiteQueen != null;
        assertEquals("White Queen",whiteQueen.toString());


        Piece blackRook = new Rook(true);
        assert blackRook != null;
        assertEquals("Black Rook",blackRook.toString());

        Piece whiteRook = new Rook(false);
        assert whiteRook != null;
        assertEquals("White Rook",whiteRook.toString());


        Piece blackBishop = new Bishop(true);
        assert blackBishop != null;
        assertEquals("Black Bishop",blackBishop.toString());

        Piece whiteBishop = new Bishop(false);
        assert whiteBishop != null;
        assertEquals("White Bishop",whiteBishop.toString());


        Piece blackKnight = new Knight(true);
        assert blackKnight != null;
        assertEquals("Black Knight",blackKnight.toString());

        Piece whiteKnight = new Knight(false);
        assert whiteKnight != null;
        assertEquals("White Knight",whiteKnight.toString());


        Piece blackPawn = new Pawn(true);
        assert blackPawn != null;
        assertEquals("Black Pawn",blackPawn.toString());

        Piece whitePawn = new Pawn(false);
        assert whitePawn != null;
        assertEquals("White Pawn",whitePawn.toString());
    }

    @Test
    @DisplayName("isBlack 메서드 테스트")
    void isBlackTest(){
        Piece blackKing = new King(true);
        assert blackKing != null;
        assertTrue(blackKing.isBlack());

        Piece whiteKing = new King(false);
        assert whiteKing != null;
        assertFalse(whiteKing.isBlack());
    }

    @Test
    @DisplayName("getType 메서드 테스트")
    void getTypeTest(){
        Piece king = new King(true);
        assert king != null;
        assertEquals(PieceType.KING, king.getType());

        Piece queen = new Queen(true);
        assert queen != null;
        assertEquals(PieceType.QUEEN, queen.getType());

        Piece rook = new Rook(true);
        assert rook != null;
        assertEquals(PieceType.ROOK, rook.getType());

        Piece bishop = new Bishop(true);
        assert bishop != null;
        assertEquals(PieceType.BISHOP, bishop.getType());

        Piece knight = new Knight(true);
        assert knight != null;
        assertEquals(PieceType.KNIGHT, knight.getType());

        Piece pawn = new Pawn(true);
        assert pawn != null;
        assertEquals(PieceType.PAWN, pawn.getType());
    }
}