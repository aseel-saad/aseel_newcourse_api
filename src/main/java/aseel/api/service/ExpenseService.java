package aseel.api.service;

import aseel.api.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);

    Expense updateExpenseDetails(Long id , Expense expense);

    List<Expense> readByCategory(String category);

    List<Expense> readByName(String keyword);

    List<Expense> readByDate(Date startDate, Date endDate);

}
