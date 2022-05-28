package me.bufferoverflow.warden.database

import com.akuleshov7.ktoml.file.TomlFileReader
import com.zaxxer.hikari.HikariConfig

@kotlinx.serialization.Serializable
data class DatabaseUser(
    val username: String,
    val host: String = "localhost",
    val password: String = "",
    val databaseName: String,
    val port: Long = 3306
) {
    companion object {
        fun fromToml(fileName: String = "database.toml", tableName: String = "database", editsBlock: HikariConfig.() -> Unit): HikariConfig {
            val res = TomlFileReader.partiallyDecodeFromFile(serializer(), fileName, tableName)
            return HikariConfig().apply {
                username = res.username
                password = res.password
                jdbcUrl = "jdbc:mysql://${res.host}:${res.port}/${res.databaseName}"
                editsBlock()
            }
        }
    }
}
