public class hw3 {
        public static void main(String[] args) {

        double loan = 100;   // סכום ההלוואה
        double rate = 0.10;     // ריבית 5%
        double payment = 15; // תשלום שנתי שאת רוצה לבדוק

        double balance = loan;

        for (int year = 1; year <= 10; year++) {
            balance = (balance - payment) * (1 + rate);
            System.out.println("Year " + year + ": balance = " + balance);
        }

        System.out.println("Final balance after 10 years = " + balance);
    }
}
