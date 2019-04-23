package org.tbwork.anole.server.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor 
@NoArgsConstructor
public class UnregisterRequest{
	
	private int clientId; 
}
