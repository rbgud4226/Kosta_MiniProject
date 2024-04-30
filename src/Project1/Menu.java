package Project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import Project1.Admin.AdminService;
import Project1.Company.CompanyService;
import Project1.Notice.NoticeService;
import Project1.Nuser.NuserService;
import Project1.SubNotice.SubNoticeService;

public class Menu {
	AdminService aservice = new AdminService();
	CompanyService cservice = new CompanyService();
	NuserService nuservice = new NuserService();
	NoticeService noservice = new NoticeService();
	SubNoticeService snservice = new SubNoticeService();

	// 최초 접속
	public void run(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("1.회원가입  2.로그인  3.종료");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m) {
			case 1:
				aservice.addUser(sc);
				break;
			case 2:
				runLogin(sc);
				 flag = false;
				break;
			case 3:
				System.out.println("종료");
				flag = false;
				break;
			}
		}
	}

	// 로그인 기능
	public void runLogin(Scanner sc) {
		boolean flag = true;
		while (flag) {
			if(aservice.login(sc)) {
				flag= false;
				if(aservice.selectGrade() == 1) {
					runUser(sc);
				}else if(aservice.selectGrade()==2){
					runCompany(sc);
				}else if(aservice.selectGrade()==0){
					runAdmin(sc);
				}
			}else {
	            System.out.println("로그인에 실패했습니다. 다시 시도하세요.");
	            flag = false;
			}
		}
	}

	// 일반회원 기능
	// 메뉴 줄맞춤.
	public void runUser(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1.내정보등록    2.내정보수정    3.내정보확인    4.회원탈퇴\n"
					             + "5.이력서 관리   6.기업정보조회  7.기업전체조회  8.종료");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m) {
			case 1:
				nuservice.addUser(sc);
				break;
			case 2:
				nuservice.editUser(sc);
				break;
			case 3:
				nuservice.selectUser();
				break;
			case 4:
				nuservice.delUser(sc);
				break;
			case 5:
				runIntro(sc);
				break;
			case 6:
				cservice.printCompanyByFiled(sc);
				break;				
			case 7:
				cservice.printAllCompany();
				break;
			case 8:
				System.out.println("로그아웃..");
				flag = false;
				aservice.UserID = 0;
				run(sc);
				break;
			}
		}
	}
  
	// 일반회원 이력서 관리
	public void runIntro(Scanner sc){
		boolean flag = true;
		while (flag){
			System.out.println("=== 이력서 관리 ===");
			System.out.println("1.공고확인  2.이력서등록  3.수정  4.삭제  5.조회  6.나가기");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m){
				case 1:
					noservice.getAll();
					break;
				case 2:
					nuservice.addIntro(sc);
					break;
				case 3:
					nuservice.editIntro(sc);
					break;
				case 4:
					nuservice.delIntro(sc);
					break;
				case 5:
					System.out.println("1.전체조회  2.번호조회  3.제목조회");
					int sel = sc.nextInt();
					switch(sel) {
						case 1:
							nuservice.getAll();
							break;
						case 2:
							nuservice.getById(sc);
							break;
						case 3:
							nuservice.getByTitle(sc);
							break;
					}
					break;
				case 6:
					flag = false;
					break;
			}
		}
	}
	
	// 기업회원 기능
	//메뉴 줄맞춤
	public void runCompany(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1.정보등록  2.정보수정  3.내정보확인 \n"+
					  				     "4.회원탈퇴  5.공고관리  6.종료");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m) {
			case 1:
				cservice.addCompany(sc);
				break;
			case 2:
				cservice.editCompany(sc);
				break;
			case 3:
				cservice.printMyCompany();
				break;
			case 4:
				cservice.deleteCompany(sc);
				break;
			case 5:
				runNotice(sc);
				break;
			case 6:
				System.out.println("로그아웃..");
				flag = false;
				aservice.UserID = 0;
				run(sc);
				break;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}

	// 기업 공고 관리
	public void runNotice(Scanner sc){
		boolean flag = true;
		while (flag){
			System.out.println("=== 공고 관리 ===");
			System.out.println("1.공고확인    2.공고등록    3.수정   4.삭제   5.조회 \n" +
											   "6.지원자확인  7.끌어올리기  8.종료");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m){
				case 1:
					noservice.getAll();
					break;
				case 2:
					noservice.addNotice(sc);
					break;
				case 3:
					noservice.editNotice(sc);
					break;
				case 4:
					noservice.delNotice(sc);
					break;
				case 5:
					System.out.println("1.전체조회  2.번호조회  3.직무별조회");
					int sel = sc.nextInt();
					switch(sel) {
						case 1:
							noservice.getAll();
							break;
						case 2:
							noservice.getByNum(sc);
							break;
						case 3:
							noservice.getByJob(sc);
							break;
					}
					break;
				case 6:
					snservice.viewUser(sc);
					break;
				case 7:
					noservice.editDate(sc);
					break;
				case 8:
					System.out.println("나가기");
					flag = false;
					break;
			}
		}
	}

	// 관리자 기능
	public void runAdmin(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1.회원전체출력  2.회원정보수정  3.회원 조회  4.회원 삭제  5.종료");
			int m = 0;
			try {
				m = sc.nextInt();
			} catch (InputMismatchException e){
				System.out.println("잘못된 입력입니다.");
			}
			switch (m) {
			case 1:
				aservice.selectAllUser();
				break;
			case 2:
				aservice.editUser(sc);
				break;
			case 3:
				aservice.selectUser(sc);
				break;
			case 4:
				aservice.deleteUser(sc);
				break;
			case 5:
				System.out.println("로그아웃..");
				flag = false;
				aservice.UserID = 0;
				run(sc);
				break;
			}
		}
	}
}
