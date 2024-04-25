package es.unex.pi.model;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public boolean validate(Map<String, String> messages){
        if(name.trim().isEmpty()||name==null) {
            messages.put("error", "Nombre vacío");
    } else if(!name.trim().matches("[A-Za-záéíóúñÁÉÍÓÚ]{2,}([\\s][A-Za-záéíóúñÁÉÍÓÚ]{2,})*")) {
            messages.put("error", "Nombre no válido: " + name.trim());
        }
        if(messages.isEmpty()) return true; 
        else return false;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
