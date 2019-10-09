package iv.root.auth.entity.user

import org.springframework.stereotype.Component
import javax.validation.constraints.NotNull

@Component
class UserMapper {

    fun toDTO(@NotNull user: User): UserDTO {
        val result = UserDTO()

        result.firstName = user.firstName
        result.lastName = user.lastName
        result.patronymicName = user.patronymicName
        result.login = user.login
        result.password = null

        return result
    }

    fun toEntity(@NotNull dto: UserDTO, encryptPassword: String): User = User(
            id = dto.id,
            firstName = dto.firstName!!,
            lastName = dto.lastName!!,
            patronymicName = dto.patronymicName!!,
            login = dto.login!!,
            password = encryptPassword
        )
}