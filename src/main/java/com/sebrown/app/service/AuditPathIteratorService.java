/**
 * 
 */
package com.sebrown.app.service;

import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
@Scope("prototype")
public class AuditPathIteratorService {

	private final List<Path> paths;
	private final ListIterator<Path> pathItr;
	
	public AuditPathIteratorService(AuditInPathService pathService) {
		paths = pathService.getPaths();
		pathItr = paths.listIterator();
	}
	
	public boolean hasNext() {
		return pathItr.hasNext();
	}
	public Path getNext() {
		Path p = null;
		if(hasNext()) {
			p = pathItr.next();			
		}
		return p;			
	}	
	
}
