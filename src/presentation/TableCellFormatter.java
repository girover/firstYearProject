package presentation;

import app.Helper;
import entities.LoanApplication;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

/**
 * This class is responsible for generating TableCell formatters 
 * for specific types or objects.
 * It provides a convenient way to format the content of table cells 
 * based on the type or properties of the underlying data.
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 */
public class TableCellFormatter {

	public static TableCell<LoanApplication, String> getTableCellFormatterForStatus(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {

	        @Override
	        protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty()
                            .getValue() < 0 ? 0
                            : indexProperty().getValue();
                    String clmStatus = param
                            .getTableView().getItems()
                            .get(currentIndex).getStatus();
                    if (clmStatus.equals("rejected")) {
                        setStyle(rejectedStyle());
                        
                    }else
                    if (clmStatus.equals("processing")) {
                    	setStyle(processingStyle());
                    }else
                	if (clmStatus.equals("approved")) {
                		setStyle(approvedStyle());
                	}
                    setText(clmStatus);
                }
            }
	        private String processingStyle() {
				return "-fx-background-color: #fdba74; "
						+ "-fx-text-fill: #92400e;"
					 + "-fx-background-radius:16px;"
					 + "-fx-border-radius:16px";
			}
			
			private String approvedStyle() {
				return "-fx-background-color: #86efac; "
					 + "-fx-text-fill: #166534; "
					 + "-fx-background-radius:16px; "
					 + "-fx-border-radius:16px";
			}
			
			private String rejectedStyle() {
				return "-fx-background-color: #fca5a5; "
					 + "-fx-text-fill:#991b1b; "
					 + "-fx-background-radius:16px; "
					 + "-fx-border-radius:16px";
			}
	    };
	}
	
	public static TableCell<LoanApplication, String> getTableCellFormatterForMonths(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				if (!empty) {
					int currentIndex = indexProperty()
							.getValue() < 0 ? 0
									: indexProperty().getValue();
					int clmMonth = param
							.getTableView().getItems()
							.get(currentIndex).getMonths();
					if (clmMonth > 36)
						setStyle(moreThanThreeYearsStyle());
					
					else
						setStyle(lessThatThreeYearsStyle());
					setText(Integer.toString(clmMonth));
				}
			}
			private String moreThanThreeYearsStyle() {
				return "-fx-text-fill: #dc2626;";
			}
			
			private String lessThatThreeYearsStyle() {
				return "-fx-text-fill: #15803d;;";
			}
		};
	}
	
	public static TableCell<LoanApplication, String> getTableCellFormatterForPayment(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				if (!empty) {
					int currentIndex = indexProperty()
							.getValue() < 0 ? 0
									: indexProperty().getValue();
					int clmDownPayment = param
							.getTableView().getItems()
							.get(currentIndex).getPayment();
					int clmLoanAmount = param
							.getTableView().getItems()
							.get(currentIndex).getLoanAmount();
					if (clmDownPayment < clmLoanAmount)
						setStyle(moreThanHalfPriceStyle());
					
					else
						setStyle(lessThatHalfPriceStyle());
					
					setText(Helper.formatCurrency(clmDownPayment));
				}
			}
			private String moreThanHalfPriceStyle() {
				return "-fx-text-fill: #dc2626;";
			}
			
			private String lessThatHalfPriceStyle() {
				return "-fx-text-fill: #15803d;";
			}
		};
	}
	
	public static TableCell<LoanApplication, String> getTableCellFormatterForLoanAmount(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				
				if(!empty) {
					int currentIndex = indexProperty()
							.getValue() < 0 ? 0
									: indexProperty().getValue();
					int loanAmount = param
							.getTableView().getItems()
							.get(currentIndex).getLoanAmount();
					
					setText(Helper.formatCurrency(loanAmount));
				}
				else {
					setText(null);
				}
			}
		};
	}
	
	public static TableCell<LoanApplication, String> getTableCellFormatterForMonthlyPayment(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				
				if(!empty) {
					int currentIndex = indexProperty()
							.getValue() < 0 ? 0
									: indexProperty().getValue();
					double monthlyPayment = param
							.getTableView().getItems()
							.get(currentIndex).getMonthlyPayment();
					
					setText(Helper.formatCurrency(monthlyPayment));
				}
				else {
					setText(null);
				}
			}
		};
	}
	
	public static TableCell<LoanApplication, String> getTableCellFormatterForRate(TableColumn<LoanApplication, String> param){
		return new TableCell<LoanApplication, String>() {
			
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				
				if(!empty) {
					int currentIndex = indexProperty()
							.getValue() < 0 ? 0
									: indexProperty().getValue();
					double rate = param
							.getTableView().getItems()
							.get(currentIndex).getInterestRate();
					
					setText(String.format("%.3f", rate));
				}
				else {
					setText(null);
				}
			}
		};
	}
	
}
