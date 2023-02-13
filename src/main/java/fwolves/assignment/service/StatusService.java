package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Status;

public interface StatusService {

	public ResponseEntity<List<Status>> get();

	public ResponseEntity<Status> get(String id);

	public ResponseEntity<Status> add(Status data);

	public ResponseEntity<Status> update(String id, Status data);

	public ResponseEntity<Status> delete(String id);
	
	public Status findByIdStatus();

}
