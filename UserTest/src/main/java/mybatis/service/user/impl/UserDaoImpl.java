package mybatis.service.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.service.common.Search;
import mybatis.service.domain.User;
import mybatis.service.user.UserDao;

import java.util.List;


public class UserDaoImpl implements UserDao {
	
	///Field
	private SqlSession sqlSession;
	
	///Constructor
	public UserDaoImpl() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	///Method
	@Override
	public int insertUser(User user) throws Exception {
		return sqlSession.insert("UserMapper.insertUser", user);
	}
	
	@Override
	public User findUser(String userId) throws Exception {
		
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}

	@Override
	public List<User> getUserList(Search search) throws Exception {
		
		return sqlSession.selectList("UserMapper.getUserList", search);
	}

	@Override
	public int updateUser(User user) throws Exception {

		return sqlSession.update("UserMapper.updateUser", user);
	}
	
	@Override
	public int removeUser(String userId) throws Exception {

		return sqlSession.update("UserMapper.removeUser", userId);
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
//	private int getTotalCount(String sql) throws Exception {
//		
//		sql = "SELECT COUNT(*) "+
//		          "FROM ( " +sql+ ") countTable";
//		
//		Connection con = DBUtil.getConnection();
//		PreparedStatement pStmt = con.prepareStatement(sql);
//		ResultSet rs = pStmt.executeQuery();
//		
//		int totalCount = 0;
//		if( rs.next() ){
//			totalCount = rs.getInt(1);
//		}
//		
//		pStmt.close();
//		con.close();
//		rs.close();
//		
//		return totalCount;
//	}
//	
//	// 게시판 currentPage Row 만  return 
//	private String makeCurrentPageSql(String sql , Search search){
//		sql = 	"SELECT * "+ 
//					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
//									" 	FROM (	"+sql+" ) inner_table "+
//									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
//					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +
//										" AND "+search.getCurrentPage()*search.getPageSize();
//				// order by -> rownum < 'page x pageSize' -> between ~ and ~
//		
//		System.out.println("UserDAO :: make SQL :: "+ sql);	
//		
//		return sql;
//	}

}