package com.sys.generator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sys.generator.consts.GeneratorConsts;
import com.sys.generator.db.AnalysisDB;
import com.sys.generator.model.TableMeta;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author
 */
public class GeneratorJspMain {

	public static void main(String[] args) throws IOException,
			TemplateException {
		List<TableMeta> tableList;
		String targetDir = "WebContent/"+GeneratorConsts.TARGET_DIR;

		tableList = AnalysisDB.readDB();
		AnalysisDB.readTables(tableList);
		// 输出到文件
		File dir = new File(targetDir);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		generatorJspEdit(tableList,targetDir);
		generatorJspManage(tableList,targetDir);
		generatorJsManage(tableList,targetDir);
		generatorController(tableList,GeneratorConsts.BASE_PACKAGE.replace(".","/")+"/action"+GeneratorConsts.BASE_MODULE.replace(".","/"));
		generatorService(tableList,GeneratorConsts.BASE_PACKAGE.replace(".","/")+"/service"+GeneratorConsts.BASE_MODULE.replace(".","/"));
	}
	/**
	 * 创建eidt.jsp
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	private static void generatorJspEdit(List<TableMeta> tableList,String targetDir) 
			throws IOException, TemplateException{
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/com/sys/generator"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template tpl = cfg.getTemplate("templates/editjsp.ftl");
		if (tableList != null) {
			for (TableMeta tm : tableList) {
				if (StringUtils.isBlank(tm.getClassName()))
					continue;
				out = new FileWriter(new File(targetDir + "edit"+tm.getClassName().toLowerCase()+".jsp"));
				tpl.process(tm, out);
				System.out.println("===文件 " + "edit"+tm.getClassName().toLowerCase()+".jsp"	+ " 生成成功===");
			}
		}

		out.flush();
		out.close();
	}
	/**
	 * 创建manage.jsp
	 * @param tableList
	 * @param targetDir
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generatorJspManage(List<TableMeta> tableList,String targetDir)
			throws IOException, TemplateException{
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/com/sys/generator"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template tpl = cfg.getTemplate("templates/managejsp.ftl");
		if (tableList != null) {
			for (TableMeta tm : tableList) {
				if (StringUtils.isBlank(tm.getClassName()))
					continue;
				out = new FileWriter(new File(targetDir +"manage"+tm.getClassName().toLowerCase()+".jsp"));
				tpl.process(tm, out);
				System.out.println("===文件 "+"manage"+tm.getClassName().toLowerCase()+".jsp 生成成功===");
			}
		}

		out.flush();
		out.close();
	}
	private static void generatorJsManage(List<TableMeta> tableList,String targetDir)
			throws IOException, TemplateException{
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/com/sys/generator"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template tpl = cfg.getTemplate("templates/managejs.ftl");
		if (tableList != null) {
			for (TableMeta tm : tableList) {
				if (StringUtils.isBlank(tm.getClassName()))
					continue;
				out = new FileWriter(new File(targetDir +"manage"+tm.getClassName().toLowerCase()+".js"));
				tpl.process(tm, out);
				System.out.println("===文件 "+"manage"+tm.getClassName().toLowerCase()+".js 生成成功===");
			}
		}

		out.flush();
		out.close();
	}
	
	private static void generatorController(List<TableMeta> tableList,String targetDir) 
			throws IOException, TemplateException{
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/com/sys/generator"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template tpl = cfg.getTemplate("templates/java.controller.ftl");
		if (tableList != null) {
			for (TableMeta tm : tableList) {
				if (StringUtils.isBlank(tm.getClassName()))
					continue;
				out = new FileWriter(new File("src/"+targetDir+"/"+tm.getClassName()+"Controller.java"));
				tpl.process(tm, out);
				System.out.println("===文件 " +tm.getClassName()+"Controller.java 生成成功===");
			}
		}

		out.flush();
		out.close();
	}
	private static void generatorService(List<TableMeta> tableList,String targetDir) 
			throws IOException, TemplateException{
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("src/com/sys/generator"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template tpl = cfg.getTemplate("templates/java.service.ftl");
		if (tableList != null) {
			for (TableMeta tm : tableList) {
				if (StringUtils.isBlank(tm.getClassName()))
					continue;
				out = new FileWriter(new File("src/"+targetDir+"/"+tm.getClassName()+"Service.java"));
				tpl.process(tm, out);
				System.out.println("===文件 " +tm.getClassName()+"Service.java 生成成功===");
			}
		}

		out.flush();
		out.close();
	}
	
}