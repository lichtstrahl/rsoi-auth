package iv.root.auth

import iv.root.auth.http.ServerResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.Assert

open class BaseTest {
    fun <T> assertHttp(response: ResponseEntity<ServerResponse<T>>) {
        Assert.isTrue(response.statusCode == HttpStatus.OK)
        Assert.isTrue(response.body?.error == ServerResponse.OK)

    }
}