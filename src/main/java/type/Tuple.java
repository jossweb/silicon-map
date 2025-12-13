package type;

public class Tuple <T, S>{
    private T first;
    private S second;

    public Tuple(T f, S s){
        this.first = f;
        this.second = s;
    }

    public T getFirst(){
        return this.first;
    }
    public void setFirst(T f){
        this.first =  f;
    }
    public S getSecond(){
        return this.second;
    }
    public void setSecond(S s){
        this.second = s;
    }
}
