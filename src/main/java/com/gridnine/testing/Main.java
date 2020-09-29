package com.gridnine.testing;

import com.gridnine.testing.filter.FilterByArrivalBeforeDeparture;
import com.gridnine.testing.filter.FilterByDepartureBeforeNow;
import com.gridnine.testing.filter.FilterByTimeOnLand;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter flightFilter = new FlightFilter();

        System.out.println("Перелеты без вылетов после текущего момента времени:");
        flightFilter.setFilter(new FilterByDepartureBeforeNow());
        flightFilter.filter(flights).forEach(System.out::println);

        System.out.println("\nПерелеты без сегментов с датой прилёта раньше даты вылета:");
        flightFilter.setFilter(new FilterByArrivalBeforeDeparture());
        flightFilter.filter(flights).forEach(System.out::println);

        System.out.println("\nПерелеты, в которых общее время, проведённое на земле, не превышает два часа:");
        flightFilter.setFilter(new FilterByTimeOnLand(120));
        flightFilter.filter(flights).forEach(System.out::println);
    }
}
