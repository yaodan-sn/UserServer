package com.mybatis.generator;

/**
 * Created by 草帽boy on 2017/2/16.
 * 启动文件，只需要点击运行就行
 */
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class StartUp {
	public static void main(String[] args) throws URISyntaxException {
		try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			File configFile = new File(StartUp.class.getResource("").getPath().split("com")[0]
					+ "generatorConfig.xml");

			System.out.println(configFile.getAbsolutePath());
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println(warnings);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}