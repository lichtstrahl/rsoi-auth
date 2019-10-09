package iv.root.auth.config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder(val crypt: BCryptPasswordEncoder) : PasswordEncoder {

    override fun encode(rawPassword: CharSequence?): String {
        return crypt.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return crypt.matches(rawPassword, encodedPassword)
    }
}