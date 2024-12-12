package de.hsaalen;

public class Logic {
    public  final int width_in_pixels = 300;
    public  final int height_in_pixels = 300;
    public  final int title_size_in_pixles = 10;
    public  final int game_loop_duration_in_ms = 140;
    public  final int initial_snake_size = 3;


    private Direction direction;
    private boolean inGame;
    private Snake snake;
    private Apple apple;
    public int appleCount = 0;

    public Logic() {
        this.inGame = true;
        this.direction = Direction.right;
    }

    public void initializeGame() {
        place_snake_at_initial_location();
        place_apple_at_random_location();

    }

    public void place_snake_at_initial_location() {
        snake = new Snake(initial_snake_size, title_size_in_pixles);
    }

    public void place_apple_at_random_location() {
        apple = new Apple(title_size_in_pixles, title_size_in_pixles);
        int x = (int) (Math.random() * maximum_tile_index_x()) * title_size_in_pixles;
        int y = (int) (Math.random() * maximum_tile_index_y()) * title_size_in_pixles;
        apple.setX(x);
        apple.setY(y);
    }

    public void checkApple() {
        if (snake.head_position().x == apple.getX() && snake.head_position().y == apple.getY() && snake.length()%3==0 && snake.length()>=6) {
            snake.grow_3fields(direction);
            place_apple_at_random_location();
            appleCount++;
        }
        if (snake.head_position().x == apple.getX() && snake.head_position().y == apple.getY()) {
            snake.grow(direction);
            place_apple_at_random_location();
            appleCount++;
        }
    }

    public void checkCollision() {
        if (snake.is_snake_colliding(width_in_pixels, height_in_pixels)) {
            inGame = false;
        }
    }

    public void moveSnake() {
        snake.move(direction);
    }

    public int maximum_tile_index_x() {
        return (width_in_pixels / title_size_in_pixles) - 1;
    }

    public int maximum_tile_index_y() {
        return (height_in_pixels / title_size_in_pixles) - 1;
    }

    public boolean is_in_game() {
        return inGame;
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
