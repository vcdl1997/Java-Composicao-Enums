package entities;

import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	private Department department;
	private List<HourContract> contracts;
	
	
	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, Department department,
			List<HourContract> contracts) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
		this.contracts = contracts;
	}
	
	
	public String getName() {
		return name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}

	public void addContract(HourContract contract) {
		this.contracts.add(contract);
	}
		
	public void removeContract(HourContract contract) {
		HourContract contractToRemove = this.contracts.stream().filter(x -> x == contract).findFirst().orElse(null);
		
		if(contractToRemove != null) this.contracts.remove(contractToRemove);
	}
	
	public Double income(Integer year, Integer month) {
		Double income = 0.0;
		
		for(HourContract contract : this.getContracts()) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(contract.getDate());
			
			
			if(year.equals(cal.get(Calendar.YEAR)) && month.equals(cal.get(Calendar.MONTH)+1)) {
				income += contract.totalValue();
			}
		}
		
		return income + this.baseSalary;
	}
	
}
