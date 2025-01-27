package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.Order;
import com.alex.library.bookmanagementsystem.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    public List<Order> findByLibrary(Library library);

    @Query(value = """
            select sum(p.quantity*m.bookprice)
                    from OrderItem p
                    join p.book m
                    join p.order order
                    where order.library = ?1""")
    public Float costTotal(Library library);
}
