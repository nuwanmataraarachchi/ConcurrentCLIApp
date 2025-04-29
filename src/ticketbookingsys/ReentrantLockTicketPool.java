package ticketbookingsys;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTicketPool implements TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final int capacity;
    private final ReentrantLock lock;

    public ReentrantLockTicketPool(int capacity) {
        this.capacity = capacity;
        this.ticketQueue = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public void addTicket(Ticket ticket) {
        lock.lock();
        try {
            if (ticketQueue.size() < capacity) {
                ticketQueue.add(ticket);
                System.out.println("Ticket added: " + ticket);
            } else {
                System.out.println("Ticket pool is full!");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Ticket purchaseTicket() {
        lock.lock();
        try {
            if (ticketQueue.isEmpty()) {
                System.out.println("No tickets available!");
                return null;
            }
            Ticket ticket = ticketQueue.poll();
            System.out.println("Ticket purchased: " + ticket);
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getTicketCount() {
        lock.lock();
        try {
            return ticketQueue.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printStatus() {
        lock.lock();
        try {
            System.out.println("Tickets in pool: " + ticketQueue.size());
        } finally {
            lock.unlock();
        }
    }
}
