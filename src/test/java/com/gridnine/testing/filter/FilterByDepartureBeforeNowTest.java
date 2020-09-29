package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterByDepartureBeforeNowTest {

    private List<Flight> flights;
    private final LocalDateTime now = LocalDateTime.now();
    private final FlightFilter filter = new FlightFilter();

    @Test
    public void testWhen1DepartureBeforeNow() {
        filter.setFilter(new FilterByDepartureBeforeNow());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(new Segment(now.minusHours(2), now)))
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhen2DeparturesBeforeNow() {
        filter.setFilter(new FilterByDepartureBeforeNow());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(new Segment(now.minusHours(2), now))),
                new Flight(List.of(
                        new Segment(now.plusHours(2), now.plusHours(4)),
                        new Segment(now.minusHours(3), now.minusHours(1)))
                )
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhenAllDeparturesBeforeNow() {
        filter.setFilter(new FilterByDepartureBeforeNow());
        flights = List.of(
                new Flight(List.of(new Segment(now.minusHours(3), now.plusHours(1)))),
                new Flight(List.of(new Segment(now.minusHours(4), now)))
        );
        assertEquals(0, filter.filter(flights).size());
    }

    @Test
    public void testWhenNoDeparturesBeforeNow() {
        filter.setFilter(new FilterByDepartureBeforeNow());
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(1), now.plusHours(5)))),
                new Flight(List.of(new Segment(now.plusHours(3), now.plusHours(5))))
        );
        assertEquals(2, filter.filter(flights).size());
    }
}
