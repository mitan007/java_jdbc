 package mysqltest;
import java.io.File;
import java.util.Scanner;

import mysql.mysql;

public class test {
	
	
    public static void main(String[] args){
    	//test.class.getClassLoader().getResource("login.txt").openStream();
    	File file = new File("E:login.txt");
        //实例化对象
        mysql vs = new mysql();
       
        //连接数据库
        vs.txt2String(file);
        
       //登陆
        vs.Login();
        
        for(;;){
   		System.out.println("请输入操作指令(输入空格则退出！)");
   		Scanner in=new Scanner(System.in);
 	   
   		
        String s=in.nextLine();
   		 if(s.equals(" ")){
   		 	System.out.println("已经结束输入！");
   		 	break;
   		 }
   		 else if(s.equals("select")){
   		 	vs.Select();
   		 }
   		 
   		 else if(s.equals("change")){
   			vs.txt2String(file);
   		 }
   		 else /*if(s.equals("insert"))*/{
   		 	vs.Insert();
   		 }
   		 }   
       
    }

}
