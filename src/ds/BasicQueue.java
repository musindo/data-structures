package ds;

public class BasicQueue<X> {
    private X[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(100);
    }

    public BasicQueue(int size) {
        this.front = -1;
        this.end = -1;
        data = (X[]) new Object[size];
    }

    public int size() {
        //If the queue is empty return 0
        if (front == -1 && end == -1) {
            return 0;
        } else {
            return end - front + 1;
        }
    }

    public void enQueue(X item) {
        //first check if the queue is full
        if ((end + 1) % data.length == front) {
            throw new IllegalStateException("The queue is full");
        }

        //check if any items has been added to queue yet
        else if (size() == 0) {
            front++;
            end++;
            data[end] = item;
        }
        //otherwise add the item to the end of the queue
        else {
            end++;
            data[end] = item;
        }
    }

    public X deQueue() {
        X item = null;

        //if the queue is empty we cant deQueue anything
        if (size() == 0) {
            throw new IllegalStateException("Nothing to dequeue");
        }
        //if item is the last in queue, the queue needs to be reset to empty
        else if (front == end) {
            item = data[front];
            data[front] = null;
            front = -1;
            end = -1;
        }

        //otherwise grab the front of the queue, return it and adjust the front pointer
        else {
            item = data[front];
            data[front] = null;
            front++;
        }
        return item;
    }

    public boolean contains(X item) {
        boolean found = false;

        //if the queue is empty just return false
        if (size() == 0) {
            return found;
        }

        for (int i = front; i < end; i++) {
            if (data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public X access(int position) {
        if (size() == 0 || position > size()) {
            throw new IllegalStateException("No items in the queue or position is greater");
        }

        int trueIndex = 0;
        for (int i = front; i < end; i++) {
            if (trueIndex == position) {
                return data[i];
            }
            trueIndex++;
        }
        throw new IllegalStateException("Could not get item from position: " + position);
    }

}
