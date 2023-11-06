package com.company.mvccontroller;

import com.company.dto.request.LoginRequestDto;
import com.company.dto.request.RegisterRequestDto;
import com.company.mvccontroller.models.WelcomeModel;
import com.company.repository.entity.User;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import static com.company.constants.EndPoints.*;

@Controller
@RequestMapping(MVCUSER)
@RequiredArgsConstructor
public class UserMvcController {
    private final UserService userService;

    @GetMapping(LOGIN)
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();

        model.setViewName("login");
        model.addObject("title","Giriş Sayfası");
        model.addObject("error","");
        return model;
    }

    @PostMapping(LOGIN)
    public ModelAndView login(LoginRequestDto dto){
        ModelAndView model = new ModelAndView();
        Optional<User> user = userService.findOptionalByUsernameAndPassword(dto);

        if(user.isEmpty()){
            model.setViewName("login");
            model.addObject("error","Kullanıcı adı ya da şifre hatalıdır.");
            return model;
        }else{
            return new ModelAndView("redirect:/mvc/user/welcome");
        }
    }

    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView model = new ModelAndView();
        model.setViewName("userRegister");
        model.addObject("error","");
        return model;
    }

    @PostMapping(REGISTER)
    public ModelAndView register(RegisterRequestDto dto){
        ModelAndView model = new ModelAndView();
        /**
         * Eğer kullanıcı mevcut ise uyarı bildirimi yap.
         */
        if(userService.existsUserByUsername(dto.getUsername())){
            model.setViewName("userRegister");
            model.addObject("error",
                    dto.getUsername()+" kullanıcı adı data önce başkası tarafından alınmıştır.");
        }else{
            userService.register(dto);
            return new ModelAndView("redirect:login");
        }
        return model;
    }

    @GetMapping(WELCOMESCREEN)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");

        WelcomeModel model = new WelcomeModel();
        model.setTitle("Welcome our Company-Employee Relation Project");
        HashMap<String, String> menuMapAdd = new HashMap<String, String>();
        menuMapAdd.put("Company List", "/mvc/company/findall");
        menuMapAdd.put("Company Register", "/mvc/company/register");
        menuMapAdd.put("Employee List", "/mvc/employee/findall");
        menuMapAdd.put("Employee Register", "/mvc/employee/register");


        model.setMenuMap(menuMapAdd);
        modelAndView.addObject("model", model);

        return modelAndView;
    }
}
