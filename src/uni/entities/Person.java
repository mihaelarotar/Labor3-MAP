package uni.entities;

public abstract class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        validate();
    }

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

    /**
     * data validator
     * checks if the firstName and lastName strings are null
     * @throws IllegalArgumentException if a string is empty
     */
    private void validate() {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
