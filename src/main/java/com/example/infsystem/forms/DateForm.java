package com.example.infsystem.forms;

import java.sql.Date;

public class DateForm {
    private Date begin;

    private Date end;

    public DateForm() {
    }

    public DateForm(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
