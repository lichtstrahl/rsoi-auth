package iv.root.auth.entity.user

import iv.root.auth.http.ServerResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.inject.Inject

@Service
class UserService {
    @Inject
    private lateinit var userMapper: UserMapper
    @Inject
    private lateinit var userRepository: UserRepository
    @Inject
    private lateinit var passwordEncoder: PasswordEncoder

    fun create(dto: UserDTO): ServerResponse<UserDTO> {
        if (exists(dto.login!!)) return ServerResponse.fail(String.format("Login %s busy", dto.login))

        val user: User = userMapper.toEntity(dto, passwordEncoder.encode(dto.password))
        val newUser: User = userRepository.save(user)
        return ServerResponse.ok(userMapper.toDTO(newUser))
    }

    fun update(dto: UserDTO): ServerResponse<UserDTO> {
        val user: User = userRepository.findByIdOrNull(dto.id)
                ?: return ServerResponse.fail(
                        String.format(Locale.ENGLISH, "User with id %d not found", dto.id),
                        ServerResponse.ENTITY_NOT_FOUND
                )

        user.firstName = dto.firstName ?: user.firstName
        user.lastName = dto.lastName ?: user.lastName
        user.patronymicName = dto.patronymicName ?: user.patronymicName

        user.login = dto.login ?: user.login
        if (exists(user.id, user.login)) return ServerResponse.fail(String.format("Login %s busy", dto.login))

//        user.password = passwordEncoder.encode(dto.password)
        userRepository.flush()
        return ServerResponse.ok(userMapper.toDTO(user))

    }

    fun get(login: String): UserDTO? {
        val user: User? = userRepository.findByLogin(login)

        return if (user != null) userMapper.toDTO(user) else null
    }

    fun getAll(): List<UserDTO> = userRepository.findAll().map { userMapper.toDTO(it) }

    fun remove(id: Long) = userRepository.deleteById(id)

    fun exists(login: String): Boolean = userRepository.findByLogin(login) != null

    fun exists(id: Long?, login: String): Boolean = userRepository.findByLogin(login)?.id != id
}