package by.pvt.user;

//import by.pvt.pojo.AppUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/register")
//public class RegisterController {
//
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping
//    public String showRegisterPage() {
//        return "registerUser";
//    }
//
//    @PostMapping
//    public String registerForm(@ModelAttribute AppUser user) {
//        if (!userService.saveNewUser(user)){
//            return "error";
//        }
//        return "redirect:home";
//    }
//
//}
