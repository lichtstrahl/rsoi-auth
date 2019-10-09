package iv.root.auth.entity.role

import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class RoleMapper {
    fun toEntity(dto: RoleDTO): Role = Role(dto.role!!)

    fun toDTO(role: Role): RoleDTO = RoleDTO(role.role)

    fun toDTO(roles: List<Role>) : List<RoleDTO>
            = roles.stream()
            .map { toDTO(it) }
            .collect(Collectors.toList())
}