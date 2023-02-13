package fwolves.assignment.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fwolves.assignment.entity.Category;
import fwolves.assignment.entity.Product;
import fwolves.assignment.entity.User;
import fwolves.assignment.service.CategoryService;
import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.PageTemp;
import fwolves.assignment.repository.OrderRepository;
import fwolves.assignment.repository.ProductRepository;
import fwolves.assignment.service.ProductService;
import fwolves.assignment.service.UserService;

@Controller
public class ShopController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService cateSV;
	@Autowired
	HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	ProductRepository DAOsp;

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

	@GetMapping("/home/shop/detail/{id}")
	public String detailView(Model model, @PathVariable("id") Long id) {
		Product product = productService.get(id).getBody();
		model.addAttribute("product", product);
		model.addAttribute("splienquan", DAOsp.splienquan(product.getCategory().getId()));
		return "user/product";
	}

	@RequestMapping("/home/shop")
	public String shopView(@RequestParam("shop_tuKhoa") Optional<String> shop_tuKhoa,
			@RequestParam("key_danhMuc") Optional<String> key_danhMuc,
			@RequestParam("shop_trang") Optional<Integer> shop_trang) {
		if (request.getMethod().equalsIgnoreCase("post")) {
			if (shop_tuKhoa.isPresent()) {
				request.getSession().setAttribute("shop_tuKhoa", shop_tuKhoa.get());
				return "redirect:/home/shop?x=3";
			}
		}
		//
		if (key_danhMuc.isPresent()) {
			try {
				//
				@SuppressWarnings("unchecked")
				Map<String, Category> map_danhmuc = (Map<String, Category>) request.getSession()
						.getAttribute("map_danhMuc");
				Category catetemp1 = map_danhmuc.get(1 + "_" + key_danhMuc.get());

				if (catetemp1 != null) {
					map_danhmuc.put(0 + "_" + key_danhMuc.get(), map_danhmuc.get(1 + "_" + key_danhMuc.get()));
					map_danhmuc.remove(1 + "_" + key_danhMuc.get());
				} else {
					map_danhmuc.put(1 + "_" + key_danhMuc.get(), map_danhmuc.get(0 + "_" + key_danhMuc.get()));
					map_danhmuc.remove(0 + "_" + key_danhMuc.get());
				}
				request.getSession().setAttribute("map_danhMuc", map_danhmuc);
				return "redirect:/home/shop?x=3";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//
		if (shop_trang.isPresent()) {
			request.getSession().setAttribute("shop_trang", shop_trang.get());
			return "redirect:/home/shop?x=3";
		}
		return "/user/shop";
	}

	@Autowired
	private OrderRepository orderRepository;

	@ModelAttribute
	public List<Order> getOrders() {
//		List<Order> list = orderService.get().getBody();
//		list.forEach(o -> System.err.println(o.getFullname()));
		return orderRepository.findAll();// list;
	}

	@ModelAttribute("shop_sanPham")
	public PageTemp<Product> getProducts() {
		/*
		 * các tiêu chí lọc sản phẩm 1> từ khóa 2> danh mục 3> giá min max
		 */
		String shop_tuKhoa = (String) request.getSession().getAttribute("shop_tuKhoa");
		//
		Map<String, Category> map_danhmuc = (Map<String, Category>) request.getSession().getAttribute("map_danhMuc");
		String[] shop_danhMuc = null;
		if (map_danhmuc != null) {
			List<Category> listtemp = new ArrayList<>();
			for (Entry<String, Category> x : map_danhmuc.entrySet()) {
				if (x.getKey().startsWith("1")) {
					listtemp.add(x.getValue());
				}
			}
			shop_danhMuc = new String[listtemp.size()];
			for (int i = 0; i < shop_danhMuc.length; i++) {
				shop_danhMuc[i] = listtemp.get(i).getId();
			}
		}
		Integer shop_trang = (Integer) request.getSession().getAttribute("shop_trang");
		if (shop_trang == null) {
			shop_trang = 0;
		}
		//
		final Double shop_max = ((Double) request.getSession().getAttribute("shop_max")) == null ? Double.MAX_VALUE
				: (Double) request.getSession().getAttribute("shop_max");
		final Double shop_min = ((Double) request.getSession().getAttribute("shop_min")) == null ? Double.MIN_VALUE
				: (Double) request.getSession().getAttribute("shop_min");
		//
		List<Product> templist = productService.getShop(shop_tuKhoa, shop_danhMuc).getBody();
		List<Product> templist2 = templist.stream()
				.filter(x -> (x.getPricezz() >= shop_min && x.getPricezz() <= shop_max)).collect(Collectors.toList());
		return new PageTemp<Product>(templist2, shop_trang, 2);
	}

	@ModelAttribute("map_danhMuc")
	public Map<String, Category> getCategories() {
		Map<String, Category> maptemp = new HashMap<String, Category>();
		List<Category> listtemp = cateSV.get().getBody();
		for (Category category : listtemp) {
			maptemp.put(1 + "_" + category.getId(), category);
		}
		// set map session
		@SuppressWarnings("unchecked")
		Map<String, Category> mapses = (Map<String, Category>) request.getSession().getAttribute("map_danhMuc");
		if (mapses != null) {
			maptemp = mapses;
		} else {
			request.getSession().setAttribute("map_danhMuc", maptemp);
		}
		return maptemp;
	}

	@RequestMapping("/home/shop/minmax")
	public String minmax(@RequestParam("shop_max") Optional<Double> shop_max,
			@RequestParam("shop_min") Optional<Double> shop_min) {
		if (shop_max.isPresent()) {
			if (shop_max.get() == -1) {
				request.getSession().removeAttribute("shop_max");
			} else {
				request.getSession().setAttribute("shop_max", shop_max.get());
			}
		}
		if (shop_min.isPresent()) {
			if (shop_min.get() == -1) {
				request.getSession().removeAttribute("shop_min");
			} else {
				request.getSession().setAttribute("shop_min", shop_min.get());
			}
		}
		return "redirect:/home/shop?z=1";
	}
}