package app.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import app.config.Position;
import app.connector.DataBaseConnector;

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
        contentPane.setBackground(Color.DARK_GRAY);
        addPanel.setBackground(Color.DARK_GRAY);

        // プルダウンの設定
        Set<String> tableNames = new HashSet<>();
        ResultSet tables = DataBaseConnector.executeSql("show tables");
        while (tables.next()) {
            tableNames.add(tables.getString(1));
        }
        JComboBox<String> comboBox = new JComboBox<>();
        tableNames.forEach(comboBox::addItem);

        JTextArea textArea = new JTextArea(50, 60);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.DARK_GRAY);
        JScrollPane scrollpane = new JScrollPane(textArea);
        JButton addButton = new JButton("Generate");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // エンティティクラス定義の生成
                textArea.setText(
                        new EntityGenerateProcessor().generateEntity(comboBox.getItemAt(comboBox.getSelectedIndex())));
            }
        });
        addPanel.add(comboBox);
        addPanel.add(addButton);
        addPanel.add(scrollpane);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        getContentPane().add(addPanel, BorderLayout.CENTER);
    }

}
