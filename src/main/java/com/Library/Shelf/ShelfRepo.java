package com.Library.Shelf;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepo extends JpaRepository<Shelf,Integer> {
Shelf findByLocation(String location);
}
