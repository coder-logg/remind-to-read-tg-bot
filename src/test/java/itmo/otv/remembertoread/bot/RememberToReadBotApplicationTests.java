package itmo.otv.remembertoread.bot;

import itmo.otv.remembertoread.bot.service.S3Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RememberToReadBotApplication.class)
@SpringBootTest
class RememberToReadBotApplicationTests {
	// "/home/aidar/Изображения/Wallpapers/wallpaperflare.com_wallpaper.jpg"

	@Autowired
	private S3Service s3Service;

	@Test
	void fileUploadTest() {
		Assertions.assertDoesNotThrow(() -> s3Service.uploadFile("rick and morty", new File("/home/aidar/Изображения/Wallpapers/wallpaperflare.com_wallpaper.jpg")));
	}

}
