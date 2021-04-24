public class GenericQueue<T> {
    private T items[];
    private int front;
    private int end;
    private int size;

    public GenericQueue() {
        front = -1;
        end = -1;
        size = 5;
        items = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return front == -1 && end == -1;
    }

    public boolean isFull() {
        return end == size - 1;
    }

    public void enqueue(T item) {
        if (isFull()) {
            resize(this.size*2);
            this.items[++end] = item;
        } else if (isEmpty()) {
            items[++front] = item;
            end = front;
        } else {
            items[++end] = item;
        }
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return this.items[front];
    }

    public void remove(T item) {
        if (this.items[front] == item) {
            dequeue();
        } else if (this.items[end] == item) {
            this.items[end] = null;
            end = end - 1;
        } else {
            T[] temp = (T[]) new Object[this.size];
            int i = 0;
            int j = 0;
            while (j < this.size) {
                if (this.items[j] == item) {
                    j++;
                    continue;
                }
                temp[i] = this.items[j];
                i++;
                j++;
            }
            if (i != j) {
                this.items = temp;
                end = end - 1;
            }
        }
        if (end + 1 == (this.size/4)) {
            resize(this.size/2);
        }
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

    private void dequeue() {
        if (isEmpty()) {
            return;
        }
        this.items[front] = null;
        if (front == end) {
            front = end = -1;
        } else {
            front++;
        }
    }
}
