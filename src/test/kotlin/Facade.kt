import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ComplexSystemStore (private val filePath: String){
    private val cache: HashMap<String, String>
    init {
        println("Reading data form the file $filePath")
        cache = hashMapOf()


    }

    fun store(key: String, value: String){
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit()= println("Storing cached data to file $filePath")
}

data class User (val login: String)


class UserRepository{
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User){
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }
    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest{
    @Test
    fun testFacade(){
        val userRepository = UserRepository()
        val user = User("spark")
        userRepository.save(user)
        val retrievedUser = userRepository.findFirst()

        Assertions.assertThat(retrievedUser.login).isEqualTo("spark")
    }
}