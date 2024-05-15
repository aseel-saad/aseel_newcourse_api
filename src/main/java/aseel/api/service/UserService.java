package aseel.api.service;

import aseel.api.model.User;
import aseel.api.model.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User readUser();

    User updateUser(UserModel user);

    void deleteUser();

    User getLoggedInUser();
}
