package com.icc.daelimi;

/**
 * 입력된 메세지와 입력자를 저장하는 객체
 *
 * ref. https://www.geeksforgeeks.org/how-to-create-a-chatbot-in-android-with-brainshop-api/
 */

public class MessageModal {
    private String message;
    private String sender;

    public MessageModal(String message, String sender){
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
