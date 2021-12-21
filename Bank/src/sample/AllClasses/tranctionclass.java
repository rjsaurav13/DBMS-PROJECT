package sample.AllClasses;

public class tranctionclass {
    private Integer transaction_number;
    private Integer account_number ;
    private String  date_of_transaction;
    private String mode ;
    private String type ;
    private Integer amount ;

    public tranctionclass(Integer transaction_number, Integer account_number, String date_of_transaction,
                          String mode,String type,Integer amount) {
        this.transaction_number = transaction_number;
        this.account_number = account_number;
        this.date_of_transaction = date_of_transaction;
        this.mode = mode;
        this.type = type;
        this.amount = amount;
    }

    public Integer getTransaction_number() {
        return transaction_number;
    }

    public Integer getAccount_number() { return account_number; }

    public String getDate_of_transaction() {
        return date_of_transaction;
    }

    public String getMode() {
        return mode;
    }

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }
}
