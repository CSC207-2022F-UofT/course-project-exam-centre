package entities.factories;

import entities.CommonUser;
import entities.User;

public class UserFactory {

    /** Creates a new instance of User
     *
     * @param firstName First name of User
     * @param lastName Last name of User
     * @param email Email of User
     * @param userId User's generated ID
     * @return Returns a new instance of User
     */
    public User create(
            String firstName,
            String lastName,
            String email,
            String userId){
        return new CommonUser(firstName, lastName, email, userId);
    }
}
