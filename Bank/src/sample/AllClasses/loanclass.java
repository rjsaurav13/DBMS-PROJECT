package sample.AllClasses;

public class loanclass{
    private Integer customer_id;
    private Integer branch_id;
    private Integer amount;

    public loanclass(Integer customer_id, Integer branch_id, Integer amount) {
        this.customer_id = customer_id;
        this.branch_id = branch_id;
        this.amount = amount;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public Integer getAmount() {
        return amount;
    }
}
