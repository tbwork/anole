package org.tbwork.anole.server.basic.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResult {

	private int token;
	private int clientId;
	private boolean success;
}
