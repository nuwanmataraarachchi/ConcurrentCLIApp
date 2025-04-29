package ticketbookingsys;

public class Producer implements Runnable {
    private final TicketPool ticketPool;
    private final String name;

    public Producer(TicketPool ticketPool, String name) {
        this.ticketPool = ticketPool;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = new Ticket(name); // Each ticket has a producer name
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(500); // Simulate delay between adding tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
