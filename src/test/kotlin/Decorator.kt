import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffe()
    fun makeMediumCoffe()
}

class NormalCoffeMachine : CoffeeMachine {
    override fun makeSmallCoffe() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeMediumCoffe() {
        println("Normal coffee machine: Making medium coffee")
    }
}

////////////////

class EnhancedCoffMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    override fun makeSmallCoffe() {
        println("Enhanced coffee machine: Making small coffee")
    }
    fun makeMilkCoffee(){
        println("Enhanced coffee machine: Making milk")
        coffeeMachine.makeSmallCoffe()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest{
    @Test
    fun testDecorator(){
        val normalMachine = NormalCoffeMachine()
        val enhancedCoffMachine = EnhancedCoffMachine(normalMachine)

        enhancedCoffMachine.makeSmallCoffe()
        println("---------------------------")
        enhancedCoffMachine.makeMediumCoffe()
        println("---------------------------")
        enhancedCoffMachine.makeMilkCoffee()
        println("---------------------------")
        normalMachine.makeMediumCoffe()

        Assertions.assertThat(enhancedCoffMachine.makeMediumCoffe())
        Assertions.assertThat(enhancedCoffMachine.makeSmallCoffe())
        Assertions.assertThat(enhancedCoffMachine.makeMilkCoffee())
    }
}