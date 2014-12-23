package eintraege;
import wuerfel.wuerfelbecher;

/**
 * Repräsentiert das Feld "Chance"
 */
public class einChance extends eintrag
{
    /**
     * Erzeugt das Feld
     */
    public einChance()
    {
        super("Chance");
    }
    
    /**
     * Eine Chance kann immer gesetzt werden, egal was man gewürfelt hat.
     */
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        //Gebe Erlaubnis zum Eintragen falls noch nicht gesetzt:
        return !_gesetzt;
    }
    
    /**
     * Gibt die Punkte zurück die in diesem Eintrag erreicht werden können
     */
    @Override public Integer gibPunkte(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            return becher.gibSumme();
         }

        return 0;
    }

    
    /**
     * Bei der Chance wird immer die Summe des Bechers eingetragen.
     */
    @Override public boolean trageEin(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            _punkte = gibPunkte(becher);
            _gesetzt = true;
            return true;
        }
        else
        {
            return false;
        }
    }
    

    
}


