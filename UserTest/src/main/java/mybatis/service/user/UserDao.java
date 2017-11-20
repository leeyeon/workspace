package mybatis.service.user;

import java.util.List;
import java.util.Map;

import mybatis.service.common.Search;
import mybatis.service.domain.User;

public interface UserDao {

	public int insertUser(User user) throws Exception;

	public User findUser(String userId) throws Exception;

	public List<User> getUserList(Search search) throws Exception;
	
	public int updateUser(User user) throws Exception;
	
	public int removeUser(String userId) throws Exception;

}