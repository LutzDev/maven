package de.hfu.residents.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;

public class ResidentRepositoryStub implements ResidentRepository {
	List<Resident> residents;
	
	@Override
	public List<Resident> getResidents() {
		return this.residents;
	}
	
	public void addData(Resident residents) {
		this.residents.add(residents);
	}

	public ResidentRepositoryStub(){
		//Zur Liste hinzufügen
//		Resident resident1= new Resident("Moritz", "Müller", "Teststraße", "Testdorf", null);
//		Resident resident2= new Resident("Tim", "Tester", "Josefdorer", "Freiburg", null);
//		Resident resident3= new Resident("Lutz", "Weigold", "Beispielstraße", "Schlumpfhausen", null);
//		this.residents= Arrays.asList(resident1, resident2, resident3);
		
		this.residents= new ArrayList<Resident>();
	}
	
	

}