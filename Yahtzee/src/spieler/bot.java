package spieler;
import wuerfel.wuerfelbecher;
import eintraege.tabelle;

/**
 * Interface der Bots
 *
 */
public interface bot
{
    
    /**
     * Gibt die aktuellen Gesamtpunkte zurück
     * @return Gesamtpunkte
     */
    public int gibPunkte();
    
    /**
     * Gibt den Namen zurück
     * @return Name
     */
    public String gibName();

    /**
     * Gibt Auskunft ob der Zettel Voll ist.
     * @return true = Zettel ist voll. false = Noch Einträge offen.
     */
    public boolean fertig();
    
    /**
     * Gibt die aktuelle Tabelle aus
     */
    public void gibTabelle();
    
    /**
     * Spielt eine Runde
     * @param bildschirmausgabe boolean ob die Runde am Bildschirm ausgegeben werden soll oder nicht.
     */
    public void spieleRunde(boolean bildschirmausgabe);
    
    /**
     * Spielt Spiele hintereinander
     * @param spiele Anzahl der zu spielenden Spiele
     */
    public void spieleSpiele(int spiele);
    
    /**
     * Spielt genau ein Spiel.
     */
    void spieleSpiel();

}
