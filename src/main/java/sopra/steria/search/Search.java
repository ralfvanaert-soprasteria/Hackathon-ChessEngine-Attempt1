package sopra.steria.search;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BMove;
import knight.clubbing.movegen.MoveGenerator;

import java.util.Random;

import static sopra.steria.EngineConst.INF;
import static sopra.steria.EngineConst.MATE_SCORE;

public class Search {

    private long startTime;

    private final Random rng = new Random();

    public Search() {

    }

    public SearchResult bestMove(BBoard board, SearchSetting setting) {
        this.startTime = System.currentTimeMillis();

        SearchResult result = new SearchResult();
        result.setScore(-INF);

        int alpha = -INF;
        int beta = INF;

        BMove[] moves = new MoveGenerator(board).generateMoves(false);

        // todo order moves

        for (BMove move : moves) {
            if (shouldStop()) break;

            board.makeMove(move, true);
            int score = -negamax(board, setting.maxDepth() - 1, -beta, -alpha, 1);
            board.undoMove(move, true);

            if (score > result.getScore()) {
                result.setScore(score);
                result.setBestMove(move.getUci());
            }

            alpha = Math.max(alpha, score);
        }

        return result;
    }

    private int negamax(BBoard board, int depth, int alpha, int beta, int ply) {
        if (shouldStop()) {
            return 0;
        }

        if (depth <= 0)
            return this.rng.nextInt(INF/10); // todo evaluate(board);

        int bestScore = -INF;

        BMove[] nextMoves = new MoveGenerator(board).generateMoves(false);

        // todo order moves

        if (nextMoves.length == 0) {
            if (board.isInCheck())
                return -MATE_SCORE + ply;
            else
                return 0;
        }

        for (BMove move : nextMoves) {
            // Make and undo move
            board.makeMove(move, true);
            int score = -negamax(board, depth - 1, -beta, -alpha, ply + 1);
            board.undoMove(move, true);

            // Update best score
            bestScore = Math.max(bestScore, score);

            // Alpha-beta pruning
            alpha = Math.max(alpha, score);

            if (alpha >= beta) {
                break;
            }
        }

        return bestScore;
    }

    private boolean shouldStop() {
        return false;
    }
}
