package sopra.steria.evaluation;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BPiece;

public class BadEvaluator implements Evaluator {
    private static final int PIECE_VALUE = 100;

    @Override
    public int evaluate(BBoard board) {
        int score = 0;

        // Me take opponent material good
        int whiteMaterial = PIECE_VALUE * Long.bitCount(board.getColorBitboard(BPiece.white));
        int blackMaterial = PIECE_VALUE * Long.bitCount(board.getColorBitboard(BPiece.black));
        score += (int) (whiteMaterial * 0.5 - blackMaterial);

        return board.isWhiteToMove() ? score : -score;
    }
}
