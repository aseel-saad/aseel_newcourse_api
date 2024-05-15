package aseel.api.repository;

import aseel.api.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserIdAndCategory(Long userId, String category);

    List<Expense> findByUserIdAndNameContaining(Long userId,String keyword);

    List<Expense> findByUserIdAndDateBetween(Long userId,Date startDate, Date endDate);

    List<Expense> findByUserId(Long userId);

    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);

}
