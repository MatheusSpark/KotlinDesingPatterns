package CreationalPatterns

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

object NetWorkDriver {
    init {
        println("Initializing NetWorkDriver $this")
    }
        fun log(): NetWorkDriver = apply { println("NetWorkDriver $this") }
}

class SinggletonTest{
    @Test
    fun testSinggleton(){
        println("Star")
        val netWorkDriver1 = NetWorkDriver.log()
        val netWorkDriver2 = NetWorkDriver.log()

        Assertions.assertThat(netWorkDriver1).isSameAs(netWorkDriver2)
        Assertions.assertThat(netWorkDriver2).isSameAs(netWorkDriver1)
    }
}