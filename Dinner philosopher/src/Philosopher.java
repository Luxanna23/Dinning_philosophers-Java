import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private long startTime;
    private int missedMeals;

    private static final int max_time_without_eating = 2;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.startTime = System.currentTimeMillis();
        this.missedMeals = 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                pickUpForks();
                eat();
                missedMeals = 0;
                putDownForks();
                sleep();
                missedMeals++;
                if (missedMeals > max_time_without_eating) {
                    die();
                    return;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        log("is thinking");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        log("is eating");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void pickUpForks() throws InterruptedException {
        leftFork.acquire();
        log("has taken a fork");
        rightFork.acquire();
        log("has taken a fork");
    }

    private void putDownForks() {
        leftFork.release();
        rightFork.release();
    }

    private void sleep() throws InterruptedException {
        log("is sleeping");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void die() {
        log("has died.");
    }

    private void log(String action) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " " + id + " " + action);
    }
}