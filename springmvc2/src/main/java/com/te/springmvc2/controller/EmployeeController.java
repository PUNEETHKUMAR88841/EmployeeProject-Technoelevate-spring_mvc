package com.te.springmvc2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.springmvc2.bean.EmployeeBean;
import com.te.springmvc2.dao.EmployeeDAO;
import com.te.springmvc2.service.EmployeeService;

@Controller
public class EmployeeController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}// end init

	@Autowired
	private EmployeeService service;

	@GetMapping("empLogin")
	public String getLoginPage() {
		return "login";

	}// end of service

	@PostMapping("empLogin")
	public String getEmpDetails(int id, String password, HttpServletRequest request, ModelMap map) {

		EmployeeBean employeeBean = service.authenticate(id, password);
		if (employeeBean != null) {
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("emp", employeeBean);
			return "homePage";
		} else {
			map.addAttribute("errMsg", "Invalid Credentials");
			return "login";
		}

	}// end of getEmpdetails

	@GetMapping("searchForm")
	public String getSearchForm(ModelMap map, HttpSession session) {
		if (session.getAttribute("emp") != null) {
			return "search";

		} else {
			map.addAttribute("errMsg", "please login first");
			return "login";
		}
	}// end of getSearch

	@GetMapping("search")
	public String searchEmp(int id, ModelMap map,
			@SessionAttribute(name = "emp", required = false) EmployeeBean employeeBean) {

		if (employeeBean != null) {
			EmployeeBean employeeBean2 = service.searchemp(id);
			if (employeeBean2 != null) {
				map.addAttribute("data", employeeBean2);

			} else {
				map.addAttribute("msg", "Data not found for id :" + id);

			}
			return "search";
		} else {
			map.addAttribute("errMsg", "please login first");
			return "login";
		}

	}// end of searchemp

	@GetMapping("getdeleteform")
	public String getDeleteForm(ModelMap map,
			@SessionAttribute(name = "emp", required = false) EmployeeBean employeeBean) {
		if (employeeBean != null) {
			return "delete";
		} else {
			map.addAttribute("errMsg", "please login first");
			return "login";

		}

	}// end of getDeleteform

	@GetMapping("udelete")
	public String deleteEmployee(int id, @SessionAttribute(name = "emp", required = false) EmployeeBean bean,
			ModelMap map) {
		if (bean != null) {
			boolean deleted = service.deleteEmp(id);
			if (deleted == true) {
				map.addAttribute("msg", "deleted successfully");
				return "delete";
			} else {
				map.addAttribute("errMsg", "user not found");
				return "delete";
			}

		} // end of delete employee

		return null;

	}// end of delete employee

	@GetMapping("viewall")
	public String viewAllEmployee(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			List<EmployeeBean> employeeBeans = service.getAllData();
			map.addAttribute("empdata", employeeBeans);
			return "allData";
		} else {
			map.addAttribute("msg", "no employees found");
			return "allData";
		}

	}// end of view

	@GetMapping("addForm")
	public String addEmployee(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			return "register";
		} else {
			map.addAttribute("msg", "please login first");
			return "login";
		}

	}// end of addemp

	@PostMapping("add")
	public String addemp(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean,
			EmployeeBean empdata) {
		if (bean != null) {
			if (bean != null) {
				if (service.addemp(empdata)) {
					map.addAttribute("msg", "added successfully");
					return "register";
				} else {
					map.addAttribute("errMsg", "something went wrong");
					return "register";
				}
			}

		}
		return "login";

	}// end of addemp

	@GetMapping("/updateForm")
	public String updateEmployee(@SessionAttribute(name = "emp", required = false) EmployeeBean bean, ModelMap map) {
		if (bean != null) {
			map.addAttribute("id", bean.getId());
			return "update";
		} else {
			map.addAttribute("errMsg", "please login first");
			return "login";
		}
	}// end of update

	@PostMapping("/update")
	public String updateEmployee(@SessionAttribute(name = "emp", required = false) EmployeeBean employeeBean,
			ModelMap map, EmployeeBean empData) {
		if (employeeBean != null) {
			if (service.update(empData)) {
				map.addAttribute("id", employeeBean.getId());
				map.addAttribute("msg", "updated successfully");
				return "update";
			} else {
				map.addAttribute("errMsg", "something went wrong");
				return "update";
			}
		} else {
			map.addAttribute("msg", "please login first");
			return "login";

		}

	}// end of updateemp

	@GetMapping("logout")
	public String logOut(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "Logout Successfull");
		return "login";

	}// end of logout

}// end class
