package uc.user.register;

public class URegisterResponseModel {
    String login;
    String creationTime;

    /** Creates a URegisterResponseModel with the login and the creationTime it took to create the User
     *
     * @param login the login details of the User
     * @param creationTime how long it took to create the User
     */
    public URegisterResponseModel(String login, String creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    /** Get the login of the User
     *
     * @return returns the login of the User
     */
    public String getLogin(){
        return login;
    }

    /** Get the time it took to create the User
     *
     * @return returns the time it took to create the User
     */
    public String getCreationTime(){
        return creationTime;
    }

    /** Sets how long it took to create the User
     *
     * @param creationTime the time it took to create the user
     */
    public void setCreationTime(String creationTime){
        this.creationTime = creationTime;
    }
}
