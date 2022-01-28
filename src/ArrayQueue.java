public class ArrayQueue<E> implements Queue<E> {
    private E[] data;
    private int f = 0;
    private int sz =0 ;
    public static int CAPACITY=16;

    public ArrayQueue(int capacity){
        data =(E[]) new Object[capacity];
    }

    public ArrayQueue(){
        this(CAPACITY);
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return (sz==0);
    }

    @Override
    public E first() {
        if(isEmpty())
            return null;
        else
            return data[f];
    }

    @Override
    public void enqueue(E e) {
        if(sz== data.length) throw new IllegalStateException("Queue is Full!");
        int avail = (sz+f)% data.length;
        data[avail]= e;
        sz++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            return null;
        E answer = data[f];
        data[f] = null;
        f=(f+1)% data.length;
        sz--;
        return answer;
    }
}
