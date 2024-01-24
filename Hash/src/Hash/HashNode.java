package Hash;

public class HashNode<T extends Comparable<T>> {
    final char DELETE = 'D';
    final char EMPTY = 'E';
    final char FULL = 'F';
    
    T data;
    char flag = EMPTY;
    
    public HashNode(T data) {
        this.data = data;
        this.flag = FULL;
    }
    
    public HashNode() {
        this.data=null;
        this.flag=EMPTY;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public char getFlag() {
        return flag;
    }
    
    public void setFlag(char flag) {
        if (flag == DELETE || flag == EMPTY || flag == FULL) {
            this.flag = flag;
        } else
            throw new IllegalArgumentException("Flag must be 'D', 'E' or 'F'");
    }
    
    public void setDeleted() {
        this.flag = DELETE;
    }
    public boolean isDeleted() {
        return  this.flag == DELETE;
        
    }
    
    public boolean isFull() {
        return flag == FULL;
    }
    
    public boolean isEmpty() {
        return (flag == EMPTY )|| (flag == DELETE);
    }
    
    @Override
    public String toString() {
        return data + " " + flag;
    }
}
