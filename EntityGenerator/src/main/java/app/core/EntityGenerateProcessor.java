package app.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import app.connector.DataBaseConnector;
import app.enums.ColumnType;
import app.utils.StringUtil;

public class EntityGenerateProcessor {
    public String generateEntity(String targetTable) {
        try {
            ResultSet rs = DataBaseConnector.executeSql("select * from " + targetTable + " limit 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            // 出力処理
            StringBuilder result = new StringBuilder().append("import javax.persistence.Column;\n")
                    .append("import javax.persistence.Entity;\n").append("import javax.persistence.GeneratedValue;\n")
                    .append("import javax.persistence.GenerationType;\n").append("import javax.persistence.Id;\n")
                    .append("import javax.persistence.Table;\n").append("\n").append("import lombok.Data;\n")
                    .append("import lombok.EqualsAndHashCode;\n").append("/**\n").append(" * ")
                    .append(rsmd.getTableName(1)).append(" entity.\n").append(" */\n").append("@Data\n")
                    .append("@Entity\n").append("@Table(name = \"").append(rsmd.getTableName(1)).append("\")\n")
                    .append("@EqualsAndHashCode(callSuper = false)\n").append("public class ")
                    .append(StringUtil.snakeToCamel(rsmd.getTableName(1), false)).append("{\n").append("\n");
            rsmd.getColumnCount();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // カラム名の出力
                result.append("    @Column(name = \"").append(rsmd.getColumnName(i + 1)).append("\")\n")
                        .append("    private ")
                        .append(ColumnType.valueOf(rsmd.getColumnTypeName(i + 1)).getObjectType()).append(" ")
                        .append(StringUtil.snakeToCamel(rsmd.getColumnName(i + 1), true)).append(";\n");
            }
            result.append("\n").append("}\n");
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
