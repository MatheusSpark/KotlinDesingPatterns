import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

data class DisplayDataType(val index: Float, val data: String)

class DataDisplay{
    fun displayData(data: DisplayDataType){
        println("Display data: ${data.index} - ${data.data}")
    }
}
//---------------------------------

data class DatabaseData(val position: Int, val amount:Int)
class DatabasseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 7))
        list.add(DatabaseData(4, 24))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter( val display: DataDisplay): DatabaseDataConverter{
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
       val returnList = arrayListOf<DisplayDataType>()
        for (data in data){
            val ddt = DisplayDataType(data.position.toFloat(),data.amount.toString() )
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }

}

class AdapterTest{
    @Test
    fun adapterTest(){
        val generator = DatabasseDataGenerator()
        val generatorData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val converterData = adapter.convertData(generatorData)

        Assertions.assertThat(converterData.size).isEqualTo(3)
        Assertions.assertThat(adapter.convertData(generatorData)).isNotNull()

    }
}