package fwolves.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fwolves.assignment.entity.CartItem;
import fwolves.assignment.entity.User;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT o FROM CartItem o WHERE o.cart.id=:cardId")
	public List<CartItem> getByCartId(@Param("cardId") Long cartId);

	@Modifying
	@Query("DELETE FROM CartItem o WHERE o.cart.id=:cartId")
	public void deleteByCartId(@Param("cartId") Long cartId);

	@Query("SELECT o FROM CartItem o WHERE o.cart.id=:cartId AND o.product.id=:productId")
	public CartItem getByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

	@Query("SELECT o FROM CartItem o WHERE o.cart.user=:user")
	public Optional<List<CartItem>> getByUser(@Param("user") User user);
}
