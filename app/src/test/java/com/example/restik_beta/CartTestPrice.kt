import com.example.restik_beta.Cart
import com.example.restik_beta.Dish
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

// Unit-тест 3, проверяет правильность расчета итоговой суммы добавленных в корзину блюд.
class CartTestPrice {

    private lateinit var dish1: Dish
    private lateinit var dish2: Dish

    @Before
    fun setUp() {
        // Подготовка тестовых данных
        dish1 = Dish(id = 1, name = "Pizza", price = 500, imageUrl = "http://test.com/pizza.jpg")
        dish2 = Dish(id = 2, name = "Burger", price = 300, imageUrl = "http://test.com/burger.jpg")

        // Добавляем блюда в корзину
        Cart.addItem(dish1)
        Cart.addItem(dish2)
    }

    @Test
    fun testGetTotalPrice() {
        // Получаем итоговую сумму корзины
        val totalPrice = Cart.getTotalPrice()

        // Проверяем, что сумма правильная (500 + 300)
        assertEquals(800, totalPrice)
    }
}
