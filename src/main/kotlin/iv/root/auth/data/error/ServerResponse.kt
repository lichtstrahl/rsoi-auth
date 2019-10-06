package iv.root.auth.data.error

data class ServerResponse<T>(
        var error: Int,
        var msg: String,
        var data: T?
) {
    companion object Status {
        val OK: Int = 0
        val DEFAULT_ERROR: Int = -1

        fun <T> ok(data: T): ServerResponse<T> = ServerResponse(error = OK, msg = "", data = data)

        fun <T> fail(msg: String, error: Int = DEFAULT_ERROR): ServerResponse<T> = ServerResponse(error, msg, null)
    }
}