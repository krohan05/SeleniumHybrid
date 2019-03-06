package com.automation.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.reader.AppConfig;
import com.automation.interfaces.IdataReader;

public class DatabaseHelper implements IdataReader {

	private final Logger log = LoggerHelper.getLogger(DatabaseHelper.class);
	private String connectionStr = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	ResultSetMetaData md = null;

	public DatabaseHelper() {
		connectionStr = AppConfig.getConfig("DataBase.ConnectionStr");
		if ("".equalsIgnoreCase(connectionStr)) {
			setConnectionStr(connectionStr);
		}

	}

	public Statement getConnection() throws SQLException, ClassNotFoundException {
		String dbDriverName = AppConfig.getConfig("DataBase.DriverName");
		if ("Oracle".equalsIgnoreCase(AppConfig.getConfig("DataBase.Type"))) {
			Class.forName(dbDriverName);
			connection = DriverManager.getConnection(this.connectionStr);
		}

		return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	public void setConnectionStr(String connectionStr) {
		this.connectionStr = connectionStr;
	}

	public ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
		try {
			Statement exeStatment = getConnection();
			if (query.contains("select") || query.contains("Select")) {
				return exeStatment.executeQuery(query);
			} else {
				throw new IllegalArgumentException("Select query not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e);
			throw e;
		}
	}

	public int executeUpdate(String query) throws SQLException, ClassNotFoundException {
		try (Statement exeStatment = getConnection()) {
			if (!(query.contains("select") || query.contains("Select"))) {
				return exeStatment.executeUpdate(query);
			} else {
				throw new IllegalArgumentException("Dml query not found");
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public synchronized Map<String, Object> getRowData(String query) throws Exception {
		statement = getConnection();
		rs = statement.executeQuery(query);
		md = rs.getMetaData();
		int columns = md.getColumnCount();

		Map<String, Object> map = new HashMap<String, Object>(columns);
		while (rs.next()) {
			for (int i = 1; i <= columns; ++i) {
				map.put(md.getColumnName(i), rs.getObject(i));
			}
		}
		connection.close();
		return map;
	}

	@Override
	public synchronized Object[][] getData(String query, String... columnName)
			throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> tableData = getTableData(query, columnName);

		Object[][] data = new Object[tableData.size()][1];

		int i = 0;

		for (Map<String, Object> map : tableData) {
			data[i++][0] = map;
		}

		return data;
	}

	@Override
	public synchronized Object[][] getData(String query) throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> tableData = getTableData(query);
		Object[][] data = new Object[tableData.size()][1];

		int i = 0;

		for (Map<String, Object> map : tableData) {
			data[i++][0] = map;
		}

		return data;
	}

	public synchronized List<Map<String, Object>> getTableData(String query)
			throws SQLException, ClassNotFoundException {
		ResultSet set = executeQuery(query);
		ResultSetMetaData metaData = set.getMetaData();
		List<Map<String, Object>> data = new LinkedList<Map<String, Object>>();

		while (set.next()) {
			Map<String, Object> tableData = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				tableData.put(metaData.getColumnLabel(i), set.getObject(i));
			}
			data.add(tableData);
		}
		return data;
	}

	public synchronized List<Map<String, Object>> getTableData(String query, String... columnName)
			throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> tableData = getTableData(query);
		List<Map<String, Object>> filterData = new LinkedList<Map<String, Object>>();

		for (Map<String, Object> map : tableData) {
			Map<String, Object> filteMap = new LinkedHashMap<String, Object>();

			for (int i = 0; i < columnName.length; i++) {
				filteMap.put(columnName[i], map.get(columnName[i]));
			}
			filterData.add(filteMap);
		}
		return filterData;
	}

}
