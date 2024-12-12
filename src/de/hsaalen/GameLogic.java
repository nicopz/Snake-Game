package de.hsaalen;

public class GameLogic {
    public static final int WIDTH_IN_PIXELS = 300;
    public static final int HEIGHT_IN_PIXELS = 300;
    public static final int TILE_SIZE_IN_PIXELS = 10;
    public static final int MAXIMUM_SNAKE_LENGTH = 900;
    public static final int GAME_LOOP_DURATION_IN_MS = 140;
    public static final int INITIAL_SNAKE_SIZE = 3;

    private Direction direction;
    private boolean inGame;
    private Snake snake;
    private Apple apple;

    public GameLogic() {
        this.inGame = true;
        this.direction = Direction.right;
    }

    public void initializeGame() {
        placeSnakeAtInitialLocation();
        placeAppleAtRandomLocation();
    }

    public void placeSnakeAtInitialLocation() {
        snake = new Snake(INITIAL_SNAKE_SIZE, TILE_SIZE_IN_PIXELS);
    }

    public void placeAppleAtRandomLocation() {
        apple = new Apple(TILE_SIZE_IN_PIXELS, TILE_SIZE_IN_PIXELS);
        int x = (int) (Math.random() * maximumTileIndexX()) * TILE_SIZE_IN_PIXELS;
        int y = (int) (Math.random() * maximumTileIndexY()) * TILE_SIZE_IN_PIXELS;
        apple.setX(x);
        apple.setY(y);
    }

    public void checkApple() {
        if (snake.head_position().x == apple.getX() && snake.head_position().y == apple.getY()) {
            snake.grow(direction);
            placeAppleAtRandomLocation();
        }
    }

    public void checkCollision() {
        if (snake.is_snake_colliding(WIDTH_IN_PIXELS, HEIGHT_IN_PIXELS)) {
            inGame = false;
        }
    }

    public void moveSnake() {
        snake.move(direction);
    }

    public int maximumTileIndexX() {
        return (WIDTH_IN_PIXELS / TILE_SIZE_IN_PIXELS) - 1;
    }

    public int maximumTileIndexY() {
        return (HEIGHT_IN_PIXELS / TILE_SIZE_IN_PIXELS) - 1;
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
