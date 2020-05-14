package com.softdome.assignment;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.softdome.assignment.CapacityUtilization.getFreeCapacities;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtilizationCapacity {

    @Test
    public void TestExpectedCapacitiesCase1(){
        Capacity c1 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0)
        );
        Capacity c2 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 14, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 22, 0)
        );
        List<Capacity> capacities = new ArrayList<>(List.of(c1, c2));

        Utilization u1 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 8, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 9, 0)
        );
        Utilization u2 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 11, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 16, 0)
        );
        List<Utilization> utilizations = new ArrayList<>(List.of(u1, u2));

        List<Capacity> freeCapacities = getFreeCapacities(capacities, utilizations);
        List<Capacity> expectedCapacities = List.of(
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 8, 0)),
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 9, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 11, 0)),
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 16, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 22, 0))
        );
        assertEquals(freeCapacities, expectedCapacities);
    }

    @Test
    public void TestExpectedCapacitiesCase2(){
        Capacity c1 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0)
        );
        Capacity c2 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 14, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 22, 0)
        );
        List<Capacity> capacities = new ArrayList<>(List.of(c1, c2));

        Utilization u0 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 7, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 8, 0)
        );

        Utilization u1 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 9, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 10, 0)
        );
        Utilization u2 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 11, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 16, 0)
        );
        List<Utilization> utilizations = new ArrayList<>(List.of(u0, u1, u2));

        List<Capacity> freeCapacities = getFreeCapacities(capacities, utilizations);
        List<Capacity> expectedCapacities = List.of(
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 7, 0)),
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 8, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 9, 0)),
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 10, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 11, 0)),
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 16, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 22, 0))
        );
        assertEquals(freeCapacities, expectedCapacities);
    }

    @Test
    public void TestExpectedCapacitiesCase3(){
        Capacity c1 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0)
        );
        List<Capacity> capacities = new ArrayList<>(List.of(c1));

        Utilization u1 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 12, 0)
        );
        List<Utilization> utilizations = new ArrayList<>(List.of(u1));

        List<Capacity> freeCapacities = getFreeCapacities(capacities, utilizations);
        List<Capacity> expectedCapacities = List.of(
                new Capacity(
                        LocalDateTime.of(2019, Month.JANUARY, 1, 12, 0),
                        LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0))
        );
        assertEquals(freeCapacities, expectedCapacities);
    }

    @Test
    public void TestExpectedCapacitiesCase4(){
        Capacity c1 = new Capacity(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0)
        );
        List<Capacity> capacities = new ArrayList<>(List.of(c1));

        Utilization u1 = new Utilization(
                LocalDateTime.of(2019, Month.JANUARY, 1, 6, 0),
                LocalDateTime.of(2019, Month.JANUARY, 1, 13, 0)
        );
        List<Utilization> utilizations = new ArrayList<>(List.of(u1));

        List<Capacity> freeCapacities = getFreeCapacities(capacities, utilizations);
        List<Capacity> expectedCapacities = new ArrayList<>();
        assertEquals(freeCapacities, expectedCapacities);
    }

}
