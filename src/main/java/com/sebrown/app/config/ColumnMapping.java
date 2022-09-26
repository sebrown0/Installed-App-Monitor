package com.sebrown.app.config;

/**
 * 
 * @author SteveBrown
 *
 */
public interface ColumnMapping {
	int getColNumFor(String wb, String sheet, String colName);
	int getColNumForAuditOut(String sheet, String colName);
}
