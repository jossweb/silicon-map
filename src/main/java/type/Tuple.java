package type;

/**
 * A generic tuple holding two values of potentially different types 
 * 
 * @param <T> type of the first element 
 * @param <S> type of the second element
 * 
 * @author FIGUEIRAS Jossua
 */
public class Tuple <T, S>{
    private T first;
    private S second;

    /**
     * Constructs a Tuple with the given elements
     * 
     * @param f the first element
     * @param s the second element
     */
    public Tuple(T f, S s){
        this.first = f;
        this.second = s;
    }
    /**
     * Returns the first element of the tuple
     * 
     * @return the first element
     */
    public T getFirst(){
        return this.first;
    }
     /**
     * Sets the first element of the tuple
     * 
     * @param f the new first element
     */
    public void setFirst(T f){
        this.first =  f;
    }
    /**
     * Returns the second element of the tuple
     * 
     * @return the second element
     */
    public S getSecond(){
        return this.second;
    }
    /**
     * Sets the second element of the tuple
     * 
     * @param s the new second element
     */
    public void setSecond(S s){
        this.second = s;
    }
}
