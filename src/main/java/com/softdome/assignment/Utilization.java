package com.softdome.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilization {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
