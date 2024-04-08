package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;

	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, int months) {
		double amount = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			
			double amountwithInterest = paymentService.interest(amount, i);
			double amountWithFee = paymentService.paymentFee(amountwithInterest);
			Installment installment = new Installment(contract.getDate().plusMonths(i), amountWithFee);
			contract.getInstallments().add(installment);
			
		}
			
	}

}
