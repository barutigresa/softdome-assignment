package com.softdome.assignment;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CapacityUtilization {

    public static List<Capacity> getFreeCapacities(List<Capacity> capacities, final List<Utilization> utilizations) {
        List<Capacity> allFreeCapacities = new ArrayList<>();
        capacities.forEach(capacity -> {
            List<Capacity> freeCapacities = getCapacities(List.of(capacity), utilizations);
            allFreeCapacities.addAll(freeCapacities);
        });

        return allFreeCapacities;
    }

    public static List<Capacity> getCapacities(List<Capacity> capacities, List<Utilization> utilizations) {
        List<Capacity> freeCapacities = new ArrayList<>();
        capacities.forEach(capacity -> {
            AtomicBoolean addCurrentCapacity = new AtomicBoolean(true);
            utilizations.forEach(utilization -> {
                if (utilization.getStartDate().isAfter(capacity.getStartDate())
                        && utilization.getStartDate().isBefore(capacity.getEndDate())) {
                    // if utilization end date is before capacity end date
                    if (utilization.getEndDate().isBefore(capacity.getEndDate())) {
                        List<Capacity> free = getCapacities(List.of(
                                new Capacity(capacity.getStartDate(), utilization.getStartDate()),
                                new Capacity(utilization.getEndDate(), capacity.getEndDate())
                                ), utilizations);
                        if(addCurrentCapacity.get()){
                            freeCapacities.addAll(free);
                            addCurrentCapacity.set(false);
                        }
                    } else {
                        capacity.setEndDate(utilization.getStartDate());
                    }
                //ELSE IF utilization end date is between capacity start and end date
                } else if (utilization.getStartDate().isEqual(capacity.getStartDate())
                        && utilization.getEndDate().isEqual(capacity.getEndDate())) {
                    addCurrentCapacity.set(false);
                } else if (utilization.getEndDate().isAfter(capacity.getStartDate())
                        && utilization.getEndDate().isBefore(capacity.getEndDate())) {
                    capacity.setStartDate(utilization.getEndDate());
                }
            });
            if (addCurrentCapacity.get()) {
                freeCapacities.add(capacity);
            }
        });

        return freeCapacities;
    }

    public static List<Capacity> getCapacities2(List<Capacity> capacities, List<Utilization> utilizations) {
        List<Capacity> freeCapacities = new ArrayList<>();
        capacities.forEach(capacity -> {
            AtomicBoolean addCurrentCapacity = new AtomicBoolean(true);
            utilizations.forEach(utilization -> {
                // if start date is between capacity
                if (utilization.getStartDate().isAfter(capacity.getStartDate())
                        && utilization.getStartDate().isBefore(capacity.getEndDate())) {
                    // 1) if end date is before capacity end date
                    if (utilization.getEndDate().isBefore(capacity.getEndDate())) {
                        List<Capacity> free = getCapacities2(List.of(
                                new Capacity(capacity.getStartDate(), utilization.getStartDate()),
                                new Capacity(utilization.getEndDate(), capacity.getEndDate())
                        ), utilizations);
                        if(addCurrentCapacity.get()){
                            freeCapacities.addAll(free);
                            addCurrentCapacity.set(false);
                        }
                    // 2) else end date is equal or after capacity end date
                    } else {
                        capacity.setEndDate(utilization.getStartDate());
                    }
                //ELSE IF start date is equal or before capacity AND
                // 1) utilization end date is between capacity start and end date
                } else if (utilization.getEndDate().isAfter(capacity.getStartDate())
                        && utilization.getEndDate().isBefore(capacity.getEndDate())) {
                    capacity.setStartDate(utilization.getEndDate());
                // 2) else end date is equal or after capacity end date
                } else {
                    addCurrentCapacity.set(false);
                }
            });
            if (addCurrentCapacity.get()) {
                freeCapacities.add(capacity);
            }
        });

        return freeCapacities;
    }
}
