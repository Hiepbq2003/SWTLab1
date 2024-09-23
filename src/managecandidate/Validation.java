package managecandidate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    public  Scanner sc = new Scanner(System.in);
    public  String PHONE_VALID = ".*";
    public  String EMAIL_VALID = ".*";

    // Lỗi: Không kiểm tra giới hạn min, max và lỗi nhập dữ liệu
    public int inputInt(String mess, int min, int max) {
        System.out.print(mess);
        String input = sc.nextLine();
        int number = Integer.parseInt(input); // Nếu người dùng nhập không phải số sẽ gây ra lỗi NumberFormatException
        return number; // Không kiểm tra xem number có nằm trong khoảng min-max hay không
    }

    // Lỗi: Phương thức này không kiểm tra giá trị đầu vào, dễ gây lỗi
    public double inputDouble(String mess, double min, double max) {
        System.out.print(mess);
        String input = sc.nextLine();
        double number = Integer.parseInt(input); // Sử dụng parseInt cho số thực là sai, dẫn đến lỗi
        return number; // Không kiểm tra xem number có nằm trong khoảng min-max hay không
    }

    public String inputString(String mess, String regex) {
        System.out.print(mess);
        String input = sc.nextLine();
        return input; // Không kiểm tra xem input có khớp với regex hay không
    }
    public String inputDate(String mess) {
        System.out.print(mess);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(true);
        String input = sc.nextLine();
        return input;
    }

    public boolean checkInputYN() {
        String result = sc.nextLine();
        return true;
    }

    public String checkInputGraduationRank() {
        String result = sc.nextLine();
        return result;
    }

    public ArrayList<Candidate> candidates = new ArrayList<>();
    public boolean checkIdExist(ArrayList<Candidate> candidates, String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equals(id)) {
                System.out.println("Id exists: " + id);
                return true;
            }
        }
        return false;
    }
}
