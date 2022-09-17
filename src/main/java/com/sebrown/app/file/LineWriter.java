/**
 * 
 */
package com.sebrown.app.file;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Append lines to given file.
 */
@Component
public class LineWriter implements FileWriter<List<String>>{

	private final Logger LOG = 
			LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void truncateExistingAndWrite(Path path, List<String> names) {
		if(Objects.nonNull(path) && Objects.nonNull(names)) {
			try  {				
				Files.write(path, names, TRUNCATE_EXISTING, WRITE);
			} catch (IOException e) {
				logError(path);
			}	
		}	else {
			logError(path);
		}
	}
	
	private void logError(Path p) {
		LOG.error(
			String.format("Cannot write to file: %s.", p.toString()));
		LOG.error("TODO: Create Aspect for this!");
	}

}
