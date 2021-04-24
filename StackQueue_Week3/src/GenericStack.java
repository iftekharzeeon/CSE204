public class GenericStack<T> {
    private T[] items;
    private int top;
    private int size;

    public GenericStack() {
        top = -1;
        size = 10;
        items = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == (size-1);
    }

    public void push(T item) {
        if (isFull()) {
            resize(this.size*2);
            this.items[++top] = item;
            return;
        }
        this.items[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = this.items[top];
        this.items[top] = null;
        top--;
        if ((top + 1) == (this.size/4)) {
            resize(this.size/2);
        }
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        T item = this.items[top];
        return item;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        if (newSize > this.size) {
            for (int i = 0; i < this.size; i++) {
                temp[i] = this.items[i];
            }
        } else {
            for (int i = 0; i < newSize; i++) {
                temp[i] = this.items[i];
            }
        }
        this.items = temp;
        this.size = newSize;
    }
}
