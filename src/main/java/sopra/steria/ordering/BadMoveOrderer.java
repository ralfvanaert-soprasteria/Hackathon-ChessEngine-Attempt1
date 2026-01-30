package sopra.steria.ordering;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BMove;
import knight.clubbing.core.BPiece;

public class BadMoveOrderer implements MoveOrderer {

    private static final int[] PIECE_PRIORITY = {
            0,    // EMPTY
            500,  // PAWN
            750,  // KNIGHT
            100,  // BISHOP
            300,  // ROOK
            450,  // QUEEN
            100   // KING
    };


    @Override
    public void orderMoves(BMove[] moves, BBoard board) {
        int[] scores = new int[moves.length];

        for (int i = 0; i < moves.length; i++) {
            scores[i] = score(moves[i], board);
        }

        sortMovesByScore(moves, scores);
    }

    private int score(BMove move, BBoard board) {
        int movingPiece = board.getPieceBoards()[move.startSquare()];

        return PIECE_PRIORITY[BPiece.getPieceType(movingPiece)];
    }

    private void sortMovesByScore(BMove[] moves, int[] scores) {
        for (int i = 0; i < moves.length - 1; i++) {
            for (int j = i + 1; j < moves.length; j++) {
                if (scores[j] > scores[i]) {
                    // Swap moves
                    BMove tempMove = moves[i];
                    moves[i] = moves[j];
                    moves[j] = tempMove;

                    // Swap scores
                    int tempScore = scores[i];
                    scores[i] = scores[j];
                    scores[j] = tempScore;
                }
            }
        }
    }
}
