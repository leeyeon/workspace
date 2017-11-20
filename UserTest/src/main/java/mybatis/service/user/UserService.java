package mybatis.service.user;

import java.util.List;

import mybatis.service.common.Search;
import mybatis.service.domain.User;

/*
 * 회원관리를 추상화 캡슐화한 UserService Interface
 */
public interface UserService {
	
	public int addUser(User user) throws Exception;
	
	public User loginUser(User user) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public List<User> getUserList(Search search) throws Exception;
	
	public int updateUser(User user) throws Exception;
	
	public int removeUser(String userId) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
}