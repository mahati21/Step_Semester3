package categoryB.Class_problemsW1;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove))
        {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper")))
        {
            return "Player Wins";
        }
        return "Computer Wins";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};
        int wins = 0;
        int losses = 0;
        int draws = 0;
        for (int round = 1; round <= 5; round++)
        {
            System.out.print("Enter your move: ");
            String playerMove = scanner.nextLine();
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);
            if (result.equals("Player Wins"))
            {
                wins++;
            }
            else if (result.equals("Computer Wins"))
            {
                losses++;
            }
            else
            {
                draws++;
            }
            System.out.println("Round " + round + " | Player: " + playerMove + " | Computer: " + computerMove + " | Result: " + result);
        }
        double winPercentage = (wins / 5.0) * 100;
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win Percentage: " + winPercentage + "%");
        scanner.close();
    }
}