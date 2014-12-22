package eintraege;

import static org.junit.Assert.*;

import org.junit.Test;

import wuerfel.wuerfelbecher;

/**
 * Testet alle Klassen der Einträge
 *
 */
public class eintraegeTest
{

    @Test
    public void test_Augen()
    {
        int[] wuerfel1 = {3,3,3,2,2};
        eintrag e1 = new eintAugenwerte(1);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertTrue(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {3,3,1,2,2};
        eintrag e2 = new eintAugenwerte(1);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {4,3,3,2,2};
        eintrag e3 = new eintAugenwerte(6);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertTrue(e3.istGueltig(becher3));
        
        int[] wuerfel4 = {6,3,1,2,2};
        eintrag e4 = new eintAugenwerte(6);
        wuerfelbecher becher4 = new wuerfelbecher(wuerfel4);
        assertTrue(e4.istGueltig(becher4));   
    }
    
    @Test
    public void test_3erPasch()
    {
        int[] wuerfel1 = {3,3,1,2,2};
        eintrag e1 = new einPasch(3);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {3,3,1,3,2};
        eintrag e2 = new einPasch(3);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {2,3,3,3,3};
        eintrag e3 = new einPasch(3);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertTrue(e3.istGueltig(becher3));
    }
    
    @Test
    public void test_4erPasch()
    {
        int[] wuerfel1 = {3,3,1,3,2};
        eintrag e1 = new einPasch(4);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {3,3,1,3,3};
        eintrag e2 = new einPasch(4);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {3,3,3,3,3};
        eintrag e3 = new einPasch(4);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertTrue(e3.istGueltig(becher3));
    }
    
    @Test
    public void test_Kniffel()
    {
        int[] wuerfel1 = {3,3,3,3,2};
        eintrag e1 = new einPasch(5);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {3,3,3,3,3};
        eintrag e2 = new einPasch(5);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
     }
    
    @Test
    public void test_FullHouse()
    {
        int[] wuerfel1 = {3,3,3,3,2};
        eintrag e1 = new einPasch(2,3);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {3,3,2,2,1};
        eintrag e2 = new einPasch(2,3);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertFalse(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {3,3,3,3,2};
        eintrag e3 = new einPasch(2,3);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertFalse(e3.istGueltig(becher3));
        
        int[] wuerfel4 = {3,2,3,2,2};
        eintrag e4 = new einPasch(2,3);
        wuerfelbecher becher4 = new wuerfelbecher(wuerfel4);
        assertTrue(e4.istGueltig(becher4));
        
     }
    
    @Test
    public void test_klStrasse()
    {
        int[] wuerfel1 = {1,2,3,5,5};
        eintrag e1 = new einStrasse(4);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {6,3,2,4,1};
        eintrag e2 = new einStrasse(4);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {6,5,3,4,1};
        eintrag e3 = new einStrasse(4);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertTrue(e3.istGueltig(becher3));
        
        int[] wuerfel4 = {1,2,3,4,5};
        eintrag e4 = new einStrasse(4);
        wuerfelbecher becher4 = new wuerfelbecher(wuerfel4);
        assertTrue(e4.istGueltig(becher4));
     }
    
    @Test
    public void test_grStrasse()
    {
        int[] wuerfel1 = {1,2,3,4,6};
        eintrag e1 = new einStrasse(5);
        wuerfelbecher becher1 = new wuerfelbecher(wuerfel1);
        assertFalse(e1.istGueltig(becher1));
        
        int[] wuerfel2 = {5,3,2,4,1};
        eintrag e2 = new einStrasse(5);
        wuerfelbecher becher2 = new wuerfelbecher(wuerfel2);
        assertTrue(e2.istGueltig(becher2));
        
        int[] wuerfel3 = {6,5,3,4,2};
        eintrag e3 = new einStrasse(5);
        wuerfelbecher becher3 = new wuerfelbecher(wuerfel3);
        assertTrue(e3.istGueltig(becher3));
        
        int[] wuerfel4 = {1,2,3,4,5};
        eintrag e4 = new einStrasse(5);
        wuerfelbecher becher4 = new wuerfelbecher(wuerfel4);
        assertTrue(e4.istGueltig(becher4));
     }

}
