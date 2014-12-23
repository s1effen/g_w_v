package eintraege;

import wuerfel.wuerfelbecher;

/**
 * Repräsentiert das Feld "3er Pasch", "4er Pasch", "Full-House" und "Kniffel"
 */
public class einPasch extends eintrag
{
    protected int _pasch1Anzahl;
    protected int _pasch2Anzahl;
    
    /**
     * Erzeugt ein Feld wo nur ein Pasch bedingung ist.
     * Also "3er Pasch", "4er Pasch" und "Kniffel"
     * @param pasch1 Anzahl des Pasches
     */
    public einPasch(Integer pasch1)
    {
        super();
        setValues(pasch1, Integer.valueOf(0));
       
    }
    
    /**
     * Erzeugt ein Feld wo zwei Pasche bedingung sind.
     * Also den "Full-House"
     * @param pasch1 Anzahl des ersten Pasches
     * @param pasch2 Anzahl des zweiten Pasches
     */
    public einPasch(Integer pasch1, Integer pasch2)
    {
        super();
        setValues(pasch1, pasch2);
    }

    /**
     * Setzt die Exmplarvariablen für die Pasche und stellt dabei sicher, dass
     * der groessere Wert immer pasch1 ist.
     * Dazu legt es anhand der Parameter fest, um welches Feld es sich handelt.
     * @param pasch1 Anzahl des ersten Pasches
     * @param pasch2 Anzahl des zweiten Pasches
     */
    private void setValues(Integer pasch1, Integer pasch2)
    {
        if(pasch1 == 5 && pasch2 == 0)
        {
            super.setzeName("Kniffel");
        }
        else if(pasch1 < 5 && pasch2 == 0)
        {
            super.setzeName(pasch1.toString() + "er Pasch"); 
        }
        else if(pasch1 + pasch2 == 5)
        {
            super.setzeName("Full House");
        }
        
        //_pasch1Anzahl soll immer die groessere sein
        if(pasch1 > pasch2)
        {
            _pasch1Anzahl = pasch1;
            _pasch2Anzahl = pasch2; 
        }
        else
        {
            _pasch1Anzahl = pasch2;
            _pasch2Anzahl = pasch1;
        }

    }
    /**
     * Prüft ob das Feld mit dem aktuellen Würfelbecher gewertet werden könnte.
     */
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        //Initialisiere Prüfgrössen:
        int pasch1Auge   = 0;
        int pasch1Anzahl = 0;
        
        int pasch2Auge   = 0;
        int pasch2Anzahl = 0;
        
        //Gehe die Zahlen 1 bis 6 druch
        for(int i=1; i <= 6; i++)
        {
            if(becher.anzahl(i) >= 2)
            {
                if(pasch1Auge == 0)
                {
                    pasch1Auge   = i;
                    pasch1Anzahl = becher.anzahl(i);
                }
                else
                {
                    pasch2Auge   = i;
                    pasch2Anzahl = becher.anzahl(i);
                }
            }
        }
            
        //pasch1Anzahl 1 soll immer das groessere sein
        if(pasch1Anzahl < pasch2Anzahl)
        {
            int temp = pasch1Anzahl;
            pasch1Anzahl = pasch2Anzahl;
            pasch2Anzahl = temp;
        }
        
        //Pasch ist erfüllt
         if(_pasch1Anzahl <= pasch1Anzahl && _pasch2Anzahl <= pasch2Anzahl)
         {
             //Gebe Erlaubnis zum Eintragen falls noch nicht gesetzt:
             return !_gesetzt;
         }
         else
         {
             //Gebe keine Erlaubnis zum Eintragen:
             return false;
         } 
    }
    
    /**
     * Trägt die Punkte ein, falls erlaubt.
     */
    @Override public boolean trageEin(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            if(_pasch1Anzahl == 5 && _pasch2Anzahl == 0)
            {
                _punkte = 50;
            }
            else if(_pasch1Anzahl < 5 && _pasch2Anzahl == 0)
            {
                _punkte = becher.gibSumme();
            }
            else if(_pasch1Anzahl + _pasch2Anzahl == 5)
            {
                _punkte = 25;
            }
            _gesetzt = true;
            return true;
        }
        else
        {
            return false;
        }
    }
}
