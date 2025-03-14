import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface DataSource

class DataBaseDataSource : DataSource

class NetWorkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DataBaseDataSource::class -> DataBaseFactory()
                NetWorkDataSource::class -> NetWorkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetWorkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetWorkDataSource()
}

class DataBaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DataBaseDataSource()
}

class AbstractFactoryTest {
    @Test
    fun aftest() {
        val dataBaseFactory = DataSourceFactory.createFactory<DataBaseDataSource>()
        val dataSource = dataBaseFactory.makeDataSource()
        println("Created datasource")
        Assertions.assertThat(dataSource).isInstanceOf(DataBaseDataSource::class.java)
    }
}