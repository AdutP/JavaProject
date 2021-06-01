package ro.uaic.info.rssowl;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import ro.uaic.info.rssowl.Commands.AddCmd;
import ro.uaic.info.rssowl.Commands.ICommand;
import ro.uaic.info.rssowl.Commands.NewCmd;
import ro.uaic.info.rssowl.Commands.PingCmd;

import java.util.HashMap;
import java.util.Map;

public class DiscordBot {

    private static final Map<String, ICommand> commands = new HashMap<String, ICommand>();
    static {
        commands.put("ping", new PingCmd());

        commands.put("new", new NewCmd());

        commands.put("add", new AddCmd());
    }


    private final DiscordClient client;
    private GatewayDiscordClient gateway = null;

    public DiscordBot(String token) {
        client = DiscordClient.create(token);
    }

    public void connect() {
        gateway = client.login().block();
    }

    public void listen() {
        gateway.on(MessageCreateEvent.class)
                // subscribe is like block, in that it will *request* for action
                // to be done, but instead of blocking the thread, waiting for it
                // to finish, it will just execute the results asynchronously.
                .subscribe(event -> {
                    final String content = event.getMessage().getContent(); // 3.1 Message.getContent() is a String
                    for (final Map.Entry<String, ICommand> entry : commands.entrySet()) {
                        // We will be using ! as our "prefix" to any command in the system.
                        if (content.startsWith('!' + entry.getKey())) {
                            entry.getValue().execute(event);
                            break;
                        }
                    }
                });
    }

    public void block() {
        gateway.onDisconnect().block();
    }
}
