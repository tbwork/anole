package org.tbwork.anole.common.message.c_2_s.subscriber;

import lombok.Data;
import org.tbwork.anole.common.message.c_2_s.CommonAuthenticationMessage;
@Data
public class S2BCommonAuthenticationMessage extends CommonAuthenticationMessage {
 
	  private String environment;
}
