package sopra.steria.evaluation;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BPiece;

public class BadEvaluator implements Evaluator {
    private static final int PIECE_VALUE = 100;

    @Override
    public int evaluate(BBoard board) {
        int white = PIECE_VALUE * Long.bitCount(board.getColorBitboard(BPiece.white));
        int black = PIECE_VALUE * Long.bitCount(board.getColorBitboard(BPiece.white));

        return white - black;
    }
}
