package com.xlx.db.mapper;



import com.xlx.entity.User;

import java.util.List;

public interface UserMapper {
	
	User login(User user);
	
	User checkUser(String user_name);

	Integer save(User user);

	List<User> getAllUser();

	User query(User user);

	List<User> query();

	Integer delete(User user);

	Integer update(User user);




}
