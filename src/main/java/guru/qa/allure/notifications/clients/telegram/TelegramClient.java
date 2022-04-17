package guru.qa.allure.notifications.clients.telegram;

import guru.qa.allure.notifications.chart.Chart;
import guru.qa.allure.notifications.clients.Notifier;
import guru.qa.allure.notifications.config.ApplicationConfig;
import guru.qa.allure.notifications.config.enums.Headers;
import guru.qa.allure.notifications.config.telegram.Telegram;
import guru.qa.allure.notifications.template.HTMLTemplate;
import kong.unirest.Unirest;

import java.io.File;

public class TelegramClient implements Notifier {
    private final Telegram telegram = ApplicationConfig.newInstance()
            .readConfig().telegram();
    private final HTMLTemplate htmlTemplate = new HTMLTemplate();


    @Override
    public void sendText() {
        Unirest.post("https://api.telegram.org/bot{token}/sendMessage")
                .routeParam("token", telegram.token())
                .header("Content-Type", Headers.URL_ENCODED.header())
                .field("chat_id", telegram.chat())
                .field("reply_to_message_id", telegram.replyTo() + "")
                .field("text", htmlTemplate.create())
                .field("parse_mode", "HTML")
                .asString()
                .getBody();
    }

    @Override
    public void sendPhoto() {
        Chart.createChart();
        Unirest.post("https://api.telegram.org/bot{token}/sendPhoto")
                .routeParam("token", telegram.token())
                .field("photo",
                        new File("chart.png"))
                .field("chat_id", telegram.chat())
                .field("reply_to_message_id", telegram.replyTo())
                .field("caption", htmlTemplate.create())
                .field("parse_mode", "HTML")
                .asString()
                .getBody();
    }
}