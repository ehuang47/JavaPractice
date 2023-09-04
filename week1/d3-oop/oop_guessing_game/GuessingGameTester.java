package oop_guessing_game;

import java.util.Scanner;

public class GuessingGameTester {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GuessingGame game;

        for (int i = 0; i < 2; i++){
            System.out.println("Welcome to the Guessing Game.\n" +
                    "Enter the maximum number.");
            int maxValue = Integer.parseInt(in.nextLine());
            game = new GuessingGame(maxValue);

            System.out.println("Enter the number of guesses allowed.");
            int maxGuesses = Integer.parseInt(in.nextLine());
            game.newGame(maxGuesses);

            while (!game.isGameOver()) {
                System.out.printf("Enter a guess. Remember, it must be between 0 and %s.\n", maxValue);
                int guess = Integer.parseInt(in.nextLine());
                String res = game.guess(guess);
                System.out.println(res);
            }
            System.out.println("The game is over. Please start a new game if you want to play again.\n" +
                    "Would you like to play again? Enter 'Y' for yes, 'N' for no.");
            String input = in.nextLine();
            if (input.equals("N")) break;
        }
    }
}
