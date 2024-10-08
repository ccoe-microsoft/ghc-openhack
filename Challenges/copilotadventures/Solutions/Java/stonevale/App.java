import java.util.HashMap;
import java.util.Map;

public class App {
    // Lookup tables as constants
    private static final Map<String, String> WINNING_MOVES = new HashMap<>();
    private static final Map<String, Integer> POINTS = new HashMap<>();

    static {
        WINNING_MOVES.put("rock", "scissors");
        WINNING_MOVES.put("scissors", "paper");
        WINNING_MOVES.put("paper", "rock");

        POINTS.put("rock", 1);
        POINTS.put("paper", 2);
        POINTS.put("scissors", 3);
    }

    public static void main(String[] args) {
        // Sample moves
        String[] player1Moves = {"scissors", "paper", "scissors", "rock", "rock"};
        String[] player2Moves = {"rock", "rock", "paper", "scissors", "paper"};

        // Calculate scores
        int player1Score = calculateScore(player1Moves, player2Moves);
        int player2Score = calculateScore(player2Moves, player1Moves);

        // Display the final scores and winner
        displayResults(player1Score, player2Score);
    }

    private static int calculateScore(String[] playerMoves, String[] opponentMoves) {
        int score = 0;
        int rounds = Math.min(playerMoves.length, opponentMoves.length);

        for (int i = 0; i < rounds; i++) {
            String playerMove = playerMoves[i];
            String opponentMove = opponentMoves[i];

            if (!playerMove.equals(opponentMove) && WINNING_MOVES.get(playerMove).equals(opponentMove)) {
                score += POINTS.get(playerMove);
            }
        }

        return score;
    }

    private static void displayResults(int player1Score, int player2Score) {
        System.out.println("Player 1 Score: " + player1Score);
        System.out.println("Player 2 Score: " + player2Score);

        String winnerMessage;
        if (player1Score > player2Score) {
            winnerMessage = "Player 1 wins!";
        } else if (player2Score > player1Score) {
            winnerMessage = "Player 2 wins!";
        } else {
            winnerMessage = "It's a draw!";
        }
        System.out.println(winnerMessage);
    }
}