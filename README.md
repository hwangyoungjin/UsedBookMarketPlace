# UsedBookMarketPlace
##중고 책 거래장터 (personal project)
###Java
---
**1. 구현 기능** (Console)
	1. 일반 사용자
		1. 가입 & 로그인
		2. 책 등록 (책의 제목, ISBN 번호, 저자, 출판사, 출판년도, 가격, 상태) 
		3. 책 검색 (책의 제목, ISBN 번호, 저자, 출판사, 출판년도, 판매자 id 별로)
		4. 등록한 책 수정
		5. 구매 E-mail 전송 ( 메일 주소로 메일이 발송되었다고만 출력) )
	2. 관리자
		1. 정해진 ID/PW
		2. 책 검색
		3. 책 삭제 -> 판매자 계정에서도 삭제
		4. 사용자 상태 수정 -> deactivated상태 사용자는 로그인 불가
	3. [MySql 연](https://www.freemysqlhosting.net/) 
	4. JUnit Test

**2. 설계**
	- DCD(Design Class Diagram)
		![DCD](https://user-images.githubusercontent.com/60174144/90244650-24980c00-de6c-11ea-94d8-f50e408c52b0.png)
	- MVCPattern + FactoryPattern
		![MVC](https://user-images.githubusercontent.com/60174144/90244406-a8052d80-de6b-11ea-94d2-9400ba11ad01.png)	
