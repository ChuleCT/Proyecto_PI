package es.unex.pi.model;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Property {

    private long id;
    private String name;
    private String address;
    private String telephone;
    private double gradesAverage;
    private String city;
    private double centerDistance;
    private String description;
    private int petFriendly;
    private int available;
    private long idu;

    public boolean validate(Map<String, String> messages) {
        if (name.trim().isEmpty() || name == null) {
            messages.put("error", "Nombre vacío");
        } else if (!name.trim().matches("[A-Za-záéíóúñÁÉÍÓÚ]{2,}([\\s][A-Za-záéíóúñÁÉÍÓÚ]{2,})*")) {
            messages.put("error", "Nombre no válido: " + name.trim());
        }
        if (messages.isEmpty())
        return true;
        else
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getGradesAverage() {
        return gradesAverage;
    }

    public void setGradesAverage(double gradesAverage) {
        this.gradesAverage = gradesAverage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    public double getCenterDistance() {
        return centerDistance;
    }

    public void setCenterDistance(double centerDistance) {
        this.centerDistance = centerDistance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(int petFriendly) {
        this.petFriendly = petFriendly;
    }

}
