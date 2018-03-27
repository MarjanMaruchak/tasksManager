package com.marjan.restful.controller;

import java.util.Arrays;
import java.util.List;

import com.marjan.restful.model.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

	final static Logger logger = Logger.getLogger(TaskController.class);

	@Autowired
	com.marjan.restful.service.TaskService TaskService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		TaskService.save(task);
		logger.debug("Added:: " + task);
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTask(@RequestBody Task task) {
		Task existingEmp = TaskService.getById(task.getId());
		if (existingEmp == null) {
			logger.debug("Task with id " + task.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			TaskService.save(task);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
		Task task = TaskService.getById(id);
		if (task == null) {
			logger.debug("Task with id " + id + " does not exists");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Task:: " + task);
		return new ResponseEntity<Task>(task, HttpStatus.FOUND);
	}


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getAllTask() {
		List<Task> tasks = TaskService.getAllq();
		if (tasks.isEmpty()) {
			logger.debug("Tasks does not exists");
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + tasks.size() + " Tasks");
		logger.debug(Arrays.toString(tasks.toArray()));
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
		Task task = TaskService.getById(id);
		if (task == null) {
			logger.debug("Task with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			TaskService.delete(id);
			logger.debug("Task with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
