package com.emsi.tp_jpa.web;

import com.emsi.tp_jpa.entities.Patient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.emsi.tp_jpa.repositories.PatientRepository;

@RestController
public class Patientcontroller {

	private PatientRepository patientRepository;
	@GetMapping("/patients")
	public List<Patient> patients(){
		return patientRepository.findAll();		
	}
	
	@GetMapping("/patients/{id}")
	public Patient patients(@PathVariable Long id ){
		return patientRepository.findById(id).get();		
	}
}
