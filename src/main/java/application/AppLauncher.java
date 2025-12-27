package application;

import simulation.Simulator;

public class AppLauncher {
    public static void main(String[] args) {
        //new Admin(0, "FIGUEIRAS", "Jossua", Staff.hashpass("123"), "contact@jossua.dev").AddMemberToDb();
        //new Technician(0, "FIGUEIRAS", "Jossua", Staff.hashpass("123"), "contact1@jossua.dev").AddMemberToDb();
        Thread simThread = new Thread(() ->{ new Simulator();});
        simThread.setDaemon(true); 
        simThread.start();

        System.out.print("\n Start interface");
        Login.main(args);

    }
}