package eintraege;

import java.util.ArrayList;

public class tabelle
{
    ArrayList <eintrag> _tabelle = new ArrayList<eintrag>();
    static int _tabBreite = 30;

    public tabelle()
    {
        _tabelle.add(new eintAugenwerte(1)); //1er
        _tabelle.add(new eintAugenwerte(2)); //2er
        _tabelle.add(new eintAugenwerte(3)); //3er
        _tabelle.add(new eintAugenwerte(4)); //4er
        _tabelle.add(new eintAugenwerte(5)); //5er
        _tabelle.add(new eintAugenwerte(6)); //6er
        //Bonus
        _tabelle.add(new einPasch(3)); //3er Pasch
        _tabelle.add(new einPasch(4)); //4er Pasch
        _tabelle.add(new einPasch(3,2)); //Full House
        _tabelle.add(new einStrasse(4)); //Kleine Strase
        _tabelle.add(new einStrasse(5)); //Grosse Strasse
        _tabelle.add(new einPasch(5)); //Kniffel
        _tabelle.add(new einChance()); //Chance
        //Gesamtpunkte

    }
    
    public int gibBonus()
    {
        int summe=0;
        for(int i=0; i < 6; i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        if(summe >= 62) return 35;
        return 0;
    }
    
    public int gesamtPunkte()
    {
        int summe=0;
        for(int i=0; i < _tabelle.size(); i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        return summe + gibBonus();
    }

    public void gibTabelle(String name)
    {

        System.out.println(line());
        System.out.println(zentriert(name.trim()));
        System.out.println(line());
        for(int i=0; i < _tabelle.size(); i++)
        {
           System.out.println(tabelle(_tabelle.get(i).gibName(), _tabelle.get(i).gibPunkte()));
           if(i == 5)
           {
               System.out.println(line());
               System.out.println(tabelle("Bonus", gibBonus()));
               System.out.println(line());
           }
           //Nach oberem Teil
        }
        System.out.println(line());
        System.out.println(tabelle("Gesamt", gesamtPunkte()));
        System.out.println(line()); 
    }
    

    private String zentriert(String s)
    {
        int lfill = (_tabBreite - s.length() -1)/2;
        int rfill = (_tabBreite)/2 -4;
        if(s.length() % 2 != 0)rfill--;
        return "|" + String.format("%1$" + lfill + "s", " ")+ s + String.format("%1$" + rfill + "s", " ") + "|";  
    }   

    private String line()
    {
        return "|----------------------------------------------------------------------------".substring(0, _tabBreite -1) + "|";
    }
    
    private String tabelle(String s, Integer i)
    {
        return "|" + String.format("%1$" + (_tabBreite/2 -1) + "s", s)+ "|" + String.format("%1$" + (_tabBreite/2 -2) + "s", i.toString()) + "|";  

     
    }
}
