package app.main;

import java.sql.SQLException;

import app.core.SampleFrame;

public class EntityGeneratorApplication {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            new SampleFrame().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
