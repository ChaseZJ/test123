package com.zzq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jDemo {
	public static void test1(){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File("students.xml"));
			Element element = document.getRootElement();
			System.out.println(element.getName());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void test2(){
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(new File("students.xml"));
			Element element = doc.getRootElement();
			List list = element.elements();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Element studentElement = (Element) iterator.next();
				System.out.print(studentElement.getName()+","+studentElement.attribute("idcard").getValue());
				System.out.print(studentElement.element("name").getText());
				System.out.println(studentElement.element("sex").getText());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test3(){
		 XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream("b.xml"));
			Document document = DocumentHelper.createDocument();
			writer.write(document);
			writer.flush();
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void test4(){
		XMLWriter writer = null;
		Document document= DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("students");
		document.add(root);
		Attribute home = DocumentHelper.createAttribute(root,"home","zzq'home");
		root.add(home);
		Attribute man = DocumentHelper.createAttribute(root, "man", "zzq");
		root.add(man);
		Element student = DocumentHelper.createElement("student");
		root.add(student);
		Attribute idcard1 = DocumentHelper.createAttribute(student, "idcard", "001");
		student.add(idcard1);
		Element name1 = DocumentHelper.createElement("name");
		name1.setText("zzq");
		student.add(name1);
		Element sex1 = DocumentHelper.createElement("sex");
		sex1.setText("男");
		student.add(sex1);
		Element student1 = DocumentHelper.createElement("student");
		root.add(student1);
		Attribute idcard2= DocumentHelper.createAttribute(student, "idcard", "002");
		student1.add(idcard2);
		Element name2 = DocumentHelper.createElement("name");
		name2.setText("hqc");
		student1.add(name2);
		Element sex2 = DocumentHelper.createElement("sex");
		sex2.setText("女");
		student1.add(sex2);
		try {
			writer = new XMLWriter(new FileOutputStream("c.xml"),OutputFormat.createPrettyPrint());
			writer.write(document);
			writer.flush();
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("程序运行结束啦");
		}
	public static void main(String[] args) {
		test4();
	}

}
