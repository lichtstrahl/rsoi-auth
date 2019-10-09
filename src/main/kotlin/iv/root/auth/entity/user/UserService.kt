package iv.root.auth.entity.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.inject.Inject
import kotlin.math.log

@Service
class UserService {
    @Inject
    private lateinit var userMapper: UserMapper
    @Inject
    private lateinit var userRepository: UserRepository
    @Inject
    private lateinit var passwordEncoder: PasswordEncoder

    fun create(dto: UserDTO): UserDTO {
        val user: User = userMapper.toEntity(dto, passwordEncoder.encode(dto.password))
        val newUser: User = userRepository.save(user)
        return userMapper.toDTO(newUser)
    }

    fun update(dto: UserDTO): UserDTO {
        val user: User = userRepository.getOne(dto.id!!)

        user.firstName = dto.firstName!!
        user.lastName = dto.lastName!!
        user.patronymicName = dto.patronymicName!!
        user.login = dto.login!!
//        user.password = passwordEncoder.encode(dto.password)
        userRepository.flush()
        return userMapper.toDTO(user)
    }

    fun get(login: String): UserDTO {
        val user: User? = userRepository.findByLogin(login)

    }
}