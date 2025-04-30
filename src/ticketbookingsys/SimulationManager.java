package ticketbookingsys;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketPool ticketPool = null;
        int ticketPoolCapacity = 0;
        int threadPoolSize = 0;
        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();

        // System Configuration Menu
        System.out.println("Welcome to the Ticket Management System");
        System.out.println("\n--- System Configuration ---");
        System.out.println("Choose ticket pool implementation:");
        System.out.println("1. Synchronized methods");
        System.out.println("2. ReentrantReadWriteLock");
        System.out.println("3. BlockingQueue");
        System.out.print("Implementation (1-3): ");
        int poolChoice = scanner.nextInt();

        System.out.print("Ticket pool capacity (5-9, 20): ");
        ticketPoolCapacity = scanner.nextInt();
        System.out.print("Thread pool size (e.g., 20): ");
        threadPoolSize = scanner.nextInt();

        switch (poolChoice) {
            case 1:
                ticketPool = new SynchronizedTicketPool(ticketPoolCapacity);
                System.out.println("System configured with Synchronized implementation");
                break;
            case 2:
                ticketPool = new ReentrantLockTicketPool(ticketPoolCapacity);
                System.out.println("System configured with ReentrantReadWriteLock implementation");
                break;
            case 3:
                ticketPool = new BlockingQueueTicketPool(ticketPoolCapacity);
                System.out.println("System configured with BlockingQueue implementation");
                break;
            default:
                System.out.println("Invalid choice, using default (Synchronized) implementation.");
                ticketPool = new SynchronizedTicketPool(ticketPoolCapacity);
        }

        // Ticket Management System Menu
        while (true) {
            System.out.println("\n--- Ticket Management System [" + ticketPool.getClass().getSimpleName() + "] ---");
            System.out.println("1. Add Producer");
            System.out.println("2. Remove Producer");
            System.out.println("3. Add Consumer");
            System.out.println("4. Remove Consumer");
            System.out.println("5. Print System Status");
            System.out.println("6. Start Threads");
            System.out.println("7. Change Implementation");
            System.out.println("8. Exit");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add Producer logic
                    System.out.print("Producer name: ");
                    String producerName = scanner.nextLine();
                    Thread producer = new Thread(new Producer(ticketPool, producerName));
                    producers.add(producer);
                    break;
                case 2:
                    // Remove Producer logic
                    if (!producers.isEmpty()) {
                        Thread removedProducer = producers.remove(producers.size() - 1);
                        removedProducer.interrupt();
                        System.out.println("Producer removed.");
                    } else {
                        System.out.println("No producers to remove.");
                    }
                    break;
                case 3:
                    // Add Consumer logic
                    System.out.print("Consumer name: ");
                    String consumerName = scanner.nextLine();
                    Thread consumer = new Thread(new Consumer(ticketPool, consumerName));
                    consumers.add(consumer);
                    break;
                case 4:
                    // Remove Consumer logic
                    if (!consumers.isEmpty()) {
                        Thread removedConsumer = consumers.remove(consumers.size() - 1);
                        removedConsumer.interrupt();
                        System.out.println("Consumer removed.");
                    } else {
                        System.out.println("No consumers to remove.");
                    }
                    break;
                case 5:
                    // Print System Status
                    ticketPool.printStatus();
                    break;
                case 6:
                    // Start Threads if both producers and consumers are added
                    if (!producers.isEmpty() && !consumers.isEmpty()) {
                        System.out.println("Starting threads...");

                        // Start all producer threads
                        for (Thread p : producers) {
                            p.start();
                        }

                        // Start all consumer threads
                        for (Thread c : consumers) {
                            c.start();
                        }
                    } else {
                        System.out.println("Both producers and consumers must be added before starting the threads.");
                    }
                    break;
                case 7:
                    // Change Implementation
                    System.out.println("You will now change the implementation.");
                    break;
                case 8:
                    // Exit
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
