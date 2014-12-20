import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class yahtzee
{
    static spieler[] _spieler;
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("        ____             __     __   _     _                ");
        System.out.println("       /\\' .\\    _____   \\ \\   / /  | |   | |               ");
        System.out.println("      /: \\___\\  / .  /\\   \\ \\_/ /_ _| |__ | |_ _______  ___ ");
        System.out.println("      \\' / . / /____/..\\   \\   / _` | '_ \\| __|_  / _ \\/ _ \\");
        System.out.println("       \\/___/  \\'  '\\  /    | | (_| | | | | |_ / /  __/  __/");
        System.out.println("                \\'__'\\/     |_|\\__,_|_| |_|\\__/___\\___|\\___|");
        System.out.println("\n\n Wie viele Spieler?");
        String eingabe = br.readLine();
        int spieler = Integer.parseInt(eingabe);
        _spieler =  new spieler[spieler];
        for(int s = 0;s < spieler; s++)
        {
            System.out.println("-- Bitte Namen für Spieler " + (s+1) + " eingeben");
            eingabe = br.readLine();
            _spieler[s] = new spieler(eingabe);
        }
        System.out.println("-- Alle Spieler eiongetragen");
        System.out.println("-- Bitte Option wählen:");
        System.out.println("1 - Punktezettel ansehen");
        System.out.println("2 - Würfeln");
        String option = br.readLine();
        switch(Integer.parseInt(option))
        {
        case 1:
            _spieler[0].gibTabelle();
            break;
        case 2:
            System.out.println("#### Noch nicht implementiert");
            break;
        default:
            System.out.println("Bitte richtige Option auswählen.");
        }
        
    }

}
