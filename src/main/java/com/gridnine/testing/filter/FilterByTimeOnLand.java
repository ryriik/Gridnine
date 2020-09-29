package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class FilterByTimeOnLand implements Filterable {

    private final int minutes;

    public FilterByTimeOnLand(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() == 1) return true;
                    long totalTime = MINUTES.between(
                            segments.get(0).getDepartureDate(),
                            segments.get(segments.size() - 1).getArrivalDate()
                    );
                    long flightTime = segments.stream()
                            .mapToLong(segment ->
                                    MINUTES.between(segment.getDepartureDate(), segment.getArrivalDate()))
                            .sum();
                    return totalTime - flightTime < minutes;
                })
                .collect(Collectors.toList());
    }
}
