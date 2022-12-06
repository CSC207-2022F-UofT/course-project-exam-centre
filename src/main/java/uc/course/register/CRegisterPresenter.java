package uc.course.register;

public interface CRegisterPresenter {
    /**
     * Prepares a success screen
     * @param course CRegisterResponseModel containing the information of the new course
     */
    CRegisterResponseModel prepareSuccessView(CRegisterResponseModel course);

    /**
     * Prepares a failure view
     * @param error A string of the error that has occurred
     * @throws // TODO: Some exception
     */
    CRegisterResponseModel prepareFailView(String error);
}
