package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterByTimeOnLandTest {

    private List<Flight> flights;
    private final LocalDateTime now = LocalDateTime.now();
    private final FlightFilter filter = new FlightFilter();

    @Test
    public void testWhen1FlightWith1StopMoreThan2HoursOnLand() {
        filter.setFilter(new FilterByTimeOnLand(120));
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(3)),
                        new Segment(now.plusHours(5), now.plusHours(8)))
                )
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhen1FlightWith2StopsMoreThan2HoursOnLand() {
        filter.setFilter(new FilterByTimeOnLand(120));
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(3)),
                        new Segment(now.plusHours(4), now.plusHours(6)),
                        new Segment(now.plusHours(7).plusMinutes(30), now.plusHours(9)))
                )
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhen2FlightsMoreThan2HoursOnLand() {
        filter.setFilter(new FilterByTimeOnLand(120));
        flights = List.of(
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(3)),
                        new Segment(now.plusHours(5), now.plusHours(8)))
                ),
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(
                        new Segment(now.plusHours(3), now.plusHours(5)),
                        new Segment(now.plusHours(8), now.plusHours(9)))
                )
        );
        assertEquals(1, filter.filter(flights).size());
    }

    @Test
    public void testWhenAllFlightsMoreThan2HoursOnLand() {
        filter.setFilter(new FilterByTimeOnLand(120));
        flights = List.of(
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(3)),
                        new Segment(now.plusHours(5), now.plusHours(8)))
                ),
                new Flight(List.of(
                        new Segment(now.plusHours(2), now.plusHours(4)),
                        new Segment(now.plusHours(5), now.plusHours(7)),
                        new Segment(now.plusHours(8).plusMinutes(30), now.plusHours(10)))
                )
        );
        assertEquals(0, filter.filter(flights).size());
    }

    @Test
    public void testWhenNoFlightsMoreThan2HoursOnLand() {
        filter.setFilter(new FilterByTimeOnLand(120));
        flights = List.of(
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))),
                new Flight(List.of(
                        new Segment(now.plusHours(1), now.plusHours(4)),
                        new Segment(now.plusHours(5), now.plusHours(8)))
                )
        );
        assertEquals(2, filter.filter(flights).size());
    }
}
