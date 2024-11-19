package org.example.o.solution;

public class LoanHandler {

    private PersonaLoanValidator personaLoanValidator;
    private GageLoanValidator gageLoanValidator;

    public void approvePersonalLoan(User user){
        if(personaLoanValidator.isValidUserPersonalLoan(user)){
            System.out.println("Loan validated");
        }
    }


    public void approveGageLoan(User user){
        if(gageLoanValidator.isValidUserGage(user)){
            System.out.println("Loan validated");
        }
    }




}
