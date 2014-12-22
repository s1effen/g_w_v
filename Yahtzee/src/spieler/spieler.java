package spieler;
import wuerfel.wuerfelbecher;
import eintraege.tabelle;

/**
 * Menschlicher Spieler
 *
 */
public class spieler {
    
	String _name;
	int _gesamtpunkte=0;
	wuerfelbecher _becher = new wuerfelbecher();
	tabelle _tabelle = new tabelle();
	
	/**
	 * Erzeugt einen neuen Spieler
	 * @param name Name des Spielers
	 */
	public spieler(String name)
	{
		_name = name;
	}
	
	/**
	 * Gibt die aktuellen Gesamtpunkte zurück
	 * @return Gesamtpunkte
	 */
	public int gibPunkte()
	{
	    return _tabelle.gesamtPunkte();
	}
	
	/**
	 * Gibt die aktuelle Tabelle aus
	 */
	public void gibTabelle()
	{
	    _tabelle.gibTabelle(_name);
	}
	
	/**
	 * Gibt den Namen zurück
	 * @return Name
	 */
	public String gibName()
	{
	    return _name;
	}
	
	/**
	 * Gibt die aktuelle Tabelle mit Markierung der Einträge die man Werten kann
	 */
    public void gibTabelleWertung()
    {
        _tabelle.gibTabelle(_name, _becher);
    }
   
    /**
     * Gibt die aktuelle Anzahl der Würfe in dieser Runde zurück
     * @return Anzahl Würfe
     */
    public int anzahlWuerfe()
    {
        return _becher.anzahlWuerfe();
    }
    
    /**
     * Gibt Auskunft ob der Zettel Voll ist.
     * @return true = Zettel ist voll. false = Noch Einträge offen.
     */
    public boolean fertig()
    {
       return _tabelle.fertig(); 
    }
    
    /**
     * Wirft alle Würfel
     * @return String mit Ergebnis
     */
	public String wirf()
	{
	    _becher.wirfAlle();
	    return "Wurf " + _becher.anzahlWuerfe() + ":\n-----------\n" + _becher.gibInhalt();
	}
	
	/**
	 * Wirft bestimmte Würfel
	 * @param w Array mit boolean Werten
	 * @return String mit Ergebnis
	 */
   public String wirfBestimmte(boolean[] w)
    {
        _becher.wirf(w[0],w[1],w[2],w[3],w[4]);
        return "Wurf " + _becher.anzahlWuerfe() + ":\n-----------\n" + _becher.gibInhalt();
    }
   
   /**
    * Wertet einen Eintrag
    * @param eintrag Eintrag der zu werten ist
    * @return boolean ob Werten geklappt hat
    */
   public boolean werte(int eintrag)
   {
       return _tabelle.werte(eintrag, _becher);
   }
	
   /**
    * Streicht einen Eintrag
    * @param eintrag Eintrag der zu werten ist
    * @return boolean ob Streichen geklappt hat
    */
   public boolean streiche(int eintrag)
   {
       return _tabelle.streiche(eintrag);
   }
}
