package Project1.Company;

import java.util.ArrayList;
import java.util.Scanner;

import Project1.Admin.AdminService;

public class CompanyService {
	CompanyDao dao;

	public CompanyService() {
		dao = new CompanyDao();
	}

	// 기업 등록
	public void addCompany(Scanner sc) {

		System.out.println("=== 기업정보 등록 ===");
		System.out.print("기업 이름     : ");
		String cname = sc.next();
		System.out.print("기업 분야     : ");
		String field = sc.next();
		System.out.print("사업자 번호   : ");
		String id = sc.next();
		System.out.print("기업 전화번호 : ");
		String tell = sc.next();
		System.out.print("기업 이메일   : ");
		String email = sc.next();
		System.out.print("기업 주소     : ");
		String addr = sc.next();
		Company p = new Company(0, 0, cname, field, id, tell, email, addr);
		int cnt = dao.insertCompany(p, AdminService.UserID);
		if (cnt > 0) {
			System.out.println("기업 등록 완료");
		} else {
			System.out.println("기업 등록 실패");
		}
	}

	// 기업 수정 기업이름, 기업분야, 기업전화번호, 기업 이메일, 기업주소
	public void editCompany(Scanner sc) {
		System.out.println("=== 기업정보 수정 ===");
		System.out.print("수정할 기업이름       : ");
		String cname = sc.next();
		System.out.print("수정 할 기업 분야     : ");
		String field = sc.next();
		System.out.print("수정 할 기업 전화번호 : ");
		String tell = sc.next();
		System.out.print("수정 할 기업 이메일   : ");
		String email = sc.next();
		System.out.print("수정 할 기업주소      : ");
		String addr = sc.next();
		Company p = new Company(0, 0, cname, field, "null", tell, email, addr);
		int cnt = dao.updateCompany(p, AdminService.UserID);
		if (cnt > 0) {
			System.out.println("기업 수정 완료");
		} else {
			System.out.println("기업 수정 실패");
		}
	}

	// 기업회원탈퇴
	public void deleteCompany(Scanner sc) {
		System.out.println("=== 기업회원 탈퇴 ===");
		System.out.print("기업정보를 삭제하시겠습니까? 1.삭제 2.취소:");
		int x = sc.nextInt();
		if (x == 1) {
			dao.deleteCompany(AdminService.UserID);
			System.out.println("기업 삭제 완료");
		} else if (x == 2) {
			System.out.println("기업 삭제 취소");
		} else {
			System.out.println("잘못된 입력입니다");
		}

	}

	// 기업 정보 조회 (분야)
	public void printCompanyByFiled(Scanner sc) {
		System.out.println("=== 기업 정보 조회(분야) ===");
		System.out.print("찾을 분야 : ");
		String field = sc.next();
		ArrayList<Company> list = dao.selectCompanyByField(field);
		if (list.isEmpty()) {
			System.out.println("조회된 기업 없음");
		} else {
			System.out.println("=== 전체 기업 조회 ===");
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			for (Company c : list) {
				System.out.println("기업번호 : " + c.getCnum() + "  || 기엄명 : " + c.getCname() + "  || 기업 분야 : " + c.getField());
				System.out.println("전화번호 : " + c.getTell() + "  || 기업 이메일 : " + c.getEmail() + "  || 기업주소 : " + c.getAddr());
				System.out.println(
						"--------------------------------------------------------------------------------------------------");
			}
			System.out.println();
		}
	}

	// 기업 정보 전체조회
	public void printAllCompany() {
		System.out.println("=== 전체 기업 조회 ===");
		ArrayList<Company> list = dao.selectAllCompany();
		if (list.isEmpty()) {
			System.out.println("조회된 기업 없음");
		} else {
			System.out.println("=== 전체 기업 조회 ===");
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			for (Company c : list) {
				System.out.println("기업번호 : " + c.getCnum() + "  || 기엄명 : " + c.getCname() + "  || 기업 분야 : " + c.getField());
				System.out.println("전화번호 : " + c.getTell() + "  || 기업 이메일 : " + c.getEmail() + "  || 기업주소 : " + c.getAddr());
				System.out.println(
						"--------------------------------------------------------------------------------------------------");
			}
			System.out.println();
		}
	}

	// 내정보 확인
	public void printMyCompany() {
		System.out.println("=== 기업 정보 조회 ===");
		System.out.println(dao.selectCompany(AdminService.UserID));
	}
}
