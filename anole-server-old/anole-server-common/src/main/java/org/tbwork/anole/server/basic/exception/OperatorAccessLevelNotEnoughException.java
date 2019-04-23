package org.tbwork.anole.server.basic.exception;

public class OperatorAccessLevelNotEnoughException extends RuntimeException {

	private static final String errorMessage = "The access level of the operator (%s) is not matched with the requested level (%s) of target operation.";
	
	public OperatorAccessLevelNotEnoughException()
    {
		super(String.format(errorMessage, "unknown", "unknown"));
    }
	 
	
	public OperatorAccessLevelNotEnoughException(String operatorAL, String requiredAL)
    {
    	super(String.format(errorMessage, operatorAL, requiredAL));
    }
}
