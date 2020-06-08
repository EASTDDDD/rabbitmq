package com.bfxy.entity;

import java.io.Serializable;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5392773867801678948L;

	private String id;
	 
    private String name;
 
    private String messageId;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
 
    public String getMessageId() {
        return messageId;
    }
 
    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }
}
