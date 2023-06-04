package threads.ticketbooking.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Event {
    private final Map<Integer,Seat> seats;
    private int seatsSold;
    private int seatsAvailable;
    private int failedAttempts;
    private int successfulAttempts;
    private boolean soldOut = false;

    public Event() {
        this(10, 10);
    }

    public Event(int rows, int cols) {
        this.seatsSold = 0;
        this.seatsAvailable = rows * cols;
        this.failedAttempts = 0;
        this.successfulAttempts = 0;

        // ConcurrentHashMap had better performance than HashMap in this case.
        seats = new ConcurrentHashMap<>();
        int id = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats.put(id++,new Seat(id, i, j));
            }
        }
    }


    public void bookSeat(String userName, int id) {
        if (this.soldOut) {
            failedAttempts++;
            String msg = "Sorry, the event is sold out.";
            System.out.println(msg);
            return;
        }

        Seat seat = seats.get(id);
        /*
        Synchronizing on the seat means only one thread can access the seat object at a time.
        But multiple threads can access different seat objects at the same time.
         */
        synchronized (seat) {
            if (!seat.isSold()) {
                System.out.println(userName + " booked seat " + seat.getId()
                                           + " at position [" + seat.getRowNumber()
                                           + "," + seat.getColNumber()+"]");
                seat.setSold(true);
                seatsAvailable--;
                seatsSold++;
                successfulAttempts++;
            } else {
                System.out.println(userName + " could not book seat " + seat.getId()
                                           + " at position [" + seat.getRowNumber()
                                           + "," + seat.getColNumber()+"]");
            failedAttempts++;
            }
        }
    }


    public EventStatistics getStatistics(){
        return new EventStatistics(seatsSold, seatsAvailable, successfulAttempts, failedAttempts);
    }

    public void setSoldOut(boolean b) {
        this.soldOut=b;
    }

    private static class EventStatistics {
        private final int seatsSold;
        private final int seatsAvailable;
        private final int successfulPurchaseAttempts;
        private final int failedPurchaseAttempts;

        public EventStatistics(int seatsSold, int seatsAvailable, int successfulPurchaseAttempts, int failedPurchaseAttempts) {
            this.seatsSold = seatsSold;
            this.seatsAvailable = seatsAvailable;
            this.successfulPurchaseAttempts = successfulPurchaseAttempts;
            this.failedPurchaseAttempts = failedPurchaseAttempts;
        }

        public int getSuccessfulPurchaseAttempts() {
            return successfulPurchaseAttempts;
        }

        public int getFailedPurchaseAttempts() {
            return failedPurchaseAttempts;
        }

        public int getSeatsSold() {
            return seatsSold;
        }

        public int getSeatsAvailable() {
            return seatsAvailable;
        }


        @Override
        public String toString() {
            return "EventStatistics{" + "seatsSold=" + seatsSold + ", seatsAvailable=" + seatsAvailable + ", \n\t\t\t\tsuccessfulPurchaseAttempts=" + successfulPurchaseAttempts + ", failedPurchaseAttempts=" + failedPurchaseAttempts + '}';
        }
    }
}
