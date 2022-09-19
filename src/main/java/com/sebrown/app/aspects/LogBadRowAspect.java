/**
 * 
 */
package com.sebrown.app.aspects;

import java.nio.file.Path;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 */
@Aspect
@Component
public class LogBadRowAspect {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution("
			+ "* com.sebrown.app.service.AuditRowCreatorService.createRow(..)) && "
			+ "args(ws, rowData, wbAuditted))")
	private void createRow(XSSFSheet ws, AppRowData rowData, Path wbAuditted) {}
	
	@Around("createRow(ws, rowData, wbAuditted)")
	public boolean logAnyError(
		ProceedingJoinPoint pjp, XSSFSheet ws, 
		AppRowData rowData, Path wbAuditted) throws Throwable {
		
		boolean isValidRow = (boolean) pjp.proceed();
		
		if(false == isValidRow) {		
			LOG.error(String.format(
					"[Create Row] Could not create row for the following params: "
					+ "[WB In: %s], [Sheet: %s], [Row data: %s]", 
					wbAuditted.getFileName(), ws.getSheetName(), rowData.toString()));
		}
		
		return isValidRow;
	}
	
}
