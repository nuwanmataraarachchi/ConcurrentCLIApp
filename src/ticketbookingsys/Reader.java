package ticketbookingsys;
public class Reader implements Runnable {
    private final TicketPool ticketPool;
    private final String name;

    public Reader(TicketPool ticketPool, String name) {
        this.ticketPool = ticketPool;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ticket = ticketPool.getTicket();
                if (ticket != null) {
                    System.out.println(name + " consumed: " + ticket);
                }
                Thread.sleep(1000);  // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
