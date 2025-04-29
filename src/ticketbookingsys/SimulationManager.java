package ticketbookingsys;

import java.util.Scanner;

public class SimulationManager {
    private TicketPool ticketPool;

    public SimulationManager() {
        this.ticketPool = new SynchronizedTicketPool(5); // Default implementation
    }

    public void configureSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Ticket Management System\n");
        System.out.println("--- System Configuration ---");
        System.out.println("Choose ticket pool implementation:");
        System.out.println("1. Synchronized methods");
        System.out.println("2. ReentrantReadWriteLock");
        System.out.println("3. BlockingQueue");
        System.out.print("Implementation (1-3): ");
        int choice = scanner.nextInt();
        System.out.print("Ticket pool capacity (5-9, 20): ");
        int capacity = scanner.nextInt();
        System.out.print("Thread pool size (e.g., 20): ");
        int threadPoolSize = scanner.nextInt();

        switch (choice) {
            case 1:
                ticketPool = new SynchronizedTicketPool(capacity);
                System.out.println("System configured with Synchronized implementation");
                break;
            case 2:
                ticketPool = new ReentrantLockTicketPool(capacity);
                System.out.println("System configured with ReentrantLock implementation");
                break;
            case 3:
                ticketPool = new BlockingQueueTicketPool(capacity);
                System.out.println("System configured with BlockingQueue implementation");
                break;
            default:
                System.out.println("Invalid choice! Using Synchronized methods by default.");
                ticketPool = new SynchronizedTicketPool(capacity);
        }

        System.out.println("System configured with capacity: " + capacity + " and thread pool size: " + threadPoolSize);
        startSimulation(threadPoolSize);
    }

    public void startSimulation(int threadPoolSize) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Ticket Management System ---");
            System.out.println("1. Add Producer");
            System.out.println("2. Remove Producer");
            System.out.println("3. Add Consumer");
            System.out.println("4. Remove Consumer");
            System.out.println("5. Add Reader");
            System.out.println("6. Remove Reader");
            System.out.println("7. View Ticket Pool Status");
            System.out.println("8. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            handleUserChoice(choice);
        }
    }

    private void handleUserChoice(int choice) {
        // Add or remove threads based on user's choices
        switch (choice) {
            case 1: // Add Producer
                addProducer();
                break;
            case 2: // Remove Producer
                // Implement producer removal logic (for simplicity, skipping for now)
                break;
            case 3: // Add Consumer
                addConsumer();
                break;
            case 4: // Remove Consumer
                // Implement consumer removal logic (for simplicity, skipping for now)
                break;
            case 5: // Add Reader
                addReader();
                break;
            case 6: // Remove Reader
                // Implement reader removal logic (for simplicity, skipping for now)
                break;
            case 7: // View Ticket Pool Status
                ticketPool.printStatus();
                break;
            case 8:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void addProducer() {
        new Thread(new Producer(ticketPool, "Producer")).start();
    }

    private void addConsumer() {
        new Thread(new Consumer(ticketPool)).start();
    }

    private void addReader() {
        new Thread(new Reader(ticketPool)).start();
    }

    public static void main(String[] args) {
        SimulationManager simulationManager = new SimulationManager();
        simulationManager.configureSystem();
    }
}