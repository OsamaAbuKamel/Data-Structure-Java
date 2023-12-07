import java.util.Iterator;

public class AStack<T extends Comparable<T>> implements Stack<T>{
    private static final int DEFAULT_SIZE=10;
    private T arr[];
    private int maxSize;
    private int top;
    
    public  AStack(){
        this(DEFAULT_SIZE);
    }
    
    public AStack(int size){
        maxSize = size;
        top=0;
        arr = (T[]) new Comparable[size];
    }
    
    @Override
    public void clear() {
        top=0;
    
    }
    
    @Override
    public boolean puh(T data) {
        if (top >= maxSize)
        return false;
        arr[top++] = data;
        return true;
    }
    
    @Override
    public T pop() {
        if (top ==0)
        return null;
        return arr[--top];
    }
    
    @Override
    public T peek() {
        if (top==0)
        return null;
        return arr[top-1];
    }
    
    @Override
    public int length() {
        return top;
    }
    
    @Override
    public boolean isEmpty() {
        return  top ==0;
    }
    
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
