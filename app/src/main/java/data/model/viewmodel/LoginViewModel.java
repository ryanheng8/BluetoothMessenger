package data.model.viewmodel;

import androidx.lifecycle.ViewModel;

import data.DataRepository;
import data.model.User;

public class LoginViewModel extends ViewModel {
    public void createAnonymousUser(String id) {
        User user = new User();
        user.id = id;
        DataRepository.getInstance().addUser(user);
    }
}
