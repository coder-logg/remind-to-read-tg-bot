package itmo.otv.remembertoread.bot.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {
	@Value("${yandex.storage.key-id}")
	private String storageKeyId;

	@Value("${yandex.storage.key}")
	private String storageKey;


	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(storageKeyId, storageKey)))
				.withEndpointConfiguration(
						new AmazonS3ClientBuilder.EndpointConfiguration(
								"storage.yandexcloud.net", "ru-central1"
						)
				)
				.build();
	}
}
