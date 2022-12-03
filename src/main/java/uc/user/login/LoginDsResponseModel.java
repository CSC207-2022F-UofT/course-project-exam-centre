package uc.user.login;

/**
 * LoginDsResponseModel represents a bundle of persistent user data.
 * @layer use cases
 */
public interface LoginDsResponseModel {
    String getUserId();
    String getFirstName();
    String getLastName();
    String getEmail();
}
