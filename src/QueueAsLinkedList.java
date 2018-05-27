import java.util.NoSuchElementException;

public class QueueAsLinkedList <T> implements Queue<T>{

        private List<T> list;

        public QueueAsLinkedList() {
            this.list = new LinkedList<>();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public void enqueue(T element) {
        	if(element == null) 
            	throw new NullPointerException();
            list.add(element);
        }


        public T dequeue() {
            if(isEmpty()) 
            	throw new NoSuchElementException();
            return list.remove().getData();
        }

        public T peek() {
            if(isEmpty()) 
            	throw new NoSuchElementException();
            return list.get(0);
        }

        public String toString() {
            return list.toString();
        }

}
