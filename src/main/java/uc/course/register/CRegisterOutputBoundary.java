package uc.course.register;

/** The CRegisterOutputBoundary provides methods for the CourseRegisterInteractor to output data.
 * @layer use cases
 */
public interface CRegisterOutputBoundary {
    /**
     * Prepares a success screen
     * @param responseModel CRegisterResponseModel containing the information of the new course
     */
    CRegisterResponseModel prepareSuccessView (CRegisterResponseModel responseModel);
    /**
     * Prepares a failure view
     * @param errorMessage A string of the error that has occurred
     * @throws ia.exceptions.CourseRegisterFailed exception
     */
    CRegisterResponseModel prepareFailView (String errorMessage);

}
