package fwolves.assignment.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import fwolves.assignment.entity.Category;
import fwolves.assignment.entity.Product;
import fwolves.assignment.repository.ProductRepository;
import fwolves.assignment.service.CategoryService;
import fwolves.assignment.service.ProductService;
import org.springframework.web.bind.annotation.ModelAttribute;

import fwolves.assignment.entity.User;
import fwolves.assignment.service.UserService;

@Controller
public class HomeController {

	@Autowired
	ProductService sanphamSV;
	@Autowired
	HttpServletRequest request;
	@Autowired
	ProductRepository sanphamDAO;
	@Autowired
	private UserService userService;

	@GetMapping("/home/index")
	public String home() {
		return "user/index";
	}
	
	@ModelAttribute("sanphamindex")
	public List<Product> sanphamindex(){
		List<Product> sanphamindex = sanphamSV.get().getBody();
		return sanphamindex;
	}
	
	@ModelAttribute("topindex")
	public List<Product> topindex(){
		List<Product> topindex = sanphamDAO.topIndex();
		return topindex;
	}

	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return userService.getCurrentUser().getBody();
	}

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService cateSV;
	
	@ModelAttribute("shop_sanPham")
	public List<Product> getProducts() {
		/*
		 * các tiêu chí lọc sản phẩm 1> từ khóa 2> danh mục 3> giá min max
		 */
		String shop_tuKhoa = (String) request.getSession().getAttribute("shop_tuKhoa");
		//
		Map<String, Category> map_danhmuc = (Map<String, Category>) request.getSession()
				.getAttribute("map_danhMuc");
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
		return productService.getShop(shop_tuKhoa, shop_danhMuc).getBody();
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

}
