package spieler;

import java.util.Random;

import wuerfel.wuerfelbecher;
import eintraege.tabelle;

public class botClass
{
    String _name = "";
    int _gesamtpunkte=0;
    wuerfelbecher _becher = new wuerfelbecher();
    tabelle _tabelle = new tabelle();

    public botClass()
    {
       _name = getName();
    }

    public int gibPunkte()
    {
        return _gesamtpunkte;
    }
    
    public String gibName()
    {
        return _name;
    }
    
    public void gibTabelle()
    {
        _tabelle.gibTabelle("Bot " +_name);
    }

    public boolean fertig()
    {
       return _tabelle.fertig(); 
    }
    
    private String getName()
    {
        Random rand = new Random();
        String[] namen = {"Angelika","Monika","Sabine","Petra","Karin","Gabriele","Birgit","Brigitte","Renate","Susanne","Barbara","Ursula","Ute","Jutta","Marion","Ingrid","Michael","Peter","Hans","Klaus","Wolfgang","Thomas","Jürgen","Andreas","Bernd","Uwe","Manfred","Rainer","Dieter","Joachim","Holger","Karl"};        
        return namen[rand.nextInt((namen.length) + 1) + 1];

    }
}
