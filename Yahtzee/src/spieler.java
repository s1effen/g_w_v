import wuerfel.wuerfelbecher;
import eintraege.tabelle;

public class spieler {
	String _name;
	int _gesamtpunkte=0;
	wuerfelbecher _becher = new wuerfelbecher();
	tabelle _tabelle = new tabelle();
	
	public spieler(String name)
	{
		_name = name;
	}
	
	public void gibTabelle()
	{
	    _tabelle.gibTabelle(_name);
	}
	
	public String wirf()
	{
	    _becher.wirfAlle();
	    return _becher.gibInhalt();
	}
	
	   public String wirfBestimmte(boolean[] w)
	    {
	        _becher.wirf(w[0],w[1],w[2],w[3],w[4]);
	        return _becher.gibInhalt();
	    }
	
}
