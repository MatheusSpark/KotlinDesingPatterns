import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

open class Equipment(
    open val price: Int,
    val name: String
)
open class Composite (name:String): Equipment(0, name){
    private val equipments = ArrayList<Equipment>()

    override val price: Int
    get() = equipments.map{ it.price }.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}
class Computer: Composite("PC")

class Processor: Equipment(1000, "Processor")

class HardDrive: Equipment(250, "HardDrive")

class Memory: Composite( "Memory")
class ROM: Equipment(100, "Read Only Memory")
class RAM: Equipment(100, "Random Access Memory")

class CompisteTest{
    @Test
    fun testComposite(){
        val memory = Memory().
        add(ROM()).
        add(RAM())
        val pc = Computer().add(memory).add(Processor()).add(HardDrive())
        println("PC price: ${pc.price}")

        Assertions.assertThat(pc.price).isEqualTo(1450)
    }
}