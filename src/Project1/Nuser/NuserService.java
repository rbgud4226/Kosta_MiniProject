package Project1.Nuser;

import java.util.ArrayList;
import java.util.Scanner;

import Project1.Admin.AdminService;
import Project1.Company.Company;
import Project1.Company.CompanyDao;
import Project1.Intro.Intro;
import Project1.Intro.IntroDao;
import Project1.Notice.NoticeDao;

public class NuserService {
	private IntroDao idao;
	private NuserDao ndao;
	private CompanyDao cdao;
	private NoticeDao nodao;

	public NuserService() {
		idao = new IntroDao();
		ndao = new NuserDao();
		cdao = new CompanyDao();
		nodao = new NoticeDao();
	}

	// 회원정보 등록
	public void addUser(Scanner sc) {
		System.out.println("=== 내 정보 등록 ===");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("최종 학력 : ");
		String edu = sc.next();
		System.out.print("주민등록 번호 : ");
		String id = sc.next();
		System.out.print("전화번호 : ");
		String tel = sc.next();
		System.out.print("메일주소 : ");
		String email = sc.next();
		System.out.print("주소 : ");
		String addr = sc.next();
		System.out.print("경력 : ");
		String career = sc.next();
		System.out.print("자격증 : ");
		String license = sc.next();

		ndao.insert(new Nuser(0, 0, name, edu, id, tel, email, addr, career, license), AdminService.UserID);
	}

	// 회원정보 수정
	public void editUser(Scanner sc) {
		System.out.println("내 정보 수정");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("최종 학력 : ");
		String edu = sc.next();
		System.out.print("전화번호 : ");
		String tel = sc.next();
		System.out.print("메일주소 : ");
		String email = sc.next();
		System.out.print("주소 : ");
		String addr = sc.next();
		System.out.print("경력 : ");
		String career = sc.next();
		System.out.print("자격증 : ");
		String license = sc.next();

		ndao.update(new Nuser(0, 0, name, edu, "", tel, email, addr, career, license), AdminService.UserID);
	}

	// 회원정보 삭제
	public void delUser(Scanner sc) {
		System.out.println("=== 회원 탈퇴 ===");
		System.out.println("삭제를 진행하시겠습니까? 동의 : 1");
		int del = sc.nextInt();
		if (del == 1) {
			ndao.delete(AdminService.UserID);
			System.out.println("삭제 완료");
		}
	}

	// 회원정보 조회
	public void selectUser() {
		System.out.println("내 정보 확인");
		System.out.println(ndao.selectByUserid(AdminService.UserID));
	}

	// 이력서 지원 현황
	public void submitIntro() {

	}

	// 내 이력서 등록
	// 주석
	public void addIntro(Scanner sc) {
		System.out.println("=== 이력서 등록 ===");
		System.out.print("공고 번호   : ");
		int cnum = sc.nextInt();
		System.out.print("이력서 제목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("이력서 내용 : ");
		sc.nextLine();
		String content = sc.nextLine();
		Nuser nuser = ndao.select(AdminService.UserID);
		idao.insert(new Intro(0, title, content, 0, cnum), nuser.getUnum());
	}

	// 내 이력서 수정
	public void editIntro(Scanner sc) {
		System.out.println("=== 이력서 수정 ===");
		System.out.print("내 이력서 번호 : ");
		int id = sc.nextInt();
		System.out.print("새로운 내 이력서 제목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("새로운 내 이력서 내용 : ");
		sc.nextLine();
		String content = sc.nextLine();
		Nuser nuser = ndao.select(AdminService.UserID);
		int cnt = idao.update(new Intro(nuser.getUnum(), title, content, id, 0), id);
		if (cnt > 0) {
			System.out.println(id + "번 이력서 수정 완료");
		} else {
			System.out.println("이력서 수정 실패");
		}
	}

	// 내 이력서 삭제
	public void delIntro(Scanner sc) {
		System.out.println("=== 이력서 삭제 ===");
		getAll();
		System.out.print("삭제할 이력서 번호 : ");
		int id = sc.nextInt();
		System.out.println(id + "번 이력서 삭제 완료");
		Nuser nuser = ndao.select(AdminService.UserID);
		idao.delete(id, nuser.getUnum());
	}

	// 내 이력서 조회(번호)
	public void getById(Scanner sc) {
		System.out.println("=== 이력서 조회(번호) ===");
		System.out.print("내 이력서 번호 : ");
		int id = sc.nextInt();
		Nuser nuser = ndao.select(AdminService.UserID);
		Intro i = idao.selectById(id, nuser.getUnum());
		if (i == null) {
			System.out.println("없음");
			System.out.println();
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("이력서 번호 : " + i.getId() + "  || 기업명 : " + cdao.selectCompanyByCnum(i.getCnum()) + "  || 기업번호 : " + i.getCnum());
			System.out.println("제목 : " +i.getTitle());
			System.out.println("내용 : " +i.getContent());
			System.out.println("--------------------------------------------------------------------------------------------------");
			if (AdminService.UserID == nuser.getUserid()) {
				System.out.println("1.내이력서수정  2.내이력서삭제  3.나가기");
				int x = sc.nextInt();
				switch (x) {
				case 1:
					editIntro(sc);
					break;
				case 2:
					delIntro(sc);
					break;
				}
			}
		}
	}

	// 내 이력서 조회(제목)
	public void getByTitle(Scanner sc) {
		System.out.println("=== 내 이력서 조회(제목) ===");
		System.out.print("내 이력서 제목 : ");
		String title = sc.next();
		Nuser nuser = ndao.select(AdminService.UserID);
		ArrayList<Intro> list = idao.selectByTitle(title, nuser.getUnum());
		if (list.isEmpty()) {
			System.out.println("없음");
		} else
			for (Intro i : list) {
				System.out.println("--------------------------------------------------------------------------------------------------");
				System.out.println("이력서 번호 : " + i.getId() + "  || 기업명 : " + cdao.selectCompanyByCnum(nodao.select(i.getCnum()).getcNum()).getCname() + "  || 공고번호 : " + i.getCnum());
				System.out.println("제목 : " +i.getTitle());
				System.out.println("내용 : " +i.getContent());
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
		System.out.println();
	}

	// 내 이력서 조회(전체)
	public void getAll() {
		System.out.println("=== 내 이력서 조회(전체 목록) ===");
		Nuser nuser = ndao.selectByUserid(AdminService.UserID);
		int unum = nuser.getUnum();
		ArrayList<Intro> list = idao.selectMyIntro(unum);
		if (list.isEmpty()) {
			System.out.println("없음");
		} else {
			for (Intro i : list) {
				System.out.println("--------------------------------------------------------------------------------------------------");
				System.out.println("이력서 번호 : " + i.getId() + "  || 기업명 : " + cdao.selectCompanyByCnum(nodao.select(i.getCnum()).getcNum()).getCname() + "  || 공고번호 : " + i.getCnum());
				System.out.println("제목 : " +i.getTitle());
				System.out.println("내용 : " +i.getContent());
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
			System.out.println();
			}
		}
	}
