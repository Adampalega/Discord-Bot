package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DeleteMessages extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] message = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("!purge")){
            int howMany = Integer.parseInt(message[1]);
            for(int i=0;i<=howMany;i++){
                event.getMessage().delete().queue();
            }
        }

    }
}
