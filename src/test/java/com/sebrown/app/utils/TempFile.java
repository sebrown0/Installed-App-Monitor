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

/**
 * @author SteveBrown
 *
 */
public class TempFile {

	public static final String TEMP_ID = "TEMP_";
	
	private static String filePath;
	private static String tempFilePath;
	private static boolean pathsOk;
	
	public static void setPath(String fp) {		
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
	
	public static void deleteTempFile() {
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
	
	public static boolean createFile() {
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
	
	public static void restoreOriginal() {		
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
	
	public static String getFilePath() {
		return filePath;
	}

	public static String getTempFilePath() {
		return tempFilePath;
	}
	
}
