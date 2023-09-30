package d2_java;

public class Calculator2 {
    public static void main(String[] args) {
//        #3
//        System.out.println(String.format("%-8s%-8s%-8s", "number", "square", "cube"));
//        for (int i = 0; i <= 10; i++){
//            int sq = i*i;
//            System.out.println(String.format("%-8s%-8s%-8s", i,sq,sq*i));
//        }

//        #4
//        System.out.println(String.format("%-8s%-8s%-8s", "a", "b", "pow(a,b)"));
//        for (int i = 1; i <= 5; i++){
//            System.out.println(String.format("%-8s%-8s%-8s", i,i+1,(int) Math.pow(i, i+1)));
//        }

//        #5
//        int[] inputs = new int[]{3,5,2,5,5,5,0};
//        int maxNum = 0;
//        int maxCount = -1;
//        HashMap<Integer, Integer> inputMap = new HashMap<Integer, Integer>();
//        for (int input: inputs) {
//            int newVal = inputMap.getOrDefault(input, 0) + 1;
//            if (newVal > maxCount) {
//                maxNum = input;
//                maxCount = newVal;
//            }
//            inputMap.put(input, newVal);
//        }
//        System.out.println(inputMap);
//        System.out.println(String.format("The largest number is %s, with occurrence count %s", maxNum, inputMap.get(maxNum)));

//        #6
//        int[] nums = new int[]{2,2,1};
//        int[] nums = new int[]{4,1,2,1,2};
//        HashMap<Integer, Integer> numHash = new HashMap<Integer, Integer>();
//        for (int num: nums){
//            numHash.put(num, numHash.getOrDefault(num, 0)+ 1);
//        }
//        for (Map.Entry<Integer, Integer> el: numHash.entrySet()) {
//            int val = el.getValue();
//            if (val == 1) System.out.println(el.getKey());
//        }

//        #7
//        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
//        for(int num: nums){
//            if (num == 1) System.out.println("ONE");
//            else if (num == 2) System.out.println("TWO");
//            else {
//                switch(num){
//                    case 3:
//                        System.out.println("THREE");
//                        break;
//                    case 4:
//                        System.out.println("FOUR");
//                        break;
//                    case 5:
//                        System.out.println("FIVE");
//                        break;
//                    case 6:
//                        System.out.println("SIX");
//                        break;
//                    case 7:
//                        System.out.println("SEVEN");
//                        break;
//                    case 8:
//                        System.out.println("EIGHt");
//                        break;
//                    case 9:
//                        System.out.println("NINE");
//                        break;
//                    default:
//                        System.out.println("OTHER");
//                }
//            }
//        }
    }
}
