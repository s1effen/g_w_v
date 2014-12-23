package eintraege;

import wuerfel.wuerfelbecher;

/**
 * Repräsentiert das Feld "Kleine Strasse" und "Grosse Strasse"
 */
public class einStrasse extends eintrag
{
    protected int _laenge;
    
    /**
     * Erzeugt das Feld der Strasse.
     * @param laenge Länge der Strasse.
     */
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
    
    /**
     * Prüft ob das Feld mit dem aktuellen Würfelbecher gewertet werden könnte.
     */
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        int min = becher.minimum(); //Minimum des Bechers
        int max = becher.maximum(); //Maximum des Bechers
        
            //Sucht eine aufsteigende Folge der Länge der Strasse
            for(int i=1; i < _laenge; i++)
            {
                if(!becher.augenzahl_vorhanden(min + i))//Nachfolger nicht vorhanden
                {
                   //Sucht eine absteigende Folge der Länge der Strasse
                    for(int j=1; j < _laenge; j++)
                    {
                        if(!becher.augenzahl_vorhanden(max - j))//Vorgaenger nicht vorhanden
                        {
                            //Gebe keine Erlaubnis zum Eintragen
                            return false;
                        }
                    }
                }
            }
            //Gebe Erlaubnis zum Eintragen falls noch nicht gesetzt:
            return !_gesetzt;
    }
    
    /**
     * Trägt die Punkte ein, falls erlaubt.
     */
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
