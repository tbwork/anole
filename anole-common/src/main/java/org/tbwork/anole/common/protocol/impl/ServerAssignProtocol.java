package org.tbwork.anole.common.protocol.impl;

import org.tbwork.anole.common.message.Message;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.protocol.IProtocol;

/**
 * @program: anole-common
 * @description: Server Assign Protocol
 * @author: tommy.tb
 * @create: 2019-04-23 13:52
 **/
public abstract class ServerAssignProtocol extends Protocol {


    public ServerAssignProtocol(){
        super("ServerAssign");
    }

    @Override
    public Object processAndResponse(Object request) {
        return null;
    }

    /**
     * Initialize the protocol.
     */
    @Override
    public void initialize() {
        this.addMessageTypePair(MessageType.C2S_ASK_FOR_SERVICE, );
    }
}
