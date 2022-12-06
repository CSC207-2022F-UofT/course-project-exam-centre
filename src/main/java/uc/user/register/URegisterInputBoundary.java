package uc.user.register;

/** The URegisterInputBoundary provides an entry point from the Interface Adapters to the Use Case layer
 * for the register user use case.
 * @layer use cases
 */
public interface URegisterInputBoundary {
    /** Invoke register user use case.
     * @param requestModel a request model containing information of the user attempting to register
     * @return a RegisterResponseModel corresponding to the requestModel
     */
    URegisterResponseModel registerUser(URegisterRequestModel requestModel);

}
