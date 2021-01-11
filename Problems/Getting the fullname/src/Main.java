class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = "";
        }
    }

    public void setLastName(String lastName) {
        // write your code here
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        // write your code here
        if ("".equals(firstName) && "".equals(lastName)) {
            return "Unknown";
        } else if ("".equals(firstName)) {
            return lastName;
        } else if ("".equals(lastName)) {
            return firstName;
        } else {
            return firstName + " " + lastName;
        }
    }
}
