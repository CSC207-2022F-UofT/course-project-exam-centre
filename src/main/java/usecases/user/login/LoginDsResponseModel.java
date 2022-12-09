package usecases.user.login;

/**
 * LoginDsResponseModel represents a bundle of persistent user data.
 * @layer use cases
 */
public interface LoginDsResponseModel {
    String getUserId();
    String getEmail();
    String getFirstName();
    String getLastName();
}
