package com.automation.interfaces;

import java.util.List;
import java.util.Map;


/**
 * @author Kumar Rohan
 *
 */
public interface IdataReader {

	public Map<String, Object> getRowData(String query) throws Exception;

	public Object[][] getData(String query, String... columnName) throws Exception;

	public Object[][] getData(String query) throws Exception;

	public List<Map<String, Object>> getTableData(String query) throws Exception;

	public List<Map<String, Object>> getTableData(String query, String... columnName) throws Exception;

}
