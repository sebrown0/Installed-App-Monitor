/**
 * 
 */
package com.sebrown.app.helpers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

import com.sebrown.app.annotations.TestHelper;

/**
 * @author SteveBrown
 *
 * A basic implementation of ProceedingJoinPoint;
 * 
 * Be careful changing values, especially if not null
 * as they will be used in tests.
 * 
 */

@TestHelper
public class ProceedingJoinPointImpl implements ProceedingJoinPoint {

	@Override
	public Signature getSignature() {
		Signature sig =	new Signature() {
			
			@Override
			public String getDeclaringTypeName() {
				return "com.sebrown.app";
			}
			
			@Override
			public String getName() {
				return "methodName";
			}
			
			@Override
			public String toShortString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String toLongString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getModifiers() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public Class getDeclaringType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		return sig;
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[] {"Arg1", 22};
	}
	
	@Override
	public String toShortString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toLongString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SourceLocation getSourceLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getKind() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StaticPart getStaticPart() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set$AroundClosure(AroundClosure arc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object proceed() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object proceed(Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
}
