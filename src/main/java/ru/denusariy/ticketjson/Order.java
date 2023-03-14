package ru.denusariy.ticketjson;

import lombok.*;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private ArrayList<Ticket> tickets;
}
