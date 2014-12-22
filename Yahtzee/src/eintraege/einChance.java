package eintraege;
import wuerfel.wuerfelbecher;


public class einChance extends eintrag
{

    public einChance()
    {
        super("Chance");
    }
    
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        
        return !_gesetzt;
    }
    
    @Override public boolean trageEin(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            _punkte = becher.gibSumme();
            _gesetzt = true;
            return true;
        }
        else
        {
            return false;
        }
    }
    

    
}


