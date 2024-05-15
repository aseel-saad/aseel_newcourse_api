package aseel.api.service;

import aseel.api.exceptions.ResourceNotFoundException;
import aseel.api.model.Expense;
import aseel.api.repository.ExpenseRepository;
import aseel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserService userService;
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findByUserId(userService.getLoggedInUser().getId());
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepo.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id , Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        return expenseRepo.save(existingExpense);
    }

    @Override
    public List<Expense> readByCategory(String category) {
        return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category);
    }

    @Override
    public List<Expense> readByName(String keyword) {
        return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword);
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate) {
        if(startDate == null)
        {
            startDate = new Date(0);
            System.out.println("start " +startDate);
        }

        if(endDate == null)
        {
            endDate = new Date(System.currentTimeMillis());
            System.out.println("end "+endDate);
        }
        return expenseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate,endDate);
    }


}
