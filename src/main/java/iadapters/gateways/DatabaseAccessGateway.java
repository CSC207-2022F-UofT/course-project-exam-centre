package iadapters.gateways;

import usecases.user.login.LoginDsGateway;
import usecases.user.register.URegisterDsGateway;

public interface DatabaseAccessGateway {

    boolean getConnectionStatus();

}
