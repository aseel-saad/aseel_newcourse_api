package aseel.api.service;

import aseel.api.model.User;
import aseel.api.model.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User readUser(Long id);

    User updateUser(UserModel user, Long id);

    void deleteUser(Long id);

    User getLoggedInUser();
}
