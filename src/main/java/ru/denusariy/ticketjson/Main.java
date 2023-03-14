package ru.denusariy.ticketjson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String PATH = "c:/java/tickets.json";

    public static void main(String[] args) {
        List<Ticket> tickets = getTicketsFromFile();
        System.out.println("Среднее время полета из пункта Владивосток в пункт Тель-Авив составляет " +
                averageTime(tickets) + " часов.");
        System.out.println("90-й процентиль времени полета между городами Владивосток и Тель-Авив составляет "
                + percentile(tickets) + " часов");

    }

    public static List<Ticket> getTicketsFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Order order = mapper.readValue(new File(PATH), Order.class);
            return order.getTickets();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static double averageTime(List<Ticket> tickets) {
        return tickets.stream()
                .filter(t -> t.getOriginName().equals("Владивосток") && t.getDestinationName().equals("Тель-Авив"))
                .mapToLong(t -> (t.getArrivalDateTime().getTime() - t.getDepartureDateTime().getTime()) / 3600000)
                .average().orElse(0);
    }

    public static int percentile(List<Ticket> tickets) {
        List<Integer> list = tickets.stream()
                .filter(t -> t.getOriginName().equals("Владивосток") && t.getDestinationName().equals("Тель-Авив"))
                .map(t -> (int)(t.getArrivalDateTime().getTime() - t.getDepartureDateTime().getTime()) / 3600000)
                .sorted()
                .collect(Collectors.toList());
        int index = (int) Math.ceil(0.9 * list.size());
        return list.get(index - 1);
    }
}
