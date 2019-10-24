package by.pvt.user;

//import by.pvt.pojo.AppUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    AppUserRepository appUserRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public boolean saveNewUser(AppUser user) {
//        if (user == null || user.getName().isEmpty() || user.getUsername().isEmpty() ||
//                user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
//            return false;
//        }
//        Integer userCountByLogin = appUserRepository.findUserCountByLogin(user.getUsername());
//        if (userCountByLogin != null && userCountByLogin >= 1) {
//            return false;
//        }
//        String encoderPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encoderPassword);
//        appUserRepository.save(user);
//        return true;
//    }
//}
