package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Calculator extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] message = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("!calculator") && message.length != 4){
            event.getChannel().sendMessage("Wpisz !calculator X + lub - lub / lub * Y").queue();
        }else if(message[0].equalsIgnoreCase("!calculator") && message[2].equalsIgnoreCase("+")){
            int x = Integer.parseInt(message[1]);
            int y = Integer.parseInt(message[3]);
            event.getChannel().sendMessage("Wynik: " + (x+y)).queue();

        }else if(message[0].equalsIgnoreCase("!calculator") && message[2].equalsIgnoreCase("-")){
            int x = Integer.parseInt(message[1]);
            int y = Integer.parseInt(message[3]);
            event.getChannel().sendMessage("Wynik: " + (x-y)).queue();

        }else if(message[0].equalsIgnoreCase("!calculator") && message[2].equalsIgnoreCase("*")){
            int x = Integer.parseInt(message[1]);
            int y = Integer.parseInt(message[3]);
            event.getChannel().sendMessage("Wynik: " + (x*y)).queue();

        }else if(message[0].equalsIgnoreCase("!calculator") && message[2].equalsIgnoreCase("/")){
            double x = Integer.parseInt(message[1]);
            double y = Integer.parseInt(message[3]);
            double result = x / y;
            String result2 = Double.toString(result);
            event.getChannel().sendMessage("Wynik: " + (result2)).queue();

        }
    }
}
