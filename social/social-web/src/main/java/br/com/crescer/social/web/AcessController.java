    package br.com.crescer.social.web;

import br.com.crescer.social.entity.Time;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.TimeService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Carlos H. Nonnemacher
 */
@Controller
public class AcessController {
    
    @Autowired
    TimeService timeService;
    
    @RequestMapping("/login")
    String login(Model m) {
        adcRegistros();
        return "login"; 
    } 
    
    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    } 
    
    @RequestMapping(value="/add")
    String add(Model m) {
        return "home";
    }
    
    private void adcRegistros(){
        Time time = new Time();
        time.setNome("Internacional");
        
        Time time2 = new Time();
        time2.setNome("Grêmio");
        
        Time time3 = new Time();
        time3.setNome("Brasil de Pelotas");
        
        Time time4 = new Time();
        time4.setNome("Juventude");
        
        Time time5 = new Time();
        time5.setNome("Chapecoensce");
        
        timeService.save(time);
        timeService.save(time2);
        timeService.save(time3);
        timeService.save(time4);
        timeService.save(time5);
        
    }
}
