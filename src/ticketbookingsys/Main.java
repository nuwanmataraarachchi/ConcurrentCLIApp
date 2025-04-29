package ticketbookingsys;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedTicketPool pool = new SynchronizedTicketPool(5);

        // Create and start threads for adding and purchasing tickets
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Ticket ticket = new Ticket("Producer1");
                    pool.addTicket(ticket); // ✅ Ticket passed here
                    System.out.println("Produced: " + ticket);
                    Thread.sleep(500); // Simulate time between ticket additions
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Ticket ticket = pool.purchaseTicket(); // ✅ Returns a Ticket
                    System.out.println("Consumed: " + ticket);
                    Thread.sleep(1000); // Simulate time between ticket purchases
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
