package ticketbookingsys;

public class Main {
    public static void main(String[] args) {
        TicketPool ticketPool = new ReentrantLockTicketPool(5); // Set a pool size

        // Create and start Writer threads (Producers)
        Thread writer1 = new Thread(new Writer(ticketPool, "Writer 1"));
        Thread writer2 = new Thread(new Writer(ticketPool, "Writer 2"));

        // Create and start Reader threads (Consumers)
        Thread reader1 = new Thread(new Reader(ticketPool, "Reader 1"));
        Thread reader2 = new Thread(new Reader(ticketPool, "Reader 2"));

        writer1.start();
        writer2.start();
        reader1.start();
        reader2.start();
    }
}
