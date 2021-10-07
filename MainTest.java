package pack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class MainTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void transmit() {
        Assertions.assertEquals(Main.Transmit(3),new Score(3,1));
    }

    @Test
    void gcd() {
        Assertions.assertEquals(Main.gcd(3,6),3);
    }

    @Test
    void add() {
        Assertions.assertEquals(Main.Add(new Score(6,1),new Score(3,1)),
                new Score(9,1));
    }

    @Test
    void minus() {
        Assertions.assertEquals(Main.Add(new Score(6,1),new Score(3,1)),
                new Score(3,1));
    }

    @Test
    void multiply() {
        Assertions.assertEquals(Main.Add(new Score(6,1),new Score(3,1)),
                new Score(18,1));
    }

    @Test
    void divide() {
        Assertions.assertEquals(Main.Add(new Score(6,1),new Score(3,1)),
                new Score(2,1));
    }

    @Test
    void judge() {
        HashSet<String> hs=null;
        Assertions.assertEquals(Main.Judge(new Expression("1+2=",new Score(3,1)),
                hs),true);
    }
}