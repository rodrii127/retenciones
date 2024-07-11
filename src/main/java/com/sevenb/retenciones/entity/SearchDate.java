package com.sevenb.retenciones.entity;

import java.time.LocalDate;

/**
 * Entity used for searching between specific dates.
 */
public class SearchDate {

    /*TODO add validation of specific date format*/
    LocalDate startDate;
    LocalDate endDate;

    public SearchDate() {
        //No-args constructor
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{\"SearchDate\":{"
            + "\"startDate\":" + startDate
            + ", \"endDate\":" + endDate
            + "}}";
    }
}
