package iv.root.auth.data.role

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "role")
data class Role(
        @Id
        @Column(name = "role", nullable = false, unique = true)
        var role: String
)