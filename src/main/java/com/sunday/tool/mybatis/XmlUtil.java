package com.sunday.tool.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Element;

public class XmlUtil {

	static StringBuffer fullAnnotation(StringBuffer sb) {
		sb.append("/*\r\n");
		sb.append("Mybatis Util JAVA => SQL\r\n");
		sb.append("\r\n");
		sb.append("Date:" + new Date().toString() + "\r\n");
		sb.append("*/\r\n");
		return sb;
	}

	static StringBuffer fullSetting(StringBuffer sb) {
		sb.append("SET NAMES utf8mb4;\r\n");
		sb.append("SET FOREIGN_KEY_CHECKS = 0;\r\n");
		sb.append("\r\n");
		return sb;
	}

	static StringBuffer fullTable(StringBuffer sb, String tableTitle, List<Element> columns,
			HashMap<String, String> settingMap) {
		// 删除已存在表
		sb.append("DROP TABLE IF EXISTS `" + tableTitle + "`;\r\n");
		// 创建表
		sb.append("CREATE TABLE `" + tableTitle + "`  (\r\n");
		String key = "";
		for (Element column : columns) {
			// 主键
			if (column.getName().equals("id")) {

				String columnName = column.attribute("column").getStringValue();
				String jdbcType = column.attribute("jdbcType").getStringValue();
				System.out.println("主键：" + columnName + "   :" + jdbcType);
				key = columnName;
				sb.append(" `" + columnName + "` " + jdbcType + "(" + getTypeDigit(jdbcType)
						+ ") NOT NULL AUTO_INCREMENT,\r\n");
			} else {
				String columnName = column.attribute("column").getStringValue();
				String jdbcType = column.attribute("jdbcType").getStringValue();
				System.out.println("字段：" + columnName + "   :" + jdbcType);
				sb.append(" `" + columnName + "` " + jdbcType + "(" + getTypeDigit(jdbcType)
						+ ") NULL DEFAULT NULL,\r\n");
			}
		}
		sb.append("PRIMARY KEY (`" + key + "`) USING BTREE\r\n");
		sb.append(") ENGINE = " + settingMap.get("dataBaseEngine") + " AUTO_INCREMENT = 2 CHARACTER SET = "
				+ settingMap.get("dataBaseCharterSet") + " COLLATE = " + settingMap.get("dataBaseCollate") + " \r\n");
		sb.append("ROW_FORMAT = " + settingMap.get("dataBaseRowFormat") + ";\r\n");
		sb.append("\r\n");
		sb.append("SET FOREIGN_KEY_CHECKS = 1;");
		return sb;
	}

	private static String getTypeDigit(String jdbcType) {
		String typeDigit = "";
		switch (jdbcType) {
		case "CHAR":
			typeDigit = "255";
			break;
		case "VARCHAR":
			typeDigit = "255";
			break;
		case "LONGVARCHAR":
			typeDigit = "255";
			break;
		case "NUMERIC":
			typeDigit = "12,2";
			break;
		case "BIT":
			typeDigit = "1";
			break;
		case "TINYINT":
			typeDigit = "12";
			break;
		case "INTEGER":
			typeDigit = "12";
			break;
		case "REAL":
			typeDigit = "12";
			break;
		case "FLOAT":
			typeDigit = "12";
			break;
		case "DOUBLE":
			typeDigit = "12";
			break;
		case "BINARY":
			typeDigit = "12";
			break;
		case "TIMESTAMP":
			typeDigit = "0";
			break;
		}

		return typeDigit;
	}

	static ArrayList<File> getFiles(String path) throws Exception {
		ArrayList<File> fileList = new ArrayList<File>();
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File fileIndex : files) {

				if (fileIndex.isDirectory()) {
					getFiles(fileIndex.getPath());
				} else {
					fileList.add(fileIndex);

				}
			}
		}
		return fileList;
	}

	static HashMap<String, String> readProperties() {
		String filePath = null;
		String generatePath = null;
		HashMap<String, String> settingMap = new HashMap<>();
		String dataBaseEngine = "InnoDB";
		String dataBaseCharterSet = "utf8";
		String dataBaseCollate = "utf8_general_ci";
		String dataBaseRowFormat = "Dynamic";
		String rewirte = "false";
		try {
			ResourceBundle resource = ResourceBundle.getBundle("mybatis_tool");
			filePath = resource.getString("mybatis.filePath");
			generatePath = resource.getString("mybatis.generatePath");
			String temp = resource.getString("mybatis.database.ENGINE");
			if (!StringUtil.isBlank(filePath)) {
				dataBaseEngine = temp;
			}
			temp = resource.getString("mybatis.database.CHARACTER_SET");
			if (!StringUtil.isBlank(filePath)) {
				dataBaseCharterSet = temp;
			}
			temp = resource.getString("mybatis.database.COLLATE");
			if (!StringUtil.isBlank(filePath)) {
				dataBaseCollate = temp;
			}
			temp = resource.getString("mybatis.database.ROW_FORMAT");
			if (!StringUtil.isBlank(filePath)) {
				dataBaseRowFormat = temp;
			}

			temp = resource.getString("mybatis.rewirte");
			if (!StringUtil.isBlank(filePath)) {
				rewirte = temp;
			}
			settingMap.put("filePath", filePath);
			settingMap.put("generatePath", generatePath);
			settingMap.put("rewirte", rewirte);
			settingMap.put("dataBaseEngine", dataBaseEngine);
			settingMap.put("dataBaseCharterSet", dataBaseCharterSet);
			settingMap.put("dataBaseCollate", dataBaseCollate);
			settingMap.put("dataBaseRowFormat", dataBaseRowFormat);

		} catch (Exception e) {
			System.err.println("资源文件读取错误，请确认properties是否正确");
		}
		if (StringUtil.isBlank(filePath)) {
			System.err.println("mybtais sql 目录不能为空");
		}
		if (StringUtil.isBlank(generatePath)) {
			System.err.println("生成目录不能为空");
		}
		return settingMap;
	}

}
