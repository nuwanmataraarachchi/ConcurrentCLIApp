package ticketbookingsys;

public class Reader implements Runnable {
    private final TicketPool ticketPool;

    public Reader(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.printStatus();
            try {
                Thread.sleep(1000); // Simulate delay between querying the pool
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
