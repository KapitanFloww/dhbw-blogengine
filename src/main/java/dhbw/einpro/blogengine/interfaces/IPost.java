package dhbw.einpro.blogengine.interfaces;

import java.io.Serializable;
import java.util.List;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;

//Achtung: Diese Datei darf nicht editiert werden!

/**
 * Das Interface beschreibt Posts, die im Blog-System erstellt und
 * veröffentlicht werden können.
 */
public interface IPost
{
    /**
     * Liefert die Id des Post.
     * 
     * @return Id des Posts
     */
    int getId();

    /**
     * Setzt die Id des Posts
     * 
     * @param p_id neue Id für den Post
     */
    void setId(int p_id);

    /**
     * Liefert die Liste aller Kommentare zum Post zurück
     * 
     * @return Liste von Kommentaren zum Post
     */
    List<IComment> getComments();

    /**
     * Fügt den angegebenen Kommentar zum Post hinzu
     * 
     * @param p_comment Kommentar, der zum Post hinzugefügt werden soll
     * @throws IllegalOperationException wird ausgelöst, falls der Autor des
     *                                   Kommentars gleichzeitig Autor des Posts
     *                                   ist.
     */
    void addComment(IComment p_comment) throws IllegalOperationException;

    /**
     * Entfernt einen Kommentar eines Benutzers.
     * 
     * @param p_user Autor des Kommentars.
     * @param p_comment Kommentar der gelöscht werden soll.
     * @throws IllegalOperationException wird ausgelöst, falls der als Parameter übergebene
     *                                   Benutzer nicht der tatsächliche Autor des Kommentars ist.
     */
    void removeComment(IUser p_user, IComment p_comment) throws IllegalOperationException;

    /**
     * Fügt den Benutzer zur Liste der Benutzer hinzu, die den Post gut finden.
     * 
     * @param p_person Benutzer, der den Post gut findet. Der Benutzer wird in die
     *               Liste der Benutzer aufgenommen, die den Post gut finden (Siehe
     *               auch {@link #getLikes()}). Falls der gleiche Benutzer diese
     *               Methode mehrmals aufruft, so wird er nur das erste Mal in die
     *               Liste aufgenommen.
     * @throws IllegalOperationException wird ausgelöst, falls der User zugleich
     *                                   Autor des Posts ist.
     */

    void like(IUser p_person) throws IllegalOperationException;

    /**
     * Fügt den Benutzer zur Liste der Benutzer hinzu, die den Post nicht gut
     * finden.
     * 
     * @param p_person User, der den Post nicht gut findet
     * @throws IllegalOperationException wird ausgelöst, falls der User zugleich
     *                                   Autor des Posts ist.
     */

    void disLike(IUser p_person) throws IllegalOperationException;

    /**
     *
     *
     * Berechnet die Resonanz eines Posts. Zur Berechnung des Scores werden die
     * Anzahl der likes und dislikes verwendet. Beispiel: Ein Post wird von 3
     * Benutzern bewertet. Die ersten 2 Benutzer finden den Post gut (like). Der
     * dritte Benutzer findet den Post nicht gut (dislike). Der Score des Posts wird
     * wie folgt berechnet: (2-1)*10 = 10
     *
     * @return Score des Posts
     */
    int getScore();

    /**
     * Liefert den Titel des Posts zurück
     * 
     * @return Titel des Posts
     */
    String getTitle();

    /**
     * Setzt oder aktualisiert den Titel des Posts
     * 
     * @param p_title Der neue Titel des Posts
     */

    void setTitle(String p_title);

    /**
     * Liefert den Inhalt des Posts zurück
     * 
     * @return Inhalt des Posts
     */
    String getContent();

    /**
     * Setzt bzw. aktualisiert den Inhalt des Posts
     * 
     * @param p_content Der neue Inhalt des Posts
     */
    void setContent(String p_content);

    /**
     * Liefert den Autor des Posts zurück
     * 
     * @return Autor des Posts
     */
    IUser getAuthor();

    /**
     * Setzt oder aktualisiert den Autor des Posts
     * 
     * @param p_author Autor, der gesetzt werden soll
     */
    void setAuthor(IUser p_author);

    /**
     * Liefert eine Liste von Personen zurück, die den Post gut finden
     * 
     * @return Liste von Personen
     */
    List<IUser> getLikes();

    /**
     * Liefert eine Liste von Personen zurück, die den Post nicht gut finden
     * 
     * @return Liste von Personen
     */
    List<IUser> getDisLikes();
}
