package ticketbookingsys;

public class Ticket {
    private static int counter = 0;
    private final int id;
    private final String producerName;

    public Ticket(String producerName) {
        this.id = ++counter;
        this.producerName = producerName;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", producer='" + producerName + "'}";
    }
}
