package rocket.app.view;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;
import rocketServer.RocketHub;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	@FXML
	private TextField txtDownPayment;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox<String> loanTerm;
	@FXML
	private Label payment;
	@FXML
	private Label error;
	@FXML
	private Button button;
	
	ObservableList<String> terms = FXCollections.observableArrayList("15 Years", "30 Years");
	
	@FXML
	private void initialize(){
		loanTerm.getItems().addAll(terms);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		try {
			lq.setdRate(a.getLoanRequest().getdRate());
			if(loanTerm.getValue() == "15 Years"){
				//15*12 converts years to months
				lq.setiTerm(15*12);
			}
			else if(loanTerm.getValue() == "30 Years"){
				//30*12 converts years to months
				lq.setiTerm(30*12);
			}
			else{
				error.setText("Please select a Loan Term");
			}
			lq.setdAmount(txtHouseCost.getAnchor());
			lq.setiCreditScore(txtCreditScore.getAnchor());
			lq.setiDownPayment(txtDownPayment.getAnchor());
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		String paymentString = String.valueOf(lRequest.getdPayment());
		payment.setText(paymentString);
		
	}
}
