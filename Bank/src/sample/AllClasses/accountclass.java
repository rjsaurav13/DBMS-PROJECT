package sample.AllClasses;

public class accountclass {
    private Integer account_number;
    private Integer customer_id;
    private Integer branch_id;
    private Integer opening_amount;
    private String opening_date;
    private String account_type;
    private String account_status;

    public accountclass(Integer account_number, Integer customer_id, Integer branch_id, Integer opening_amount, String opening_date, String account_type, String account_status) {
        this.account_number = account_number;
        this.customer_id = customer_id;
        this.branch_id = branch_id;
        this.opening_amount = opening_amount;
        this.opening_date = opening_date;
        this.account_type = account_type;
        this.account_status = account_status;
    }

    public Integer getAccount_number() {
        return account_number;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public Integer getOpening_amount() {
        return opening_amount;
    }

    public String getOpening_date() {
        return opening_date;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getAccount_status() {
        return account_status;
    }

}
