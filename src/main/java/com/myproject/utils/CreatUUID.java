package com.myproject.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myproject.dao.questionnaireMapper;
import com.myproject.pojo.user;
import com.myproject.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreatUUID {
	
	@Autowired
	private questionnaireMapper questionnaireMapper;

	public static String uuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getuuid(int number) {
		if (number < 1) {
			return null;
		}
		String[] retArray = new String[number];
		for (int i = 0; i < number; i++) {
			retArray[i] = uuid();
		}
		return retArray;
	}

	public static String exportWord(String templateFolder, String ftlFile, Map map, String localPath, String title)
			throws IOException {
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassicCompatible(true);
		configuration.setDirectoryForTemplateLoading(new File(templateFolder)); // 读取ftl 模板位置
		try {
			String datePath = DateUtils.getNowTime("yyyy/MM/dd");
			// 使用时间为文件夹生成word生成的位置
			String path = localPath + "/word/" + datePath;
			// 定义word文档名称
			String fileName = title + ".doc";
			// 创建文件夹
			File outFile = new File(path + "/" + fileName);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			// 读取 模板内容
			Template template = configuration.getTemplate(ftlFile, "utf-8");

			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

			template.process(map, out);

			out.flush();
			out.close();

			return path + "/" + fileName;
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String percent(double arg){      
         DecimalFormat df = new DecimalFormat("0.00%");
         return df.format(arg);
	
	}

			 
}
