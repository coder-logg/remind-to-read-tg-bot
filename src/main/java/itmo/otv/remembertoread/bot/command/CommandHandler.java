package itmo.otv.remembertoread.bot.command;

import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
	void addBook(Message message, SilentSender sender, MessageSender absSender);
	void deleteBook(MessageContext message, SilentSender sender);

}
