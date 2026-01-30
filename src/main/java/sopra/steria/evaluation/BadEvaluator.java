package sopra.steria.evaluation;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BPiece;

public class BadEvaluator implements Evaluator {
    private final int PAWN_VALUE = 100;
    private final int KNIGHT_VALUE = 200;
    private final int BISHOP_VALUE = 300;
    private final int ROOK_VALUE = 400;
    private final int QUEEN_VALUE = 500;

    @Override
    public int evaluate(BBoard board) {
        int wPawn = Long.bitCount(board.getBitboard(BPiece.whitePawn));
        int wKnight = Long.bitCount(board.getBitboard(BPiece.whiteKnight));
        int wBishop = Long.bitCount(board.getBitboard(BPiece.whiteBishop));
        int wRook = Long.bitCount(board.getBitboard(BPiece.whiteRook));
        int wQueen = Long.bitCount(board.getBitboard(BPiece.whiteQueen));
        int white = wPawn * PAWN_VALUE +
                wKnight * KNIGHT_VALUE +
                wBishop * BISHOP_VALUE +
                wRook * ROOK_VALUE +
                wQueen * QUEEN_VALUE;

        int bPawn = Long.bitCount(board.getBitboard(BPiece.blackPawn));
        int bKnight = Long.bitCount(board.getBitboard(BPiece.blackKnight));
        int bBishop = Long.bitCount(board.getBitboard(BPiece.blackBishop));
        int bRook = Long.bitCount(board.getBitboard(BPiece.blackRook));
        int bQueen = Long.bitCount(board.getBitboard(BPiece.blackQueen));
        int black = bPawn * PAWN_VALUE +
                bKnight * KNIGHT_VALUE +
                bBishop * BISHOP_VALUE +
                bRook * ROOK_VALUE +
                bQueen * QUEEN_VALUE;

        return white - black;
    }
}
