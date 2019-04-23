package org.tbwork.anole.server.protocal;

import org.tbwork.anole.common.message.Message;

public interface IProtocol {

    public String getName();

    /**
     * Check whether the received message belongs to this protocol,
     * or not.
     * @return
     */
    public boolean checkMessage();

    /**
     * A protocol's basic feature is that the participant receives something,
     * deal with it, and then return something as response.
     * @param message
     * @return
     */
    public Message tackleWith(Message message);


}
