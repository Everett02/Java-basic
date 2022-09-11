package test;

public class User {
    private String username;
    private String password;
    private String registerID;
    private String phoneNumber;


    public User() {
    }

    public User(String username, String password, String invitationID, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.registerID = invitationID;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return invitationID
     */
    public String getRegisterID() {
        return registerID;
    }

    /**
     * @param invitationID
     */
    public void setRegisterID(String invitationID) {
        this.registerID = invitationID;
    }

    /**
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", invitationID = " + registerID + ", phoneNumber = " + phoneNumber + "}";
    }
}
