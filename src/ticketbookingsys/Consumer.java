package ticketbookingsys;

public class Consumer implements Runnable {
    private final TicketPool ticketPool;
    private final String name;

    public Consumer(TicketPool ticketPool, String name) {
        this.ticketPool = ticketPool;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ticket = ticketPool.getTicket();  // Consumer reads (takes ticket)
                if (ticket != null) {
                    System.out.println(name + " consumed: " + ticket);
                }
                Thread.sleep(1500); // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
