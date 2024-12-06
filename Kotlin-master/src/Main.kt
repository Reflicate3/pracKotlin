import java.time.LocalDate

class Expense(val amount: Double, val category: String, val date: LocalDate) {

    fun printExpenseInfo() {
        println("Сумма: $amount, Категория: $category, Дата: $date")
    }
}

class ExpenseTracker {

    private val expenses: MutableList<Expense> = mutableListOf()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
        println("Расход добавлен: ")
        expense.printExpenseInfo()
    }

    fun printAllExpenses() {
        if (expenses.isEmpty()) {
            println("Нет расходов.")
        } else {
            println("Список всех расходов:")
            for (expense in expenses) {
                expense.printExpenseInfo()
            }
        }
    }

    fun calculateTotalByCategory() {
        if (expenses.isEmpty()) {
            println("Нет расходов для подсчета.")
            return
        }

        val categoryTotals = mutableMapOf<String, Double>()

        for (expense in expenses) {
            categoryTotals[expense.category] = categoryTotals.getOrDefault(expense.category, 0.0) + expense.amount
        }

        println("Сумма расходов по категориям:")
        for ((category, total) in categoryTotals) {
            println("Категория: $category, Сумма: $total")
        }
    }
}

fun main() {
    val tracker = ExpenseTracker()


    tracker.addExpense(Expense(100.0, "Продукты", LocalDate.of(2024, 9, 1)))
    tracker.addExpense(Expense(50.0, "Транспорт", LocalDate.of(2024, 9, 3)))
    tracker.addExpense(Expense(200.0, "Развлечения", LocalDate.of(2024, 9, 5)))
    tracker.addExpense(Expense(75.0, "Продукты", LocalDate.of(2024, 9, 6)))

    tracker.printAllExpenses()
    tracker.calculateTotalByCategory()
}
