package BehaviouralPatterns

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

sealed class AuthorizationState

object Unauthorized: AuthorizationState()

class Authorized(val username: String): AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state){
            is Authorized -> true
            is Unauthorized -> false
        }
    val username: String
        get(){
            return when (val state = this.state){
                is Authorized -> state.username
                is Unauthorized -> "Unknown"
            }
        }
    fun logginUser(username: String){
        state = Authorized(username)
    }

    fun logoutUser(){
        state = Unauthorized
    }

    override fun toString() = "User $username is logger in: $isAuthorized"
}

class StateTest(){
    @Test
    fun testState(){
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.logginUser("user")
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("user")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
        Assertions.assertThat(authorizationPresenter.username).isEqualTo("Unknown")
    }
}