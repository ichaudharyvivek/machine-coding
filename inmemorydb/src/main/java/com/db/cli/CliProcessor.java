package com.db.cli;

import java.util.Scanner;

import com.db.services.DatabaseService;

public class CliProcessor {
    private DatabaseService dbService;

    public CliProcessor(DatabaseService dbService) {
        this.dbService = dbService;
    }

    public void begin() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String nextLine = sc.nextLine();
            String[] commands = nextLine.split(" ");
            if (commands[0] == "exit") {
                break;
            }

            try {
                process(commands);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        sc.close();
    }

    private void process(String[] commands) {
        String dbCommand = commands[0];
        switch (dbCommand) {
            case "create":

                break;

            default:
                break;
        }
    }
}
