package ru.netology.homework9

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MainKtTest {
    @Before
    fun clearBeforeTest() {
        ChatService.clear()

    }

    @Test
    fun addChat() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
    }

    @Test(expected = NoChatListException::class)
    fun deleteChatNoChatListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.deleteChat(2, chatId = 1)
    }

    @Test
    fun deleteChat() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.deleteChat(1, chatId = 1)
    }

    @Test(expected = NoChatListException::class)
    fun addMessageNoChatListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(2, 1, Message(2, 1, "Когда встреча"))
    }

    @Test(expected = NoChatException::class)
    fun addMessageNoChatException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 2, Message(2, 1, "Когда встреча"))
    }

    @Test
    fun addMessage() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
    }

    @Test(expected = NoChatListException::class)
    fun deleteNoChatListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.deleteMessage(2, 1, 2, 1)
    }


    @Test(expected = NoChatException::class)
    fun deleteNoChatException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.deleteMessage(1, 2, 2, 1)
    }

    @Test
    fun deleteMessage() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.deleteMessage(1, 1, 2, 1)
    }

    @Test
    fun deleteMessageDeleteChat() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.deleteMessage(1, 1, 1, 2)
        ChatService.deleteMessage(1, 1, 1, 1)
        ChatService.deleteMessage(1, 1, 2, 1)
    }

    @Test(expected = NoChatListException::class)
    fun editMessageNoChatListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.editMessage(2, 1, 2, 1, "Когда встреча?")
    }


    @Test(expected = NoChatException::class)
    fun editMessageNoChatException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.editMessage(1, 2, 2, 1, "Когда встреча?")
    }

    @Test(expected = NoMessageException::class)
    fun editMessageNoMessageException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.editMessage(1, 1, 2, 2, "Когда встреча?")
    }

    @Test
    fun editMessage() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))
        ChatService.editMessage(1, 1, 2, 1, "Когда встреча?")
    }

    @Test(expected = NoChatListException::class)
    fun getUnreadMessageListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadMessage(2, 1, 1, 1, 3)
    }


    @Test(expected = NoChatException::class)
    fun getUnreadMessageNoChatException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadMessage(1, 2, 1, 1, 3)
    }

    @Test
    fun getUnreadMessage() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadMessage(1, 1, 1, 1, 3)
    }

    @Test(expected = NoChatListException::class)
    fun getUnreadChatWithUserListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadChatWithUser(2, 1)
    }

    @Test
    fun getUnreadChatWithUser() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadChatWithUser(1, 1)
    }

    @Test
    fun getUnreadChatWithUserNull() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча", true))
        var result = ChatService.getUnreadChatWithUser(1, 1)
        assertTrue(result == "No message")
    }

    @Test(expected = NoChatListException::class)
    fun getUnreadChatWithUserCountListException() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadChatWithUserCount(2, 1)
    }

    @Test
    fun getUnreadChatWithUserCount() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.addMessage(1, 1, Message(2, 1, "Когда встреча"))

        ChatService.getUnreadChatWithUserCount(1, 1)
    }


    @Test
    fun printChatLists() {
        ChatService.addChat(1, Chat(chatId = 1, "Отдых"), Message(1, 1, "Привет"))
        ChatService.printChatLists()
    }
}