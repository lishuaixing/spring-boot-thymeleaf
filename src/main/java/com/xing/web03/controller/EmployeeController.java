package com.xing.web03.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xing.web03.Dao.DepartmentDao;
import com.xing.web03.Dao.EmployeeDao;
import com.xing.web03.pojo.Department;
import com.xing.web03.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //去添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加数据
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        //查询员工信息
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //查询所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }


    @PostMapping(value = "/updateEmp")
    public String updateEmp(Employee employee){
        //修改数据
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id,Model model){
        employeeDao.deleteById(id);
        return "redirect:/emps";
    }
}
