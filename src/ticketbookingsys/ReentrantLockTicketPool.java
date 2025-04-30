package ticketbookingsys;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTicketPool implements TicketPool {
    private final Queue<String> tickets;
    private final int capacity;
    private final ReentrantReadWriteLock lock;

    public ReentrantLockTicketPool(int capacity) {
        this.capacity = capacity;
        this.tickets = new LinkedList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void addTicket(String ticket) {
        lock.writeLock().lock();
        try {
            if (tickets.size() < capacity) {
                tickets.add(ticket);
                System.out.println("Ticket added: " + ticket);
            } else {
                System.out.println("Ticket pool is full.");
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String getTicket() {
        lock.readLock().lock();
        try {
            if (!tickets.isEmpty()) {
                return tickets.poll();
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void printStatus() {
        lock.readLock().lock();
        try {
            System.out.println("Ticket Pool Status: " + tickets.size() + "/" + capacity + " tickets in the pool.");
        } finally {
            lock.readLock().unlock();
        }
    }
}
