public class MyWrapper { // goal: take a primitive type, have ways to access its value and override? things
    private int i;

    MyWrapper(int x){
        i = x;
    }
    public int getVal(){
        return i;
    }

    public void setVal(int x){
        i = x;
    }

    public void increment(){
        i++;
    }

    @Override public String toString(){
        return Integer.toString(i);
    }

    public static void main(String[] args){
        int temp = new Integer(1);
        MyWrapper temp2 = new MyWrapper(1);
    }
}
