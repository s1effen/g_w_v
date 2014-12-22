package eintraege;

import java.util.ArrayList;

import wuerfel.wuerfelbecher;

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
    
    public Integer gibBonus()
    {
        Integer summe=0;
        for(int i=0; i < 6; i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        if(summe >= 62) return 35;
        return 0;
    }
    
    public Integer gesamtPunkte()
    {
        Integer summe=0;
        for(int i=0; i < _tabelle.size(); i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        return summe + gibBonus();
    }
    
    public boolean werte(int eintrag, wuerfelbecher becher)
    {
        return  _tabelle.get(eintrag -1).trageEin(becher);
    }

    public boolean streiche(int eintrag)
    {
        return  _tabelle.get(eintrag -1).streiche();
    }
    
    public boolean fertig()
    {
        for(eintrag e : _tabelle)
        {
            if(!e.gesetzt()) return false;
        }
        return true;
    }
    
    public void gibTabelle(String name)
    {

        System.out.println(line());
        System.out.println(zentriert(name.trim()));
        System.out.println(line());
        for(int i=0; i < _tabelle.size(); i++)
        {
           if(_tabelle.get(i)._gestrichen)
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "X"));
           }
           else if(!_tabelle.get(i)._gesetzt)
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "-"));
           }
           else
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), _tabelle.get(i).gibPunkte().toString()));

           }
           if(i == 5)
           {
               System.out.println(line());
               System.out.println(tabelle("Bonus", gibBonus().toString()));
               System.out.println(line());
           }
           //Nach oberem Teil
        }
        System.out.println(line());
        System.out.println(tabelle("Gesamt", gesamtPunkte().toString()));
        System.out.println(line()); 
    }
    
    public void gibTabelle(String name, wuerfelbecher becher)
    {

        System.out.println(line());
        System.out.println(zentriert(name.trim()));
        System.out.println(line());
        for(int i=0; i < _tabelle.size(); i++)
        {
            
            if(_tabelle.get(i)._gestrichen)
            {
                System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "X"));
            }
            else if(_tabelle.get(i)._gesetzt)
            {
                System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), _tabelle.get(i).gibPunkte().toString()));
            }
            else
            {
                //Feld ist mit aktuellem Becher gueltig
                if(_tabelle.get(i).istGueltig(becher))
                {
                    System.out.println(tabelle((i+1), _tabelle.get(i).gibName() + "*", "-"));
                }
                else //Feld ist mit aktuellem Becher ungueltig
                {
                    System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "-"));

                }
            }

           if(i == 5)
           {
               System.out.println(line());
               System.out.println(tabelle("Bonus", gibBonus().toString()));
               System.out.println(line());
           }
           //Nach oberem Teil
        }
        System.out.println(line());
        System.out.println(tabelle("Gesamt", gesamtPunkte().toString()));
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
    
    private String tabelle(String a, String b)
    {
        return "|" + String.format("%1$" + (_tabBreite -5 -1) + "s", a)+ "|" + String.format("%1$" + (5 -2) + "s", b) + "|";       
    }
    
    private String tabelle(Integer n, String a, String b)
    {
        return "|" + n.toString() + String.format("%1$" + (_tabBreite -5 -1 -n.toString().length()) + "s", a)+ "|" + String.format("%1$" + (5 -2) + "s", b) + "|";       
    }
}
