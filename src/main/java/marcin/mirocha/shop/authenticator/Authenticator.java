package marcin.mirocha.shop.authenticator;

import marcin.mirocha.shop.db.UserRepository;
import marcin.mirocha.shop.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class Authenticator {
    private final String seed = "hq_6#%r8rPJ)zoZaIvV6X@HqMVaz;C7wNM39Rl]{hCE!YF]8Xo";
    private final UserRepository userRepository = new UserRepository();
    public static String loggedUserRole;

    public boolean authenticator(String login, String password) {
        User user = this.userRepository.findByLogin(login);

        if (user != null &&
                user.getPassword().equals(DigestUtils.md5Hex(password + this.seed))) {
            loggedUserRole = user.getRole();
            return true;
        }
        return false;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
