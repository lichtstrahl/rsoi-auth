package iv.root.auth.entity.user

import javax.annotation.Nullable
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity(name = "user")
class User (

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    var id: Long?,

    @NotNull
    @Column(name = "f_name", nullable = false)
    var firstName: String,

    @NotNull
    @Column(name = "l_name", nullable = false)
    var lastName: String,

    @Nullable
    @Column(name = "p_name")
    var patronymicName: String,

    @NotNull
    @Column(name = "login", nullable = false)
    var login: String,

    @NotNull
    @Column(name = "password", nullable = false)
    var password: String
) {

    constructor(@NotNull dto: UserDTO, encryptPassword: String): this(
            id = dto.id,
            firstName = dto.firstName!!,
            lastName = dto.lastName!!,
            patronymicName = dto.patronymicName!!,
            login = dto.login!!,
            password = encryptPassword
    )
}