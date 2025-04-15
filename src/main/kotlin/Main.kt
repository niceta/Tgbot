import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main(args: Array<String>) {
    val bot = bot {
        token = "TG_API_TOKEN"
        dispatch {
            text {
                if (text.contains("www.instagram.com")) {
                    val messageToSend = LinkFixerService.getFixedLink(text)
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = messageToSend)
                }
            }
        }
    }
    bot.startPolling()
}