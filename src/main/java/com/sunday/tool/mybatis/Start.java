package com.sunday.tool.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Start {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SAXReader reader = new SAXReader();

		HashMap<String, String> settingMap = XmlUtil.readProperties();

		try {
			System.out.println("--------------开始生成sql文件--------------");
			ArrayList<File> files = XmlUtil.getFiles(settingMap.get("filePath"));
			for (File file : files) {
				Document document = reader.read(file);
				Element rootDom = document.getRootElement();
				System.out.println("根路径：" + rootDom);
				if (!rootDom.getName().equals("mapper")) {
					System.err.println("该文件:" + file.getName() + "不符合mybatis规则，无法进行下步操作！");
					return;
				}

				String[] tableTitles = rootDom.element("resultMap").attribute("type").getStringValue().split("\\.");
				String tableTitle = StringUtil.toUnderline(tableTitles[tableTitles.length - 1]);

				List<Element> columns = rootDom.element("resultMap").elements();
				// 生成sql文件
				StringBuffer sb = new StringBuffer();
				sb = XmlUtil.fullAnnotation(sb);
				sb = XmlUtil.fullSetting(sb);
				sb = XmlUtil.fullTable(sb, tableTitle, columns, settingMap);
				FileUtil.createFile(settingMap.get("generatePath"), tableTitle + ".sql", sb.toString(),
						Boolean.valueOf(settingMap.get("rewirte")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------生成sql文件结束--------------");
	}
}
