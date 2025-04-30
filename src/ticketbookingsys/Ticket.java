package ticketbookingsys;

public class Ticket {
    private final int id;
    private final String producerName;

    public Ticket(int id, String producerName) {
        this.id = id;
        this.producerName = producerName;
    }

    public int getId() {
        return id;
    }

    public String getProducerName() {
        return producerName;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", producerName='" + producerName + "'}";
    }
}
