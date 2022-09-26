/**
 * 
 */
package com.sebrown.app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component @Scope("prototype")
public class TempFile {

	public static final String TEMP_ID = "TEMP_";
	
	private String filePath;
	private String tempFilePath;
	private boolean pathsOk;
	
	public void setPath(String fp) {		
		if(Objects.nonNull(fp)) {
			filePath = fp;
			Path p = Paths.get(filePath); 
			
			tempFilePath = 
				p.getParent().toString() + 
				"/" + TEMP_ID +	
				p.getFileName().toString();	
		}else {
			pathsOk = false;
		}
		pathsOk = true;
	}
	
	public void deleteTempFile() {
		var tempExists = Files.exists(
				Paths.get(tempFilePath));
		
		if(tempExists) {
			try {
				FileUtils.forceDelete(new File(tempFilePath));
			} catch (IOException e) {
				// TODO Log aspect
				e.printStackTrace();
			}				
		}	
	}
	
	public boolean createFile() {
		if(pathsOk) {
			try {
				FileUtils.copyFile(
						new File(filePath), new File(tempFilePath));			
			} catch (Exception e) {
				// TODO Log aspect 
				return false;
			}
			return true;			
		}else {
			return false;
		}			
	}
	
	public void restoreOriginal() {		
		try {
			FileUtils.copyFile(
					new File(tempFilePath), new File(filePath));
			FileUtils.forceDelete(
					new File(tempFilePath));
		} catch (IOException e) {
			// TODO Log aspect
			e.printStackTrace();
		}
	}
	
	public String getFilePath() {
		return filePath;
	}

	public String getTempFilePath() {
		return tempFilePath;
	}
	
}
