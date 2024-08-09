import java.util.concurrent.Semaphore;

public class Dinner {
    private static final int nb_philosopher = 5;

    public void startSimulation() {
        Semaphore[] forks = new Semaphore[nb_philosopher];
        for (int i = 0; i < nb_philosopher; i++) {
            forks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[nb_philosopher];
        for (int i = 0; i < nb_philosopher; i++) {
            Semaphore leftFork = forks[i];
            Semaphore rightFork = forks[(i + 1) % nb_philosopher];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
            new Thread(philosophers[i], "Philosopher " + i).start();
        }
    }
}