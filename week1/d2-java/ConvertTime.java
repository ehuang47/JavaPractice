public class ConvertTime {

    static void convertMins(int minutes){
        int days = (int) (minutes / 60 / 24);
        int years = (int) (days/365);
        days = days % 365;
        System.out.println(String.format("%s minutes is approximately %s years and %s days", minutes, years, days));
    }

    public static void main(String[] args) {
        ConvertTime.convertMins(3456789);
    }
}
