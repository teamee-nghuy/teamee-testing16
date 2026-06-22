import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bai tap 3 (level 1)
        // Tính Tổng Các Số Chẵn
        System.out.println("BAI TAP 3 (LEVEL 1)");
        System.out.print("Vui long nhap so de tinh tong: ");
        int sumTarget =  sc.nextInt();
        int sum = 0;

        for(int i = 0; i < sumTarget; i = i+2){
            sum += i;
        }

        System.out.println("Tong cac so chan la: " + sum);


        // Bai tap 4 (level 1)
        // Đếm số từ trong một chuỗi
        System.out.println("BAI TAP 4 (LEVEL 1)");
        System.out.print("Vui long nhap chuoi: ");
        sc.nextLine();
        String stringCnt = sc.nextLine();
        int cntSpace = 0;

        for (int i = 0; i < stringCnt.length(); i++){
            if(stringCnt.charAt(i) == ' ') {
                cntSpace ++;
            }
        }

        System.out.println("So khoang trong cua chuoi la: " + cntSpace);


        // Bai tap 5 (level 1)
        // In Chữ Cái Đầu Của Mỗi Từ
        System.out.println("BAI TAP 5 (LEVEL 1)");
        System.out.print("Vui long nhap chuoi: ");
        String strFirstChar = sc.nextLine();
        String resultFirstChar = String.valueOf(strFirstChar.charAt(0)).toUpperCase();

        for (int i = 0; i < stringCnt.length(); i++){
            if(strFirstChar.charAt(i) == ' ') {
                resultFirstChar += String.valueOf(strFirstChar.charAt(i+1)).toUpperCase();
            }
        }

        System.out.println("Cac ky tu dau cua chuoi la: " + resultFirstChar);


        // Bai tap 1 (level 2)
        System.out.println("BAI TAP 1 (LEVEL 2)");
        System.out.print("Vui long nhap thu nhap 1 nam: ");
        double yearIncome = sc.nextInt();
        double taxFee = 0;

        if(yearIncome > 80000000) {
            taxFee = (yearIncome - 80000000)*0.35;
            yearIncome = 80000000;
        }

        if(yearIncome >= 52000000) {
            taxFee += (yearIncome - 52000000)*0.3;
            yearIncome = 52000000;
        }

        if(yearIncome >= 32000000) {
            taxFee += (yearIncome - 32000000)*0.25;
            yearIncome = 32000000;
        }

        if(yearIncome >= 18000000) {
            taxFee += (yearIncome - 18000000)*0.2;
            yearIncome = 18000000;
        }

        if(yearIncome >= 10000000) {
            taxFee += (yearIncome - 10000000)*0.15;
            yearIncome = 10000000;
        }

        if(yearIncome >= 5000000) {
            taxFee += (yearIncome - 5000000)*0.1;
            yearIncome = 5000000;
        }

        taxFee += yearIncome*0.05;

        System.out.printf("Thue cua ban la: %.0f%n", taxFee);


        // Bai tap 2 (level 2)
        // Kiểm Tra Chuỗi Đối Xứng
        System.out.println("BAI TAP 2 (LEVEL 2)");
        System.out.print("Vui long nhap chuoi: ");
        sc.nextLine();
        String symmetryStr = sc.nextLine();
        int lengthStr = symmetryStr.length();
        boolean isSymmetry = true;

        for(int i = 0; i < lengthStr/2; i++) {
            if(symmetryStr.charAt(i) != symmetryStr.charAt(lengthStr-1-i)) {
                isSymmetry = false;
            }
        }

        System.out.println("Chuoi doi xung: " + isSymmetry);
    }
}