package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataMySql {
    private static final QueryRunner runner = new QueryRunner();

    public DataMySql() {

    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:msql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.AuthCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.AuthCode.class));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

}
