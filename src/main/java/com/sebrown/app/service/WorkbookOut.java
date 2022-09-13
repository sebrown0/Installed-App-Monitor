/**
 * 
 */
package com.sebrown.app.service;

import java.util.Optional;

/**
 * @author SteveBrown
 *
 */
public interface WorkbookOut {
	Optional<Integer> getColumnNumber(String forName);
}
