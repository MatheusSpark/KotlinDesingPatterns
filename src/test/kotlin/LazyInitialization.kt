import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show(){
        println(message)
    }
}
class Window {
    val box by lazy {AlertBox()}

    fun showMessage(message: String){
        box.message = message
        box.show()
    }
}

class Window2{
    lateinit var box: AlertBox
    fun showMessage(message: String){
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest{
    @Test
    fun windowTest(){
        val window = Window()
        window.showMessage("Window test")
        Assertions.assertThat(window.box.message).isNotNull()
        val window2 = Window2()
        window2.showMessage("Window 2")
        Assertions.assertThat(window2.box.message).isNotNull()
    }
}