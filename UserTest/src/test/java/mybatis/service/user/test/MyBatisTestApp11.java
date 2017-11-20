package mybatis.service.user.test;

import java.util.ArrayList;

import mybatis.service.common.Search;
import mybatis.service.domain.User;
import mybatis.service.user.impl.UserDaoImpl;

import org.apache.ibatis.session.SqlSession;

/*
 * FileName : MyBatisTestApp11.java 
 *  :: Persistence Layer unit Test : MyBatis + DAO
  */
public class MyBatisTestApp11 {
	
	///main method
	public static void main(String[] args) throws Exception{

		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		//==> UserDaoImpl11 ���� �� sqlSession instance setter injection
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setSqlSession(sqlSession);
		System.out.println("\n");
		
		//==> Test �� User instance ����  
		User user = new User("test01","�ָ�","test01","user","null","010-1234-5678",
				"����� ������","bit@gmail.com");
		
		//1. addUser Test  
		System.out.println(":: 1. addUser(INSERT)  ? ");
		System.out.println(":: "+ userDao.insertUser(user) );
		System.out.println("\n");
		
		//2. getUser Test :: �ָ� inert Ȯ�� 
		System.out.println(":: 2. getUser(SELECT)  ? ");
		System.out.println(":: "+ userDao.findUser(user.getUserId()) );
		System.out.println("\n");

		//3. uadateUser Test  :: �ָ� ==> �´� ����
		user.setUserName("�´�");
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+userDao.updateUser(user) );
		System.out.println("\n");
		
		//4. getUserList Test ::
		System.out.println(":: 4. getUserList(SELECT)  ? ");
		Search search = new Search();
		search.setSearchCondition("1");
		search.setSearchKeyword("�´�");
		
		System.out.println(":: List<User> ���� : "+ userDao.getUserList(search) );
		System.out.println("\n");
		
		//5. removeUser Test
		System.out.println(":: 5. removeUser (DELETE)  ? ");
		System.out.println(":: "+userDao.removeUser(user.getUserId()) );
		System.out.println("\n");
		
		//6. getUserList Test 
		System.out.println(":: 6. getUserList(SELECT)  ? ");
		System.out.println("List<User> ���� : "+ userDao.getUserList(search) );
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class