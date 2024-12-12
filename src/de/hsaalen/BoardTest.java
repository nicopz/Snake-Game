package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void  test_maximum_tile_index_x()
    {
        GameLogic gameLogic = new GameLogic();
        int maximum_tile_index_x = gameLogic.maximum_tile_index_x();
        assertEquals( ( maximum_tile_index_x + 1 ) * gameLogic.title_size_in_pixles, gameLogic.width_in_pixels );
    }

    @Test
    public void  test_maximum_tile_index_y()
    {
        GameLogic gameLogic = new GameLogic();
        int maximum_tile_index_y = gameLogic.maximum_tile_index_y();
        assertEquals( ( maximum_tile_index_y + 1 ) * gameLogic.title_size_in_pixles, gameLogic.width_in_pixels );
    }
    @Test
    public void testConcatenate() {
        Board board = new Board();
        assertNotNull( board );
    }

    @Test
    public void testApple(){
        GameLogic gameLogic = new GameLogic();
        Apple apple = new Apple(gameLogic.title_size_in_pixles, gameLogic.title_size_in_pixles);
        int r_x = (int) (Math.random() * gameLogic.maximum_tile_index_x());
        int r_y = (int) (Math.random() * gameLogic.maximum_tile_index_y());
        apple.setX(r_x*gameLogic.title_size_in_pixles);
        apple.setY(r_y*gameLogic.title_size_in_pixles);
        assertNotEquals(null ,apple);
        assertEquals(r_x*gameLogic.title_size_in_pixles, apple.getX());
        assertEquals(r_y*gameLogic.title_size_in_pixles, apple.getY());
    }

    @Test
    public void init_board(){
        Board board = new Board();
        assertNotNull( board );
    }

}

