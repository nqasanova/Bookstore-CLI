package src.Metadata;

import src.Connectivity.Database_Connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metadata extends Database_Connection {
    public static void printTableNamesandColumns() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println(tableName + ":");
            System.out.println();
            printColumns(tableName);
            System.out.println("---------------------------------------");
        }
    }

    public static void printColumns(String tableName) throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            System.out.println(columnName);
        }
    }

    public static void printColumnDetails() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            System.out.println(tableName + " table:");
            System.out.println();
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                System.out.println(columnName + " " + columnType);
            }
            System.out.println();
            System.out.println("---------------------------------------");
        }
    }

    public static void printPrimaryKeys() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            System.out.println(tableName + " table:");
            System.out.println();
            if (!primaryKeys.next()) {
                System.out.println("No primary keys found for this table.");
            } else {
                do {
                    String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                    System.out.println(primaryKeyColumnName);
                } while (primaryKeys.next());
            }
            System.out.println();
            System.out.println("---------------------------------------");
        }
    }

    public static void printForeignKeys() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            System.out.println(tableName + " table:");
            System.out.println();
            if (!foreignKeys.next()) {
                System.out.println("No foreign keys found for this table.");
            } else {
                do {
                    String foreign_key_column = foreignKeys.getString("FKCOLUMN_NAME");
                    String referenced_table_name = foreignKeys.getString("PKTABLE_NAME");
                    String referenced_column_name = foreignKeys.getString("PKCOLUMN_NAME");
                    System.out.print(foreign_key_column + " references " + referenced_table_name + "(" + referenced_column_name + ")");
                    System.out.println();
                } while (foreignKeys.next());
            }
            System.out.println();
            System.out.println("-----------------------------------------------------");
        }
    }
}