package ticketbookingsys;

public interface TicketPool {
    void addTicket(String ticket);
    String getTicket();
    void printStatus();
}
