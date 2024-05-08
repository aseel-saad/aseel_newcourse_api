package aseel.api.repository;

import aseel.api.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);

    List<Expense> findByNameContaining(String keyword);

    List<Expense> findByDateBetween(Date startDate, Date endDate);

}
