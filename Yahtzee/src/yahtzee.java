import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class yahtzee
{
    static spieler[] _spieler;
    
    public static void main(String[] args) throws IOException
    {
        init();
        menu(_spieler[0]); //Später alternierend...
        
    }
    
    private static void menu(spieler aktuellerSpieler) throws IOException
    {
        System.out.println("1 - Punktezettel ansehen");
        System.out.println("2 - Würfeln");
        
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1:
            aktuellerSpieler.gibTabelle();
            break;
        case 2:
            wuerfelMenu(aktuellerSpieler);
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
        System.out.println(aktuellerSpieler.wirf());
        System.out.println("-- Bitte Option wählen:");
        System.out.println("1 - Bestimmte Wuerfel in den Becher");
        System.out.println("2 - Alle Wuefel in den Becher");
        System.out.println("3 - Werten");
        switch(eingabeNum("Bitte option wählen."))
        {
        case 1:
            aktuellerSpieler.wirfBestimmte(wuerfelInBecher(wuerfelInBecherAuswaehlen()));
            break;
        case 2:
            wuerfelMenu(aktuellerSpieler);
            break;
        case 3:
            System.out.println("fehlt noch.");
            break;
        default:
            System.out.println("Bitte richtige Option auswählen.");
        }
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
    
    private static String[] wuerfelInBecherAuswaehlen() throws IOException
    {
        String[] welcheS = eingabeString("Welche Wuefel? z.B. 1 3 5").split(" ");
        for(String s : welcheS)
        {
            if(!s.matches("[-+]?\\d*\\.?\\d+")) //numerisch
            {
                System.out.println("Bitte korrekt eingeben.");
                welcheS = wuerfelInBecherAuswaehlen();
                break;
            }
        }
        return welcheS;
    }
    private static void init() throws IOException
    {
        int spieler = eingabeNum("Anzahl Spieler?");
        _spieler =  new spieler[spieler];
        for(int s = 0;s < spieler; s++)
        {
            _spieler[s] = new spieler(eingabeString("Name:"));
        }
        System.out.println("-- Alle Spieler eingetragen");
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
            System.out.println("Please enter a number");
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
