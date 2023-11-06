package com.company.mvccontroller;

import com.company.dto.request.RequestCompanyDto;
import com.company.mvccontroller.models.CompanyModel;
import com.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;

import static com.company.constants.EndPoints.*;

@Controller
@RequestMapping(MVCCOMPANY)
@RequiredArgsConstructor
public class CompanyMvcController {
    private final CompanyService companyService;
    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        CompanyModel companyModel = new CompanyModel();

        companyModel.setTitle("Company Register");

        HashMap<String, String> menuMapAdd = new HashMap<String, String>();
        menuMapAdd.put("Company List", "/mvc/company/findall");
        menuMapAdd.put("Company Register", "/mvc/company/register");
        menuMapAdd.put("Employee List", "/mvc/employee/findall");
        menuMapAdd.put("Employee Register", "/mvc/employee/register");


        companyModel.setMenuMap(menuMapAdd);

        modelAndView.setViewName("companyRegister");
        modelAndView.addObject("error","");
        modelAndView.addObject("model",companyModel);
        return modelAndView;
    }

    @PostMapping(REGISTER)
    public ModelAndView register(RequestCompanyDto dto){
        ModelAndView model = new ModelAndView();
        System.out.println(dto.getCompanyName());

        if(companyService.existsCompanyByCompanyName(dto.getCompanyName())){
            model.setViewName("companyRegister");
            model.addObject("error",
                    dto.getCompanyName()+"Company name has already registered");
        }else{
            companyService.register(dto);
            return new ModelAndView("redirect:register");
        }
        return model;
    }

    @GetMapping(FINDALL)
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("companyList");

        CompanyModel companyModel = new CompanyModel();
        companyModel.setTitle("Company List");

        HashMap<String, String> menuMapAdd = new HashMap<String, String>();
        menuMapAdd.put("Company List", "/mvc/company/findall");
        menuMapAdd.put("Company Register", "/mvc/company/register");
        menuMapAdd.put("Employee List", "/mvc/employee/findall");
        menuMapAdd.put("Employee Register", "/mvc/employee/register");


        companyModel.setMenuMap(menuMapAdd);
        companyModel.setCompanyList(companyService.findAll());

        modelAndView.addObject("model",companyModel);
        return modelAndView;
    }
}
