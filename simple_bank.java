/**
 * Created by Jason Argo on 10/22/18.
 * Note: dollar balance is stored in a long value in cent units to avoid round-off error existing in doubles or floats
 */
public class Bank {
    private String name;

    public Bank(String name){
        this.name = name;
    }

    public class Account {
        private String Owner;
        private long Balance;
        private double interest_rate = 0.0;
        private int account_days = 0;
        private boolean individual_or_corporate;
        private boolean checking_or_investment;


        public Account(String Owner, long Balance, boolean individual_or_corporate, boolean checking_or_investment,
                       double interest_rate){
            this.Owner = Owner;
            this.Balance = Balance;
            this.individual_or_corporate = individual_or_corporate;
            this.checking_or_investment = checking_or_investment;
            this.interest_rate = interest_rate;
        }

        public String getOwner(){
            return this.Owner;
        }

        public long getBalance(){
            return this.Balance;
        }

        public boolean isIndividual(){ return this.individual_or_corporate; }

        public boolean isChecking() { return this.checking_or_investment; }

        public double getInterestRate(){ return this.interest_rate; }

        public int getAccountDays(){ return this.account_days; }

        public boolean canWithdraw(long withdraw_quantity){
            if (withdraw_quantity > Balance){
                System.out.println("Account does not have enough funds");
                return false; }
            else if (individual_or_corporate && (Balance - withdraw_quantity) < 50000){
                System.out.println("Individual must leave at least $500 in account");
                return false; }
            else { return true; }
        }

        public void deposit(long deposit_quantity){
            Balance += deposit_quantity;
        }

        public boolean withdraw(long withdraw_quantity){
            if (!canWithdraw(withdraw_quantity)){ return false; }
            else { Balance -= withdraw_quantity; return true; }
        }
    }

    public String getName(){
        return this.name;
    }

    public boolean transfer(Account fromAccount, Account toAccount, long quantity){
        if (fromAccount.withdraw(quantity)){
            toAccount.deposit(quantity); return true; }
        else { System.out.println("transfer failed"); return false; }
    }

    // testing cases in main
    public static void main(String[] args) {
        Bank CO = new Bank("Capital_One");
        Bank.Account ONE = CO.new Account("Bob", 100000, true, true, 10.5);
        Bank.Account TWO = CO.new Account("Sally", 50000, true, false, 6.75);
        System.out.println(ONE.getBalance());
        System.out.println(TWO.getBalance());
        System.out.println(CO.transfer(TWO, ONE, 1000));
        System.out.println(ONE.getBalance());
        System.out.println(TWO.getBalance());
    }

}
