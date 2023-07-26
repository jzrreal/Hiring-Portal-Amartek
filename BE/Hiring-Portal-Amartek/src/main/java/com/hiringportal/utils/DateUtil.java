package com.hiringportal.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Integer getAge(Date DOB) {
        LocalDate localDateDOB = Instant.ofEpochMilli(DOB.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(localDateDOB, LocalDate.now()).getYears();
    }
}
