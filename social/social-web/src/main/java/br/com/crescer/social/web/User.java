package br.com.crescer.social.web;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Carlos H. Nonnemacher
 */
public class User {
    
    @NotEmpty()
    private String firstName;
    
    @NotEmpty()
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
