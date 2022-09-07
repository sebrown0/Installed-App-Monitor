/**
 * 
 */
package com.sebrown.app.error;

import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
@Scope("prototype")
public class ErrorToHandle implements 
	RaisedError, ErrorHandler, ErrorMessage {
		
	public enum ErrorSeverity {
		LOW, MEDIUM, HIGH, CRITICAL
	}

	private final com.sebrown.app.error.LogAndHandleError errHandler;
	
	private String fromClass = "unknown";
	private String fromMethod = "unknown";
	private String msg = "unknown";
	private ErrorSeverity severity;
	private List<String> args;
			
	public ErrorToHandle(com.sebrown.app.error.LogAndHandleError errHandler) {		
		this.errHandler = errHandler;
	}

	@Override //RaisedError
	public ErrorToHandle setFromClass(String fromClass) {
		this.fromClass = fromClass;
		return this;
	}
	@Override //RaisedError
	public ErrorToHandle setFromMethod(String fromMethod) {
		this.fromMethod = fromMethod;
		return this;
	}
	@Override //RaisedError
	public ErrorToHandle setArgs(List<String> args) {
		this.args = args;
		return this;
	}
	@Override //RaisedError
	public ErrorToHandle setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	@Override //RaisedError
	public ErrorToHandle setSeverity(ErrorSeverity severity) {
		this.severity = severity;
		return this;
	}

	public String getFromClass() {
		return fromClass;
	}

	public String getFromMethod() {
		return fromMethod;
	}

	public String getMsg() {
		return msg;
	}

	public ErrorSeverity getSeverity() {
		return severity;
	}
		
	private String getArgsAsString() {
		if(Objects.nonNull(args)) {
			StringBuilder sb = new StringBuilder();
			for(String s : args) {
				sb.append(s + ", ");			
			}
			String str = sb.toString();
			return str.substring(0, str.length() - 2);	
		}
		return "none";
	}
	
	private String getSeverityAsStr() {
		return (Objects.nonNull(severity)) ? severity.name() : "unknown";
	}
	
	@Override //ErrorMessage
	public String getMessage() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		String str = 
			String.format("[%s.%s], with args[%s], caused and error of severity [%s], with msg[%s]", 
					fromClass, fromMethod, getArgsAsString(), 
					getSeverityAsStr(), msg);
		
		return str;
	}
		
	@Override //ErrorHandler
	public void handleError(boolean quitting) {
		errHandler.handleError(quitting, severity);			
	}
	
}
