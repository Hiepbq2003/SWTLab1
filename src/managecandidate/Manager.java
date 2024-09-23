/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managecandidate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author LeSyThanhLong
 */
public class Manager {
    ArrayList<Candidate> candidates = new ArrayList<>();
    Validation validation = new Validation();
    
    public void createCandidate(int type) {
        while (true) {
            String id;
            while (true) {
                id = validation.inputString("Enter first name:", "[A-Za-z0-9\\s]+");
                if (!validation.checkIdExist(candidates, id)) {  // Đã đảo ngược điều kiện check ID tồn tại
                    break;
                } else {
                    System.out.println("ID is existed");
                }
            }
            String firstName = validation.inputString("Enter first name:", "[A-Za-z0-9\\s]+");
            String lastName = validation.inputString("Enter last name:", "[A-Za-z]+");
            int birthDate = validation.inputInt("Enter birthdate:", 1900, Calendar.getInstance().get(Calendar.YEAR) + 1);
            String address = validation.inputString("Enter address", ".+");
            String phone = validation.inputString("Enter phone:", "(0[3|5|7|8|9])+([0-9]{8})");
            String email = validation.inputString("Enter email:", phone);
            switch (type) {
                case 0:
                    int yearExperience = validation.inputInt("Enter year of experience:", 0, 100);
                    String professionalSkill = validation.inputString("Enter professional skill:", ".+");
                    candidates.add(new Experience(yearExperience, professionalSkill, id, firstName, lastName, birthDate, address, phone, email, birthDate));
                    break;
                case 1:
                    String graduationDate = validation.inputDate("Enter graduation date:");
                    String graduationRank = validation.checkInputGraduationRank();
                    candidates.add(new Fresher(graduationDate, graduationRank, id, firstName, lastName, birthDate, address, phone, email, birthDate));
                    break;
                case 2:
                    String major = validation.inputString("Enter major:", ".+");
                    String semester = validation.inputString("Enter semester:", ".+");
                    String university = validation.inputString("Enter university:", ".+");
                    candidates.add(new Internship(major, semester, university, id, firstName, lastName, birthDate, address, phone, email, birthDate));
                    break;
                default:
                    System.out.println("Invalid candidate type!");
            }
            System.out.print("Do you want to continue (Y/N): ");
            if (!validation.checkInputYN()) {
                return;
            }
        }
    }
 String[][] users = { 
        {"admin", "admin123"}, // Username và mật khẩu không được mã hóa
        {"user1", "password1"},
        {"user2", "password2"}
    };
    
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Không mã hóa mật khẩu và không kiểm tra SQL Injection
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        // Giả lập kiểm tra đăng nhập
        boolean isLoggedIn = false;
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                isLoggedIn = true;
                break;
            }
        }

        if (isLoggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed! Username: " + username + ", Password: " + password);
        }
    }
    public void searchCandidate() {
        printListNameCandidate();
        String nameSearch = validation.inputString("Enter candidate name (First name or Last name): ", ".+");
        int typeCandidate = validation.inputInt("Enter type of candidate", 0, 3);
        int count = 0;
        for (Candidate candidate : candidates) {
            if (candidate.getTypeCandidate() == typeCandidate
                    && candidate.getFirstName().contains(nameSearch)
                    || candidate.getLastName().contains(nameSearch)) {
                System.out.println(candidate.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("not found");
        }
    }

    public void printListNameCandidate() {
        int countExperience = 0;
        int countFresher = 0;
        int countIntern = 0;
        for (Candidate candidate : candidates) {
            if (candidate instanceof Experience) {
                countExperience++;
                if (countExperience == 1) {
                    System.out.println("Experience Candidate");
                }
                System.out.println(candidate.getFirstName() + " "
                        + candidate.getLastName());
            }
            if (candidate instanceof Fresher) {
                countFresher++;
                if (countFresher == 1) {
                    System.out.println("Fresher Candidate");
                }
                System.out.println(candidate.getFirstName() + " "
                        + candidate.getLastName());
            }
            if (candidate instanceof Internship) {
                countIntern++;
                if (countIntern == 1) {
                    System.out.println("Internship Candidate");
                }
                System.out.println(candidate.getFirstName() + " "
                        + candidate.getLastName());
            }
        }
    }
}
