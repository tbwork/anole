package org.tbwork.anole.common.message.server_assign.c_2_s;

import org.tbwork.anole.common.message.Message;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.model.server_assign.AssignServerRequest;

/**
 * @program: anole-common
 * @description: Request for a server to be connected to.
 * @author: tommy.tb
 * @create: 2019-04-23 15:21
 **/
public class AssignServerRequestMessage extends Message <AssignServerRequest>{

    public AssignServerRequestMessage() {
        super(MessageType.C2S_ASK_FOR_SERVICE);
    }

}
