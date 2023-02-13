package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Status;
import fwolves.assignment.repository.StatusRepository;
import fwolves.assignment.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public ResponseEntity<List<Status>> get() {
		return ResponseEntity.ok(statusRepository.findAll());
	}

	@Override
	public ResponseEntity<Status> get(String id) {
		Optional<Status> status = statusRepository.findById(id);
		if (!status.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(status.get());
	}

	@Override
	public ResponseEntity<Status> add(Status data) {
		Status status = statusRepository.save(data);
		return ResponseEntity.ok(status);
	}

	@Override
	public ResponseEntity<Status> update(String id, Status data) {
		Optional<Status> existOptional = statusRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Status updated = statusRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<Status> delete(String id) {
		Optional<Status> existOptional = statusRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		statusRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public Status findByIdStatus() {
		return statusRepository.findByIdStatus();
	}
}
