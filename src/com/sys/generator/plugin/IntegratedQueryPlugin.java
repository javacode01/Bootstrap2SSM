package com.sys.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
/**
 * 综合查询插件
 * @author Administrator
 *
 */
public class IntegratedQueryPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		integratedQuery(topLevelClass, introspectedTable);
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	private void integratedQuery(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		//生成引入类
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"java.util.Map"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"com.sys.utils.BeanUtils"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"java.util.Arrays"));
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("List<Criteria>")); 
		method.setName("integratedQuery");
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				"java.util.Map"), "queryCondationMap"));
		String methodBody=getMethodBody();
		method.addBodyLine(methodBody);
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		
	}
	private String getMethodBody(){
		String methodBody=""+
		"    Criteria criteria = createCriteriaInternal();\n"+
		"    for(Object key : queryCondationMap.keySet()) {\n"+
		"		String field = ((String)key).split(\"@\")[0];\n"+
		"		String opt = ((String)key).split(\"@\")[1];\n"+
		"		if(((String)key).contains(\"between\")){\n"+
		"         criteria.addCriterion(BeanUtils.camelToUnderline(field)+\" \"+opt,((String)queryCondationMap.get(key)).split(\",\")[0],((String)queryCondationMap.get(key)).split(\",\")[1],(String)key);\n"+
		"		}else if(((String)key).contains(\"IS NULL\")||((String)key).contains(\"IS NOT NULL\")){\n" +
		"         criteria.addCriterion(BeanUtils.camelToUnderline(field)+\" \"+opt);\n"+
		"		}else if(((String)key).contains(\"@IN\")||((String)key).contains(\"@NOT IN\")){\n" +
		"         String values = (String)queryCondationMap.get(key);\n" +
		" 		  List val=Arrays.asList(values.split(\",\"));\n" +
        " 		  criteria.addCriterion(BeanUtils.camelToUnderline(field)+\" \"+opt,val,(String)key);\n" +
		"		}else{\n"+
		"         criteria.addCriterion(BeanUtils.camelToUnderline(field)+\" \"+opt,queryCondationMap.get(key),(String)key);\n"+
		"		}\n"+
		"    }\n"+
		"	 oredCriteria.add(criteria);\n"+
		"    return oredCriteria;\n";
		return methodBody;
	}
}
