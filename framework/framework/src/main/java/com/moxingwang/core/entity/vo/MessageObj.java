package com.moxingwang.core.entity.vo;

/**
 * 功能：错误消息对象
 */
public class MessageObj {
    private String message_id;//消息的编号(在properties中定义的ID)
    private String message_obj;//消息对应的界面字段名
    private String message;//消息内容(多国语言的消息文字)

    public MessageObj() {
    }

    public MessageObj(String message_id, String message) {
        this.message_id = message_id;
        this.message = message;
    }

    public MessageObj(String message_id,  String message,String message_obj) {
        this.message_id = message_id;
        this.message_obj = message_obj;
        this.message = message;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_obj() {
        return message_obj;
    }

    public void setMessage_obj(String message_obj) {
        this.message_obj = message_obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
