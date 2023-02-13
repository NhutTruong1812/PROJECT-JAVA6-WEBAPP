package fwolves.assignment.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.repository.ProductRepository;
import fwolves.assignment.service.DTool;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/report")
public class ReportRestCtrller {

	@Autowired
	private ProductRepository DAOProduct;
	@Autowired
	HttpServletRequest request;

	// hành động: thống kê theo ngày
	String from = DTool.dateToString(new Date(), "yyyy-MM-dd") + " 00:00:00";
	String to = DTool.dateToString(new Date(), "yyyy-MM-dd") + " 23:59:59";

	@GetMapping("/tkdoanhthu")
	public List<Object[]> tkdoanhthu(@RequestParam("sttout") Optional<String> getfrom, @RequestParam("endout") Optional<String> getto) {
		//
		if (getfrom.isPresent() || getto.isPresent()) {
			from = getfrom.get().replace("@", " ");
			to = getto.get().replace("@", " ");
			request.getSession().setAttribute("from", from);
			request.getSession().setAttribute("to", to);
		}
		String fromss = (String) request.getSession().getAttribute("from");
		String toss = (String) request.getSession().getAttribute("to");
		if (fromss != null || toss != null) {
			from = fromss;
			to = toss;
		}
		// list ở đây
		List<Object[]> list = DAOProduct.tkDoanhThu(DTool.str2Timestamp(from), DTool.str2Timestamp(to));
		list.forEach(l->{
			System.err.println("sadasdasdasdasd" + l[0]);
		});
		//
		return list;
	}

	@GetMapping("/tknguoidung")
	public List<Object[]> tknguoidung(@RequestParam("sttout") Optional<String> getfrom, @RequestParam("endout") Optional<String> getto) {
		//
		if (getfrom.isPresent() || getto.isPresent()) {
			from = getfrom.get().replace("@", " ");
			to = getto.get().replace("@", " ");
			request.getSession().setAttribute("from", from);
			request.getSession().setAttribute("to", to);
		}
		String fromss = (String) request.getSession().getAttribute("from");
		String toss = (String) request.getSession().getAttribute("to");
		if (fromss != null || toss != null) {
			from = fromss;
			to = toss;
		}
		// list ở đây
		List<Object[]> list = DAOProduct.tkNguoiDung(DTool.str2Timestamp(from), DTool.str2Timestamp(to));
		return list;
	}

	@GetMapping("/tksanpham")
	public List<Object[]> tksanpham() {
		List<Object[]> list = DAOProduct.tkSanPham();
		return list;
	}
}
