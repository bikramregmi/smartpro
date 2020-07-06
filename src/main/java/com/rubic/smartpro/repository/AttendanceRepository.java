package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.Attendance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the Attendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

   @Query("select a from Attendance a where a.insertedDate=?1")
   List< Attendance> findAllForToday(LocalDate date);

    Attendance findByInsertedDateAndEmployee_eCode(LocalDate strDate, String employeeCode);

    Attendance findOneByIdAndInsertedDate(Long id, LocalDate strDate);
}
