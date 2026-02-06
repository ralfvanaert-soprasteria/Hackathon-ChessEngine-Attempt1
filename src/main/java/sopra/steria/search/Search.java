package sopra.steria.search;

import knight.clubbing.core.BBoard;
import knight.clubbing.core.BMove;
import knight.clubbing.movegen.MoveGenerator;
import sopra.steria.evaluation.BadEvaluator;
import sopra.steria.evaluation.Evaluator;
import sopra.steria.ordering.BadMoveOrderer;
import sopra.steria.ordering.MoveOrderer;

import static sopra.steria.EngineConst.INF;
import static sopra.steria.EngineConst.MATE_SCORE;

public class Search {

    private long startTime;
    private SearchSetting setting;

    private final Evaluator evaluator;
    private final MoveOrderer moveOrderer;

    public Search() {
        this.evaluator = new BadEvaluator();
        this.moveOrderer = new BadMoveOrderer();
    }

    public SearchResult bestMove(BBoard board, SearchSetting setting) {
        this.startTime = System.currentTimeMillis();
        this.setting = setting;

        SearchResult result = new SearchResult();
        result.setScore(-INF);

        int alpha = -INF;
        int beta = INF;

        for (int depth = 1; depth <= setting.maxDepth(); depth++) {
            if (shouldStop()) break;

            BMove[] moves = new MoveGenerator(board).generateMoves(false);

            moveOrderer.orderMoves(moves, board);

            for (BMove move : moves) {
                if (shouldStop()) break;

                board.makeMove(move, true);
                int score = -negamax(board, depth - 1, -beta, -alpha, 1);
                board.undoMove(move, true);

                if (score > result.getScore()) {
                    result.setScore(score);
                    result.setBestMove(move.getUci());
                }

                alpha = Math.max(alpha, score);
            }
        }

        return result;
    }

    private int negamax(BBoard board, int depth, int alpha, int beta, int ply) {
        if (shouldStop()) {
            return 0;
        }

        if (depth <= 0)
            return evaluator.evaluate(board);

        int bestScore = -INF;

        BMove[] nextMoves = new MoveGenerator(board).generateMoves(false);

        moveOrderer.orderMoves(nextMoves, board);

        if (nextMoves.length == 0) {
            if (board.isInCheck())
                return -MATE_SCORE + ply;
            else
                return 0;
        }

        if (board.isDrawByRepetition()) {
            return 0;
        }

        for (BMove move : nextMoves) {
            board.makeMove(move, true);
            int score = -negamax(board, depth - 1, -beta, -alpha, ply + 1);
            board.undoMove(move, true);

            bestScore = Math.max(bestScore, score);
            alpha = Math.max(alpha, score);

            if (alpha >= beta) {
                break;
            }
        }

        return bestScore;
    }

    private boolean shouldStop() {
        return System.currentTimeMillis() - startTime >= setting.timeLimit();
    }
}
