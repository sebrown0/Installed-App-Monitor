/**
 * 
 */
package com.sebrown.app.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sebrown.app.config.Config;
import com.sebrown.app.workbook.WorkbookWalker;

/**
 * @author SteveBrown
 *
 * 	Get the WBs on the resource path that start
		with the Audit In prefix, i.e., ISO-Audit.
 */
@Service
public class AuditInPathService {
	
	private List<Path> paths;
	
	private final WorkbookWalker wbw;
	private final WorkbookService wbServ;
	
	public AuditInPathService(WorkbookWalker wbw, 
		WorkbookService wbServ) {
		
		this.wbw = wbw;
		this.wbServ = wbServ;
	}

	public List<Path> getPaths(Config props){
		if(Objects.isNull(paths)) {
			paths = wbw.getPathsOfWorkbooks(
					wbServ.getWbAuditIn().getNameStartsWith(), 
					props.getResourcePath());	
		}
		return paths;
	}
	
}
