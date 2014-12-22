package eintraege;

import wuerfel.wuerfelbecher;


public class eintAugenwerte extends eintrag
{
    int _zahl;
    
    public eintAugenwerte(Integer zahl)
    {
        super(zahl.toString() + "er");
        _zahl = zahl;
    }
    
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        return !_gesetzt && becher.augenzahl_vorhanden(_zahl);
    }
    
    @Override public boolean trageEin(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            _punkte = becher.gibSumme(_zahl);
            _gesetzt = true;
            return true;
        }
        else
        {
            return false;
        }
    }
    

}
