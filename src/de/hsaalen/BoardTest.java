package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void  test_maximum_tile_index_x()
    {
        Board board = new Board();
        int maximum_tile_index_x = board.maximum_tile_index_x();
        assertEquals( ( maximum_tile_index_x + 1 ) * board.tile_size_in_pixels, board.width_in_pixels );
    }

    @Test
    public void  test_maximum_tile_index_y()
    {
        Board board = new Board();
        int maximum_tile_index_y = board.maximum_tile_index_y();
        assertEquals( ( maximum_tile_index_y + 1 ) * board.tile_size_in_pixels, board.width_in_pixels );
    }
    @Test
    public void testConcatenate() {
        Board board = new Board();
        assertNotNull( board );
    }

    @Test
    public void testApple(){
        Board board = new Board();
        Apple apple = new Apple(board.tile_size_in_pixels, board.tile_size_in_pixels);
        int r_x = (int) (Math.random() * board.maximum_tile_index_x());
        int r_y = (int) (Math.random() * board.maximum_tile_index_y());
        apple.setX(r_x*board.tile_size_in_pixels);
        apple.setY(r_y*board.tile_size_in_pixels);
        assertNotEquals(null ,apple);
        assertEquals(r_x*board.tile_size_in_pixels, apple.getX());
        assertEquals(r_y*board.tile_size_in_pixels, apple.getY());
    }

}