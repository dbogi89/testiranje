package com.bigjava.service.algorithm;

/**
 * Created by dbogicevic
 */
public class ResponseFlight {
    private String path;
    private Double price;

    public ResponseFlight() {

    }

    public ResponseFlight(String path, Double price) {
        this.path = path;
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
