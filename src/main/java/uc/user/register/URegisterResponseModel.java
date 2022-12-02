package uc.user.register;

import entities.User;

public class URegisterResponseModel {
    User user;
    Boolean loginStatus;
    String timestamp;

    /** Creates a URegisterResponseModel with the login and the creationTime it took to create the User
     *
     * @param user the User corresponding to the URegisterResponseModel
     * @param loginStatus the login details of the User
     * @param timestamp how long it took to create the User
     */
    public URegisterResponseModel(User user, Boolean loginStatus, String timestamp) {
        this.user = user;
        this.loginStatus = loginStatus;
        this.timestamp = timestamp;
    }

    /** Get the login of the User
     *
     * @return returns the login of the User
     */
    public Boolean getLoginStatus(){
        return loginStatus;
    }

    /** Get the time it took to create the User
     *
     * @return returns the time it took to create the User
     */
    public String getTimestamp(){
        return timestamp;
    }

    /** Sets how long it took to create the User
     *
     * @param timestamp the time it took to create the user
     */
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
}
