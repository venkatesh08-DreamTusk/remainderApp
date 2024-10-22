package com.remainder.remainderApp.repository;

import com.remainder.remainderApp.entity.Remainder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemainderRepository extends JpaRepository<Remainder,Long> {
}
