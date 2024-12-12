package de.hsaalen;

public class GameLogic {
    public static final int width_in_pixels = 300;
    public static final int height_in_pixels = 300;
    public static final int title_size_in_pixles = 10;
    public static final int MAXIMUM_SNAKE_LENGTH = 900;
    public static final int game_loop_duration_in_ms = 140;
    public static final int initial_snake_size = 3;

    private Direction direction;
    private boolean inGame;
    private Snake snake;
    private Apple apple;

    public GameLogic() {
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
        int x = (int) (Math.random() * maximumTileIndexX()) * title_size_in_pixles;
        int y = (int) (Math.random() * maximumTileIndexY()) * title_size_in_pixles;
        apple.setX(x);
        apple.setY(y);
    }

    public void checkApple() {
        if (snake.head_position().x == apple.getX() && snake.head_position().y == apple.getY()) {
            snake.grow(direction);
            place_apple_at_random_location();
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

    public int maximumTileIndexX() {
        return (width_in_pixels / title_size_in_pixles) - 1;
    }

    public int maximumTileIndexY() {
        return (height_in_pixels / title_size_in_pixles) - 1;
    }

    public boolean isInGame() {
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
