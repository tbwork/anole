package org.tbwork.anole.server.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResult {

	private int token;
	private int clientId;
	private boolean success;
}
