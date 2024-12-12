package de.hsaalen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    private final Logic logic;
    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {
        logic = new Logic();
        logic.initializeGame();

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(logic.width_in_pixels, logic.height_in_pixels));
        loadImages();
    }

    private void loadImages() {
        ball = new ImageIcon("src/resources/dot.png").getImage();
        apple = new ImageIcon("src/resources/apple.png").getImage();
        head = new ImageIcon("src/resources/head.png").getImage();
    }

    public void start_game_loop_timer() {
        timer = new Timer(logic.game_loop_duration_in_ms, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (logic.is_in_game()) {
            Apple apple = logic.getApple();
            g.drawImage(this.apple, apple.getX(), apple.getY(), this);

            Snake snake = logic.getSnake();
            for (int z = 0; z < snake.length(); z++) {
                if (z == 0) {
                    g.drawImage(head, snake.position(z).x, snake.position(z).y, this);
                } else {
                    g.drawImage(ball, snake.position(z).x, snake.position(z).y, this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (logic.width_in_pixels - metr.stringWidth(msg)) / 2, logic.height_in_pixels / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logic.is_in_game()) {
            logic.checkApple();
            logic.checkCollision();
            logic.moveSnake();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) logic.setDirection(Direction.left);
            if (key == KeyEvent.VK_RIGHT) logic.setDirection(Direction.right);
            if (key == KeyEvent.VK_UP) logic.setDirection(Direction.up);
            if (key == KeyEvent.VK_DOWN) logic.setDirection(Direction.down);
        }
    }
}
