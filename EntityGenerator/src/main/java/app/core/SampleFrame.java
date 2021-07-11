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

import app.connector.DataBaseConnector;
import config.Position;

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
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = DataBaseConnector
                            .executeSql("select * from " + comboBox.getItemAt(comboBox.getSelectedIndex()));
                    ResultSetMetaData rsmd = rs.getMetaData();
                    rsmd.getColumnCount();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // カラム名の出力
                        System.out.println(rsmd.getColumnTypeName(i + 1));
                        System.out.println(rsmd.getColumnName(i + 1));
                    }
                } catch (SQLException _e) {
                    _e.printStackTrace();
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
