import java.awt.List;
import java.util.ArrayList;


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
	 * Gibt nur 1er oder nur 2er usw. zurück
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
	
	public boolean kniffel()
	{
		int voriger = 0;
		for(int i=1;i < 5; i++)
		{
			if(_wuerfel.get(i)._augen != voriger)return false;
			voriger = _wuerfel.get(i)._augen;
		}
		return true;
	}
	
	private int minimum()
	{
		int min = 6;
		for(wuerfel w : _wuerfel)
		{
			if(w._augen < min)min = w._augen;
		}
		return min;
	}
	
	private int maximum()
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
	private boolean augenzahl_vorhanden(int augen)
	{
		for(wuerfel w : _wuerfel)
		{
			if(w._augen == augen)return true;
		}
		return false;
	}
	
	public boolean grStrasse()
	{
		return strasse(5);
	}
	
	public boolean klStrasse()
	{
		return strasse(4);
	}
	
	private boolean strasse(int laenge)
	{
		int min = minimum();
		if(min <= 3) //Es kann eine Strasse geben...
		{
			
			for(int i=1; i < laenge; i++)
			{
				if(!augenzahl_vorhanden(min + i)) return false;//Nachfolger nicht vorhanden
			}
			return true;
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
	
	boolean dreierPasch()
	{
		for(int i=1;i<6;i++)
		{
			if(anzahl(i) == 3)return true;
		}
		return false;
	}
	
	boolean viererPasch()
	{
		for(int i=1;i<6;i++)
		{
			if(anzahl(i) == 4)return true;
		}
		return false;
	}
	public boolean fullHouse()
	{
		int dreier=0;
		if(dreierPasch()) //Mindestens ein Dreierpasch
		{
			for(int i=1;i<6;i++) //3er ermitteln
			{
				if(anzahl(i)==3)dreier=i;
			}
			//Prüfen ob es noch einen Zweier != Dreier gibt:
			for(int i=1;i<6;i++) //noch 2er dabei?
			{
				if(anzahl(i)==2 && i!=dreier)return true;
			}
		}
		return false;
	}
}
