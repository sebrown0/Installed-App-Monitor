/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

import com.sebrown.app.config.AppConfig.Workbook;

/**
 * @author SteveBrown
 *
 */
public interface WorkbookProperties {
	Map<String, Workbook> getWorkbooks();
}
