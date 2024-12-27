import com.example.restik_beta.Cart
import com.example.restik_beta.Dish
import org.junit.Assert.*
import org.junit.Test

// Unit-тест, проверяет, что добавленное блюдо появляется в корзине и правильно рассчитывается итоговая сумма.
class CartTest {

    @Test
    fun testAddItemToCart() {
        // Данный тест проверяет, что блюдо добавляется в корзину

        // Создание тестового блюда
        val dish = Dish(id = 1, name = "Pizza", price = 500, imageUrl = "http://test.com/pizza.jpg")

        // Добавляем блюдо в корзину
        Cart.addItem(dish)

        // Проверка, что корзина содержит только добавленное блюдо
        val itemsInCart = Cart.getItems()
        assertTrue(itemsInCart.contains(dish))

        // Проверка итоговой суммы
        assertEquals(500, Cart.getTotalPrice())
    }
}
