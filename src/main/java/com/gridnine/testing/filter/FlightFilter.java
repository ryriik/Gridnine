package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FlightFilter {

    private Filterable filter;

    public void setFilter(Filterable filter) {
        this.filter = filter;
    }

    public List<Flight> filter(List<Flight> flights) {
        return filter.filter(flights);
    }
}
