package events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.List;

public class DeleteMessages extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] message = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("!purge")){
            event.getMessage().delete().queue();
            purge(event.getChannel(), Integer.parseInt(message[1]));
        }

    }


    private void purge(TextChannel channel, int howMany){
        /** deleting only messages up to 2 weeks**/
        MessageHistory history = new MessageHistory(channel);
        List<Message> msgs;

        msgs = history.retrievePast(howMany).complete();
        channel.deleteMessages(msgs).queue();
    }
}
