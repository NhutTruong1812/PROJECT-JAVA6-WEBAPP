package fwolves.assignment.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import fwolves.assignment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT o FROM Product o WHERE o.category.deleted=false")
	public List<Product> findAllCateFalse();

	@Query("select p from Product p")
	Page<Product> findAllProduct(Pageable pageable);

	@Query("select p from Product p where p.deleted = false")
	List<Product> findAll2();

	@Query(nativeQuery = true, value = "select p.* from Product p "
			+ "left outer join (select ProductId, sum(Quantity) as solu from OrderDetail group by ProductId) od on od.ProductId = p.Id where p.Deleted = 'false' "
			+ "order by od.solu desc")
	public List<Product> topIndex();

	@Query(nativeQuery = true, value = "select p.* from product p " + "where p.name like ?1 and p.CategoryId in ?2 and p.Deleted = 'false'")
	public List<Product> getShop(String tuKhoa, String[] danhMuc);

	@Query(nativeQuery = true, value = "select p.Name as sp, ca.Name as dm, pp1.price, sum(od.quantity) as quanz, sum(pp1.price*od.quantity) as sumz from Product p "
			+ "join ( " + "	select od.ProductId, od.quantity, o.CreateDate from OrderDetail od "
			+ "	join Product p on od.ProductId = p.Id "
			+ "	join (select o.* from [Order] o where o.StatusId = 'DELIVERED') o on o.Id = od.OrderId "
			+ "	where o.CreateDate between ?1 and ?2 " + "	group by od.ProductId, od.quantity, o.CreateDate "
			+ ") od on p.Id = od.ProductId " + "join ( " + "	select pp1.ProductId, pp1.price from PriceHistory pp1 "
			+ "	left outer join (select pp2.ProductId, max(pp2.ChangeDate) as datez from PriceHistory pp2 where pp2.ChangeDate between '2020-01-01' and ?2 group by pp2.ProductId) pp2 on pp2.ProductId = pp1.ProductId "
			+ "	where pp1.ChangeDate = pp2.datez " + ") pp1 on pp1.ProductId = p.Id "
			+ "join Category ca on ca.Id = p.CategoryId " + "group by p.Name, pp1.price, ca.Name "
			+ "order by sumz desc")
	List<Object[]> tkDoanhThu(Timestamp from, Timestamp to);

	@Query(nativeQuery = true, value = "select o.un as username, o.fn as hoten, o.sdt as sdt, COUNT(o.ido) as landatz, iif(SUM(o.tongchi) is null, 0, SUM(o.tongchi)) as tongchiz from [Order] ozz "
			+ "right outer join ( "
			+ "	select u.username as un, u.fullname as fn, u.PhoneNumber as sdt, o.Id as ido, SUM(od.prirezzz * od.quantity) as tongchi from [Order] o "
			+ "	left outer join [User] u on u.Id = o.UserId " + "	left outer join ( "
			+ "		select od.OrderId, od.quantity, p.prirezz as prirezzz from OrderDetail od "
			+ "		left outer join ( " + "			select p.Id , pp1.pricez as prirezz from Product p "
			+ "			left outer join( "
			+ "				select pp1.ProductId, pp1.price as pricez from PriceHistory pp1 "
			+ "				left outer join (select pp2.ProductId, max(ChangeDate) as datez from PriceHistory pp2 where ChangeDate between '2020-01-01' and ?2 group by ProductId) pp2 on pp2.ProductId = pp1.ProductId "
			+ "				where pp1.ChangeDate = pp2.datez " + "			) pp1 on pp1.ProductId = p.Id "
			+ "		) p on p.Id = od.ProductId " + "	) od on od.OrderId = o.Id "
			+ "	where o.CreateDate between ?1 and ?2 and o.StatusId like '%' "
			+ "	group by u.username, u.fullname, u.PhoneNumber, o.Id " + ") o on o.ido = ozz.Id "
			+ "group by o.un, o.fn, o.sdt " + "order by tongchiz desc")
	List<Object[]> tkNguoiDung(Timestamp from, Timestamp to);

	@Query(nativeQuery = true, value = "select p.Name as sp, c.name as dm, sum(p.Quantity) as quanz, iif(pp1.pricez is null, 0, pp1.pricez) as gia, iif(SUM(p.Quantity * pp1.pricez) is null, 0, SUM(p.Quantity * pp1.pricez)) as sumz from Product p "
			+ "left outer join Category c on c.id = p.CategoryId " + "left outer join ( "
			+ "	select pp1.ProductId, pp1.price as pricez from PriceHistory pp1 "
			+ "	left outer join (select pp2.ProductId, max(ChangeDate) as datez from PriceHistory pp2 group by ProductId) pp2 on pp2.ProductId = pp1.ProductId "
			+ "	where pp1.ChangeDate = pp2.datez) pp1 on pp1.ProductId = p.id " + "group by p.name, c.name, pp1.pricez")
	List<Object[]> tkSanPham();

	@Query(nativeQuery = true, value = "select p.* from Product p where p.CategoryId = ?1")
	List<Product> splienquan(String CategoryId);
	
	
}
