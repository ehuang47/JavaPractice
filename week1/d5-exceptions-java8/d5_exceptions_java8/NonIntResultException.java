package d5_exceptions_java8;

public class NonIntResultException extends ArithmeticException{
    String excMessage;
    public NonIntResultException(Integer v1, Integer v2){
        this.excMessage = String.format("%s divided by %s is not an integer!", v1,v2);
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }

    public static void main(String[] args){
        try {
            Integer res = 7/2;
            throw new NonIntResultException(7,2);
        } catch (NonIntResultException e) {
            System.out.println(e.getExcMessage());
        }
    }
}
