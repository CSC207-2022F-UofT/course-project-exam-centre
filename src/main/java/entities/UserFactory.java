package entities;

public interface UserFactory {

    User create(String firstName, String lastName, String email, int userId);

}
