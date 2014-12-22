import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import spieler.bot;
import spieler.botHard;
import spieler.botMedium;
import spieler.botSimple;
import spieler.spieler;


/**
 * Zentrale Exekutive für ein Yahtzee Spiel.
 * @author Franziska Eger, Frauke Heinecke, Steffen Haesler
 *
 */
public class yahtzee
{
    static spieler[] _spieler;
    static int aktuellerSpieler;
    static bot[] _bots;
    
    public static void main(String[] args) throws IOException
    {
        header();
        System.out.println("1 - Simulation mit Bots");
        System.out.println("2 - Interaktives Spiel");
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1:
            initBots();
            initBotGames();
            break;
        case 2:
            initSpieler();
            initBots();
            aktuellerSpieler = 0;
            System.out.println("-----------------------------\n" + _spieler[aktuellerSpieler].gibName() + " ist dran.\n-----------------------------");
            menu(_spieler[aktuellerSpieler]); 
            break;
        default:
            System.out.println("Bitte richtige Option auswählen.");
        }
    }
    
    /**
     * Legt fest wie viele Spiele die Bots im Simulationsmodus spielen sollen
     * @throws IOException
     */
    private static void initBotGames() throws IOException
    {
        int spiele = eingabeNum("Wie viele Spiele simulieren?.");
        for(bot b : _bots)
        {
            b.spieleSpiele(spiele);
        }
    }

    /**
     * Zentrales Spielermenü
     * @param aktuellerSpieler Aktueller Spieler
     * @throws IOException
     */
    private static void menu(spieler aktuellerSpieler) throws IOException
    {
        System.out.println("1 - Punktezettel ansehen");
        System.out.println("2 - Würfeln");
        
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1:
            aktuellerSpieler.gibTabelle();
            menu(aktuellerSpieler);
            break;
        case 2:
            wirf(aktuellerSpieler);
            break;
        default:
            System.out.println("Bitte richtige Option auswählen.");
        }

    }
    
    /**
     * Es wird der erste Wurf einer Runde geworfen und danach das Würfelmenü aufgerufen.
     * @param aktuellerSpieler Aktueller Spieler
     * @throws IOException
     */
    private static void wirf(spieler aktuellerSpieler) throws IOException
    {
        System.out.println(aktuellerSpieler.wirf());
        wuerfelMenu(aktuellerSpieler);
    }
    
    /**
     * Das Würfelmenü. Sind noch Würfe über kann man erneut werfen.
     * Dazu kann man sich die Punktetabelle ansehen oder Felder Streichen sowie mit
     * den aktuellen Würfeln werten.
     * @param aktuellerSpieler Aktueller Spieler
     * @throws IOException
     */
    private static void wuerfelMenu(spieler aktuellerSpieler) throws IOException
    {
        System.out.println("-- Bitte Option wählen:");
        System.out.println("1 - Punktezettel ansehen");
        System.out.println("2 - Werten");
        System.out.println("3 - Streichen");
        
        if(aktuellerSpieler.anzahlWuerfe() < 3)
        {
            System.out.println("4 - Bestimmte Wuerfel in den Becher");
            System.out.println("5 - Alle Wuefel in den Becher");
        }
        
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1: //Zettel zeigen
            aktuellerSpieler.gibTabelle();
            wuerfelMenu(aktuellerSpieler);
            break;
        case 2: // Werten
            aktuellerSpieler.gibTabelleWertung();
            wertungMenu(aktuellerSpieler);
            aktuellerSpieler.gibTabelle();
            neueRunde();
            break;
        case 3: //Streichen
            aktuellerSpieler.gibTabelle();
            streichMenu(aktuellerSpieler);
            aktuellerSpieler.gibTabelle();
            neueRunde();
            break;
        case 4:
            if(aktuellerSpieler.anzahlWuerfe() < 3)
            {
                System.out.println(aktuellerSpieler.wirfBestimmte(wuerfelInBecher(wuerfelInBecherAuswaehlen())));
            }
            else
            {
                System.out.println("Bitte richtige Option auswählen.");
            }
            wuerfelMenu(aktuellerSpieler);
            break;
        case 5:
            if(aktuellerSpieler.anzahlWuerfe() < 3)
            {
                System.out.println(aktuellerSpieler.wirf());
            }
            else
            {
                System.out.println("Bitte richtige Option auswählen.");
            }
            wuerfelMenu(aktuellerSpieler);
            break;
    
        default:
            System.out.println("Bitte richtige Option auswählen.");
            wuerfelMenu(aktuellerSpieler);
        }
    }
    
    /**
     * Menü um die Wertung einzugeben.
     * @param aktuellerSpieler Aktueller Spieler
     * @throws IOException
     */
    private static void wertungMenu(spieler aktuellerSpieler) throws IOException
    {
        aktuellerSpieler.werte(eingabeNum("-- Bitte Eintrag zum Werten wählen: (Zu wertende sind mit * markiert)"));
    }
    
    /**
     * Menü um einen Eintrag zu Streichen
     * @param aktuellerSpieler Aktueller Spieler
     * @throws IOException
     */
    private static void streichMenu(spieler aktuellerSpieler) throws IOException
    {
        aktuellerSpieler.streiche(eingabeNum("-- Bitte Eintrag zum Streichen wählen: (Zu streichende haben noch keine Punkte)"));
    }
    
    /**
     * Wirft die angegebenen Würfel aus dem Array wieder in den Becher und würfelt.
     * @param welcheS Array mit Würfeln als String
     * @return
     */
    private static boolean[] wuerfelInBecher(String[] welcheS)
    {
        boolean[] welcheB = {false, false, false, false, false};
        for(String s : welcheS)
        {
            welcheB[Integer.parseInt(s)-1] = true;
        }
        return welcheB;
        
    }
    
    /**
     * Erzeugt eine neue Runde und legt dadurch den nächsten Spieler fest.
     * Wenn alle Spieler ihre Blöcke voll haben, endet das Spiel.
     * Wird inklusie Bots gespielt werden hier auch deren Runden ausgeführt.
     * @throws IOException
     */
    private static void neueRunde() throws IOException
    {
        if(_spieler.length == aktuellerSpieler -1) //Alle Menschen haben gespielt. Jetzt sind die Bots dran.
        {
            for(bot b : _bots)
            {
                System.out.println("-----------------------------\nBot " + b.gibName() + " ist dran.\n-----------------------------");
                b.spieleRunde(true);
            }
        }
        if(_spieler.length > 1)
        {
            aktuellerSpieler = (aktuellerSpieler+1) % _spieler.length;
            System.out.println("-----------------------------\n" + _spieler[aktuellerSpieler].gibName() + " ist dran.\n-----------------------------");
        }
        
        if(alleSpielerFertig() && alleBotsFertig())
        {
            //Zeige Punktübersicht
            
            for(spieler s : _spieler)
            {
                System.out.println(s.gibName() + ": " + s.gibPunkte());
            }
            for(bot b : _bots)
            {
                System.out.println("Bot " + b.gibName() + ": " + b.gibPunkte());
            }
        }
        else
        {
            if(_spieler[aktuellerSpieler].fertig())
            {
                //Nächster Spieler ist dran. Sollte theoretisch nicht Vorkommen, aber lassen wir mal so
                neueRunde();
            }
            else
            {
                menu(_spieler[aktuellerSpieler]);
            }
        }

    }
    
    /**
     * Prüft ob alle Spieler fertig sind.
     * @return true: Alle fertig, false:Nicht alle fertig.
     */
    private static boolean alleSpielerFertig()
    {
        for(spieler s : _spieler)
        {
            if(!s.fertig()) return false;
        }
        return true;
    }
    
    /**
     * Prüft ob alle Bots fertig sind.
     * @return true: Alle fertig, false:Nicht alle fertig.
     */
    private static boolean alleBotsFertig()
    {
        for(bot b : _bots)
        {
            if(!b.fertig()) return false;
        }
        return true;
    }
    
    /**
     * Menü zum Auswählen der Würfel die zurück in den Becher sollen
     * @return Array mit den eingegebenen Würfeln als String
     * @throws IOException
     */
    private static String[] wuerfelInBecherAuswaehlen() throws IOException
    {
        String[] welcheS = eingabeString("Welche Wuefel? z.B. 1 3 5").split(" ");
        for(String s : welcheS)
        {
            if(!s.matches("[1-5]")) //numerisch 1-5
            {
                System.out.println("Bitte korrekt eingeben.");
                welcheS = wuerfelInBecherAuswaehlen();
                break;
            }
        }
        return welcheS;
    }
    
    /**
     * Menü zum eingeben der Spieler
     * @throws IOException
     */
    private static void initSpieler() throws IOException
    {
        int spieler = eingabeNum("Anzahl Spieler?");
        _spieler =  new spieler[spieler];
        for(int s = 0;s < spieler; s++)
        {
            _spieler[s] = new spieler(eingabeString("Spieler " + (s+1) + " Name:"));
        }
        System.out.println("-- Alle Spieler eingetragen"); 
    }
    
    /**
     * Menü zum eingeben der Bots
     * @throws IOException
     */
    private static void initBots() throws IOException
    {
        int bots = eingabeNum("Anzahl Bots?");
        _bots =  new bot[bots];
        for(int b = 0;b < bots; b++)
        {
            _bots[b] = eingabeBotTyp(b+1);
        }
        System.out.println("-- Alle Bots eingetragen");        
        
    }
    
    /**
     * Menü zum festlegen des Bot Typs.
     * @param b Nummer des Bots
     * @return gibt den Bot als Exemplar zurück
     * @throws IOException
     */
    private static bot eingabeBotTyp(int b) throws IOException
    {
        switch (eingabeNum("Welcher Typ des Bots: " + b + "?\n1 - Simple\n2 - Mediaum\n3 - Hard"))
        {
        case 1:
            return new botSimple();
        case 2:
            return new botMedium();
        case 3:
            return new botHard();
        default:
            System.out.println("Bitte richtige Option auswählen.");
            return eingabeBotTyp(b);
        }
    }
    
    /**
     * Fragt den Benutzer nach einem numerischem Wert und prüft dieses.
     * Falls nicht numerisch, wird der Benutzer erneut aufgefordert.
     * @param question Frage an den Benutzer
     * @return Erfragter numerischer Wert
     * @throws IOException
     */
    private static int eingabeNum(String question) throws IOException
    {
        System.out.println(question);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String eingabe = br.readLine();
        if(eingabe.matches("[-+]?\\d*\\.?\\d+")) //numerisch
        {
            return Integer.parseInt(eingabe);
        }
        else
        {
            System.out.println("Bitte gib eine Ziffer ein.");
            return eingabeNum(question);
        }
    }
    
    /**
     * Fragt den Benutzer nach einem alphanumerischen Wert und prüft dieses.
     * Falls nicht numerisch, wird der Benutzer erneut aufgefordert.
     * @param question Frage an den Benutzer
     * @return Erfragter alphanumerischer Wert
     * @throws IOException
     */
    private static String eingabeString(String question) throws IOException
    {
        System.out.println(question);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String eingabe = br.readLine();
        return eingabe;
    }
    
    /**
     * Erzeugt einen tollen Header!
     */
    private static void header()
    {
        System.out.println("        ____             __     __   _     _                ");
        System.out.println("       /\\' .\\    _____   \\ \\   / /  | |   | |               ");
        System.out.println("      /: \\___\\  / .  /\\   \\ \\_/ /_ _| |__ | |_ _______  ___ ");
        System.out.println("      \\' / . / /____/..\\   \\   / _` | '_ \\| __|_  / _ \\/ _ \\");
        System.out.println("       \\/___/  \\'  '\\  /    | | (_| | | | | |_ / /  __/  __/");
        System.out.println("                \\'__'\\/     |_|\\__,_|_| |_|\\__/___\\___|\\___|");
    }

}
