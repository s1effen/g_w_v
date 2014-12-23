package wuerfel;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Ein Würfelbecher
 *
 */
public class wuerfelbecher {

    //Alle Würfeld des Bechers als Liste:
	ArrayList<wuerfel> _wuerfel = new ArrayList<wuerfel>();
	//Würfe des Bechers
	protected int _wuerfe = 0;
	
	/**
	 * Erzeugt einen neuen Würfelbecher mit Fünf Würfeln.
	 */
	public wuerfelbecher()
	{
	    _wuerfe = 0;
		for(int i=0; i< 5; i++)
		{
			 _wuerfel.add(new wuerfel());
		}
	}
	
	/**
	 * Erzeugt einen Würfelbecher mit exakt diesen Würfeln.
	 * Nicht zum Schummeln benutzen.
	 * Notwendig für Testklassen.
	 * @param wuerfel Würfel die in den Becher sollen.
	 */
    public wuerfelbecher(int[] wuerfel)
    {
        for(int i=0; i< 5; i++)
        {
            _wuerfel.add(new wuerfel(wuerfel[i]));
        }
    }
	
    /**
     * Wirft alle Würfel neu
     */
    public void wirfAlle()
    {
        wirf(true,true,true,true,true);
    }
    
	/**
	 * Wirft bestimmte Würfel neu
	 * @param w1 Würfel1 neu werfen
	 * @param w2 Würfel2 neu werfen
	 * @param w3 Würfel3 neu werfen
	 * @param w4 Würfel4 neu werfen
	 * @param w5 Würfel5 neu werfen
	 */
	public void wirf(boolean w1, boolean w2, boolean w3, boolean w4, boolean w5)
	{
		if(_wuerfe > 3)
		{
			//Man darf nur 3 mal werfen.
		}
		else
		{
		    _wuerfe++;
			if(w1)_wuerfel.get(0).wirf();
			if(w2)_wuerfel.get(1).wirf();
			if(w3)_wuerfel.get(2).wirf();
			if(w4)_wuerfel.get(3).wirf();
			if(w5)_wuerfel.get(4).wirf();
		}
	}
	
	/**
	 * Gibt die Anzahl der bisherigen Würfe zurück.
	 * @return Anzahl Würfe.
	 */
	public int anzahlWuerfe()
	{
	    return _wuerfe;
	}
	
	/**
	 * Gibt den Inhalt des Bechers als String zurück.
	 * @return Inhalt als String.
	 */
	public String gibInhalt()
	{  
	    String ausgabe ="";
	    for(int i=0; i < 5; i++)
	    {
	        ausgabe = ausgabe +  "Würfel " + (i+1) + ": " + _wuerfel.get(i)._augen + "\n";
	    }
	    return ausgabe;
	}
	
	/**
	 * Gibt die Summe aller Wuerfel zurueck
	 * @return Summe als int
	 */
	public int gibSumme()
	{
		int summe=0;
		for(wuerfel w : _wuerfel)
		{
			summe += w._augen;
		}
		return summe;
	}
	
	/**
	 * Gibt nur 1er oder nur 2er usw. zurueck
	 * @param augen Welche Würfel sollen gezählt werden
	 * @return Summe als int
	 */
	public int gibSumme(int augen)
	{
		int summe=0;
		for(wuerfel w : _wuerfel)
		{
			if(w._augen == augen)summe += w._augen;
		}
		return summe;
	}
	
	/**
	 * Gibt die kleinste Augenzahl zurück.
	 * @return Augenzahl als int
	 */
	public int minimum()
	{
		int min = 6;
		for(wuerfel w : _wuerfel)
		{
			if(w._augen < min)min = w._augen;
		}
		return min;
	}
	
	 /**
     * Gibt die größte Augenzahl zurück.
     * @return Augenzahl als int
     */
	public int maximum()
	{
		int max = 1;
		for(wuerfel w : _wuerfel)
		{
			if(w._augen > max)max = w._augen;
		}
		return max;
	}
	
	/**
	 * Prüft ob ein Würfel mit der angegebenen Augenzahl vorhanden ist
	 * @param augen Augenzahl des Würfels
	 * @return boolean ob Würfel im Becher
	 */
	public boolean augenzahl_vorhanden(int augen)
	{
		for(wuerfel w : _wuerfel)
		{
			if(w._augen == augen)return true;
		}
		return false;
	}
	
    /**
     * Prüft wie oft ein Würfel mit der angegebenen Augenzahl vorhanden ist
     * @param augen Augenzahl des Würfels
     * @return Anzahl der Würfel im Becher mit der Augenzahl als int
     */
	public int anzahl(int augen)
	{
		int count=0;
		if(_wuerfel.contains(new wuerfel(augen)))
		{
			for(wuerfel w : _wuerfel)
			{
				if(w._augen == augen)count++;
			}
		}
		return count;
	}
}
