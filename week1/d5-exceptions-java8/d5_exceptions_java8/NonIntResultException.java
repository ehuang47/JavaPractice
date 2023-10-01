package d5_exceptions_java8;

public class NonIntResultException extends RuntimeException {
    private String excMessage;
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
            int res = 7/0;
//            throw new NonIntResultException(7,2);
        } catch (NonIntResultException e) {
            System.out.println(e.getExcMessage());
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
