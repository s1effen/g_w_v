package eintraege;

import wuerfel.wuerfelbecher;

/**
 * Repräsentiert die oberen Felder (1 bis 6).
 */
public class eintAugenwerte extends eintrag
{
    //Das Feld das repräsentiert werden soll:
    int _zahl;
    
    /**
     * Ereugt eines der oberen Felder.
     * @param zahl legt das Feld fest
     */
    public eintAugenwerte(Integer zahl)
    {
        super(zahl.toString() + "er");
        _zahl = zahl;
    }
    
    /**
     * Prüft ob das Feld mit dem aktuellen Würfelbecher gewertet werden könnte.
     */
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        //Gebe Erlaubnis zum Eintragen falls noch nicht gesetzt und mindestens ein Würfel mit der Zahl vorhanden:
        return !_gesetzt && becher.augenzahl_vorhanden(_zahl);
    }
    
    /**
     * Gibt die Punkte zurück die in diesem Eintrag erreicht werden können
     */
    @Override public Integer gibPunkte(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            return becher.gibSumme(_zahl);
        }
        return 0;
    }
    
    /**
     * Trägt die Punkte ein, falls erlaubt.
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
