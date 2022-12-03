package uc.user.login;

/**
 * LoginInputBoundary provides an entry-point to the login use case.
 * Used by a Controller and implemented by LoginInteractor.
 * @layer use cases
 */
public interface LoginInputBoundary {
    /** Invoke login use case.
     * @param requestModel
     * @return a LoginResponseModel corresponding to the requestModel
    LoginResponseModel logIn(LoginRequestModel requestModel);
}
