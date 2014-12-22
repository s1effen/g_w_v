package eintraege;
import java.util.ArrayList;

import wuerfel.wuerfelbecher;

/**
 * Spiegelt den Zettel im Schreibblock im Kniffel wieder.
 *
 */
public class tabelle
{
    //Alle Einträge
    ArrayList <eintrag> _tabelle = new ArrayList<eintrag>();
    //Breite der Tabelle:
    static int _tabBreite = 30;

    /**
     * Erzeugt einen neuen leeren Zettel.
     */
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
    
    /**
     * Gibt den aktuellen Bonus des oberen Feldes zurück.
     * @return Bonus als int.
     */
    public Integer gibBonus()
    {
        Integer summe=0;
        
        //Geht obere Felder druch
        for(int i=0; i < 6; i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        if(summe >= 62) return 35;
        return 0;
    }
    
    /**
     * Gibt die aktuellen Gesamtpunkte zurück
     * @return Gesamtpunkte als int.
     */
    public Integer gesamtPunkte()
    {
        Integer summe=0;
        
        //Geht die gesamte Tabelle druch
        for(int i=0; i < _tabelle.size(); i++)
        {
            summe = summe + _tabelle.get(i)._punkte;
        }
        return summe + gibBonus();
    }
    
    /**
     * Wertet einen Eintrag.
     * @param eintrag Position des Eintrages im Zettel
     * @param becher Würfelbecher der zum Werten genutzt werden soll
     * @return Gibt zurück ob das Eintragen geklappt hat (erlaubt war).
     */
    public boolean werte(int eintrag, wuerfelbecher becher)
    {
        return  _tabelle.get(eintrag -1).trageEin(becher);
    }

    /**
     * Streicht einen Eintrag.
     * @param eintrag Position des Eintrages im Zettel
     * @return Gibt zurück ob das Streichen geklappt hat.
     */
    public boolean streiche(int eintrag)
    {
        return  _tabelle.get(eintrag -1).streiche();
    }
    
    /**
     * Gibt Auskunft ob der Zettel Voll ist.
     * @return true = Zettel ist voll. false = Noch Einträge offen.
     */
    public boolean fertig()
    {
        for(eintrag e : _tabelle)
        {
            if(!e.gesetzt() && !e._gestrichen) return false;
        }
        return true;
    }
    
    /**
     * Gibt die Tabelle aus mit den aktuellen Werten aus. 
     * Nicht gesetzte Einträge haben ein "-",
     * gestrichene ein "X".
     * @param name wird in der Tabelle oben angezeigt.
     */
    public void gibTabelle(String name)
    {

        System.out.println(line());
        System.out.println(zentriert(name.trim()));
        System.out.println(line());
        
        //Geht die gesamte Tabelle durch
        for(int i=0; i < _tabelle.size(); i++)
        {
           if(_tabelle.get(i)._gestrichen) //gestrichene Felder
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "X"));
           }
           else if(!_tabelle.get(i)._gesetzt) //nicht gesetzte Felder
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "-"));
           }
           else //gesetzte Felder
           {
               System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), _tabelle.get(i).gibPunkte().toString()));

           }
           
           //Gib Bonus aus:
           if(i == 5)
           {
               System.out.println(line());
               System.out.println(tabelle("Bonus", gibBonus().toString()));
               System.out.println(line());
           }
        }
        
        //Gib Gesamtpunkte aus:
        System.out.println(line());
        System.out.println(tabelle("Gesamt", gesamtPunkte().toString()));
        System.out.println(line()); 
    }
    
    /**
     * Gibt die Tabelle aus mit den aktuellen Werten aus. Markiert hierbei aber mit einem *, welche
     * Einträge man mit dem gegebenen Würfelbecher alles eintragen könnte. 
     * @param name wird in der Tabelle oben angezeigt.
     * @param becher becher mit dem geprüft werden soll.
     */
    public void gibTabelle(String name, wuerfelbecher becher)
    {

        System.out.println(line());
        System.out.println(zentriert(name.trim()));
        System.out.println(line());
        
        //Geht die gesamte Tabelle durch
        for(int i=0; i < _tabelle.size(); i++)
        {
            
            if(_tabelle.get(i)._gestrichen) //gestrichene Felder
            {
                System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "X"));
            }
            else if(_tabelle.get(i)._gesetzt) //gesetzte Felder
            {
                System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), _tabelle.get(i).gibPunkte().toString()));
            }
            else //nicht gesetzte Felder
            {
                //Feld ist mit aktuellem Becher gueltig: markieren
                if(_tabelle.get(i).istGueltig(becher))
                {
                    System.out.println(tabelle((i+1), _tabelle.get(i).gibName() + "*", "-"));
                }
                else //Feld ist mit aktuellem Becher ungueltig: nicht markieren
                {
                    System.out.println(tabelle((i+1), _tabelle.get(i).gibName(), "-"));

                }
            }

            //Gib Bonus aus:
           if(i == 5)
           {
               System.out.println(line());
               System.out.println(tabelle("Bonus", gibBonus().toString()));
               System.out.println(line());
           }
        }
        
        //Gib Gesmamtpunkte aus:
        System.out.println(line());
        System.out.println(tabelle("Gesamt", gesamtPunkte().toString()));
        System.out.println(line()); 
    }
    
    /**
     * Gibt eine Tabellenzeile mit einem Zentrierten String zurück
     * @param s String der Zentriert werden soll
     * @return Tabellenzeile
     */
    private String zentriert(String s)
    {
        int lfill = ((_tabBreite - s.length())/2 -1);
        int rfill = _tabBreite - (lfill + s.length()) + s.length()%2 -2;
        if(s.length() % 2 != 0)rfill--;
        return "|" + String.format("%1$" + lfill + "s", " ")+ s + String.format("%1$" + rfill + "s", " ") + "|";  
    }   

    /**
     * Gibt eine Zwischenlinie zurück
     * @return Tabellenzeile
     */
    private String line()
    {
        return "|----------------------------------------------------------------------------".substring(0, _tabBreite -1) + "|";
    }
    
    /**
     * Gibt eine Tabellenzeile mit einem Tabellenartigen String zurück.
     * @param a linke Zelle
     * @param b rechte Zelle
     * @return Tabellenzeile
     */
    private String tabelle(String a, String b)
    {
        return "|" + String.format("%1$" + (_tabBreite -5 -1) + "s", a)+ "|" + String.format("%1$" + (5 -2) + "s", b) + "|";       
    }
    
    /**
     * Gibt eine Tabellenzeile mit einem Tabellenartigen String zurück.
     * @param n index für linke Zelle
     * @param a linke Zelle
     * @param b rechte Zelle
     * @return
     */
    private String tabelle(Integer n, String a, String b)
    {
        return "|" + n.toString() + String.format("%1$" + (_tabBreite -5 -1 -n.toString().length()) + "s", a)+ "|" + String.format("%1$" + (5 -2) + "s", b) + "|";       
    }
}
