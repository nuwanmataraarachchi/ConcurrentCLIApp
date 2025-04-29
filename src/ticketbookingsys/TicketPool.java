package ticketbookingsys;

public interface TicketPool {
    void addTicket(Ticket ticket);
    Ticket purchaseTicket();
    int getTicketCount();
    void printStatus();
}
