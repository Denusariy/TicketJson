package ru.denusariy.ticketjson;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String origin;
    @JsonSetter("origin_name")
    private String originName;
    private String destination;
    @JsonSetter("destination_name")
    private String destinationName;
    @JsonSetter("departure_date")
    private String departureDate;
    @JsonSetter("departure_time")
    private String departureTime;
    @JsonSetter("arrival_date")
    private String arrivalDate;
    @JsonSetter("arrival_time")
    private String arrivalTime;
    private String carrier;
    private int stops;
    private int price;

    public Date getDepartureDateTime() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yy HH:mm");
        try {
            return format.parse(this.getDepartureDate() + " " + this.getDepartureTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Date getArrivalDateTime() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yy HH:mm");
        try {
            return format.parse(this.getArrivalDate() + " " + this.getArrivalTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
