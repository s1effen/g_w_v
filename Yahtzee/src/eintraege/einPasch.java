package eintraege;

import wuerfel.wuerfelbecher;


public class einPasch extends eintrag
{
    int _pasch1Anzahl;
    int _pasch2Anzahl;
    public einPasch(Integer pasch1)
    {
        super();
        setValues(pasch1, Integer.valueOf(0));
       
    }
    public einPasch(Integer pasch1, Integer pasch2)
    {
        super();
        setValues(pasch1, pasch2);
    }

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
    
    @Override public boolean istGueltig(wuerfelbecher becher)
    {
        int pasch1Auge   = 0;
        int pasch1Anzahl = 0;
        
        int pasch2Auge   = 0;
        int pasch2Anzahl = 0;
        
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
        
         if(_pasch1Anzahl <= pasch1Anzahl && _pasch2Anzahl <= pasch2Anzahl)
         {
             return !_gesetzt;
         }
         else
         {
             return false;
         } 
    }
    
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
