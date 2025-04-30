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
        try {
            while (true) {
                String ticket = "Ticket " + System.nanoTime();
                ticketPool.addTicket(ticket);  // Producer writes (adds ticket)
                System.out.println(name + " produced: " + ticket);
                Thread.sleep(1000); // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
