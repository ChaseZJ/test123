package com.zzq;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xmldemo {
	public static void test1() {
		Properties p = new Properties();
		p.setProperty("gz.srouce.code", "gz");
		p.setProperty("gz.srouce.", "gz1");
		p.setProperty("gz", "gz2");
		try {
			p.storeToXML(new FileOutputStream("a.xml"), "这是我第一个xml文件","utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test2() {
		Properties p = new Properties();
		try {
			p.loadFromXML(new FileInputStream("a.xml"));
			System.out.println(p.getProperty("gz.srouce.code"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test3(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse("students.xml");
			Element e = d.getDocumentElement();
			System.out.println(e.getNodeName());
			System.out.println(e.getAttribute("schoolMaster"));
			NodeList studentlist = e.getChildNodes();
			for (int i = 0; i < studentlist.getLength(); i++) {
				Node student = studentlist.item(i);
				if ("student".equals(student.getNodeName())) {
					System.out.println(student.getNodeName());
				}
				//System.out.println(student.getNodeName());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test4(){
		DocumentBuilderFactory sbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = sbf.newDocumentBuilder();
			Document d = db.parse("students.xml");
			NodeList students = d.getElementsByTagName("student");
			for (int i = 0; i < students.getLength(); i++) {
				Node student = students.item(i);
				System.out.println(student.getNodeName());
				NodeList studentlist = student.getChildNodes();
				for (int j = 0; j < studentlist.getLength(); j++) {
					Node node = studentlist.item(j);
					//System.out.println(node.getNodeName());
					if ("name".equals(node.getNodeName())) {
						System.out.println(node.getTextContent());
					}
					if ("sex".equals(node.getNodeName())) {
							System.out.println(node.getTextContent());
						}
				}
			}
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<Student> getStudentList(){
		ArrayList<Student> list = new ArrayList<Student>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse("students.xml");
			NodeList studentlist = document.getElementsByTagName("student");
			for (int i = 0; i < studentlist.getLength(); i++) {
				Student stu = new Student();
				Node student = studentlist.item(i);
				String idcard = student.getAttributes().item(0).getNodeValue();
				stu.setIdcard(idcard);
				NodeList nodeList = student.getChildNodes();
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node node = nodeList.item(j);
					if ("name".equals(node.getNodeName())) {
						stu.setName(node.getTextContent());
					}
						if ("sex".equals(node.getNodeName())) {
							stu.setSex(node.getTextContent());
					}
				}
				list.add(stu);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		//test4();
		for (Iterator iterator = getStudentList().iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			System.out.println(student.getIdcard());
			System.out.println(student.getName());
			System.out.println(student.getSex());
		}
	}

}
class Student{
	String idcard;
	String name;
	String sex;
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Student(String idcard, String name, String sex) {
		super();
		this.idcard = idcard;
		this.name = name;
		this.sex = sex;
	}
	public Student() {
		super();
	}
	
}