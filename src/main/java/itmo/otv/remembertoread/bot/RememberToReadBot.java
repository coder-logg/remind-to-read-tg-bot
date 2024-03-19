package itmo.otv.remembertoread.bot;

import itmo.otv.remembertoread.bot.command.AbilityCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class RememberToReadBot extends AbilityBot {
    static final int CREATOR_ID = 1000;

    @Autowired
    private AbilityCommandHandler commandHandler;

    public HashMap<String, UserState> userStates = new HashMap<>();


    public RememberToReadBot(Environment env) {
        super(env.getProperty("bot.token"), env.getProperty("bot.name"));
    }

    @Override
    public long creatorId() {
        return CREATOR_ID;
    }


    // todo save, info, sendMsgRegularly

    public Ability addBook() {
        return Ability
                .builder()
                .name("addbook")
                .info("adding book to personal storage")
                .locality(Locality.USER)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> commandHandler.addBook(ctx.update().getMessage(), silent, sender))
                .reply((bot, upd) -> {
                    upd.getMessage().setText(upd.getMessage().getCaption());
                    commandHandler.addBook(upd.getMessage(), silent, sender);
                }, upd -> {
					String caption = upd.getMessage().getCaption();
					return caption != null && caption.trim().split("\\s+")[0].equals("/addbook");
                })
                .input(1)
                .enableStats()
                .build();
    }
}
