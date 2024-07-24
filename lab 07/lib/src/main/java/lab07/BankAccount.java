package lab07;

/* BankAccount
 * A bank account has a balance and an interest rate.
 * (note - in real life, NEVER use floating point numbers
 * to represent currency - use a specialized data type like
 * Java's BigDecimal instead.
 * The rules of bank accounts:
 *  Interest rates and balances can never be negative
 *  Accounts are either open or closed - if they're open,
 *  they can be closed if they have < 1 cent left in them.
 *  Attempting to close an account with >= 1 cent should
 *  throw an IllegalStateException.
 *
 */

import java.util.Objects;

public class BankAccount {

    private float balance;
    private float interestRate;
    private boolean open;

    public BankAccount() {
        this(0, 0);
    }

    public BankAccount(float balance, float interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.open = true;
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float newBalance) {
        if (newBalance < 0 ){
            throw new IllegalStateException();
        }
        this.balance = newBalance;
        assert this.invariant(): "Invariant failed at set balance method";
    }

    public float getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(float newInterestRate) {
        if ( newInterestRate < 0 ){
            throw new IllegalStateException();
        }
        this.interestRate = newInterestRate;
        assert this.invariant(): "Invariant failed at set interest rate method";
    }

    //Assumes old balance and interest are non negative and that new interest is non negative
    public void applyInterest() {
        if (!this.invariant()){
            throw new IllegalStateException();
        }
        float oldBalance = this.balance;
        float newInterest = this.calculateInterest();
        this.setBalance(oldBalance + newInterest);
        assert this.invariant(): "Invariant failed at apply balance";
    }

    //Assumes that balance and interest rates are non-negative, enforced with invariant
    private float calculateInterest() {
        if (!this.invariant()){
            throw new IllegalStateException();
        }
        return this.balance * this.interestRate;
    }


    public void close() {
        //Precondition account balance is less than 1
        //Use exception here per bank account rules and because it's a precondition
        if (this.balance >= 1){
            throw new IllegalStateException();
        }
        if (!this.open) {
            return;
        }
        this.open = false;
        assert !this.open: "Bank account failed to close";  //!this.open will always be true
        assert this.invariant(): "Invariant failed in close method";
    }



    public boolean invariant(){
        //Check balance and interest aren't negative
        if (balance<0 || interestRate<0) {
            return false;
        }
        /*Not required to check if bank account is open or closed
          due to variable being boolean which is defaulted to false and can't be null
         */
        if (!this.open && this.balance >= 1){
            return false;
        }


        return true;
    }
}
