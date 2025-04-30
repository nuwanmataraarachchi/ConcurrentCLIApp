package ticketbookingsys;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedTicketPool implements TicketPool {
    private final Queue<String> tickets;
    private final int capacity;

    public SynchronizedTicketPool(int capacity) {
        this.capacity = capacity;
        this.tickets = new LinkedList<>();
    }

    @Override
    public synchronized void addTicket(String ticket) {
        if (tickets.size() < capacity) {
            tickets.add(ticket);
            System.out.println("Ticket added: " + ticket);
        } else {
            System.out.println("Ticket pool is full.");
        }
    }

    @Override
    public synchronized String getTicket() {
        if (!tickets.isEmpty()) {
            String ticket = tickets.poll();
            return ticket;
        }
        return null;
    }

    @Override
    public void printStatus() {
        System.out.println("Ticket Pool Status: " + tickets.size() + "/" + capacity + " tickets in the pool.");
    }
}
