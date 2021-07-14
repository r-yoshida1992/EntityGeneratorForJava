package app.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.config.Position;
import app.connector.DataBaseConnector;
import app.enums.ColumnType;
import app.utils.StringUtil;

public class SampleFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public SampleFrame() throws SQLException {
        setTitle("Entity Generator");
        // ×ボタンでアプリ終了
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウ幅
        setBounds(Position.TOP, Position.LEFT, Position.WIDTH, Position.HEIGHT);
        // アイコンの設定
        setIconImage(new ImageIcon("src/main/resources/image/icon.png").getImage());

        // textFieldの配置
        JPanel contentPane = new JPanel();
        JPanel addPanel = new JPanel();

        // プルダウンの設定
        Set<String> tableNames = new HashSet<>();
        ResultSet tables = DataBaseConnector.executeSql("show tables");
        while (tables.next()) {
            tableNames.add(tables.getString(1));
        }
        JComboBox<String> comboBox = new JComboBox<>();
        tableNames.forEach(comboBox::addItem);

        JButton addButton = new JButton("Generate");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    ResultSet rs = DataBaseConnector.executeSql(
                            "select * from " + comboBox.getItemAt(comboBox.getSelectedIndex()) + " limit 1");
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // 出力処理
                    String header = new StringBuilder().append("import javax.persistence.Column;\n")
                            .append("import javax.persistence.Entity;\n")
                            .append("import javax.persistence.GeneratedValue;\n")
                            .append("import javax.persistence.GenerationType;\n")
                            .append("import javax.persistence.Id;\n").append("import javax.persistence.Table;\n")
                            .append("\n").append("import lombok.Data;\n").append("import lombok.EqualsAndHashCode;\n")
                            .append("/**\n").append(" * ").append(rsmd.getTableName(1)).append(" entity.\n")
                            .append(" */\n").append("@Data\n").append("@Entity\n").append("@Table(name = \"")
                            .append(rsmd.getTableName(1)).append("\")\n")
                            .append("@EqualsAndHashCode(callSuper = false)\n").append("public class ")
                            .append(StringUtil.snakeToCamel(rsmd.getTableName(1), false)).append("{\n").append("\n")
                            .toString();
                    System.out.print(header);
                    rsmd.getColumnCount();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // カラム名の出力
                        System.out.println("    @Column(name = \"" + rsmd.getColumnName(i + 1) + "\")");
                        System.out.println(
                                "    private " + ColumnType.valueOf(rsmd.getColumnTypeName(i + 1)).getObjectType() + " "
                                        + StringUtil.snakeToCamel(rsmd.getColumnName(i + 1), true) + ";");
                    }
                    String footer = new StringBuilder().append("\n").append("}\n").toString();
                    System.out.print(footer);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        addPanel.add(comboBox);
        addPanel.add(addButton);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        getContentPane().add(addPanel, BorderLayout.CENTER);
    }

}
