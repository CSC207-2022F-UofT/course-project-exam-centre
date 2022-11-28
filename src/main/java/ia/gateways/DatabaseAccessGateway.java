package ia.gateways;

import uc.state.update.UpdateStateDsGateway;
import uc.user.login.LoginDsGateway;
import uc.user.register.URegisterDsGateway;

public interface DatabaseAccessGateway
        extends LoginDsGateway,
        URegisterDsGateway {

    boolean getConnectionStatus();

}
