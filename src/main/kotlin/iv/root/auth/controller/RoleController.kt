package iv.root.auth.controller

import iv.root.auth.data.error.ServerResponse
import iv.root.auth.data.role.Role
import iv.root.auth.data.role.RoleDTO
import iv.root.auth.data.role.RoleMapper
import iv.root.auth.data.role.RoleRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.inject.Inject

@Controller
@RequestMapping("/api")
class RoleController {

    @Inject
    lateinit var roleRepository: RoleRepository
    @Inject
    lateinit var roleMapper: RoleMapper

    @PostMapping(value = ["/role"])
    fun create(@RequestBody dto: RoleDTO) : ResponseEntity<ServerResponse<RoleDTO>> {
        val newRole: Role? = dto.role?.let {
            val role: Role = roleMapper.toEntity(dto)
            roleRepository.save(role)
        }

        return if (newRole != null)
            ResponseEntity.ok(ServerResponse.ok(roleMapper.toDTO(newRole)))
        else
            ResponseEntity.ok(ServerResponse.fail("Can't create new role ${dto.role}"))
    }

    @GetMapping(value = ["/role/all"])
    fun getAll() : ResponseEntity<ServerResponse<List<RoleDTO>>> {
        val roles: List<Role> = roleRepository.findAll()
        return ResponseEntity.ok(ServerResponse.ok(roleMapper.toDTO(roles)))
    }
}