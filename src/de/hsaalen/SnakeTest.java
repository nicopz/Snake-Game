package de.hsaalen;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
public class SnakeTest
{
    @Test
    public void test_initialization()
    {
        Snake snake = new Snake( 3, 10 );
        assertEquals( snake.length(), 3 );

        IntPair coordinate;
        coordinate= snake.position(0);
        assertEquals( coordinate.x, 50 );
        assertEquals( coordinate.y, 50 );

        coordinate = snake.position(2);
        assertEquals( coordinate.x, 30 );
        assertEquals( coordinate.y, 50 );
    }
    @Test
    public void test_move_snake_right()
    {
        Snake snake = new Snake( 3, 10 );
        snake.move( Direction.right );
        snake.move( Direction.right );

        IntPair coordinate = snake.position(0);
        assertEquals( coordinate.x, 70 );
        assertEquals( coordinate.y, 50 );
        assertEquals( snake.length(), 3 );
    }
    @Test
    public void test_move_snake_left()
    {
        Snake snake = new Snake( 3, 10 );
        snake.move( Direction.left );
        snake.move( Direction.left );
        snake.move( Direction.left );

        IntPair coordinate = snake.position(0);
        assertEquals( coordinate.x, 20 );
        assertEquals( coordinate.y, 50 );
        assertEquals( snake.length(), 3 );
    }
    @Test
    public void test_move_snake_start()
    {
        Snake snake = new Snake( 3, 10 );

        IntPair coordinate = snake.position(0);
        assertEquals( coordinate.x, 50 );
        assertEquals( coordinate.y, 50 );
        assertEquals( snake.length(), 3 );
    }

    @Test
    public void test_is_colliding_with_itself()
    {
        Snake snake = new Snake( 3, 10 );
        assertFalse( snake.is_colliding_with_itself() );
        snake.grow( Direction.up );
        snake.grow( Direction.left );
        snake.move( Direction.down );
        assertTrue( snake.is_colliding_with_itself() );
    }
    @Test
    public void test_is_outside_board()
    {
        Snake snake = new Snake( 3, 10 );
        assertFalse( snake.is_outside_board( 300, 300 ) );
        for ( int i = 0; i < 10; i++ )
            snake.move( Direction.left );
        assertTrue( snake.is_outside_board( 300, 300 ) );
    }

}
