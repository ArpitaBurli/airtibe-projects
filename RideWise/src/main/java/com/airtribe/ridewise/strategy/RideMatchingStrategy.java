package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    /** Returns the best driver from the supplied candidates, or null if none is suitable. */
    Driver findDriver(Rider rider, List<Driver> drivers);
}
