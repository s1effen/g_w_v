package eintraege;

import wuerfel.wuerfelbecher;


public class einStrasse extends eintrag
{
    int _laenge;
    public einStrasse(Integer laenge)
    {
        super();
        if(laenge == 4)
        {
            super.setzeName("Kleine Strasse");
        }
        else if(laenge == 5)
        {
            super.setzeName("Grosse Strasse"); 
        }
        _laenge = laenge;
    }
    
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        int min = becher.minimum();
        if(min <= 3) //Es kann eine Strasse geben...
        {
            
            for(int i=1; i < _laenge; i++)
            {
                if(!becher.augenzahl_vorhanden(min + i)) return false;//Nachfolger nicht vorhanden
            }
            return true;
        }
        return false;

    }

}
