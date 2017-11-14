package ibatis.services.user.test;

import java.io.Reader;
import java.util.List;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : MyBatisTestApp.java
  * �� JBDCTestApp.java �� �� ����.
  * �� MyBatis Framework ���� �����ϴ� API�� ��� users table �� ���� SELECT   
 */
public class MyBatisTestApp01 {
	
	///main method
	public static void main(String[] args) throws Exception{
		
		//==> mybatis-config.xml : MyBatis Framework �� �ٽ� MetaData
		//==> UserMapper.xml : SQL �� ���� MetaData 
		
		//==> 1. xml metadata �д� Stream ����
		Reader reader = Resources.getResourceAsReader("sql/mybatis-config01.xml");
		
		//==> 2. Reader ��ü�� �̿� xml metadata �� ������ ���� ������ ����, ��밡���� 
		//==>     SqlSession�� �����ϴ� SqlSessionFactory  instance ����
		SqlSessionFactory sqlSessionFactory 
											= new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		//==> 3. xml �� ��ϵ� query ���� �� ������ ���(?) ��ü�� ���� List ��ü �ޱ� 
		List<User> list01 = sqlSession.selectList("UserMapper01.getUserList");
		System.out.println("#####################################");
		System.out.println(":: ȸ������ ��� ::");
		
		for (User user : list01) {
			System.out.println( user.toString() ) ;
		}
		System.out.println("\n");
		
		User user = (User)sqlSession.selectOne("UserMapper01.getUser","user01");
		System.out.println("#####################################");
		System.out.println(":: user01 ���� ��� ::");
		System.out.println(user);
		System.out.println("\n");
		
		user.setUserId("user03");
		user.setPassword("user03");
		System.out.println("#####################################");
		System.out.println(":: user03 user_name ��� ::");
		System.out.println((String)sqlSession.selectOne("UserMapper01.findUserId", user));
		System.out.println("\n");
		
		System.out.println("#####################################");
		System.out.println(":: ���̰� 20 ��� ::");
		List<String> list02 = sqlSession.selectList("UserMapper01.getUserListAge", new Integer(20));
		//List<String> list02 = sqlSession.selectList("UserMapper01.getUserListAge", new Integer(20),new RowBounds(0,2));
		
		for (String name : list02) {
			System.out.println((list02.indexOf(name)+1)+"��° ȸ��::"+name);
		}
		
		//==> 3. SqlSession  close
		sqlSession.close();

	}// end of main
}//end of class

/*
 * �� MyBatisTestApp / JDBCTestApp �� ���� MyBatis Framework �� ����
 * �� SQL,Ŀ�ؼ�,Ʈ���輱 �� ��Ÿ����Ÿ�� ĸ��ȭ������, 
 *     :: ���� => mybatis-config.xml / UserMapper.xml
 * �� JDBCö�� :  Connection => Statement => ResultSet
 *      resource ���� : close
 *      query ���� ��� �����Ͻ���ü ���ε� JDBC API �� ����Ͽ� ����� �ݺ������� �ݵ��
 *      �����ϴ� �Ϸ��� ������ ������.
 *      :: ���� =>List<User> list = sqlSession.selectList("UserMapper.getUserList");
 *  
 *  �� MyBatis Framework �� ����
 *  �� �۰� �����ϴ� (�ٸ� ���̺귯���� �������� ����. )
 *  �� SQL Mapper(Data Mapper) =>SQL �� �����Ͻ���ü ���ε�
 *  �� ���꼺 / ���� / �۾��� �й� (�ҽ��ڵ�� SQL �� �и�)
 *  �� ���ɻ��� �и� 
 *       ( DBMS �� �������� API���� �� JDBC API�� �ƴ� �����Ͻ� ��ü�� ������ �۾�)
 *        
  *  �� MyBatis Framework �� JDBC ������ ����ȭ / JDBC ö���� �߻�ȭ / ĸ��ȭ�� lib 
*/
