package iv.root.auth.controller

import iv.root.auth.data.user.User
import iv.root.auth.data.user.UserDTO
import iv.root.auth.data.user.UserMapper
import iv.root.auth.data.user.UserRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.inject.Inject

@Slf4j
@Controller
@RequestMapping("/api")
class UserController {

    val logger = LoggerFactory.getLogger(UserController::class.java)

    @Inject
    lateinit var userRepository: UserRepository
    @Inject
    lateinit var userMapper: UserMapper

    @GetMapping(value = ["/user"])
    fun getUser(@RequestParam(name = "login") login: String) : ResponseEntity<UserDTO> {
        val usr: User? = userRepository.findByLogin(login)
        return if (usr != null)
            ResponseEntity.ok(userMapper.toDTO(usr))
        else
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(UserDTO(login = login))
    }
}

