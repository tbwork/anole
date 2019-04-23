package org.tbwork.anole.common.protocol.impl;

import org.tbwork.anole.common.message.Message;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.protocol.IProtocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: anole-common
 * @description: Basic implement for IProtocol
 * @author: tommy.tb
 * @create: 2019-04-23 13:55
 **/
public abstract class Protocol implements IProtocol {

    private String protocolName;

    private Map<MessageType, MessageType> messageTypeMap = new HashMap<MessageType,MessageType>();


    public Protocol(String protocolName){
        this.protocolName = protocolName;
    }

    /**
     * Get the name of protocol.
     *
     * @return the protocol's name.
     */
    @Override
    public String getName() {
        return protocolName;
    }

    /**
     * Check whether the received message belongs to this protocol,
     * or not.
     *
     * @return
     */
    @Override
    public boolean checkMessage(Message message) {
        for(Map.Entry<MessageType, MessageType> mapItem : messageTypeMap.entrySet()){
            if(mapItem.getKey().equals(message.getType())){
                return true;
            }
        }
        return false;
    }


    protected void addMessageTypePair(MessageType requestMessageType, MessageType responseMessageType){
        messageTypeMap.put(requestMessageType, responseMessageType);
    }

    public abstract Object processAndResponse(Object request);


    /**
     * A protocol's basic feature is that the participant receives something,
     * deal with it, and then return something as response.
     *
     * @param message
     * @return
     */
    @Override
    public Message tackleWith(Message message) {
        Object request = message.getBody();
        Object response = processAndResponse(request);
        MessageType requestMessageType = message.getType();
        MessageType responseMessageType = messageTypeMap.get(requestMessageType);
        if(responseMessageType == null){
            responseMessageType = MessageType.VOID;
        }
        return new Message(responseMessageType, response);
    }
}
