package wuerfel;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet den Würfelbecher
 *
 */
public class wuerfelbecherTest
{

    @Test
    public void test()
    {
        int[] wuerfel = {1,3,5,2,1};
        wuerfelbecher becher = new wuerfelbecher(wuerfel);
        
        assertTrue(becher.anzahl(1) == 2);
        assertTrue(becher.anzahl(2) == 1);
        assertTrue(becher.anzahl(6) == 0);
        assertTrue(becher.augenzahl_vorhanden(2));
        assertFalse(becher.augenzahl_vorhanden(6));
        assertTrue(becher.gibSumme() == 12);
        assertTrue(becher.gibSumme(1) == 2);
        assertTrue(becher.maximum() == 5);
        assertTrue(becher.minimum() == 1);
    }

}
