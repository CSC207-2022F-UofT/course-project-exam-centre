package uc.user.login;

/**
 * Method defined in LoginInputBoundary is used by a Controller
 * as an entry-point to the login use case implemented by LoginInteractor.
 * @layer use cases
 */
public interface LoginInputBoundary {
    LoginResponseModel logIn (LoginRequestModel requestModel);
}
