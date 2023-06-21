package threads.ticketbooking;

import threads.ticketbooking.domain.Event;

import java.util.concurrent.CountDownLatch;

public class TestCases {
    static void thousandThreads100Seats() throws InterruptedException {
        Event event = new Event(10, 10);
        int numThreads = 1000;
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            //using Runnable more readable than lambda for me
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    event.bookSeat(Thread.currentThread().getName(), (int) (Math.random() * 100));
                    latch.countDown();
                }
            });
            t.start();


        }
        latch.await();
        printStats("================ thousandThreads100Seats Statistics =================", event,
                   "=====================================================================");
    }

    static void seatPurchasedAlready() throws InterruptedException {
        Event event = new Event(10, 10);
        int seatId = 1;
        CountDownLatch latch = new CountDownLatch(2);


        new Thread(() -> {
            event.bookSeat(Thread.currentThread()
                                   .getName(), seatId);
            latch.countDown();
        }).start();

        new Thread(() -> {
            event.bookSeat(Thread.currentThread()
                                   .getName(), seatId);
            latch.countDown();
        }).start();

        latch.await();
        printStats("================= seatPurchasedAlready Statistics =================", event,
                   "===================================================================");
    }

    static void eventSoldOut() throws InterruptedException {
        Event event = new Event(10, 10);
        event.setSoldOut(true);

        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                event.bookSeat(Thread.currentThread()
                                       .getName(), (int) (Math.random() * 100));
                latch.countDown();
            }).start();
        }
        latch.await();
        printStats("====================== eventSoldOut Statistics ======================", event,
                   "=====================================================================");
    }

    private static void printStats(String top, Event event, String bottom) {
        System.out.println();
        System.out.println(top);
        System.out.println(event.getStatistics());
        System.out.println(bottom);
        System.out.println("\n\n\n\n");
    }

}
