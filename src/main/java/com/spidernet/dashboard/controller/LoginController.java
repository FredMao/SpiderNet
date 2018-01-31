package com.spidernet.dashboard.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spidernet.dashboard.entity.Employee;
import com.spidernet.dashboard.entity.EmployeeDetl;
import com.spidernet.dashboard.service.EmployeeDetlService;
import com.spidernet.dashboard.service.EmployeeService;
import com.spidernet.dashboard.service.MenuService;

@Controller
@SessionAttributes("employeeTemp")
@RequestMapping("/manage")
public class LoginController
{
    @Resource
    EmployeeService userService;

    @Resource
    EmployeeDetlService employeeDetlService;
    
    @Resource
	MenuService menuService;    

    private static Logger logger = LoggerFactory
            .getLogger(LoginController.class);

    @Value("${ER_PATTERN}")
    private String erPattern;

    @Value("${HR_PATTERN}")
    private String hrPattern;

    @RequestMapping("/loginPage")
    public String loginPage(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "login";
    }

    @RequestMapping("/top")
    public String top(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "top";
    }

    @RequestMapping("/left")
    public String left(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "left";
    }

    @RequestMapping("/footer")
    public String footer(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "footer";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Boolean checkUser(final HttpServletRequest request,
            final HttpServletResponse response, Employee employee)
    {
        String userName = request.getParameter("userName");
        request.getSession().setAttribute("SystemName", "SpiderNet");

        Pattern patternEr = Pattern.compile(erPattern);
        Pattern patternHr = Pattern.compile(hrPattern);

        Employee employeeTemp = null;

        if (patternEr.matcher(userName).matches())
        {
            logger.debug("You are using ER_NUMBER to login the System");
            employeeTemp = new Employee();
            employee.setErNumber(userName);
            employeeTemp = userService.accountValidByErNumber(employee);
        }

        if (patternHr.matcher(userName).matches())
        {
            logger.debug("You are using HR_NUMBER to login the System");
            employeeTemp = new Employee();
            employee.setHrNumber(userName);
            employeeTemp = userService.accountValidByHrNumber(employee);
        }

        /*
            if (patternWechat.matcher(userName).matches())
            {
                // employee.setWechatOpenId(userName);
            }
        */

        if (employeeTemp != null)
        {
            logger.info("Find the Employee correctly, enter the index page");

            request.getSession().setAttribute("employee", employeeTemp);
            String empId = employeeTemp.getEmployeeId();
            EmployeeDetl employeeDetl = employeeDetlService.queryDetail(empId);
            request.getSession().setAttribute("employeeDetl", employeeDetl);
            
            //insert user menu information
        	List<Object> menuObject = menuService.menuListByEmp(empId);
            request.getSession().setAttribute("menuList", menuObject);
            
            return true;
        }
        else
        {
            logger.info("Can not find the Employee in the DB");

            return false;
        }
    }

    @RequestMapping("/logout")
    public String logout(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        request.getSession().removeAttribute("employee");
        request.getSession().removeAttribute("SystemName");

        return "login";
    }
    
//    @RequestMapping("/listMenu")
//    @ResponseBody
//    public List<Object> menuList(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//    	Employee emp = (Employee) request.getSession().getAttribute("employee");
//    	String empId = emp.getEmployeeId();
//    	List<Object> menuList = menuService.menuListByEmp(empId);
//    	
//    	return menuList;
//    }
}
