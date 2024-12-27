import com.example.restik_beta.Cart
import com.example.restik_beta.Dish
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

// Unit-тест 2, проверяет, что блюдо удаляется из корзины и итоговая сумма обновляется.
class CartTestDelete {

    private lateinit var dish: Dish

    @Before
    fun setUp() {
        // тестовые данных
        dish = Dish(id = 1, name = "Pizza", price = 500, imageUrl = "http://test.com/pizza.jpg")
        Cart.addItem(dish) // Добавляем блюдо в корзину
    }

    @Test
    fun testRemoveItemFromCart() {
        // Удаляем блюдо из корзины
        Cart.removeItem(dish)

        // Проверка, что корзина больше не содержит блюдо
        val itemsInCart = Cart.getItems()
        assertFalse(itemsInCart.contains(dish))

        // Проверка итоговой суммы (должна быть 0, так как корзина пустая)
        assertEquals(0, Cart.getTotalPrice())
    }
}
