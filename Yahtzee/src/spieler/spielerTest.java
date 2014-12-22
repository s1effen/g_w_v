package spieler;

import static org.junit.Assert.*;

import org.junit.Test;

import wuerfel.wuerfelbecher;

public class spielerTest
{

    @Test
    public void testZettelVoll()
    {
        spieler s = new spieler("Hans");
        
        int[] wuerfel1 = {6,6,6,6,6};
        s._becher = new wuerfelbecher(wuerfel1);
        s.werte(6);
        
        int[] wuerfel2 = {5,5,5,5,5};
        s._becher = new wuerfelbecher(wuerfel2);
        s.werte(5);
        
        int[] wuerfel3 = {5,5,5,4,4};
        s._becher = new wuerfelbecher(wuerfel3);
        s.werte(9);

        int[] wuerfel4 = {4,4,4,4,4};
        s._becher = new wuerfelbecher(wuerfel4);
        s.werte(4);
        
        s.streiche(1);
        s.streiche(2);
        s.streiche(3);
        s.streiche(7);
        s.streiche(8);
        s.streiche(10);
        s.streiche(11);
        s.streiche(12);
        //s.gibTabelle();
        assertFalse(s.fertig());
        s.streiche(13);
        //s.gibTabelle();
        assertTrue(s.fertig());
    }
    
    public void testStreichen()
    {
        spieler s = new spieler("Hans");
        
        s.streiche(1);
        
        //Man kann nicht in gestrichene Eintragen
        int[] wuerfel1 = {1,1,1,2,2};
        s._becher = new wuerfelbecher(wuerfel1);
        assertFalse(s.werte(1)); 
        assertTrue(s.werte(2)); 

        //Man kann eingeragene nicht streichen
        assertFalse(s.streiche(2)); 
        assertTrue(s.streiche(3)); 


    }

}
