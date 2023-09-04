package oop_guessing_game;

public class GuessingGame {
    final private Generator gen;
    private boolean gameOver;
    private int answer, differential, maxGuessesAllowed, numGuessesTaken;
    final private int max;

    GuessingGame() {
        this.gen = new Generator();
        this.max = 0;
    }

    GuessingGame(int max) {
        this.gen = new Generator();
        this.max = max;
    }

    void newGame(int maxGuessesAllowed) {
        setMaxGuessesAllowed(maxGuessesAllowed);
        setAnswer(gen.generate(getMax()));
        resetGameOver();
        setDifferential(max);
        setNumGuessesTaken(0);
    }

    String guess(int newGuess) {
        setNumGuessesTaken(getNumGuessesTaken() + 1);
        if (newGuess < 0 || newGuess > getMax())
            return String.format("Guess out of bounds. Please pick between 0 and %s", getMax());

        if (getNumGuessesTaken() == getMaxGuessesAllowed()) {
            setGameOver();
            if (newGuess == getAnswer()) return "Congrats! You solved it on the last try.";
            else return String.format("You lost the game. The answer was %s.", getAnswer());
        }

        String result;
        if (newGuess == getAnswer()) {
            setGameOver();
            return "You got the correct answer!";
        } else if (newGuess > getAnswer()) {
            result = "Your guess is too large.";
        } else {
            result = "Your guess is too small.";
        }

        if (getDifferential() != getMax())
            result += String.format(" Getting %s.", getDifferential() < Math.abs(newGuess - getAnswer()) ? "Colder" : "Warmer");
        setDifferential(newGuess);
        return result;
    }

    //    Getters & Setters
    boolean isGameOver() {
        return gameOver;
    }
    void setGameOver() {
        this.gameOver = true;
    }
    void resetGameOver(){
        this.gameOver = false;
    }

    int getAnswer(){
        return answer;
    }
    void setAnswer(int answer){
        this.answer = answer;
    }

    int getDifferential(){
        return differential;
    }
    void setDifferential(int guess){
        this.differential = Math.abs(guess - answer);
    }

    int getMax(){
        return max;
    }

    int getMaxGuessesAllowed(){
        return maxGuessesAllowed;
    }
    void setMaxGuessesAllowed(int maxGuessesAllowed){
        this.maxGuessesAllowed = maxGuessesAllowed;
    }

    int getNumGuessesTaken(){
        return numGuessesTaken;
    }
    void setNumGuessesTaken(int numGuessesTaken){
        this.numGuessesTaken = numGuessesTaken;
    }
}

class Generator {
    int generate(int max) {
        return (int) (Math.random() * max);
    }
}