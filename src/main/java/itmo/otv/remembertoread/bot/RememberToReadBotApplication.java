package itmo.otv.remembertoread.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class RememberToReadBotApplication {
//	@Autowired
//	private ApplicationContext context;

	public static void main(String[] args) {
//		try {
			ConfigurableApplicationContext context = SpringApplication.run(RememberToReadBotApplication.class, args);
//			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot(context.getBean(RememberToReadBot.class));
//		} catch (TelegramApiException e) {
//			throw new RuntimeException(e);
//		}
	}

}
