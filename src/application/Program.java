package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter department's name: ");
		Department department = new Department(scan.nextLine());
		
		System.out.println("Enter worker data: ");
		System.out.println("Name: ");
		String workerName = scan.nextLine();
		
		System.out.println("Level: ");
		WorkerLevel level = WorkerLevel.valueOf(scan.next());
		scan.nextLine();
		
		System.out.println("Base Salary: ");
		Double baseSalary = scan.nextDouble();
		
		List<HourContract> contracts = new ArrayList<>();
		
		System.out.println("How many contracts to this worker? ");
		Integer quantityWorkers = scan.nextInt();
		
		SimpleDateFormat stdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0; i < quantityWorkers; i++) {
			System.out.println("Enter contract #" + (i+1) + " data:");
			System.out.println("Date (DD/MM/YYYY)");
			Date contractDate = stdf.parse(scan.next());
			scan.nextLine();
			
			System.out.println("Value per hour:");
			Double valuePerHour = scan.nextDouble();
			
			System.out.println("Duration (hours):");
			Integer durationHours = scan.nextInt();
			
			contracts.add(new HourContract(contractDate, valuePerHour, durationHours));
		}
		
		Worker worker = new Worker(workerName, level, baseSalary, department, contracts);
		
		System.out.println("Enter month and year to calculate income (MM/YYYY)");
		String income = scan.next();
		scan.nextLine();
		String[] period = income.split("/");
		Integer month = Integer.parseInt(period[0]);
		Integer year = Integer.parseInt(period[1]);
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + income + ": " + String.format("%.2f", worker.income(year, month)));
		
		scan.close();
	}

}
