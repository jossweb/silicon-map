package application;

import domain.Admin;
import domain.Staff;
import domain.Technician;

public class AppLauncher {
    public static void main(String[] args) {
        //new Admin(0, "FIGUEIRAS", "Jossua", Staff.hashpass("123"), "contact@jossua.dev").AddMemberToDb();
        //new Technician(0, "FIGUEIRAS", "Jossua", Staff.hashpass("123"), "contact1@jossua.dev").AddMemberToDb();

        Login.main(args);
    }
}