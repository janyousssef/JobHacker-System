package threads.oddeven;

public class NumberPrinter implements Runnable{
    private final int max;
    private final String threadName;
    private int number;

    public NumberPrinter(int number, int max, String threadName) {
        this.number = number;
        this.max = max;
        this.threadName = threadName;
    }

    @Override
    synchronized public void run() {
        if (number <= max) {
            System.out.println(threadName + ": " + number);
            number+=2;
        }
    }
}
