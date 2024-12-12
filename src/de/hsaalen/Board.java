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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    public final int width_in_pixels = 300;
    public final int height_in_pixels = 300;
    public final int tile_size_in_pixels = 10;
    public final int maximum_snake_length = 900;
    private final int game_loop_duration_in_ms = 140;
    public final int initial_snake_size = 3;

    public Snake snake;
    public Apple init_apple;


    private int current_snake_size;

    Direction direction = Direction.right;

    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(width_in_pixels, height_in_pixels));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initGame() {

        place_snake_at_initial_location();
        place_apple_at_random_location();
        start_game_loop_timer();
    }
    public void start_game_loop_timer(){
        timer = new Timer(game_loop_duration_in_ms, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, init_apple.getX(), init_apple.getY(),this);

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
        g.drawString(msg, (width_in_pixels - metr.stringWidth(msg)) / 2, height_in_pixels / 2);
    }

    private void checkApple() {

        //if ((snake.head_position().x == apple_x) && (snake.head_position().y == apple_y))
        if ((snake.head_position().x == init_apple.getX()) && (snake.head_position().y == init_apple.getY())){

            snake.grow(direction);
            place_apple_at_random_location();
        }
    }

    private void move() {
        snake.move(direction);
    }

    private void checkCollision() {
        if(snake.is_snake_colliding(width_in_pixels, height_in_pixels)) {
            inGame = false;
        }
    }

    public int maximum_tile_index_x()
    {
        return ( width_in_pixels / tile_size_in_pixels ) - 1;
    }

    public int maximum_tile_index_y()
    {
        return ( height_in_pixels / tile_size_in_pixels ) - 1;
    }

    public void place_snake_at_initial_location()
    {
        snake = new Snake(3, tile_size_in_pixels);
    }


    private void place_apple_at_random_location() {

        init_apple = new Apple(tile_size_in_pixels, tile_size_in_pixels);

        int r = (int) (Math.random() * maximum_tile_index_x());
        init_apple.setX(r*tile_size_in_pixels);

        r = (int) (Math.random() * maximum_tile_index_y());
        init_apple.setY(r*tile_size_in_pixels);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                direction = Direction.left;
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                direction = Direction.right;

            }

            if ((key == KeyEvent.VK_UP)) {
                direction = Direction.up;

            }

            if ((key == KeyEvent.VK_DOWN)) {
                direction = Direction.down;
            }
        }
    }
}
