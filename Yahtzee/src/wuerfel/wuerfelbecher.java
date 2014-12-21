package wuerfel;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;


public class wuerfelbecher {


	ArrayList<wuerfel> _wuerfel = new ArrayList<wuerfel>();
	int _wuerfe = 0;
	public wuerfelbecher()
	{
		for(int i=0; i< 5; i++)
		{
			 _wuerfel.add(new wuerfel());
		}
	}
	
	   public void wirfAlle()
	    {
	       wirf(true,true,true,true,true);
	    }
	   
	public void wirf(boolean w1, boolean w2, boolean w3, boolean w4, boolean w5)
	{
		_wuerfe++;
		if(_wuerfe == 3)
		{
			//Man darf nur 3 mal werfen.
			
		}
		else
		{
			if(w1)_wuerfel.get(0).wirf();
			if(w2)_wuerfel.get(1).wirf();
			if(w3)_wuerfel.get(2).wirf();
			if(w4)_wuerfel.get(3).wirf();
			if(w5)_wuerfel.get(4).wirf();
		}
	}
	
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
	 * @return
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
	 * @param augen
	 * @return
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
	
	public int minimum()
	{
		int min = 6;
		for(wuerfel w : _wuerfel)
		{
			if(w._augen < min)min = w._augen;
		}
		return min;
	}
	
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
	 * @param augen
	 * @return
	 */
	public boolean augenzahl_vorhanden(int augen)
	{
		for(wuerfel w : _wuerfel)
		{
			if(w._augen == augen)return true;
		}
		return false;
	}
	
	public int anzahl(int augen)
	{
		int count=0;
		if(_wuerfel.contains(augen))
		{
			for(wuerfel w : _wuerfel)
			{
				if(w._augen == augen)count++;
			}
		}
		return count;
		
	}
	

}
