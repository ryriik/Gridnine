package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterByArrivalBeforeDepartureTest {

    private List<Flight> flights;
    private final LocalDateTime now = LocalDateTime.now();
    private final FlightFilter filter = new FlightFilter();

    @Test
    public void testWhen1ArrivalBeforeDeparture() {
        filter.setFilter(new FilterByArrivalBeforeDeparture());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(4), now.plusHours(2)))),
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4))))
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhen2ArrivalsBeforeDeparture() {
        filter.setFilter(new FilterByArrivalBeforeDeparture());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(new Segment(now.plusHours(3), now.plusHours(1)))),
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(3)),
                        new Segment(now.plusHours(6), now.plusHours(3)))
                )
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhenAllArrivalsBeforeDeparture() {
        filter.setFilter(new FilterByArrivalBeforeDeparture());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(4), now.plusHours(2)))),
                new Flight(List.of(new Segment(now.plusHours(6), now.plusHours(4))))
        );
        assertEquals(0, filter.filter(flights).size());
    }

    @Test
    public void testWhenNoArrivalsBeforeDeparture() {
        filter.setFilter(new FilterByArrivalBeforeDeparture());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(new Segment(now.plusHours(4), now.plusHours(6))))
        );
        assertEquals(2, filter.filter(flights).size());
    }
}
