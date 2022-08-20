package com.bob.springbootsecurity.model;

import java.sql.Date;

public class NewBook {
    private Long bookId;
    private String title;
    private String author;
    private String callNumber;
    private String barcode;
    private Date exhFr;
    private Date exhEnd;
    private Date reviseDate;
    private short actYn;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getExhFr() {
        return exhFr;
    }

    public void setExhFr(Date exhFr) {
        this.exhFr = exhFr;
    }

    public Date getExhEnd() {
        return exhEnd;
    }

    public void setExhEnd(Date exhEnd) {
        this.exhEnd = exhEnd;
    }

    public Date getReviseDate() {
        return reviseDate;
    }

    public void setReviseDate(Date reviseDate) {
        this.reviseDate = reviseDate;
    }

    public short getActYn() {
        return actYn;
    }

    public void setActYn(short actYn) {
        this.actYn = actYn;
    }
}
