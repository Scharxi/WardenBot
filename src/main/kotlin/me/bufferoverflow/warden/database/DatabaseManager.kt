package me.bufferoverflow.warden.database

import com.zaxxer.hikari.HikariDataSource
import mu.KLogger
import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {
    private val logger: KLogger = KotlinLogging.logger { }
    private var dataSource: HikariDataSource

    init {
        logger.info("Fetching data from database.toml")
        val config = DatabaseUser.fromToml {
            driverClassName = "com.mysql.cj.jdbc.Driver"
            addDataSourceProperty("prepStmtCacheSize", "250")
            addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        }
        dataSource = HikariDataSource(config)
        Database.connect(dataSource)
        logger.info("Successfully connected to database")
    }

    fun initTables() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns()
        }
    }
}