package com.marjan.restful.service;

import java.io.Serializable;
import java.util.List;

import com.marjan.restful.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marjan.restful.model.Task;
import com.marjan.restful.repository.TaskRepository;


@Service
public class DefaultTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Override
	public Task save(Task entity) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		entity.setUser_name(authentication.getName());
		return taskRepository.save(entity);
	}

	@Override
	public Task getById(Serializable id) {
		return taskRepository.findOne((Long) id);
	}

	@Override
	public List<Task> getAllq() {
		return taskRepository.getAllq();
	}


	@Override
	public void delete(Serializable id) {
		taskRepository.delete((Long) id);
	}

}
