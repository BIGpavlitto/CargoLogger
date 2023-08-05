package models;
import java.util.Date;

public class Time {
    private int id;
    private Date timestamp; // Using java.util.Date to store the timestamp

    public Time() {
        // Default constructor
    }

    public Time(Date timestamp) {
        this.timestamp = timestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
