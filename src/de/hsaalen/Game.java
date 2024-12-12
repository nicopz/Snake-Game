package de.hsaalen;

import java.awt.EventQueue;
import javax.swing.JFrame;
public class Game extends JFrame {
    private Board board;
    private WindowInitializer windowInitializer;
    public Game() {
        board = new Board();
        add(board);
        WindowInitializer.initialize(this,"Snake");
        startGame();
    }

    private void startGame(){
        board.place_snake_at_initial_location();
        board.place_apple_at_random_location();
        board.start_game_loop_timer();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}
