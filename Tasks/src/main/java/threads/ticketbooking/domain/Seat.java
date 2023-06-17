package threads.ticketbooking.domain;

import java.util.Objects;

public class Seat {
    private final int id;
    private final int rowNumber;
    private final int colNumber;
    private boolean isSold;

    public Seat(int id, int rowNumber, int colNumber) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.isSold = false;
    }

    public int getId() {
        return id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id && rowNumber == seat.rowNumber && colNumber == seat.colNumber && isSold == seat.isSold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowNumber, colNumber, isSold);
    }

    @Override
    public String toString() {
        return "Seat{" + "id=" + id + ", rowNumber=" + rowNumber + ", colNumber=" + colNumber + ", isBooked=" + isSold + '}';
    }
}
