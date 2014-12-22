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
        int max = becher.maximum();
        
            for(int i=1; i < _laenge; i++)
            {
                if(!becher.augenzahl_vorhanden(min + i))//Nachfolger nicht vorhanden
                {
                    for(int j=1; j < _laenge; j++)
                    {
                        if(!becher.augenzahl_vorhanden(max - j))//Vorgaenger nicht vorhanden
                        {
                            return false;
                        }
                    }
                }
            }
            return !_gesetzt;
    }
    
    @Override public boolean trageEin(wuerfelbecher becher)
    {
        if(istGueltig(becher))
        {
            if(_laenge == 4)
            {
                _punkte = 30;
            }
            else if(_laenge == 5)
            {
                _punkte = 40;
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
