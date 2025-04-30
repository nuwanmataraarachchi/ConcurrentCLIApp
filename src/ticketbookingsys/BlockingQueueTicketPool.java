package ticketbookingsys;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTicketPool implements TicketPool {
    private final BlockingQueue<String> tickets;

    public BlockingQueueTicketPool(int capacity) {
        tickets = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void addTicket(String ticket) {
        try {
            tickets.put(ticket);
            System.out.println("Ticket added: " + ticket);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String getTicket() {
        try {
            return tickets.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override
    public void printStatus() {
        System.out.println("Ticket Pool Status: " + tickets.size() + "/" + tickets.remainingCapacity() + " tickets in the pool.");
    }
}
