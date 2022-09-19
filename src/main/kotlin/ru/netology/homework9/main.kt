package ru.netology.homework9

import java.lang.RuntimeException


data class Chat(
    val chatId: Int = 0, val title: String = "",
    var message: MutableList<Message> = mutableListOf()
)

data class Message(var ownerId: Int = 0, var messageId: Int = 0, var text: String = "", var read: Boolean = false)
data class ChatList(val chats: MutableList<Chat> = mutableListOf())
class NoChatListException : RuntimeException()
class NoChatException : RuntimeException()
class NoMessageException : RuntimeException()
object ChatService {
    private val chatLists = mutableMapOf<Int, ChatList>()
    fun clear() {
        chatLists.clear()

    }
    fun addChat(userId: Int, chat: Chat, message: Message) {

        chatLists.getOrPut(userId) { ChatList() }.chats.add(chat)
        addMessage(userId, chat.chatId, message)
    }

    fun deleteChat(userId: Int, chatId: Int) {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        chatList.chats.retainAll { it.chatId != chatId }

    }

    fun addMessage(userId: Int, chatId: Int, message: Message) {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        val chat = chatList.chats.find { it.chatId == chatId } ?: throw NoChatException()
        chat.message.add(message)
    }

    fun deleteMessage(userId: Int, chatId: Int, ownerId: Int, messageId: Int) {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        val chat = chatList.chats.find { it.chatId == chatId } ?: throw NoChatException()

        chat.message.removeAll {
            (it.messageId == messageId && it.ownerId == ownerId)

        }
        if (chat.message.isEmpty()) {
            deleteChat(userId, chatId)
        }
    }

    fun editMessage(userId: Int, chatId: Int, ownerId: Int, messageId: Int, text: String) {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        val chat = chatList.chats.find { it.chatId == chatId } ?: throw NoChatException()
        val message =
            chat.message.find { it.messageId == messageId && it.ownerId == ownerId } ?: throw NoMessageException()
        message.text = text

    }

    fun getUnreadMessage(userId: Int, chatId: Int, ownerId: Int, messageId: Int, count: Int): List<Message> {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        val chat = chatList.chats.find { it.chatId == chatId } ?: throw NoChatException()
        return chat.message.takeLastWhile {
            it.ownerId !== ownerId &&
                    it.messageId >= messageId &&
                    chat.message.indexOf(it) <= count
        }
            .onEach { it.read = true }
    }

    fun getUnreadChatWithUser(userId: Int, ownerId: Int): Any {
        val chatList = chatLists[userId] ?: throw NoChatListException()

        return when (chatList.chats.find {
            it.message.find { it.ownerId != ownerId && it.read==false } != null
        }) {
            null ->
             "No message"


            else -> chatList.chats.takeLastWhile {
                it.message.find { it.ownerId != ownerId && it.read==false } != null
            }
        }
    }

    fun getUnreadChatWithUserCount(userId: Int, ownerId: Int): Int {
        val chatList = chatLists[userId] ?: throw NoChatListException()
        var count = 0
        chatList.chats.takeLastWhile {
            it.message.find { it.ownerId != ownerId && !it.read } != null
        }.onEach { count += 1 }
        return count
    }

    fun printChatLists() {
        println(chatLists)
    }

}

fun main() {
    ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
    ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
    ChatService.printChatLists()
    ChatService.editMessage(1, 1, 2, 1, "Когда встреча?")
    ChatService.addMessage(1, 1, Message(1, 2, "sad"))
    ChatService.printChatLists()
    ChatService.deleteMessage(1, 1, 1, 2)

    ChatService.printChatLists()
    println(ChatService.getUnreadChatWithUser(1, 1))
    println(ChatService.getUnreadChatWithUserCount(1, 1))
    println(ChatService.getUnreadMessage(1, 1, 1, 1, 3))
    println(ChatService.getUnreadChatWithUser(1, 1))
    println(ChatService.getUnreadChatWithUserCount(1, 1))
}