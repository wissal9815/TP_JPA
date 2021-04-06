package com.emsi.tp_jpa;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.emsi.tp_jpa.entities.Patient;
import com.emsi.tp_jpa.repositories.PatientRepository;



@SpringBootApplication
public class TpJpaApplication implements CommandLineRunner{
	
	@Autowired	
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//CREATION
		
		patientRepository.save(new Patient(null,"Hassan",new Date(),2200,false));
		patientRepository.save(new Patient(null,"Farah",new Date(),200,false));
		patientRepository.save(new Patient(null,"Imane",new Date(),1300,false));
		patientRepository.save(new Patient(null,"Aymane",new Date(),300,false));
		patientRepository.save(new Patient(null,"Yassine",new Date(),2300,true)); 
		
		System.out.println("***************************");
		
		//AFFICHAGE
		
		patientRepository.findAll().forEach(p->{
			System.out.println(p.toString());
		});
		
		System.out.println("***************************");
		
		//TROUVER PATIENT PAR ID
		
		Patient patient = patientRepository.findById(4L).get();
		System.out.println(patient.getNom());
		System.out.println("***************************");
		
		//TROUVER PATIENT PAR NOM
		
		List<Patient> patients = patientRepository.findByNomContains("F");
		patients.forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
		
		//TROUVER PATIENT PAR MALADE
		
		List<Patient> patients2 = patientRepository.findByMalade(true);
		patients2.forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
		
		//TROUVER PATIENT PAR NOM ET MALADE
		
		List<Patient> patients3 = patientRepository.findByNomContainsAndMalade("a",true);
		patients2.forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
		
		//SUPPRESSION D'UN PATIENT
		
		//patientRepository.deleteById(5L);
		
		//AFFICHER POUR TESTER
		
		List<Patient> patients4 = patientRepository.findAll();
		patients4.forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
		
		//PAGINATION AFFICHAGE PAGE PAR PAGE
		Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(1, 2));
		System.out.println("Nombre des pages:" + pagePatients.getTotalPages());
		List<Patient> listPatients = pagePatients.getContent();
		pagePatients.forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
		
		//TROUVER PATIENT PAR ET PAGE
		
		Page<Patient> patients5 = patientRepository.findByNomContains("a",PageRequest.of(0, 2));
		patients5.getContent().forEach(p->{
			System.out.println(p.toString());
		});	
		System.out.println("***************************");
	}

}
