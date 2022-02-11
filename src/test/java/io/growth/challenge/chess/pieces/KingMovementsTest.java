package io.growth.challenge.chess.pieces;

import io.growth.challenge.chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("King 이 움직일 수있는 패턴 테스트")
class KingMovementsTest {
    private static final int LINE_SIZE = 8;
    private Position[][] positions;

    private void initPositions(){
        positions = new Position[LINE_SIZE][LINE_SIZE];
        for(int i=0;i<LINE_SIZE;i++){
            for(int j=0;j<LINE_SIZE;j++){
                positions[i][j] = new Position(i,j);
            }
        }
    }

    @Nested
    @DisplayName("장애물이 없을 때")
    class WhenNoBlockedTests {

        @Test
        @DisplayName("모든 방향으로 한 칸씩 전진할 수 있다.")
        void shouldCanGoEveryDirectionPerOneSquare(){
            initPositions();

            // King 이 f5에 놓여져 있을 때
            int[] rowCol = Position.parseToIntArr("f5");
            Position piecePosition = positions[rowCol[0]][rowCol[1]];
            Piece king = new King(true);
            piecePosition.setPiece(king);

            // King 이 움직일 수 있는 칸을 확인한다.
            List<String> kingMovablePositions = king.getMovablePosition(piecePosition, positions).stream().map(Position::toAlgebraicNotation).collect(Collectors.toList());
            List<String> answer = List.of("e6","f6","g6","e5","g5","e4","f4","g4");
            assertTrue(kingMovablePositions.containsAll(answer));
            assertTrue(answer.size() == kingMovablePositions.size());
        }
    }

    @Nested
    @DisplayName("장애물이 있을 때")
    class WhenBlockedTests {

        @Test
        @DisplayName("아군으로 막혀있을 때 이동할 수 없다.")
        void shouldCannotGoBlockedByAlly(){
            initPositions();

            // King 이 f5에, 아군 Pawn 이 f4에 놓여져 있을 때
            int[] rowColForKing = Position.parseToIntArr("f5");
            int[] rowColForAllyPiece = Position.parseToIntArr("f4");
            Position kingPosition = positions[rowColForKing[0]][rowColForKing[1]];
            Position allyPosition = positions[rowColForAllyPiece[0]][rowColForAllyPiece[1]];
            Piece king = new King(true);
            kingPosition.setPiece(king);
            allyPosition.setPiece(new Pawn(true));

            // King 이 움직일 수 있는 칸을 확인한다.
            List<String> kingMovablePositions = king.getMovablePosition(kingPosition, positions).stream().map(Position::toAlgebraicNotation).collect(Collectors.toList());
            List<String> answer = List.of("e6","f6","g6","e5","g5","e4","g4");
            assertTrue(kingMovablePositions.containsAll(answer));
            assertTrue(answer.size() == kingMovablePositions.size());
        }

        @Test
        @DisplayName("적군으로 막혀있을 때 상대 기물을 잡을 수 있다.")
        void shouldCanCaptureEnemy(){
            initPositions();

            // King 이 f5에, 적군 Pawn 이 f4에 놓여져 있을 때
            int[] rowColForKing = Position.parseToIntArr("f5");
            int[] rowColForAllyPiece = Position.parseToIntArr("f4");
            Position kingPosition = positions[rowColForKing[0]][rowColForKing[1]];
            Position enemyPosition = positions[rowColForAllyPiece[0]][rowColForAllyPiece[1]];
            Piece king = new King(true);
            kingPosition.setPiece(king);
            enemyPosition.setPiece(new Pawn(false));

            // King 이 움직일 수 있는 칸을 확인한다.
            List<String> kingMovablePositions = king.getMovablePosition(kingPosition, positions).stream().map(Position::toAlgebraicNotation).collect(Collectors.toList());
            List<String> answer = List.of("e6","f6","g6","e5","f4","g5","e4","g4");
            assertTrue(kingMovablePositions.containsAll(answer));
            assertTrue(answer.size() == kingMovablePositions.size());
        }
    }
}