package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogInfoMessage;

@Service
@Scope("prototype")
public class XSSFWorkbookService {
	private XSSFWorkbook wb;
	private OutputStream fileOut;  
	private String wbPath;
	
	public XSSFWorkbookService setWorkBookPath(String wbPath) {
		this.wbPath = wbPath;
		return this;
	}
	
	@LogInfoMessage(msg = "XSSFWorkbookService.getWorkbook: Getting XSSF Workbook.")
	public XSSFWorkbook getWorkbook() {//throws IOException  {
		if(Objects.isNull(wb)) {			
			try (FileInputStream file = new FileInputStream(new File(wbPath))){
				wb = new XSSFWorkbook(file);
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		
//    FileInputStream file = new FileInputStream(new File(fileLocation));
//    XSSFWorkbook workbook = new XSSFWorkbook(file);
        
		return wb;
	}
	
	public void flushWorkbook() {
		if(Objects.nonNull(wb)) {			
			try (OutputStream os = getFileOut()) {
				wb.write(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private OutputStream getFileOut() {
		if(Objects.isNull(fileOut)) {
			try (OutputStream fo = new FileOutputStream(new File(wbPath))){
				fileOut = fo;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileOut;
	}
	

}
