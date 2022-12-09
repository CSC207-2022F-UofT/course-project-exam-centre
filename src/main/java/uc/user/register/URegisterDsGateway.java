package uc.user.register;

/** The URegisterDsGateway provides methods to compare information and save to persistent data
 * Would take in information in the form of a URegisterDsRequestModel
 * @layer use cases
 */
public interface URegisterDsGateway {
    /**
     * Method to check if the user's information exists in persistent data
     * @param email the email of the user attempting to register
     * @return returns a boolean if the user's email exists in persistent data
     */
    boolean checkIfUserExistsByEmail(String email);

    /**
     * Method to save the user's information to persistent data
     * @param requestModel the information of the user being saved
     * @return a unique userId that will be assigned to the user created
     */
    String saveUser(URegisterDsRequestModel requestModel);

    boolean getConnectionStatus();
}
