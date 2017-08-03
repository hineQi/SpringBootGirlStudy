package com.hine.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;


public class FormaterUtil<T>{

	
	public static String convertPropertyToColmun(String property){
		char[] cs = property.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		for(char c : cs){
			if(c>='A'&&c<='Z'){
				stringBuffer.append("_");
			}
			stringBuffer.append(Character.toUpperCase(c));
		}
 		return stringBuffer.toString();
	}

	public static String getJdbcType(Field field){
		String fieldType = field.getType().getName();
		if(fieldType.endsWith("String")){
			return "VARCHAR";
		}else if(fieldType.endsWith("Date")){
			return "TIMESTAMP";
		}else{
			return "DECIMAL";
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, IOException {
		//new BY QiHaiYang
//		createXml("E:\\MyEclipse9.0\\B2C4.2WorkPlace-advance\\passcar-dal\\src\\main\\resources", "mybatis.presell","com.b2bex.presell.mapper", PresellActivity.class, "PRO_PRESELL_ACTIVITY", "PPA");
//		createMapperAndDao("E:\\MyEclipse9.0\\B2C4.2WorkPlace-advance\\passcar-dal\\src\\main\\java", PresellActivity.class, "com.b2bex.presell.mapper", BaseMapper.class, "com.b2bex.presell.dao", MyBatisDao.class);

//		createXml("D:\\WorkSpaceE\\v1\\passcar-dal\\src\\main\\resources", "mybatis.order","com.b2bex.order.mapper", GoodsModelStock.class, "car_goods_model_stock", "CGMS");
//		createMapperAndDao("D:\\WorkSpaceE\\v1\\passcar-dal\\src\\main\\java", GoodsModelStock.class, "com.b2bex.order.mapper", BaseMapper.class, "com.b2bex.order.dao", MyBatisDao.class);
//		createXml("E:\\workspace\\V4.3.0\\passcar-dal\\src\\main\\resources", "mybatis.order","com.b2bex.order.mapper", PurchasedGoodsTask.class, "SYS_PURCHASED_GOODS_TASK", "SPGT");
//		createMapperAndDao("E:\\workspace\\V4.3.0\\passcar-dal\\src\\main\\java", PurchasedGoodsTask.class, "com.b2bex.order.mapper", BaseMapper.class, "com.b2bex.order.dao", MyBatisDao.class);
		
//		createXml("E:\\workspace\\V1\\passcar-dal\\src\\main\\resources", "mybatis.order","com.b2bex.order.mapper", SalesOrderSnapTask.class, "TRA_SALES_ORDER_SNAP_TASK", "TSOST");
//		createMapperAndDao("E:\\workspace\\V1\\passcar-dal\\src\\main\\java", SalesOrderSnapTask.class, "com.b2bex.order.mapper", BaseMapper.class, "com.b2bex.order.dao", MyBatisDao.class);
		
//		createXml("E:\\workspace\\V1\\passcar-dal\\src\\main\\resources", "mybatis.order","com.b2bex.order.mapper", SalesOrderSnapshot.class, "TRA_SALES_ORDER_SNAPSHOT", "TSOS");
//		createMapperAndDao("E:\\workspace\\V1\\passcar-dal\\src\\main\\java", SalesOrderSnapshot.class, "com.b2bex.order.mapper", BaseMapper.class, "com.b2bex.order.dao", MyBatisDao.class);
//		
//		createXml("E:\\workspace\\V1\\passcar-dal\\src\\main\\resources", "mybatis.order","com.b2bex.order.mapper", SalesOrderHeaderSnapshot.class, "TRA_SALES_ORDER_HEADER_SNAP_TASK", "TSOHST");
//		createMapperAndDao("E:\\workspace\\V1\\passcar-dal\\src\\main\\java", SalesOrderHeaderSnapshot.class, "com.b2bex.order.mapper", BaseMapper.class, "com.b2bex.order.dao", MyBatisDao.class);
		
//		createXml("E:\\workspace\\V1\\passcar-dal\\src\\main\\resources", "mybatis.users","com.b2bex.users.mapper", UpdatePasswordRecord.class, "AUT_UPDATE_PASSWORD_RECORD", "AUPR");
//		createMapperAndDao("E:\\workspace\\V1\\passcar-dal\\src\\main\\java", UpdatePasswordRecord.class, "com.b2bex.users.mapper", BaseMapper.class, "com.b2bex.users.dao", MyBatisDao.class);
	}

	/**
	 * 
	 * @param <T>
	 * @param resourceBundlePath			资源包绝对路径，如"D:\\work3.3\\com.qeegoo.service\\com.b2bex.pay"
	 * @param mapperPackageName				*Mapper.xml文件的包名，如"com.b2bex.pay.mapper.statistics"
	 * @param entityClass					待转换成xml的IdEntity子类，类中如果包含非持久化字段，即没有对应的数据库字段存储，那么可以对该属性加“@Column(ColumnType=persistence.temporary)”注解
	 * @param tableName						对应的数据库表明
	 * @param tableAlias					数据库别名
	 * @throws ParserConfigurationException
	 */
	public static <T> void createXml(String resourceBundlePath, String mapperPathName, String mapperPackageName, Class<T> entityClass, String tableName, String tableAlias) throws ParserConfigurationException {
		String fileName = resourceBundlePath;
		for(String node : mapperPathName.split("\\.")){
			fileName = fileName + "\\" +node;
		}
		fileName = fileName + "\\" + entityClass.getSimpleName() + "Mapper.xml";
		System.out.println(fileName);
		createXml(fileName, entityClass, tableName, tableAlias, mapperPackageName);
	}
	
	public static <T> Field[] getAllField(Class<T> entityClass){
		Field[] fields = new Field[0];
		for(Field field : entityClass.getDeclaredFields()){
			Column col = field.getAnnotation(Column.class);
			if(col!=null){
				if(persistence.TEMPORARY.equals(col.ColumnType())){
					continue;
				}
			}
			Field[] _fields = new Field[1];
			_fields[0] = field;
			fields = concat(fields, _fields);
		}
		Field[] aLLFields = concat(null, fields);
		if(entityClass.getSimpleName().equals("IdEntity")){
			return aLLFields;
		}else{
			return getAllField(entityClass.getSuperclass(), aLLFields);
		}
	}

	public static <T> Field[] getAllField(Class<T> entityClass, Field[] fields){
//		Field[] aLLFields = concat(fields, entityClass.getDeclaredFields());
		for(Field field : entityClass.getDeclaredFields()){
			Column col = field.getAnnotation(Column.class);
			if(col!=null){
				if(persistence.TEMPORARY.equals(col.ColumnType())){
					continue;
				}
			}
			Field[] _fields = new Field[1];
			_fields[0] = field;
			fields = concat(fields, _fields);
		}
		Field[] aLLFields = concat(null, fields);
		if(entityClass.getSimpleName().equals("IdEntity")){
			return aLLFields;
		}else{
			return getAllField(entityClass.getSuperclass(), aLLFields);
		}
	}
	
	public static Field[] concat(Field[] f1, Field[] f2){
		if(f1==null){f1 = new Field[0];}
		if(f2==null){f2 = new Field[0];}
		Field[] alLFields = new Field[f1.length+f2.length];
		System.arraycopy(f1, 0, alLFields, 0, f1.length);
		System.arraycopy(f2, 0, alLFields, f1.length, f2.length);
		return alLFields;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param fileName		mapper.xml目标文件
	 * @param entityClass   待生成.xml的entity的class
	 * @param tableName		对应entity的表名
	 * @param tableAlias    表的别名
	 * @param mapperPackageName   对应interface-mapper.java的包名
	 * @throws ParserConfigurationException
	 */
	public static <T> void createXml(String fileName, Class<T> entityClass, String tableName, String tableAlias, String mapperPackageName) throws ParserConfigurationException {
		String entityName = entityClass.getSimpleName();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.newDocument();
		
		Field[] fields = getAllField(entityClass);
		
		//mapper.xml头与根节点
	    Element root = document.createElement("mapper");
	    root.setAttribute("namespace", mapperPackageName+"."+entityName+"Mapper");
		document.appendChild((Node) root);
		Element cache = (Element)document.createElement("cache");
		cache.setAttribute("eviction","LRU");
		cache.setAttribute("type", "org.mybatis.caches.memcached.MemcachedCache");
		root.appendChild(cache);
		
		//resultMap节点
		Element resultMap = (Element)document.createElement("resultMap");
		resultMap.setAttribute("id", entityName+"Map");
		resultMap.setAttribute("type", entityName);
//		Element id = (Element)document.createElement("result");
//		id.setAttribute("column", "ID");
//		id.setAttribute("property", "id");
//		id.setAttribute("jdbcType", "DECIMAL");
//		resultMap.appendChild(document.createTextNode("\n\t"));
//		resultMap.appendChild(id);
//		Element version = (Element)document.createElement("result");
//		version.setAttribute("column", "VERSION");
//		version.setAttribute("property", "version");
//		version.setAttribute("jdbcType", "DECIMAL");
//		resultMap.appendChild(document.createTextNode("\n\t"));
//		resultMap.appendChild(version);
//		Field[] fields = entityClass.getDeclaredFields();
		for(Field field : fields){
			if(!field.getName().equalsIgnoreCase("serialVersionUID")){
				String fieldName = field.getName();
				Element result = (Element)document.createElement("result");
				result.setAttribute("column", convertPropertyToColmun(fieldName));
				result.setAttribute("property", fieldName);
				result.setAttribute("jdbcType", getJdbcType(field));
				resultMap.appendChild(document.createTextNode("\n\t"));
				resultMap.appendChild(result);
			}
		}
		root.appendChild(resultMap);
		
		//base_column_list sql
		Element baseColumnList = (Element)document.createElement("sql");
		baseColumnList.setAttribute("id", "Base_Column_List");
		StringBuffer buffer = new StringBuffer();
		baseColumnList.appendChild(document.createTextNode("\n\t"));
//		buffer.append("ID,VERSION,");
		for(Field field : fields){
			if(!field.getName().equalsIgnoreCase("serialVersionUID")){
				buffer.append(convertPropertyToColmun(field.getName())+",");
			}
		}
		buffer.deleteCharAt(buffer.length()-1);
		((Node) baseColumnList).appendChild(document.createTextNode(buffer.toString()));
		baseColumnList.appendChild(document.createTextNode("\n"));
		root.appendChild(baseColumnList);
		
		//base_column_list with tableAlias sql
		Element baseColumnList_alias = (Element)document.createElement("sql");
		baseColumnList_alias.setAttribute("id", tableAlias+"_Base_Column_List");
		StringBuffer buffer_alias = new StringBuffer();
		baseColumnList_alias.appendChild(document.createTextNode("\n\t"));
//		buffer_alias.append((tableAlias!=null?(tableAlias+"."):"")+"ID,"+(tableAlias!=null?(tableAlias+"."):"")+"VERSION,");
		for(Field field : fields){
			if(!field.getName().equalsIgnoreCase("serialVersionUID")){
				buffer_alias.append((tableAlias!=null?(tableAlias+"."):"")+convertPropertyToColmun(field.getName())+",");
			}
		}
		buffer_alias.deleteCharAt(buffer_alias.length()-1);
		((Node) baseColumnList_alias).appendChild(document.createTextNode(buffer_alias.toString()));
		baseColumnList_alias.appendChild(document.createTextNode("\n"));
		root.appendChild(baseColumnList_alias);
		
		//get
		Element getElement = (Element)document.createElement("select");
		getElement.setAttribute("id", "get");
		getElement.setAttribute("resultMap", entityName+"Map");
		getElement.setAttribute("parameterType", "long");
		getElement.appendChild(document.createTextNode("\n\tselect"));
		getElement.appendChild(document.createTextNode("\n\t\t"));
		Element incluse1 = (Element)document.createElement("include");
		incluse1.setAttribute("refid", "Base_Column_List");
		getElement.appendChild(incluse1);
		getElement.appendChild(document.createTextNode("\n\tfrom "+tableName+" "+tableAlias));
		getElement.appendChild(document.createTextNode("\n\twhere ID = #{id,jdbcType=DECIMAL}\n"));
		root.appendChild(getElement);
		
		
		//delete
		Element delElement = (Element)document.createElement("delete");
		delElement.setAttribute("id", "delete");
		delElement.setAttribute("parameterType", "long");
		delElement.appendChild(document.createTextNode("\n\tdelete"));
		delElement.appendChild(document.createTextNode("\n\tfrom "+tableName+" "+tableAlias));
		delElement.appendChild(document.createTextNode("\n\twhere ID = #{id,jdbcType=DECIMAL}\n"));
		root.appendChild(delElement);
		
		//insert
		Element insElement = (Element)document.createElement("insert");
		insElement.setAttribute("id", "insert");
		insElement.setAttribute("parameterType", entityName);
		insElement.appendChild(document.createTextNode("\n\tinsert into "+tableName+"("));
		insElement.appendChild(document.createTextNode("\n\t\t"));
		Element incluse2 = (Element)document.createElement("include");
		incluse2.setAttribute("refid", "Base_Column_List");
		insElement.appendChild(incluse2);
		insElement.appendChild(document.createTextNode("\n\t) values ("));
//		insElement.appendChild(document.createTextNode("\n\t#{id,jdbcType=DECIMAL},"));
//		insElement.appendChild(document.createTextNode("\n\t#{version,jdbcType=DECIMAL}"));
		boolean firstFlag = true;
		for(Field field : fields){
			if(!field.getName().equalsIgnoreCase("serialVersionUID")){
				if(firstFlag){
					firstFlag = false;
				}else{
					insElement.appendChild(document.createTextNode(","));
				}
				insElement.appendChild(document.createTextNode("\n\t#{"+field.getName()+",jdbcType="+getJdbcType(field)+"}"));
			}
		}
		insElement.appendChild(document.createTextNode("\n\t)\n"));
		root.appendChild(insElement);
		
		
		//update
		Element updElement = (Element)document.createElement("update");
		updElement.setAttribute("id", "update");
		updElement.setAttribute("parameterType", entityName);
		updElement.appendChild(document.createTextNode("\n\tupdate "+tableName+" "+tableAlias));
		updElement.appendChild(document.createTextNode("\n\t"));
		Element setElement = (Element)document.createElement("set");
		setElement.appendChild(document.createTextNode("\n\t\tVERSION = VERSION + 1"));
		for(Field field : fields){
			String fieldName = field.getName();
			if(!fieldName.equalsIgnoreCase("serialVersionUID")
					&&!fieldName.equalsIgnoreCase("ID")
					&&!fieldName.equalsIgnoreCase("VERSION")){
				setElement.appendChild(document.createTextNode("\n\t\t"));
				Element ifElement = (Element)document.createElement("if");
				ifElement.setAttribute("test", fieldName+" != null");
				ifElement.appendChild(document.createTextNode("\n\t\t,"+convertPropertyToColmun(fieldName)+" = #{"+fieldName+",jdbcType="+getJdbcType(field)+"}"));
				ifElement.appendChild(document.createTextNode("\n\t\t"));
				setElement.appendChild(ifElement);
			}
		}
		setElement.appendChild(document.createTextNode("\n\t"));
		updElement.appendChild(setElement);
		updElement.appendChild(document.createTextNode("\n\twhere ID = #{id,jdbcType=DECIMAL} and version = #{version,jdbcType=DECIMAL}\n"));
		root.appendChild(updElement);
		
		
		//findListForExample
		Element exampleSelect = document.createElement("select");
		exampleSelect.setAttribute("id", "findListForExample");
		exampleSelect.setAttribute("parameterType", entityName);
		exampleSelect.setAttribute("resultMap", entityName+"Map");
		exampleSelect.appendChild(document.createTextNode("\n\tselect\n\t"));
		Element incluse3 = (Element)document.createElement("include");
		incluse3.setAttribute("refid", (tableAlias!=null?tableAlias+"_":"")+"Base_Column_List");
		exampleSelect.appendChild(incluse3);
		exampleSelect.appendChild(document.createTextNode("\n\tfrom "+tableName+" "+tableAlias+"\n\t"));
		Element incluse4 = (Element)document.createElement("include");
		incluse4.setAttribute("refid", "common_query_condition");
		exampleSelect.appendChild(incluse4);
		root.appendChild(exampleSelect);
		
		
		//findListForMap
		Element mapSelect = document.createElement("select");
		mapSelect.setAttribute("id", "findListForMap");
		mapSelect.setAttribute("parameterType", "map");
		mapSelect.setAttribute("resultMap", entityName+"Map");
		mapSelect.appendChild(document.createTextNode("\n\tselect\n\t"));
		Element incluse5 = (Element)document.createElement("include");
		incluse5.setAttribute("refid", (tableAlias!=null?tableAlias+"_":"")+"Base_Column_List");
		mapSelect.appendChild(incluse5);
		mapSelect.appendChild(document.createTextNode("\n\tfrom "+tableName+" "+tableAlias+"\n\t"));
		Element incluse6 = (Element)document.createElement("include");
		incluse6.setAttribute("refid", "common_query_condition");
		mapSelect.appendChild(incluse6);
		root.appendChild(mapSelect);
		
		//common_query_condition
		Element condition = document.createElement("sql");
		condition.setAttribute("id", "common_query_condition");
		condition.appendChild(document.createTextNode("\n\t"));
		Element where = document.createElement("where");
		
//		where.appendChild(document.createTextNode("\n\t\t"));
//		Element ifElement = document.createElement("if");
//		ifElement.setAttribute("test", "id != null");
//		ifElement.appendChild(document.createTextNode("\n\t\t\t"));
//		ifElement.appendChild(document.createTextNode((tableAlias!=null?tableAlias+".ID = ":"ID = ")+"#{id,jdbcType=DECIMAL}\n\t\t"));
//		where.appendChild(ifElement);
		
		for(Field field : fields){
			String fieldName = field.getName();
			if(!fieldName.equalsIgnoreCase("serialVersionUID")){
				where.appendChild(document.createTextNode("\n\t\t"));
				String type = getJdbcType(field);
				if("TIMESTAMP".equals(type)){
					Element ifElementBegin = (Element)document.createElement("if");
					ifElementBegin.setAttribute("test", fieldName+"Begin != null");
					ifElementBegin.appendChild(document.createTextNode("\n\t\t"));
					ifElementBegin.appendChild(document.createCDATASection("AND "+(tableAlias!=null?tableAlias+".":"")+convertPropertyToColmun(fieldName)+" >= to_date(#{"+fieldName+"Begin},'yyyy-mm-dd hh24:mi:ss')"));
					ifElementBegin.appendChild(document.createTextNode("\n\t\t"));
					where.appendChild(ifElementBegin);
					where.appendChild(document.createTextNode("\n\t\t"));
					Element ifElementEnd = (Element)document.createElement("if");
					ifElementEnd.setAttribute("test", fieldName+"End != null");
					ifElementEnd.appendChild(document.createTextNode("\n\t\t"));
					ifElementEnd.appendChild(document.createCDATASection("AND "+(tableAlias!=null?tableAlias+".":"")+convertPropertyToColmun(fieldName)+" <= to_date(#{"+fieldName+"End},'yyyy-mm-dd hh24:mi:ss')"));
					ifElementEnd.appendChild(document.createTextNode("\n\t\t"));
					where.appendChild(ifElementEnd);
				}else if("VARCHAR".equals(type)){
					Element ifElement = (Element)document.createElement("if");
					ifElement.setAttribute("test", fieldName+" != null");
					ifElement.appendChild(document.createTextNode("\n\t\tAND "+(tableAlias!=null?tableAlias+".":"")+convertPropertyToColmun(fieldName)+" LIKE '%'||#{"+fieldName+"}||'%'"));
					ifElement.appendChild(document.createTextNode("\n\t\t"));
					where.appendChild(ifElement);
				}else{
					Element ifElement = (Element)document.createElement("if");
					ifElement.setAttribute("test", fieldName+" != null");
					ifElement.appendChild(document.createTextNode("\n\t\tAND "+(tableAlias!=null?tableAlias+".":"")+convertPropertyToColmun(fieldName)+" = #{"+fieldName+"}"));
					ifElement.appendChild(document.createTextNode("\n\t\t"));
					where.appendChild(ifElement);
				}
			}
		}
		
		where.appendChild(document.createTextNode("\n\t"));
		condition.appendChild(where);
		root.appendChild(condition);
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
	
		try{
	
		Transformer transformer = tf.newTransformer();
	
		DOMSource source = new DOMSource(document);
		
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//mybatis.org//DTD Mapper 3.0//EN");
	
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		
//		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
	
		StreamResult result = new StreamResult(pw);
	
		transformer.transform(source, result);
	
		}catch(TransformerConfigurationException e){
	
		System.out.println(e.getMessage());
	
		}catch(IllegalArgumentException e){
	
		System.out.println(e.getMessage());
	
		}catch(TransformerException e){
	
		System.out.println(e.getMessage());
	
		} catch (FileNotFoundException e) {
	
		// TODO Auto-generated catch block
	
		e.printStackTrace();
	
		}

	}

	/**
	 * 初始化mapper.java及dao.java文件
	 * 如果该文件不存在，程序会自动创建
	 * 如果文件已存在，则不会再进行更新
	 * 存在的文件需要更新的，可以删除或重命名原文件后，再运行本程序
	 * @param <T>
	 * @param resourceBundlePath	资源包的绝对路径，具体到资源包文件夹这一层
	 * @param entityClass						
	 * @param mapperPackageName		mapper.java包名
	 * @param baseMapperClass
	 * @param daoPackageName		dao.java包名
	 * @param baseDaoClass
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void createMapperAndDao(String resourceBundlePath, Class<T> entityClass, String mapperPackageName, Class baseMapperClass, String daoPackageName, Class baseDaoClass) throws IOException {
		String mapperFileName = resourceBundlePath;
		for(String node : mapperPackageName.split("\\.")){
			mapperFileName = mapperFileName + "\\" +node;
		}
		mapperFileName = mapperFileName + "\\" + entityClass.getSimpleName() + "Mapper.java";
		File mapperFile=new File(mapperFileName);
		if(!mapperFile.exists()){
			mapperFile.createNewFile();
			FileOutputStream mapperfos = new FileOutputStream(mapperFile,true);
			StringBuffer mapperBuf = new StringBuffer();
			mapperBuf.append("package ").append(mapperPackageName).append(";\n\n");
			mapperBuf.append("import ").append(baseMapperClass.getName()).append(";\n");
			mapperBuf.append("import ").append(entityClass.getName()).append(";\n\n");
			mapperBuf.append("public interface ").append(entityClass.getSimpleName()).append("Mapper extends ").append(baseMapperClass.getSimpleName()).append("<").append(entityClass.getSimpleName()).append("> {\n\n}");
			System.out.println(mapperBuf.toString());
			try {
				mapperfos.write(mapperBuf.toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				mapperfos.close();
			}
		}
		
		
		String daoFileName = resourceBundlePath;
		for(String node : daoPackageName.split("\\.")){
			daoFileName = daoFileName + "\\" +node;
		}
		daoFileName = daoFileName + "\\" + entityClass.getSimpleName() + "Dao.java";
		File daoFile=new File(daoFileName);
		if(!daoFile.exists()){
			daoFile.createNewFile();
			FileOutputStream daofos = new FileOutputStream(daoFile,true);
			StringBuffer daoBuf = new StringBuffer();
			daoBuf.append("package ").append(daoPackageName).append(";\n\n");
			daoBuf.append("import org.springframework.stereotype.Component;\n");
			daoBuf.append("import ").append(baseDaoClass.getName()).append(";\n");
			daoBuf.append("import ").append(entityClass.getName()).append(";\n\n");
			daoBuf.append("@Component\n");
			daoBuf.append("public class ").append(entityClass.getSimpleName()).append("Dao extends ").append(baseDaoClass.getSimpleName()).append("<").append(entityClass.getSimpleName()).append("> {\n\n}");
			try {
				daofos.write(daoBuf.toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				daofos.close();
			}
		}
	}
}
