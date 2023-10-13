package ru.dilgorp.documentation.platform.editor.base

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.support.TransactionTemplate

abstract class BaseRepositoryTest : BaseTest() {
    @Autowired
    private lateinit var transactionalTemplate: TransactionTemplate

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    protected fun <T> transactional(block: () -> T): T? = transactionalTemplate.execute { block() }

    val dollar = '$'

    @BeforeEach
    fun setUp() {
        transactional {
            jdbcTemplate.execute(
                """
            DO ${dollar}func${dollar}
            BEGIN
            EXECUTE  (SELECT 'TRUNCATE TABLE ' || string_agg(oid::regclass::text, ', ') || ' CASCADE'
                    FROM pg_class
                    WHERE relkind = 'r'
                    AND relnamespace = 'public'::regnamespace
                    AND relname != 'databasechangelog'
                    AND relname != 'databasechangeloglock'
            );
            END
            ${dollar}func${dollar}
        """
            )
        }
    }
}