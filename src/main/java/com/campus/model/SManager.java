package com.campus.model;

public class SManager {

    private int eventId;
    private String name;
    private String eventDate;
    private String venue;
    private int capacity;

    public SManager() {}

    public SManager(int eventId, String name, String eventDate, String venue, int capacity) {
        this.eventId = eventId;
        this.name = name;
        this.eventDate = eventDate;
        this.venue = venue;
        this.capacity = capacity;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", name=" + name + ", eventDate=" + eventDate
                + ", venue=" + venue + ", capacity=" + capacity + "]";
    }
}
