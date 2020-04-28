package events;

import helperclass.GetURL;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import helperclass.JsonReaderURL;

import java.awt.*;
import java.io.IOException;

public class Weather extends ListenerAdapter {

    @Override
    public void  onGuildMessageReceived(GuildMessageReceivedEvent event){
        Dotenv dotenv = Dotenv.configure()
                .directory("src/assets")
                .load();

        String[] message = event.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("!weather") && message[1].equalsIgnoreCase("dab")){
            try {
                String temp = JsonReaderURL.JSONstringer(dotenv.get("TOMEK"),"temp");
                event.getChannel().sendMessage("W Debie jest" + temp + " C").queue();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(message[0].equalsIgnoreCase("!weather") && message[1].equalsIgnoreCase("cisnienie")){
            try {
                String cisnienie = JsonReaderURL.JSONstringer(dotenv.get("TOMEK"),"pressure");
                event.getChannel().sendMessage("Jaworzno cisnienie " + cisnienie + " hpa").queue();
            } catch (IOException e){
                e.printStackTrace();
            }

        }else if(message[0].equalsIgnoreCase("!weather") && message[1].equalsIgnoreCase("test")){
            String test = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM25", "values", "name", "value");
            event.getChannel().sendMessage("Test  " + test ).queue();
        }else if(message[0].equalsIgnoreCase("!weather") && message[1].equalsIgnoreCase("air")) {
            String pm1 = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM1", "values", "name", "value");
            String pm25 = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM25", "values", "name", "value");
            String pm10 = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM10", "values", "name", "value");
            String pm25Percentage = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM25", "standards", "pollutant", "percent");
            String pm10Percentage = JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "PM10", "standards", "pollutant", "percent");
            String airDescription =  JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "AIRLY_CAQI", "indexes", "name", "description");
            int airConditionColor = Integer.parseInt(JsonReaderURL.JSONstrigerGet("https://airapi.airly.eu/v2/measurements/installation?indexType=AIRLY_CAQI&installationId=2953", "AIRLY_CAQI", "indexes", "name", "color" ).substring(1),16);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Air quality");
            eb.setColor(new Color(airConditionColor));
            eb.setDescription("PM25: " + pm25Percentage + " % \n" + "PM10: " + pm10Percentage + " % \n \n" +"PM1: " + pm1 + " ug/m^3 \n" + "PM25: " + pm25 + " ug/m^3 \n" + "PM10: " + pm10 +" ug/m^3\n \n" + airDescription );
            eb.setFooter("test");
            event.getChannel().sendMessage(eb.build()).queue();

        }else if(message[0].equalsIgnoreCase("!weather")){
            event.getChannel().sendMessage("Dostepne opcje: \n !weather dab, cisnienie" ).queue();
        }

    }
}

