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
        return true;

    }
    
}


