package org.tbwork.anole.server.model.request;


import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tbwork.anole.common.enums.ClientType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private SocketChannel socketChannel; 
	private RegisterParameter registerParameter;
	private ClientType clientType; 
}
