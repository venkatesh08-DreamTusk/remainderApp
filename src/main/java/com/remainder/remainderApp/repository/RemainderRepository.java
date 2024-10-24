package com.remainder.remainderApp.repository;

import com.remainder.remainderApp.entity.Remainder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RemainderRepository extends JpaRepository<Remainder,Long> {


    List<Remainder> findByDateTimeBetween(LocalDateTime now, LocalDateTime localDateTime);


}
