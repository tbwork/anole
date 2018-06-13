package org.tbwork.anole.server.basic.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateRequest{ 
	private int clientId;
	private int token;  
}
