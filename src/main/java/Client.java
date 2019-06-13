package main.java;

import main.java.service.TicTacToeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Client
{
    public static void main(String[] args)
    {
        TicTacToeService ticTacToeService = SpringApplication.run(Client.class, args).getBean(TicTacToeService.class);

        ticTacToeService.startGame("client1");
    }
}
