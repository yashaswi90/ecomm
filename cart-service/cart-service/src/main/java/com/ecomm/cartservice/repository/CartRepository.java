package com.ecomm.cartservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomm.cartservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> getByUserId(String userId);

    @Query("SELECT e from Cart e where e.cartId=:cartId and e.userId =:userId")
    Cart findByCartIdAndUserId(@Param("cartId") Long cartId, @Param("userId") String userId);
}
