import java.util.Iterator;

public class StackIterator<T> implements Iterator<T> {
        private int index;
        private T[] data;

        public StackIterator(T[] data, int size) {
            // Assign the data to the class variable
            this.data = data;
            // Set the index to the size of the array minus 1
            index = size - 1;
        }

        @Override
        public boolean hasNext() {
            // Check if the index is greater than or equal to 0
            return index >= 0;
        }

        @Override
        public T next() {
            // Declare a variable to store the return value
            return data[index--];
            // Return the value at the current index and decrement the index
        }
    }