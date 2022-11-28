package usecases.course.register;

public interface CRegisterPresenter {
    CRegisterResponseModel prepareSuccessView(CRegisterResponseModel course);
    CRegisterResponseModel prepareFailView(String error);
}
