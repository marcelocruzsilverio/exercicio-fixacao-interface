package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;



public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Número: ");
		int contractNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(), dtf);
		System.out.print("Valor do contrato: ");
		double contractValue = sc.nextDouble();
		System.out.print("Entre com o número de parcelas: ");
		int months = sc.nextInt();
		
		Contract contract = new Contract(contractNumber, date, contractValue);
		
		ContractService service = new ContractService(new PaypalService());
		service.processContract(contract, months);
		
		System.out.println("Parcelas: ");
		
		for (Installment inst : contract.getInstallments()) {
			System.out.println(inst.getDueDate().format(dtf) + " - " + String.format("%.2f", inst.getAmount()));
			
		}
		
		
		sc.close();
		
	}

}
