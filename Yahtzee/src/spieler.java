
public class spieler {
	String _name;
	Integer[] _obererTeil = new Integer[6];
	int _bonus;
	
	int _3erPasch;
	int _4erPasch;
	int _fullHouse;
	int _klStrasse;
	int _grStrasse;
	int _yahtzee;
	int _chance;
	wuerfelbecher _becher = new wuerfelbecher();
	
	public spieler(String name)
	{
		_name = name;
	}
	
	public void feldObererTeil(int augenzahl)
	{
		_obererTeil[augenzahl -1] = _becher.anzahl(augenzahl) * augenzahl;
	}
	
	public int gesamtPunkte()
	{
		return 0;
	}
	
	
	
}
