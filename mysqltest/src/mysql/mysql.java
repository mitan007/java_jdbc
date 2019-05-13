package mysql;
//import mysqltest.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class mysql{
	
	   public static Connection Conn;//数据库连接对象
	   //数据库连接地址
	   Scanner sc=new Scanner(System.in);
	   static Scanner sq=new Scanner(System.in);
	   public static String URL;//="jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
	   public static String user;//="root";//数据库用户名
	   public static String password; //="LH1998101739";//数据库密码
	   public static String[] segments; 
	   public static String txt2String(File file){
	         StringBuilder result = new StringBuilder();
	         try{ 
	             BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	             String t = null;
	             while((t = br.readLine())!=null){//使用readLine方法，一次读一行
	                  segments = t.split("\t");
	                  
	                 
	                  System.out.println("请输入要操作的数据库");
	 	              String r=sq.nextLine();
	                  
	 	              if(r.equals(" ")||r.equals("")) ;
	 	              else
	 	    		   segments[3]=r;
	                  }
	                  URL ="jdbc:mysql://"+segments[5]+":"+segments[6]+"/"+segments[3]+"?"+segments[4];
	                  user=segments[1];
	                  password=segments[2];
	             br.close();    
	         }catch(Exception e){
	             e.printStackTrace();
	         }
	         return result.toString();
	     }
	  
	 //
  
	   public static Connection getConnection() {
		   try{
			   Class.forName("com.mysql.jdbc.Driver");//加载驱动
			   System.out.println("驱动加载成功!");
		   } catch(ClassNotFoundException e){
			   e.printStackTrace();
		   }
		   try {

	            //通过DriverManager类的getConenction方法指定三个参数,连接数据库
	            Conn = DriverManager.getConnection(URL, user, password);
	            
	            System.out.println("连接数据库成功!!!");
                
	            //返回连接对象
	            return Conn;

	        } catch (SQLException e) {
	            // TODO: handle exception
	            e.printStackTrace();
	            return null;
	        }
	   }
	       // 连接对象
	       private Connection conn ;
	       // 传递sql语句
	       private Statement stt;
	       // 结果集
	       private ResultSet set;
	     
	       // 查询
	       public void Select() {
	           try {
	               // 获取连接
	               conn = getConnection();
	               if (conn == null)
	                   return;
	               // 定义sql语句
	               System.out.println("请输入sql指令");
	               String r = sc.nextLine();
	               // 执行sql语句
	               stt = conn.createStatement();
	               // 返回结果集
	               set = stt.executeQuery(r);
	               ResultSetMetaData data=set.getMetaData();
	               // 获取数据
	              //ResultSetMetaData rsmd=set.getMetaData();//获取结果集rs元数据
	               int colCount=data.getColumnCount();//获取列数
	               String[] colNames=new String[colCount];

	               for(int i=0;i<colCount;i++){
	               colNames[i] = data.getColumnName(i+1);//通过列索引取出列名
	               System.out.print(colNames[i]+"\t");
	               }
	               System.out.print("\n");
	               while(set.next()){
	            	 
	            	   for(String colName:colNames){
	            	   System.out.print(/*colName+":"+*/set.getString(colName)+"\t");
	            	   }
	            	   System.out.println();
	            	   }
	             
	           } catch (Exception e) {
	               e.printStackTrace();
	           } finally {

	               // 释放资源
	               try {
	                   set.close();
	                   conn.close();
	               } catch (Exception e2) {
	                   // TODO: handle exception
	               }

	           }
	       }
	       public void Login(){
	           
	           try {
	               //获取连接
	               conn = getConnection();
	               if(conn==null)
	               return;
	               
	               //获取用户输入的账号和密码
	               //Scanner input = new Scanner(System.in);
	               System.out.print("请输入用户名:");
	               String zh = sc.nextLine();
	               System.out.print("请输入密码:");
	               String mm = sc.nextLine();
	               //定义sql语句
	               String sql = "SELECT * from yonghu where zhanghao=? and mima=?";//用两个问号代替可以防sql注入暴力破解密码；
	               //StringBuffer s = new StringBuffer();
	               //sql.append("SELECT * from yonghu where zhanghao=? and mima=?");
	               //stt = conn.createStatement();
	               // 返回结果集
	               //stt.executeQuery(name);
	               PreparedStatement state=conn.prepareStatement(sql);                    //容器
	               state.setString(1, zh);                                        //将第n个值替换成某个值
	               state.setString(2, mm);
	               ResultSet re=state.executeQuery();                       //上传数据库返回结果集
	               
	               if(re.next()){    //如果取到了值，那么输出
	                   System.out.println("登陆成功"+re.getString(1)+"，欢迎你");
	               
	               }
	               else{
	                   System.out.println("登陆失败，账号或密码输入错误");
	                   System.exit(0);
	               }
	           } catch (Exception e) {
	               e.printStackTrace();
	           }finally{
	               //释放资源
	               try {
	                   
	                   conn.close();
	                   
	               } catch (Exception e2) {}
	               
	           }
	           
	           
	       }
	       public void Insert() {
	           try {
	               // 获取连接
	               conn = getConnection();
	               if (conn == null)
	                   return;
	               // 定义sql语句
	               System.out.println("请输入sql指令");
	               String r = sc.nextLine();
	               // 执行sql语句
	               //PreparedStatement ps = conn.prepareStatement(r);
	               
	               // 返回结果集
	              // ps.executeUpdate(r);
	               // 获取数据
	             //  ResultSetMetaData data=set.getMetaData();
	               // 获取数据
	               
	             // ResultSetMetaData rs=set.getMetaData();//获取结果集rs元数据
	               if(r.equals("show tables;")){
	            	   DatabaseMetaData meta = conn.getMetaData();
		               ResultSet set = meta.getTables(null, null, null, 
		            		   new String[] { "TABLE" });		  		  		         
		               while(set.next()){
		            	   System.out.println("表名：" + set.getString(3));
		            	   //System.out.println("表所属用户名：" + set.getString(2));
		            	   
	               }
	               }
	               
	               else{
	            // 执行sql语句
	               PreparedStatement ps = conn.prepareStatement(r);
	               
	               // 返回结果集
	               ps.executeUpdate(r);
	               // 获取数据
	             //  ResultSetMetaData data=set.getMetaData();
	               // 获取数据
	               
	             // ResultSetMetaData rs=set.getMetaData();//获取结果集rs元数据
	               }
	             
	           } catch (Exception e) {
	               e.printStackTrace();
	           } finally {

	               // 释放资源
	               try {
	                   set.close();
	                   conn.close();
	               } catch (Exception e2) {
	                   // TODO: handle exception
	               }

	           }
	       }	       
 }

