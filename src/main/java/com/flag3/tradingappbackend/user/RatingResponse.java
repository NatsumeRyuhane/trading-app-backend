package com.flag3.tradingappbackend.user;

public class RatingResponse {
    private double totolRating;
    private int RatingUserSize;

    public RatingResponse(double totolRating, int RatingUserSize) {
        this.totolRating = totolRating;
        this.RatingUserSize = RatingUserSize;
    }

    public double getTotolRating() {
        return totolRating;
    }

    public int getRatingUserSize() {
        return RatingUserSize;
    }
}
