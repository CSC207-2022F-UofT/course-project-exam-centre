package uc.course.register;

/** The CRegisterOutputBoundary provides methods for the CourseRegisterInteractor to output data.
 * @layer use cases
 */
public interface CRegisterOutputBoundary {
    CRegisterResponseModel prepareSuccessView (CRegisterResponseModel responseModel);
    CRegisterResponseModel prepareFailView (String errorMessage);

}
