package com.bridgelabz.assignment;


public class InvoiceGenerator {
    //constant variables
    private static final double MINIMUM_COST_PER_KM = 10.0;
    private static final int COST_PER_TIME = 1;
    private static final int MINIMUM_FARE = 5;
    private RideRepository rideRepository;

    //calculate the total fare
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    //calculating Fare for Multiple rides
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary calculateFare(Ride[] rides, String type) {
        double totalFare = 0;
        if (type == "premium") {
            for (Ride ride : rides) {
                totalFare += this.calculateFare(ride.distance, ride.time);
            }
            return new InvoiceSummary(rides.length, totalFare);
        }
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }


    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId), "normal");
    }

}