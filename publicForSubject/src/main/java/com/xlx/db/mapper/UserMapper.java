package com.xlx.db.mapper;

import com.xlx.db.entity.User;

public interface UserMapper {
	
	User login(User user);
	
	User checkUser(String user_name);

	boolean save(User user);
	
}
