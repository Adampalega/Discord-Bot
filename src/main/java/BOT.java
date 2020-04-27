import events.Calculator;
import events.HelloEvent;
import events.Weather;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BOT {
    public static void main(String [] args) throws LoginException {
        Dotenv dotenv = Dotenv.configure()
                .directory("src/assets")
                .load();

        JDA jda = new JDABuilder(dotenv.get("DISCORD")).build();

        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new Calculator());
        jda.addEventListener(new Weather());
    }
}
