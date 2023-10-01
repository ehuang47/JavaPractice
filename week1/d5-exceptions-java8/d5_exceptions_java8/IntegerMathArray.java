package d5_exceptions_java8;

public class IntegerMathArray {
    private Integer[] dividends;
    private Integer[] divisors;
    public IntegerMathArray(){}
    public IntegerMathArray(Integer[] dividends, Integer[] divisors){
        this.dividends = dividends;
        this.divisors = divisors;
    }

    public Integer[] getDividends() {
        return dividends;
    }
    public void setDividends(Integer[] dividends) {
        this.dividends = dividends;
    }
    public Integer[] getDivisors() {
        return divisors;
    }
    public void setDivisors(Integer[] divisors) {
        this.divisors = divisors;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {10,7,12,10};
        Integer[] arr2 = {2,3,4, 0};
        IntegerMathArray mathArr = new IntegerMathArray(arr1, arr2);
        mathArr.divideAll();
    }

    public void divideAll() {
        for (int i = 0; i < dividends.length; i++) {
            try {
                int quotient = dividends[i] / divisors[i];
                if (dividends[i] % divisors[i] != 0) {
                    throw new NonIntResultException(dividends[i], divisors[i]);
                }
                System.out.printf("Quotient of %s / %s = %s\n", dividends[i], divisors[i], quotient);
            } catch (NonIntResultException e) {
//                e.printStackTrace();
                System.out.println(e.getExcMessage());
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by 0");
                e.printStackTrace();
            }
        }
    }
}
