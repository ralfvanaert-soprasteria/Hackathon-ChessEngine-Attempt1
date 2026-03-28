package sopra.steria.evaluation;

import knight.clubbing.core.BBoard;

public interface Evaluator {
    int evaluate(BBoard board); // should return a score relative to the side to move (Negamax convention)
}
