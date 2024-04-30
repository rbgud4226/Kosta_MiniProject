package Project1.Notice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Project1.Admin.AdminService;
import Project1.Company.CompanyDao;

public class NoticeService {
	private Notice no;
	private NoticeDao dao;
	private CompanyDao cdao;

	public NoticeService() {
		dao = new NoticeDao();
		cdao = new CompanyDao();
	}

	// 공고 등록
	public void addNotice(Scanner sc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("=== 공고 등록 ===");
		System.out.println("연봉을 등록하세요 : ");
		int salary = sc.nextInt();
		System.out.println("직무를 등록해 주세요 : ");
		String job = sc.next();
		System.out.println("마감일을 작성해주세요(년-월-일) : ");
		String deadline = sc.next();
		java.util.Date date;
		try {
				date = formatter.parse(deadline);
			    java.sql.Date sqldate = new java.sql.Date(date.getTime());
			    dao.insert(new Notice(0, 0, null, salary, job, sqldate), cdao.selectCompany(AdminService.UserID).getCnum());
			} catch (ParseException e) {
				 System.out.println("잘못된 날짜 형식입니다. 년-월-일 형식으로 입력해주세요.");
				e.printStackTrace();
			}
	}

	// 공고 삭제
	public void delNotice(Scanner sc) {
		System.out.println("=== 공고 삭제 ===");
		System.out.print("삭제할 공고번호 : ");
		int com_id = sc.nextInt();
		dao.delete(com_id);
	}

	// 공고 수정
	public void editNotice(Scanner sc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("=== 공고 수정 ===");
		getAll();
		System.out.println("===============");
		System.out.print("변경할 공고번호 선택 : ");
		int com_id = sc.nextInt();
		System.out.print("new 연봉 : ");
		int salary = sc.nextInt();
		System.out.print("new 직무 : ");
		String job = sc.next();
		System.out.println("new 마감일(년-월-일) : ");
		String deadline = sc.next();
		java.util.Date date;
		try {
			date = formatter.parse(deadline);
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			dao.update(new Notice(0, 0, null, salary, job, sqldate), com_id);
		} catch (ParseException e) {
			System.out.println("잘못된 날짜 형식입니다. 년-월-일 형식으로 입력해주세요.");
			e.printStackTrace();
		}
	}
	
	// 공고 번호로 검색
	public void getByNum(Scanner sc) {
		System.out.println("=== 번호로 검색 ===");
		System.out.print("num : ");
		int com_id = sc.nextInt();
		Notice n = dao.select(com_id);
		if (n == null) {
			System.out.println("없는 글번호");
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("공고번호 : " + n.getCom_id() + "  || 기업명 : " + cdao.selectCompanyByCnum(n.getcNum()).getCname() + "  || 기업번호 : " + n.getcNum());
			System.out.println("직무 : " + n.getJob() + "  || 급여 : " + n.getSalary() + "  || 공고기간 : " + n.getPeriod() + " 부터 " + n.getDeadLine() + " 까지");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println();
			if (no.getcNum() == n.getcNum()) {
				System.out.println("1.수정  02.삭제  3.상세페이지종료");
				int x = sc.nextInt();
				switch (x) {
				case 1:
					editNotice(sc); // error
					break;
				case 2:
					delNotice(sc); // error
					break;
				}
			}
		}
	}

	// 공고 직무별 검색
	public void getByJob(Scanner sc) {
		System.out.println("=== 직무별 검색 ===");
		System.out.print("검색할 직무를 입력하세요 : ");
		String job = sc.next();
		ArrayList<Notice> list = dao.selectByJob(job);
		if (list.isEmpty()) {
			System.out.println("검색된 결과가 없다");
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------");
			for (Notice n : list) {
				System.out.println("공고번호 : " + n.getCom_id() + "  || 기업명 : " + cdao.selectCompanyByCnum(n.getcNum()).getCname() + "  || 기업번호 : " + n.getcNum());
				System.out.println("직무 : " + n.getJob() + "  || 급여 : " + n.getSalary() + "  || 공고기간 : " + n.getPeriod() + " 부터 " + n.getDeadLine() + " 까지");
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
			System.out.println();
		}
	}

	// 전체 공고 보기
	public void getAll() {
		System.out.println("=== 글목록 ===");
		ArrayList<Notice> list = dao.selectAll();
		if (list.isEmpty()) {
			System.out.println("검색된 결과가 없다");
		} else {
			System.out.println("--------------------------------------------------------------------------------------------------");
			for (Notice n : list) {
				System.out.println("공고번호 : " + n.getCom_id() + "  || 기업명 : " + cdao.selectCompanyByCnum(n.getcNum()).getCname() + "  || 기업번호 : " + n.getcNum());
				System.out.println("직무 : " + n.getJob() + "  || 급여 : " + n.getSalary() + "  || 공고기간 : " + n.getPeriod() + " 부터 " + n.getDeadLine() + " 까지");
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
			System.out.println();
		}
	}

	// 공고 끌어올리기
	public void editDate(Scanner sc) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("=== 공고 끌어올리기 ===");
		getAll();
		System.out.println("===============");
		System.out.print("변경할 공고번호 선택 : ");
		int com_id = sc.nextInt();

		//date 클래스에서 현재 날짜를 가져와 SQL 데이터로 형식 변환
		java.util.Date today = new java.util.Date();
		java.sql.Date sqldate = new java.sql.Date(today.getTime());

		//DAO 호출하여 공고날짜 업데이트
		dao.updateDate(sqldate,com_id);
	}
}
