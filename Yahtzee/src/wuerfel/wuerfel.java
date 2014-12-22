package wuerfel;
import java.util.Random;

/**
 * Ein Würfel
 *
 */
public class wuerfel {
    
    int _augen;
    Random _rand = new Random(); //Randomisierer
    
    /**
     * Erzeugt einen noch nicht geworfenen Würfel.
     */
	public wuerfel()
	{
		
	}
	
	/**
	 * Erzeugt einen würfel mit einem bestimmten Ergebnis.
	 * Nicht zum Schummeln gedacht sondern für die Testklassennotwendig.
	 * @param augen Augenzahl des Würfels
	 */
    public wuerfel(int augen)
    {
        _augen = augen;
    }
	   
    /**
     * Wirft den Würfel
     * @return Augenzahl
     */
	public int wirf()
	{
		_augen = _rand.nextInt((5) + 1) + 1;
		return _augen;
	}
	
    //@Override
    public boolean equals(Object obj) 
    {
        if (!(obj instanceof wuerfel))
            return false;
        if (obj == this)
            return true;
        wuerfel vergleich = (wuerfel) obj;
        return equals(vergleich);
        
    }
    
    public boolean equals(wuerfel vergleich)
    {
      if(_augen == vergleich._augen)
      {
          return true;
      }
      else
      {
          return false;
      }
    }
    
    @Override
    public int hashCode()
    {
        return _augen;
    }
}
