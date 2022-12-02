package uc.user.login;

/**
 * Methods defined in LoginDsResponseModel are used by LoginInteractor
 * to access persistent data.
 * @layer use cases
 */
public interface LoginDsResponseModel {
    String getUserId();
    String getFirstName();
    String getLastName();
    String getEmail();
}
