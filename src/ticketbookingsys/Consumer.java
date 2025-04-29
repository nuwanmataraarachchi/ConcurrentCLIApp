package ticketbookingsys;

public class Consumer implements Runnable {
    private final TicketPool ticketPool;

    public Consumer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.purchaseTicket();
            try {
                Thread.sleep(500); // Simulate delay between purchasing tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
