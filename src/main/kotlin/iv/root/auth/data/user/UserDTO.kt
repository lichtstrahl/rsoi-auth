package iv.root.auth.data.user

import lombok.NoArgsConstructor
import javax.annotation.Nullable

@NoArgsConstructor
data class UserDTO(
        @Nullable
        var firstName: String?,
        @Nullable
        var lastName: String?,
        @Nullable
        var patronymicName: String?,
        @Nullable
        var login: String?,
        @Nullable
        var password: String?
) {
        constructor(login: String) : this(null, null, null, login, null)
        constructor() : this(null, null, null, null, null)
}