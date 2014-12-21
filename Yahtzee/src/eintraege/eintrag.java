package eintraege;
import wuerfel.*;
abstract public class eintrag
{
    String _name;
    int _punkte;
    
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
    
    public int gibPunkte()
    {
        return _punkte;
    }
    
    public boolean istGueltig(wuerfelbecher becher)
    {
        return false;
    }
}
