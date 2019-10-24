package by.pvt.service;

import by.pvt.pojo.AppRole;
import by.pvt.pojo.AppUser;
import by.pvt.pojo.RoleName;
import by.pvt.repository.AppRoleRepository;
import by.pvt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserService {

    private static Logger log = Logger.getLogger("UserService");

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean saveUser(AppUser user) {
        log.info("Saving user=" + user);
        if (user == null || user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||
                user.getEmail().isEmpty() || user.getPassword().isEmpty() ||
                userRepository.findUserByEmail(user.getEmail()) != null) {
            return false;
        }
        AppRole userRole = roleRepository.findByRoleName(RoleName.USER);
        if (userRole == null) {
            userRole = new AppRole();
            userRole.setRoleName(RoleName.USER);
            roleRepository.save(userRole);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        log.info("Encoded password: " + encodedPassword);
        user.setPassword(encodedPassword);
        user.setAppRoles(Set.of(userRole));
        userRepository.save(user);
        return true;
    }

}
