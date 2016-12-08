package br.com.crescer.social.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Carlos H. Nonnemacher
 */
@Controller
public class AcessController {

    @RequestMapping("/login")
    String login(User user) {
        return "login"; 
    } 
    
    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    } 
    
    @RequestMapping(value="/add", method = POST)
    String add(@Valid User user, BindingResult bindingResult) {
        return "login";
    } 
    
    
}
