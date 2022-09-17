/**
 * 
 */
package com.sebrown.app.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Read the contents of a file into a List<String>.
 * 
 */
@Component
public class FileLineReader implements FileReader<List<String>> {
	
	private final Logger LOG = 
			LoggerFactory.getLogger(this.getClass());

	private List<String> lines;

	@Override
	public List<String> readFile(Path path) {
		return readLinesOrQuit(path);
	}
	
	public List<String> readLinesOrQuit(Path path) {
		if(Objects.nonNull(path)) {
			lines = new ArrayList<>();
			try (Stream<String> in = Files.lines(path)){
				in.forEach(line -> lines.add(line));
			} catch (Exception e) {
				LOG.error(String.format("Error reading file: %s. Quitting!", path.toString()));
				LOG.error("TODO: Create Aspect for this!");
				System.exit(-1);
			}	
		}
		return lines;		
	}
	
}
