package StructuralPatterns

import org.junit.jupiter.api.Test

interface Image {
    fun display()
}

class RealImage(private val filename: String) : Image {
    override fun display() {
        println("RealImage: $filename ")
    }

    private fun loadFromDisk(filename: String) {
        println("Loading from disk: $filename")
    }

    init {
        loadFromDisk(filename)
    }
}

class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        println("ProcyImage: $filename ")
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage!!.display()
    }
}

class ProxyTest{
    @Test
    fun testProxy(){
        val image = ProxyImage("test.jpg")

        image.display()
        println("----------------")

        image.display()

    }
}