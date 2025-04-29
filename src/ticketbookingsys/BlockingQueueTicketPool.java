package ticketbookingsys;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTicketPool implements TicketPool {
    private final BlockingQueue<Ticket> ticketQueue;

    public BlockingQueueTicketPool(int capacity) {
        this.ticketQueue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void addTicket(Ticket ticket) {
        try {
            if (!ticketQueue.offer(ticket)) {
                System.out.println("Ticket pool is full!");
            } else {
                System.out.println("Ticket added: " + ticket);
            }
        } catch (Exception e) {
            System.out.println("Error adding ticket: " + e.getMessage());
        }
    }

    @Override
    public Ticket purchaseTicket() {
        try {
            Ticket ticket = ticketQueue.take(); // Blocks if no ticket is available
            System.out.println("Ticket purchased: " + ticket);
            return ticket;
        } catch (InterruptedException e) {
            System.out.println("Error purchasing ticket: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int getTicketCount() {
        return ticketQueue.size();
    }

    @Override
    public void printStatus() {
        System.out.println("Tickets in pool: " + ticketQueue.size());
    }
}
