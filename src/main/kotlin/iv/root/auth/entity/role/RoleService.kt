package iv.root.auth.entity.role

import iv.root.auth.http.ServerResponse
import org.springframework.stereotype.Service
import javax.inject.Inject

@Service
class RoleService {
    @Inject
    lateinit var roleMapper: RoleMapper
    @Inject
    lateinit var roleRepository: RoleRepository


    fun create(dto: RoleDTO) : ServerResponse<RoleDTO> {
        val role:Role = roleMapper.toEntity(dto)
        val newRole = roleRepository.save(role)
        return ServerResponse.ok(roleMapper.toDTO(newRole))
    }
}