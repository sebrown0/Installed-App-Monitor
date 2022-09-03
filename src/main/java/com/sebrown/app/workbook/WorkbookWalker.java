/**
 * 
 */
package com.sebrown.app.workbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Get the paths of the files that start with certain text
 * and are on or below the given path.
 */
@Component
public class WorkbookWalker {
	
	public List<Path> getPathsOfWorkbooks(String thatStartWith, String onPath) {
		List<Path> workbooks = null;
		
		try (Stream<Path> wbPaths = Files.list(Paths.get(onPath))) {
			workbooks = wbPaths
					.filter(Files::isRegularFile)
					.filter(p -> p.getFileName().toString().startsWith(thatStartWith))
					.toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return workbooks;
	}

}
