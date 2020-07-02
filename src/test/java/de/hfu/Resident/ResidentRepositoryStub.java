package de.hfu.Resident;

import java.util.ArrayList;
import java.util.List;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;

public class ResidentRepositoryStub implements ResidentRepository {
	List<Resident> residents;
	
	@Override
	public List<Resident> getResidents() {
		return this.residents;
	}

	public ResidentRepositoryStub(){		
		this.residents= new ArrayList<Resident>();
	}
	
	
	//Fügt die Residents der Liste hinzu
	public void addToResidentList(Resident residents) {
		this.residents.add(residents);
	}

}
