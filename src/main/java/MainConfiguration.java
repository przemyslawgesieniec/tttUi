package main.java;

import main.java.service.TicTacToeService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class MainConfiguration {

    @Bean
    public HessianProxyFactoryBean hessianInvoker()
    {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8000/tictactoe");
        invoker.setServiceInterface(TicTacToeService.class);
        return invoker;
    }

}
