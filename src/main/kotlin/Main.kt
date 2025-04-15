import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.channel
import com.github.kotlintelegrambot.dispatcher.chatMember
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main(args: Array<String>) {
    val bot = bot {
        token = System.getenv("TELEGRAM_TOKEN")
            ?: error("TELEGRAM_TOKEN environment variable is not set!")
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