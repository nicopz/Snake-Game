package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void  test_maximum_tile_index_x()
    {
        Logic logic = new Logic();
        int maximum_tile_index_x = logic.maximum_tile_index_x();
        assertEquals( ( maximum_tile_index_x + 1 ) * logic.title_size_in_pixles, logic.width_in_pixels );
    }

    @Test
    public void  test_maximum_tile_index_y()
    {
        Logic logic = new Logic();
        int maximum_tile_index_y = logic.maximum_tile_index_y();
        assertEquals( ( maximum_tile_index_y + 1 ) * logic.title_size_in_pixles, logic.width_in_pixels );
    }
    @Test
    public void testConcatenate() {
        Board board = new Board();
        assertNotNull( board );
    }

    @Test
    public void testApple(){
        Logic logic = new Logic();
        Apple apple = new Apple(logic.title_size_in_pixles, logic.title_size_in_pixles);
        int r_x = (int) (Math.random() * logic.maximum_tile_index_x());
        int r_y = (int) (Math.random() * logic.maximum_tile_index_y());
        apple.setX(r_x* logic.title_size_in_pixles);
        apple.setY(r_y* logic.title_size_in_pixles);
        assertNotEquals(null ,apple);
        assertEquals(r_x* logic.title_size_in_pixles, apple.getX());
        assertEquals(r_y* logic.title_size_in_pixles, apple.getY());
    }

    @Test
    public void init_board(){
        Board board = new Board();
        assertNotNull( board );
    }

}

