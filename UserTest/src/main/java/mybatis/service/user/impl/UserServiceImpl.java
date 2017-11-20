package mybatis.service.user.impl;

import java.util.List;

import mybatis.service.common.Search;
import mybatis.service.domain.User;
import mybatis.service.user.UserDao;
import mybatis.service.user.UserService;

public class UserServiceImpl implements UserService {
	
	///Field
	private UserDao userDao;
	
	///Constructor
	public UserServiceImpl() {
		userDao=new UserDaoImpl();
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	///Method
	public int addUser(User user) throws Exception {
		return userDao.insertUser(user);
	}

	public User loginUser(User user) throws Exception {
		User dbUser=userDao.findUser(user.getUserId());

		if(! dbUser.getPassword().equals(user.getPassword())){
			throw new Exception("로그인에 실패했습니다.");
		}
		
		return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDao.findUser(userId);
	}

	public List<User> getUserList(Search search) throws Exception {
		return userDao.getUserList(search);
	}

	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	public int removeUser(String userId) throws Exception {
		return userDao.removeUser(userId);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.findUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}
}