package sopra.steria.search;

public class SearchResult {
    private int score;
    private String bestMove;
    private int depth;
    private long nodesSearched;
    private long timeTakenMillis;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBestMove() {
        return bestMove;
    }

    public void setBestMove(String bestMove) {
        this.bestMove = bestMove;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public long getNodesSearched() {
        return nodesSearched;
    }

    public void setNodesSearched(long nodesSearched) {
        this.nodesSearched = nodesSearched;
    }

    public long getTimeTakenMillis() {
        return timeTakenMillis;
    }

    public void setTimeTakenMillis(long timeTakenMillis) {
        this.timeTakenMillis = timeTakenMillis;
    }
}
