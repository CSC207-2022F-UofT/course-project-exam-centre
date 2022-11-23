package driver;

import entities.StateTracker;
import fworks.da.PostgresAccessManager;
import ia.gateways.DatabaseAccessGateway;
import ui.MainFrame;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        new MainFrame();

//        StateTracker currentState = new StateTracker();
//        System.out.print("====    U of T Exam Centre     ===\n");
//        System.out.print("====  CSC 207 Course Project   ===");
//
//        try {
//            DatabaseAccessGateway db = new PostgresAccessManager("localhost", 5432,
//                    "postgres", "exam_centre", "postgrespw", false);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        while (currentState.getRunStatus()) {
//            // ...
//        }
    }
}
