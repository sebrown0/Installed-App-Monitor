/**
 * 
 */
package com.sebrown.app.error;

import java.util.List;

import com.sebrown.app.error.ErrorToHandle.ErrorSeverity;

/**
 * @author SteveBrown
 *
 */
public interface RaisedError {
	
	ErrorToHandle setFromClass(String fromClass);
	ErrorToHandle setFromMethod(String fromMethod);
	ErrorToHandle setArgs(List<String> args);
	ErrorToHandle setMsg(String msg);
	ErrorToHandle setSeverity(ErrorSeverity severity);
	
}
