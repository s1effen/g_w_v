import java.util.HashMap;
import java.util.Map;


public class spieler {
	String _name;
	int _gesamtpunkte=0;
	HashMap <String, Integer> _tabelle = new HashMap<String, Integer>();
	wuerfelbecher _becher = new wuerfelbecher();
	int _tabBreite = 40;
	
	public spieler(String name)
	{
		_name = name;
		_tabelle.put("1er", 0);
		_tabelle.put("2er", 0);
		_tabelle.put("3er", 0);
		_tabelle.put("4er", 0);
		_tabelle.put("5er", 0);
		_tabelle.put("6er", 0);
		_tabelle.put("Bonus", 0);
		_tabelle.put("Dreierpasch", 0);
		_tabelle.put("Viererpasch", 0);
		_tabelle.put("Full-House", 0);
		_tabelle.put("Kleine Strasse", 0);
		_tabelle.put("Grosse Strasse", 0);
	    _tabelle.put("Grosse Strasse", 0);
	    _tabelle.put("Kniffel", 0);
        _tabelle.put("Chance", 0);
	}
	
	public void gibTabelle()
	{
	    String s;
	    
	    System.out.println("-------------------------------");
		System.out.println("------------------------");
		for(Map.Entry<String, Integer> zeile: _tabelle.entrySet())
		{
		   System.out.println("|" + zeile.getKey() +  "|"  + zeile.getValue() + "|");
		   if(zeile.getKey().equals("6er") || zeile.getKey().equals("Bonus") || zeile.getKey().equals("Chance") )System.out.println("------------------------");
		}
		System.out.println("|Gesamt|" + gesamtPunkte() + "|");
	    System.out.println("------------------------");
	}
	
	public int gesamtPunkte()
	{
		return 0;
	}
	
    private static String zentriert(String s)
    {
        int fill = ((40 - s.length()) )/2;
        if(fill  + fill + s.length() == 39)
        {
            return "|" + String.format("%1$" + fill + "s", " ")+ s + String.format("%1$" + fill + "s", "|");  
  
        }
        else
        {
            return "|" + String.format("%1$" + (fill-1) + "s", " ")+ s + String.format("%1$" + fill + "s", "|");  
        }
    }   
	/*
	private String links(String s)
	{
	    
	}

	private String tabelle(String s, String s)
    {
	        
    }
	
    private String tabelle(String s, int i)
    {
            
    }
	*/
}
