package type;

/**
 * A generic tuple holding two values of potentially different types |
 * Un tuple générique contenant deux valeurs de types potentiellement différents.
 * 
 * @param <T> type of the first element | type du premier élément
 * @param <S> type of the second element | type du second élément
 * 
 * @author FIGUEIRAS Jossua
 */
public class Tuple <T, S>{
    private T first;
    private S second;

    /**
     * Constructs a Tuple with the given elements |
     * Construit un Tuple avec les éléments donnés.
     * 
     * @param f the first element | le premier élément
     * @param s the second element | le second élément
     */
    public Tuple(T f, S s){
        this.first = f;
        this.second = s;
    }
    /**
     * Returns the first element of the tuple |
     * Retourne le premier élément du tuple.
     * 
     * @return the first element | le premier élément
     */
    public T getFirst(){
        return this.first;
    }
     /**
     * Sets the first element of the tuple |
     * Définit le premier élément du tuple.
     * 
     * @param f the new first element | le nouvel élément
     */
    public void setFirst(T f){
        this.first =  f;
    }
        /**
     * Returns the second element of the tuple |
     * Retourne le second élément du tuple.
     * 
     * @return the second element | le second élément
     */
    public S getSecond(){
        return this.second;
    }
    /**
     * Sets the second element of the tuple |
     * Définit le second élément du tuple.
     * 
     * @param s the new second element | le nouvel élément
     */
    public void setSecond(S s){
        this.second = s;
    }
}
