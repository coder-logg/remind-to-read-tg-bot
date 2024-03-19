package itmo.otv.remembertoread.bot.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
@Slf4j
public class S3Service {
	@Value("${yandex.storage.bucket-name}")
	private String BUCKET_NAME;

	@Autowired
	private AmazonS3 s3Client;

	public void uploadFile(String name, File file) {
		try {
			upload(name, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void upload(String name, InputStream inputStream) {
		try {
			log.info("saving new object with name={} to bucket={}", name, BUCKET_NAME);
			PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, name, inputStream, new ObjectMetadata());
			PutObjectResult putObjectResult = s3Client.putObject(request);
			log.info("saved new object in storage: key='{}', requestResult={}", name, ToStringBuilder.reflectionToString(putObjectResult));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}
}
