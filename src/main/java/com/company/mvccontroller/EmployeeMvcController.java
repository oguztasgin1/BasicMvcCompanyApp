package com.company.mvccontroller;

import com.company.dto.request.LoginRequestDto;
import com.company.dto.request.RegisterEmployeeRequestDto;
import com.company.dto.request.RequestCompanyDto;
import com.company.dto.request.RequestUpdateEmployeeDto;
import com.company.mvccontroller.models.EmployeeModel;
import com.company.repository.entity.Employee;
import com.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
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
@RequestMapping(MVCEMPLOYEE)
@RequiredArgsConstructor
public class EmployeeMvcController {
    private final EmployeeService employeeService;
    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setTitle("Employee Register");

        HashMap<String, String> menuMapAdd = new HashMap<String, String>();
        menuMapAdd.put("Company List", "/mvc/company/findall");
        menuMapAdd.put("Company Register", "/mvc/company/register");
        menuMapAdd.put("Employee List", "/mvc/employee/findall");
        menuMapAdd.put("Employee Register", "/mvc/employee/register");

        employeeModel.setMenuMap(menuMapAdd);


        modelAndView.setViewName("employeeRegister");
        modelAndView.addObject("error","");
        modelAndView.addObject("model", employeeModel);
        return modelAndView;
    }

    @PostMapping(REGISTER)
    public ModelAndView register(RegisterEmployeeRequestDto dto){
        ModelAndView model = new ModelAndView();

        if(employeeService.existsEmployeeByEmail(dto.getEmail())){
            model.setViewName("employeeRegister");
            model.addObject("error",
                    dto.getCompanyName()+"Employee e-mail has already registered");
        }else{
            employeeService.register(dto);
            return new ModelAndView("redirect:register");
        }
        return model;
    }

    @GetMapping(FINDALL)
    public ModelAndView findAll(String companyName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeList");

        EmployeeModel model = new EmployeeModel();
        model.setTitle("Employee List");

        HashMap<String, String> menuMapAdd = new HashMap<String, String>();
        menuMapAdd.put("Company List", "/mvc/company/findall");
        menuMapAdd.put("Company Register", "/mvc/company/register");
        menuMapAdd.put("Employee List", "/mvc/employee/findall");
        menuMapAdd.put("Employee Register", "/mvc/employee/register");

        model.setMenuMap(menuMapAdd);
        model.setEmployeeList(employeeService.findAll());

        modelAndView.addObject("model", model);
        return modelAndView;

    }

    @PostMapping(DELETE)
    public ModelAndView delete(Long id){
        System.out.println("Silme işlemi çalıştı silinen id ...: "+ id);
        try{
            if(id !=null && id>0)
                employeeService.deleteById(id);
        }catch (Exception exception){
            System.out.println("hata oluştu...: "+ exception.toString());
        }

        return new ModelAndView("redirect:welcome");
    }

    @PostMapping(UPDATE)
    public ModelAndView update(RequestUpdateEmployeeDto dto){
        try{
            if(dto.getId() !=null && dto.getId()>0)
                employeeService.updateById(dto);
        }catch (Exception exception){
            System.out.println("hata oluştu...: "+ exception.toString());
        }

        return new ModelAndView("redirect:findall");
    }

}
