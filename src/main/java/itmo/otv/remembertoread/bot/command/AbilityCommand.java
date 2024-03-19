package itmo.otv.remembertoread.bot.command;

import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.SilentSender;

public interface AbilityCommand {
    void execute(MessageContext context, SilentSender sender);
}
