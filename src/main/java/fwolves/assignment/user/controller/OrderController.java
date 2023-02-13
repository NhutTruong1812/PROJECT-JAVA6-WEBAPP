package fwolves.assignment.user.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.OrderHistory;
import fwolves.assignment.entity.PageTemp;
import fwolves.assignment.entity.Status;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.OrderService;
import fwolves.assignment.service.StatusService;
import fwolves.assignment.service.UserService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	StatusService statusService;
	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

	@RequestMapping("/home/order/detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Long id) {
		User sessionUser = userService.getCurrentUser().getBody();
		Order item = orderService.get(id).getBody();
		System.err.println(item.getFullname());
		model.addAttribute("orderItem", item);
		List<OrderHistory> sumOrder = orderService.findSumOrder(sessionUser, item);
		model.addAttribute("sumOrder", sumOrder);
		//
		Integer paingSession = (Integer) session.getAttribute("sessionPage");
		int paging = paingSession != null ? paingSession : 0;
		List<Order> page = orderService.findAllOrder(sessionUser);
		session.setAttribute("sessionPage", paging);
		model.addAttribute("orderItems", getOrderzz(page, paging, 10));
		return "/user/order";
	}

	@RequestMapping("/home/order/delete/{id}")
	public String orderDelete(Model model, @PathVariable("id") Long id) {
		User sessionUser = userService.getCurrentUser().getBody();
		Status findName = statusService.findByIdStatus();
		Order order = orderService.get(id).getBody();
		order.setStatus(findName);
		orderService.update(id, order);
		//
		Integer paingSession = (Integer) session.getAttribute("sessionPage");
		int paging = paingSession != null ? paingSession : 0;
		List<Order> page = orderService.findAllOrder(sessionUser);
		session.setAttribute("sessionPage", paging);
		model.addAttribute("orderItems", getOrderzz(page, paging, 10));

		return "/user/order";
	}

	@RequestMapping("/home/order/status/cancel")
	public String listCancel(Model model) {
		User sessionUser = userService.getCurrentUser().getBody();
		//
		Integer paging = (Integer) session.getAttribute("sessionPage");
		System.err.println(paging);
		List<Order> listCancel = orderService.findAllCANCELED(sessionUser);
		model.addAttribute("orderItems", getOrderzz(listCancel, paging, 10));
		return "/user/order";
	}

	@RequestMapping("/home/order/status/waiting")
	public String listwaiting(Model model) {
		User sessionUser = userService.getCurrentUser().getBody();
		//
		Integer paging = (Integer) session.getAttribute("sessionPage");
		List<Order> listCancel = orderService.findAllWATING(sessionUser);
		model.addAttribute("orderItems", getOrderzz(listCancel, paging, 10));
		return "/user/order";
	}

	@RequestMapping("/home/order/status/deliver")
	public String listdeliver(Model model) {
		User sessionUser = userService.getCurrentUser().getBody();
		//
		Integer paging = (Integer) session.getAttribute("sessionPage");
		List<Order> listCancel = orderService.findAllDELIVERED(sessionUser);
		model.addAttribute("orderItems", getOrderzz(listCancel, paging, 10));
		return "/user/order";
	}

	@RequestMapping("/home/order/status/delivering")
	public String listdelivering(Model model) {
		User sessionUser = userService.getCurrentUser().getBody();
		//
		Integer paging = (Integer) session.getAttribute("sessionPage");
		List<Order> listCancel = orderService.findAllDELIVERING(sessionUser);
		model.addAttribute("orderItems", getOrderzz(listCancel, paging, 10));
		return "/user/order";
	}

	@GetMapping("/home/order")
	public String orderView(@RequestParam("index") Optional<Integer> index, Model model) {
		User sessionUser = userService.getCurrentUser().getBody();
		//
		int paging = index.orElse(0);
		List<Order> page = orderService.findAllOrder(sessionUser);
		session.setAttribute("sessionPage", paging);
		model.addAttribute("orderItems", getOrderzz(page, paging, 10));
		return "/user/order";
	}

	public PageTemp<Order> getOrderzz(List<Order> templist, int trang, int baonhieu) {
		return new PageTemp<Order>(templist, trang, baonhieu);
	}
}
