package mybatis.service.user;

import java.util.List;

import mybatis.service.domain.Search;
import mybatis.service.domain.User;

public interface UserService {
	
	// ȸ������
	public int addUser(User user) throws Exception;
	
	// ȸ�� �Ѹ��� ���� �˻�
	public User getUser(String userId) throws Exception;
	
	// ȸ������ ����
	public int updateUser(User user) throws Exception;
	
	// ȸ������ ����
	public int removeUser(String userId) throws Exception;
	
	// ȸ������ ����Ʈ �˻�
	public List<User> getUserList(Search search) throws Exception;
}
