import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main(args: Array<String>) {
    val bot = bot {
        token = "7504379155:AAEK76HT0xRH2eKyJC4wU4Lz8hMcrU3lCxE"
        dispatch {
            text {
                if (text.contains("www.instagram.com")) {
                    val messageToSend = LinkFixer.fixLink(text)
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = messageToSend)
                }
            }
        }
    }
    bot.startPolling()
}