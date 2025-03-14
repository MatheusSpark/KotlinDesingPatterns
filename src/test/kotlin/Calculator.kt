import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class Calculator {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
}

class calculatorTest{
    @Test
    fun testSum(){
        val calculator = Calculator()
       Assertions.assertThat (calculator.sum(5,3)).isEqualTo(8)
    }
}