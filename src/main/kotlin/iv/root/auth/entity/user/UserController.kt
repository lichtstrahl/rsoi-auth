package iv.root.auth.entity.user

import iv.root.auth.http.ServerResponse
import iv.root.auth.http.Validator
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
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
    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var validator: Validator

    @PostMapping(value = ["/user"])
    fun create(@RequestBody dto: UserDTO) : ResponseEntity<ServerResponse<UserDTO>> {
        val buffer = validator
                .reset()
                .addNullField("id")
                .addField("firstName")
                .addField("patronymicName")
                .addField("login")
                .addField("password")
                .validStructure(dto)
                .export()

        return if (buffer.isEmpty()) {
            val newUser: UserDTO = userService.create(dto)
            return ResponseEntity.ok(ServerResponse.ok(newUser))
        } else
            ResponseEntity.ok(ServerResponse.fail(buffer.toString(), ServerResponse.VALIDATION_JSON_ERROR))
    }

    @PutMapping(value = ["/user"])
    fun update(@RequestBody dto: UserDTO) : ResponseEntity<ServerResponse<UserDTO>> {
        val buffer = validator
                .reset()
                .addField("id")
                .addField("firstName")
                .addField("lastName")
                .addField("patronymicName")
                .addField("login")
                .addField("password")
                .validStructure(dto)
                .export()
        return if (buffer.isEmpty()) {
            val updated: UserDTO = userService.update(dto)
            return ResponseEntity.ok(ServerResponse.ok(updated))
        } else ResponseEntity.ok(ServerResponse.fail(buffer.toString(), ServerResponse.VALIDATION_JSON_ERROR))
    }

    @GetMapping(value = ["/user"])
    fun getUser(@RequestParam(name = "login") login: String) : ResponseEntity<UserDTO> {
        val usr: UserDTO? = userService.get(login)
        return if (usr != null)
            ResponseEntity.ok(usr)
        else
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(UserDTO(login = login))
    }

    @GetMapping(value = ["/user/all"])
    fun getAll() : ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.getAll())
    }
}

