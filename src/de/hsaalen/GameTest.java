package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testGame() {
        Game game = new Game();
        assertNotNull(game);
    }
}
