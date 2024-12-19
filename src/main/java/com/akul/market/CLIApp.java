package com.akul.market;

import com.akul.market.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CLIApp implements CommandLineRunner {
    private final ApplicationContext context;

    @Autowired
    public CLIApp(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(CLIApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 3) {
            throw new Exception("it must be 3 parameters!",
                    new IllegalArgumentException("Invalid number of arguments"));
        }

        String operation = args[0];
        String inputFilePath = args[1];
        String outputFilePath = args[2];

        FileService fileService = context.getBean(FileService.class);

        switch (operation) {
            case "search": {
                fileService.search(inputFilePath, outputFilePath);
                break;
            }
            case "stat": {
                fileService.stat(inputFilePath, outputFilePath);
                break;
            }
        }

    }

}
