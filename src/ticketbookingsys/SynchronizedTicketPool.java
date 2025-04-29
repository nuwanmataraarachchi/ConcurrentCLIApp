package ticketbookingsys;

import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedTicketPool implements TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final int capacity;

    public SynchronizedTicketPool(int capacity) {
        this.capacity = capacity;
        this.ticketQueue = new LinkedList<>();
    }

    @Override
    public synchronized void addTicket(Ticket ticket) {
        if (ticketQueue.size() < capacity) {
            ticketQueue.add(ticket);
            System.out.println("Ticket added: " + ticket);
        } else {
            System.out.println("Ticket pool is full!");
        }
    }

    @Override
    public synchronized Ticket purchaseTicket() {
        if (ticketQueue.isEmpty()) {
            System.out.println("No tickets available!");
            return null;
        }
        Ticket ticket = ticketQueue.poll();
        System.out.println("Ticket purchased: " + ticket);
        return ticket;
    }

    @Override
    public synchronized int getTicketCount() {
        return ticketQueue.size();
    }



    @Override
    public synchronized void printStatus() {
        System.out.println("Tickets in pool: " + ticketQueue.size());
    }
}
