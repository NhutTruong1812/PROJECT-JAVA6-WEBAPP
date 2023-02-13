package fwolves.assignment.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DTool {
	// Phương thức chuyển chuỗi thành ngày
	public static Date StringToDate(String ngayJava, String kieu) {
		try {
//			ngayJava = doiKieuNgay(ngayJava);
			DateFormat kieuNgay = new SimpleDateFormat(kieu);
			return kieuNgay.parse(ngayJava);
		} catch (Exception e) {
//            System.out.println("lỗi đổi chuỗi thành ngày: " + e);
		}
		return null;
	}

	// Phương thức chuyển ngày thành chuỗi
	public static String dateToString(Date ngaySQL, String kieu) {
		DateFormat kieuNgay = new SimpleDateFormat(kieu);
		return kieuNgay.format(ngaySQL);
	}

	// Phương thức tăng hoặc giảm ngày SQL
	public static String addDays(String ngayJava, float them, String NGAYhayGIO) {
		Date ngaySQL = StringToDate(ngayJava, "dd-MM-yyyy HH:mm:ss");

		if (NGAYhayGIO.equalsIgnoreCase("ngay")) {
			them = them * 24 * 60 * 60 * 1000;
		} else if (NGAYhayGIO.equalsIgnoreCase("gio")) {
			them = them * 60 * 60 * 1000;
		}
		ngaySQL.setTime((long) (ngaySQL.getTime() + Math.ceil(them)));
		ngayJava = dateToString(ngaySQL, "dd-MM-yyyy HH:mm:ss");
		return ngayJava;
	}

	// Phương thức chuyển ngày về 1 dạng dd-MM-yyyy
	public static String doiKieuNgay(String ngayDoi) {
		String kytu = "[.//]{1}";
		ngayDoi = ngayDoi.trim();
		String ngayMoi = "";
		for (int i = 0; i < ngayDoi.length(); i++) {
			if (ngayDoi.substring(i, i + 1).matches(kytu)) {
				ngayMoi = ngayMoi + "-";
			} else {
				ngayMoi = ngayMoi + ngayDoi.substring(i, i + 1);
			}
		}
		return ngayMoi;
	}

	public static int tinhNGAYGIO(String DAU, String CUOI, String NGAYhayGIO) {
		String phngayDAU = DAU.substring(0, 10);
		String phangioDAU = DAU.substring(11, 16);
		float gioDAU = Float.valueOf(phangioDAU.substring(0, 2));
		float phutDAU = Float.valueOf(phangioDAU.substring(3, 5));

		String phngayCUOI = CUOI.substring(0, 10);
		String phangioCUOI = CUOI.substring(11, 16);
		float gioCUOI = Float.valueOf(phangioCUOI.substring(0, 2));
		float phutCUOI = Float.valueOf(phangioCUOI.substring(3, 5));

		float NGAYGIO = 0f;
		float ngayDAU = StringToDate(phngayDAU, "dd-MM-yyyy").getTime() + gioDAU * 60 * 60 * 1000 + phutDAU * 60 * 1000;
		float ngayCUOI = StringToDate(phngayCUOI, "dd-MM-yyyy").getTime() + gioCUOI * 60 * 60 * 1000
				+ phutCUOI * 60 * 1000;
		NGAYGIO = ngayCUOI - ngayDAU;
		NGAYhayGIO = NGAYhayGIO.toLowerCase();
		if (NGAYhayGIO.equalsIgnoreCase("ngay")) {
			NGAYGIO = NGAYGIO / 1000 / 60 / 60 / 24;
		} else if (NGAYhayGIO.equalsIgnoreCase("gio")) {
			NGAYGIO = NGAYGIO / 1000 / 60 / 60;
		}

		return (int) Math.ceil(NGAYGIO);
	}

	public static void main(String[] args) {
		System.out.println(tinhNGAYGIO("13-12-2021 14:00:00", "14-12-2021 17:00:00", "gio"));
		;
	}

	public static String setDatetimeLocal(String time) {
		// yyyy-MM-dd HH:mm:ss >> yyyy-MM-ddThh:mm
		String dd = "", MM = "", yyyy = "", HH = "", mm = "";
		yyyy = time.substring(0, 4);
		MM = time.substring(5, 7);
		dd = time.substring(8, 10);
		HH = time.substring(11, 13);
		mm = time.substring(14, 16);
		return String.format("%s-%s-%sT%s:%s", yyyy, MM, dd, HH, mm);
	}
	
	public static Timestamp str2Timestamp(String zzz) {
//		System.err.println(new Timestamp(DTool.StringToDate(zzz, "yyyy-MM-dd HH:mm:ss").getTime()));
		return new Timestamp(DTool.StringToDate(zzz, "yyyy-MM-dd HH:mm:ss").getTime());
	}
}
