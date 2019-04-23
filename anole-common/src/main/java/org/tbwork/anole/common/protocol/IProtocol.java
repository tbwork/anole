package org.tbwork.anole.common.protocol;

import org.tbwork.anole.common.message.Message;

public interface IProtocol {

    /**
     * Get the name of protocol.
     * @return the protocol's name.
     */
    public String getName();


    /**
     * Initialize the protocol.
     */
    public void initialize();

    /**
     * Check whether the received message belongs to this protocol,
     * or not.
     * @return
     */
    public boolean checkMessage(Message mesage);

    /**
     * A protocol's basic feature is that the participant receives something,
     * deal with it, and then return something as response.
     * @param message
     * @return
     */
    public Message tackleWith(Message message);


}
