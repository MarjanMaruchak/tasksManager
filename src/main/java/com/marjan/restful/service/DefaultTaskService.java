package com.marjan.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marjan.restful.model.Task;
import com.marjan.restful.repository.TaskRepository;


@Service
public class DefaultTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task save(Task entity) {
		return taskRepository.save(entity);
	}

	@Override
	public Task getById(Serializable id) {
		return taskRepository.findOne((Long) id);
	}

	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		taskRepository.delete((Long) id);
	}

}
