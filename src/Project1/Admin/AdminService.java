package Project1.Admin;

import Project1.Company.CompanyDao;
import Project1.Nuser.NuserDao;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
  private AdminDao dao;
  private NuserDao ndao;
  private CompanyDao cdao;
  public static int UserID;
  public AdminService(){
    dao = new AdminDao();
    ndao = new NuserDao();
    cdao = new CompanyDao();
  }

  // 회원 가입
  public void addUser(Scanner sc){
    System.out.println("=== 회원 가입 ===");
    System.out.print("ID : ");
    String id = sc.next();
    System.out.print("PW : ");
    String pw = sc.next();
    System.out.println("가입 유형");
    System.out.println("1. 개인   2. 기업");
    int grade = sc.nextInt();
    if(grade ==1){
      dao.insert(new Admin(id, pw, 0 , 1));
      System.out.println("개인회원 가입 완료되었습니다.");
    } else if(grade == 2){
      dao.insert(new Admin(id, pw, 0, 2));
      System.out.println("기업회원 가입 완료 되었습니다.");
    } else {
      System.out.println("잘못된 접근입니다.");
      addUser(sc);
    }
  }

  // 로그인
  public boolean login(Scanner sc){
    System.out.println("=== 로그인 ===");
    System.out.print("id : ");
    String id = sc.next();
    System.out.print("pw : ");
    String pw = sc.next();
    Admin a = dao.selectId(id);
    boolean flag =false;
    if(a == null){
      System.out.println("없는 id 입니다.");
    } else {
      if(a.getLogpw().equals(pw)){
        System.out.println("로그인 성공");
        UserID = a.getUserid();
        flag = true;
      } else {
        System.out.println("로그인 실패");
      }
    }
    return flag;
  }

  // 전체 회원 출력
  public void selectAllUser(){
    System.out.println("=== 전체 회원 ===");
    ArrayList<Admin> list = dao.selectAll();
    for(Admin a : list){
      if(a.getGrade()==1){
        System.out.println(a);
        System.out.println(ndao.select(a.getUserid()));
      }else{
        System.out.println(a);
        System.out.println(cdao.selectCompany(a.getUserid()));
      }
      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
  }

  // 회원 조회
  public void selectUser(Scanner sc){
    System.out.println("=== 회원 조회 ===");
    System.out.print("조회할 회원 번호 : ");
    int num = sc.nextInt();
    System.out.println(dao.select(num));
    if(dao.select(num).getGrade()==1){ // 일반, 기업 필터 출력
    System.out.println(ndao.select(num));
    } else {
    System.out.println(cdao.selectCompany(num));
    }
  }

  // 회원 삭제
  public void deleteUser(Scanner sc){
    System.out.println("=== 회원 삭제 ===");
    System.out.print("삭제할 회원 번호 : ");
    int num = sc.nextInt();
    System.out.println("!!! 삭제할 회원이 맞습니까? 맞으면 1 !!!");
    if(dao.select(num).getGrade()==1){ // 일반, 기업 필터 출력
      System.out.println(ndao.select(num));
    } else {
      System.out.println(cdao.selectCompany(num));
    }
    int check = sc.nextInt();
    if(check == 1){
      System.out.println("삭제 완료");
      dao.delete(num);
    } else {
      System.out.println("삭제 취소");
    }
  }

  // 회원 정보 수정
  public void editUser(Scanner sc){
    System.out.println("=== 회원 정보 수정 ===");
    System.out.println("수정할 대상 : ");
    int userid = sc.nextInt();
    System.out.println("NEW ID : ");
    String id = sc.next();
    System.out.println("NEW PW : ");
    String pw = sc.next();
    System.out.println("NEW GRADE (0: admin, 1: general, 2: company): ");
    int grade = sc.nextInt();
    dao.update(new Admin(id, pw, 0, grade), userid);
  }
  
  // 로그인 등급 구분
  public int selectGrade() {
	  return dao.select(UserID).getGrade();
  }
}
