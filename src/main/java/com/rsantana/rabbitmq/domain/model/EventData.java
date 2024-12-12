package com.rsantana.rabbitmq.domain.model;

public class EventData {
    private String eventType;
    private String oldAddress;
    private String newAddress;

    // Construtor vazio (necessário para deserialização do Jackson)
    public EventData() {}

    // Construtor completo
    public EventData(String eventType, String oldAddress, String newAddress) {
        this.eventType = eventType;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
    }

    // Getters e Setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "eventType='" + eventType + '\'' +
                ", oldAddress='" + oldAddress + '\'' +
                ", newAddress='" + newAddress + '\'' +
                '}';
    }
}

