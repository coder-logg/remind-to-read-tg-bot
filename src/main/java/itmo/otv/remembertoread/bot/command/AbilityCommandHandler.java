package itmo.otv.remembertoread.bot.command;

import itmo.otv.remembertoread.bot.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Component
@Slf4j
public class AbilityCommandHandler implements CommandHandler {
	@Autowired
	private S3Service s3Service;

	public

	@Override
	public void addBook(Message message, SilentSender sender, MessageSender absSender) {
		log.info("uploading new book. Message: {}", message);
		sender.send("please upload the book", message.getChat().getId());
		String[] splitedCmd = message.getCaption().trim().split("\\s+");
		log.info("splited command {}", Arrays.toString(splitedCmd));
		if (message.getDocument() != null) {
			sender.execute(new GetFile(message.getDocument().getFileId()))
					.ifPresent(file -> {
						try {
							s3Service.uploadFile(message.getFrom().getUserName() + "/" + splitedCmd[1], absSender.downloadFile(file.getFilePath()));
						} catch (TelegramApiException e) {
							throw new RuntimeException(e);
						}
					});
		}
		sender.send("adding book", message.getChat().getId());

	}

	@Override
	public void deleteBook(MessageContext context, SilentSender sender) {

	}
}
