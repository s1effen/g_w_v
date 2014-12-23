package eintraege;
import wuerfel.*;
abstract public class eintrag
{
    protected String _name;
    protected int _punkte;
    protected boolean _gestrichen = false;
    protected boolean _gesetzt    = false;
    
    public eintrag()
    {

    }
    
    public eintrag(String name)
    {
        _name = name;
    }
    
    public String gibName()
    {
        return _name;
    }
    
    public void setzeName(String name)
    {
        _name = name;
    }
    
    public Integer gibPunkte()
    {
        return _punkte;
    }
    
    public boolean trageEin(wuerfelbecher becher)
    {
        return false;
    }
    
    public boolean istGueltig(wuerfelbecher becher)
    {
        return false;
    }
    
    public boolean gestrichen()
    {
        return _gestrichen;
    }
    
    public boolean gesetzt()
    {
        return _gesetzt;
    }
    
    public Integer gibPunkte(wuerfelbecher becher)
    {
        return 0;
    }
    
    public boolean streiche()
    {
        if(!gestrichen())
        {
            _punkte     = 0;
            _gestrichen = true;
            return true;
        }
        else
        {
            return false;
        }
    }
}
