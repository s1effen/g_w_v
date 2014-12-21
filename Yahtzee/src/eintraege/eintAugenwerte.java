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
        return true;

    }
    

}
