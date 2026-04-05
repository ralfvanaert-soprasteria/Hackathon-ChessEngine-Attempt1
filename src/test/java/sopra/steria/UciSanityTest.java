package sopra.steria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UciSanityTest {

    private Uci uci;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        uci = new Uci();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Requirement of (baseline) engine: UCI base should work, including uci
     */
    @Test
    void testUciCommand() {
        // Act
        uci.handleCommand("uci");
        String output = outContent.toString();

        assertTrue(output.contains("id name"), "Engine should identify itself");
        assertTrue(output.contains("uciok"), "Engine should respond with uciok");
    }

    /**
     * Requirement of (baseline) engine: UCI base should work, including isReady
     */
    @Test
    void testIsReadyCommand() {
        uci.handleCommand("isready");
        String output = outContent.toString();

        assertTrue(output.contains("readyok"), "Engine should respond with readyok");
    }

    /**
     * Requirement of (baseline) engine: UCI base should work, including position startpos
     */
    @Test
    void testPositionStartpos() {
        uci.handleCommand("position startpos");

        assertNotNull(uci.getBoard(), "Board should be initialized");
        assertTrue(uci.getBoard().isWhiteToMove(), "White should be to move in startpos");
    }

    /**
     * Requirement of (baseline) engine: UCI base should work, including position startpos with moves
     */
    @Test
    void testPositionWithMoves() {
        uci.handleCommand("position startpos moves e2e4 e7e5");

        assertNotNull(uci.getBoard(), "Board should be initialized");
        assertTrue(uci.getBoard().isWhiteToMove(), "White should be to move after black's move");
    }

    /**
     * Requirement of (baseline) engine: UCI base should work, including position fen
     */
    @Test
    void testPositionFen() {
        uci.handleCommand("position fen rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");

        assertNotNull(uci.getBoard(), "Board should be initialized from FEN");
        assertFalse(uci.getBoard().isWhiteToMove(), "Black should be to move");
    }

    /**
     * Requirement of (baseline) engine: UCI should work with standard procedure
     */
    @Test
    void testStartProcedures() {
        uci.handleCommand("uci");
        uci.handleCommand("isready");
        uci.handleCommand("position startpos moves e2e4 e7e5 g1f3");

        String output = outContent.toString();

        assertTrue(output.contains("id name"), "Engine should identify itself");
        assertTrue(output.contains("uciok"), "Engine should respond with uciok");
        assertTrue(output.contains("readyok"), "Engine should respond with readyok");
        assertNotNull(uci.getBoard(), "Board should be initialized");
        assertFalse(uci.getBoard().isWhiteToMove(), "Black should be to move");
    }

    /**
     * Requirement of (baseline) engine: UCI should work with standard procedure including go
     */
    @Test
    void testStartProcedure_withGo() throws InterruptedException {
        uci.handleCommand("uci");
        uci.handleCommand("isready");
        uci.handleCommand("position startpos moves e2e4 e7e5 g1f3");
        uci.handleCommand("go wtime 1000 btime 1000");
        Thread.sleep(300);

        String output = outContent.toString();

        assertTrue(output.contains("id name"), "Engine should identify itself");
        assertTrue(output.contains("uciok"), "Engine should respond with uciok");
        assertTrue(output.contains("readyok"), "Engine should respond with readyok");
        assertNotNull(uci.getBoard(), "Board should be initialized");
        assertFalse(uci.getBoard().isWhiteToMove(), "Black should be to move");
        assertTrue(output.contains("info depth"));
        assertTrue(output.contains("bestmove"));
    }
}