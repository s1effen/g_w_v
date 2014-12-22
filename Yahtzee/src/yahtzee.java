import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import spieler.bot;
import spieler.botHard;
import spieler.botMedium;
import spieler.botSimple;
import spieler.spieler;


public class yahtzee
{
    static spieler[] _spieler;
    static int aktuellerSpieler;
    static bot[] _bots;
    public static void main(String[] args) throws IOException
    {
        System.out.println("1 - Simulation mit Bots");
        System.out.println("2 - Interaktives Spiel");
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1:
            initBots();
            initGame();
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
    
    private static void initGame() throws IOException
    {
        int spiele = eingabeNum("Wie viele Spiele simulieren?.");
        for(bot b : _bots)
        {
            b.spieleSpiele(spiele);
        }
    }

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
    
    private static void wirf(spieler aktuellerSpieler) throws IOException
    {
        //Wurf 1
        System.out.println(aktuellerSpieler.wirf());
        wuerfelMenu(aktuellerSpieler);
    }
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
    
    private static void wertungMenu(spieler aktuellerSpieler) throws IOException
    {
        aktuellerSpieler.werte(eingabeNum("-- Bitte Eintrag zum Werten wählen: (Zu wertende sind mit * markiert)"));
    }
    
    private static void streichMenu(spieler aktuellerSpieler) throws IOException
    {
        aktuellerSpieler.streiche(eingabeNum("-- Bitte Eintrag zum Streichen wählen: (Zu streichende haben noch keine Punkte)"));
    }
    
    private static boolean[] wuerfelInBecher(String[] welcheS)
    {
        boolean[] welcheB = {false, false, false, false, false};
        for(String s : welcheS)
        {
            welcheB[Integer.parseInt(s)-1] = true;
        }
        return welcheB;
        
    }
    
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
    
    private static boolean alleSpielerFertig()
    {
        for(spieler s : _spieler)
        {
            if(!s.fertig()) return false;
        }
        return true;
    }
    
    private static boolean alleBotsFertig()
    {
        for(bot b : _bots)
        {
            if(!b.fertig()) return false;
        }
        return true;
    }
    
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
    
    private static String eingabeString(String question) throws IOException
    {
        System.out.println(question);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String eingabe = br.readLine();
        return eingabe;
    }
    
    private void header()
    {
        System.out.println("        ____             __     __   _     _                ");
        System.out.println("       /\\' .\\    _____   \\ \\   / /  | |   | |               ");
        System.out.println("      /: \\___\\  / .  /\\   \\ \\_/ /_ _| |__ | |_ _______  ___ ");
        System.out.println("      \\' / . / /____/..\\   \\   / _` | '_ \\| __|_  / _ \\/ _ \\");
        System.out.println("       \\/___/  \\'  '\\  /    | | (_| | | | | |_ / /  __/  __/");
        System.out.println("                \\'__'\\/     |_|\\__,_|_| |_|\\__/___\\___|\\___|");
        System.out.println("\n\n Wie viele Spieler?");
    }

}
