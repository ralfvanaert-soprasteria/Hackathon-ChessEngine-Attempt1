package sopra.steria.ordering;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BMove;
import knight.clubbing.core.BPiece;

public class BadMoveOrderer implements MoveOrderer {

    @Override
    public void orderMoves(BMove[] moves, BBoard board) {
        int[] scores = new int[moves.length];

        for (int i = 0; i < moves.length; i++) {
            scores[i] = score(moves[i], board);
        }

        sortMovesByScore(moves, scores);
    }

    private int score(BMove move, BBoard board) {
        int score = 0;
        int movingPiece = board.getPieceBoards()[move.startSquare()];
        int victimPiece = board.getPieceBoards()[move.targetSquare()];

        // Oooh me move knight!
        if (BPiece.getPieceType(movingPiece) == BPiece.knight)
            score += 1000;

        switch(BPiece.getPieceType(victimPiece)) {
            case BPiece.queen:
                score += 900; // Oooh me queen!
                break;
            case BPiece.rook:
                score += 500; // Oooh juicy rook!
                break;
            case BPiece.none:
                break;
            default:
                score += 100; // Take take take!
                break;
        }

        return score;
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
