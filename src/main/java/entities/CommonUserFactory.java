package entities;

public class CommonUserFactory implements UserFactory {

    /** Creates a new instance of CommonUser
     *
     * @param firstName First name of User
     * @param lastName Last name of User
     * @param email Email of User
     * @param userId User's generated ID
     * @return Returns a new instance of CommonUser
     */
    @Override
    public CommonUser create(String firstName, String lastName, String email, int userId){
        return new CommonUser(firstName, lastName, email, userId);
    }
}
