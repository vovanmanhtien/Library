use LIBRARI
delete Information

use LIBRARI
delete History

use LIBRARI
delete Books

delete Account
/* create new database*/
create database LIBRARI

/* create new table for user login*/
use LIBRARI
create table Account(
id varchar(100) PRIMARY KEY,
pass varchar(100)
)
/* insert data -- table Books*/
USE LIBRARI
INSERT INTO Account(id, pass)
VALUES
	('manhtien','123456'),
	('quochuy','123321'),
	('drone','222222'),
	('word','333333'),
	('lacnguyen','123456'),
	('votien','444444'),
	('nguyenhuy','555555'),
	('tienmanh','123456'),
	('admin', '123456')
/* create new table -- information user*/
use LIBRARI
create table Information(
id varchar(100) PRIMARY KEY,
fullname nvarchar(100) NOT NULL,
phonenumber nvarchar(10) NULL,
position nvarchar(100) NOT NULL,
addr nvarchar(200) NULL,
birthday date NULL,
sex nvarchar(20) NOT NULL,
nation nvarchar(100) NOT NULL,
email varchar(100) NULL,
cccd varchar(20) NOT NULL,
course int NOT NULL,
majors nvarchar(100) NOT NULL
CONSTRAINT fk_Account_id FOREIGN KEY (id) REFERENCES Account(id)
)

/* insert data -- table Information*/
USE LIBRARI
INSERT INTO Information(id, fullname, phonenumber, position, addr, birthday, sex, nation, email, cccd, course, majors)
VALUES
	('manhtien', N'Võ Văn Mạnh Tiến', '0327319624', N'Sinh Viên', N'Huế', '02/04/1999', N'Nam', N'Việt Nam', 'vovanmanhtien1999@gmail.com', '046099005342', 44, N'Công Nghệ Thông Tin'),
	('quochuy', N'Nguyễn Quốc Huy', '0327333324', N'Sinh Viên', N'Huế', '02/05/2000', N'Nam', N'Việt Nam', 'nguyenquochuy@gmail.com', '0460990011510', 44, N'Công Nghệ Thông Tin'),
	('drone', N'Võ Văn Mạnh drone', '0327319222', N'Sinh Viên', N'Huế', '07/11/1998', N'Nam', N'Việt Nam', 'vovanmanhdrone1@gmail.com', '046333005342', 44, N'Công Nghệ Thông Tin'),
	('word', N'Trần Lê Anh', '0999319624', N'Sinh Viên', N'Huế', '02/13/2002', N'Nam', N'Việt Nam', 'anhtran2002@gmail.com', '046098760942', 44, N'Công Nghệ Thông Tin'),
	('lacnguyen', N'Nguyễn Văn Lắc', '0764502224', N'Sinh Viên', N'Huế', '07/12/2001', N'Nam', N'Việt Nam', 'lacnguyen@gmail.com', '046099787642', 44, N'Công Nghệ Thông Tin'),
	('votien', N'Võ Thị Bịp', '055414489', N'Sinh Viên', N'Huế', '06/10/2003', N'Nam', N'Việt Nam', 'bipvo2003@gmail.com', '046123456780', 44, N'Công Nghệ Thông Tin'),
	('nguyenhuy', N'Nguyễn Văn Huy', '0935889000', N'Sinh Viên', N'Huế', '02/07/1999', N'Nam', N'Việt Nam', 'nguyenvanhuy1999@gmail.com', '046099012345', 44, N'Công Nghệ Thông Tin'),
	('admin', N'Đây là Quản Lý', '0935889000', N'Quản Lý', N'Huế', '02/07/1999', N'Nam', N'Việt Nam', 'nguyenvanhuy1999@gmail.com', '046099012345', 44, N'Công Nghệ Thông Tin')

/* create new table -- information books*/
create table Books(
id varchar(100) PRIMARY KEY,
fullname nvarchar(100) NOT NULL,
author nvarchar(100) NOT NULL,
catelory nvarchar(100) NOT NULL,
company_publishing nvarchar(200) NOT NULL,
date_publishing date NULL,
quantity int NOT NULL,
quantity_available int NOT NULL
)
/* insert data -- table Books*/
USE LIBRARI
INSERT INTO Books(id, fullname, author, catelory, company_publishing, date_publishing, quantity, quantity_available)
VALUES
	('TIN1', N'Java Cơ Bản', N'Nguyễn Văn An', N'Tin Học', N'Đại Học Khoa Học Huế', '02/02/2023', 20, 20),
	('TIN2', N'Java Nâng Cao', N'Nguyễn Văn Bảo', N'Tin Học', N'Đại Học Khoa Học Huế', '02/04/2023', 20, 20),
	('KNM5012', N'Kỹ Năng Mềm', N'Nguyễn Quốc Huy', N'Khoa Học', N'Đại Học Khoa Học Huế', '12/08/2023', 10, 10),
	('TIN1013', N'TIN1013', N'Võ Văn Mạnh Tiến', N'Khoa Học', N'Đại Học Khoa Học Huế', '10/05/2023', 08, 08),
	('TOA1023', N'Đại số tuyến tính', N'Trần Lê QUốc Anh', N'Toán Học', N'Đại Học Khoa Học Huế', '11/11/2023', 12, 12),
	('TOA1034', N'Phép tính vi tích phân hàm một biến', N'Nguyễn Thị Quỳnh Nga', N'Toán Học', N'Đại Học Khoa Học Huế', '09/07/2023', 20, 20),
	('XHH1012', N'Xã hội học đại cương', N'Lê Ngọc Đông Quân', N'Xã Hội và Nhân Văn', N'Đại Học Khoa Học Huế', '12/13/2023', 05, 05),
	('TIN2033', N'Anh văn chuyên ngành', N'Nguyễn Thị Hoài', N'Anh Văn', N'Đại Học Khoa Học Huế', '04/06/2023', 12, 12),
	('TIN3043', N'Kỹ nghệ phần mềm', N'Đặng Thị Như Ý', N'Phần Mềm', N'Đại Học Khoa Học Huế', '01/05/2023', 20, 20),
	('LIS1022', N'	Văn hóa Việt Nam đại cương', N'Nguyễn Văn Hào', N'Xã Hội và Nhân Văn', N'Đại Học Khoa Học Huế', '02/15/2023', 09, 09),
	('TIN3013', N'Ngôn ngữ lập trình bậc cao', N'Trần Hiếu', N'Phần Mềm', N'Đại Học Khoa Học Huế', '12/07/2023', 20, 20),
	('LLCTKT2', N'Kinh tế chính trị Mác - Lênin', N'Hoàng Thị Cẩm Nhung', N'Lý Luận Chính Trị', N'Đại Học Khoa Học Huế', '09/05/2023', 16, 16),
	('LLCTTH3', N'Triết học Mác - Lênin', N'Nguyễn Thị Phương Uyên', N'Lý Luận Chính Trị', N'Đại Học Khoa Học Huế', '08/19/2023', 13, 13),
	('TIN3012', N'Ngôn ngữ truy vấn có cấu trúc (SQL)', N'Trần An', N'Phần Mềm', N'Đại Học Khoa Học Huế', '07/15/2023', 12, 12),
	('TIN3113', N'Nhập môn trí tuệ nhân tạo', N'Nguyễn Văn Bình', N'Tin Học', N'Đại Học Khoa Học Huế', '12/04/2023', 13, 13),
	('TIN3014', N'Data Structures and Algorithms', N'Võ Đăng Bình', N'Tin Học', N'Đại Học Khoa Học Huế', '06/16/2023', 13, 13),
	('TIN4113', N'Quy trình phát triển phần mềm', N'Trần Thị Như Yến', N'Phần Mềm', N'Đại Học Khoa Học Huế', '04/22/2023', 18, 18),
	('TIN4183', N'	Kiểm định phần mềm', N'Trần Ngọc Như ', N'Phần Mềm', N'Đại Học Khoa Học Huế', '07/25/2023', 12, 12),
	('TOA1012', N'	Cơ sở toán', N'Trần Thị Tâm Anh', N'Toán Học', N'Đại Học Khoa Học Huế', '11/09/2023', 16, 16),
	('TIN1070', N'Tin học đại cương', N'Nguyễn Quốc Bảo', N'Khoa Học', N'Đại Học Khoa Học Huế', '12/08/2023', 17, 17),
	('TOA2033', N'	Phương pháp tính', N'Trần Ngọc Tâm Liên', N'Toán Học', N'Đại Học Khoa Học Huế', '09/14/2023', 16, 16),
	('TOA2023', N'Xác suất thống kê', N'Nguễn Ngọc Quỳnh Nhung', N'Toán Học', N'Đại Học Khoa Học Huế', '10/04/2023', 19, 19),
	('TIN4403', N'Lập trình ứng dụng cho các thiết bị di động', N'Lê Thị Ngọc', N'Toán Học', N'Đại Học Khoa Học Huế', '07/17/2023', 13, 13),
	('TIN4423', N'Web ngữ nghĩa', N'Chương Thị Kiều', N'Lập Trình', N'Đại Học Khoa Học Huế', '12/07/2023', 12, 12),
	('TIN4063', N'Phần mềm mã nguồn mở', N'Nguyễn Bá Toàn', N'Phần Mềm', N'Đại Học Khoa Học Huế', '02/07/2023', 11, 11),
	('TIN4483', N'Xây dựng ứng dụng với .NET Framework', N'La Thị Oanh', N'Lập Trình', N'Đại Học Khoa Học Huế', '11/05/2023', 13, 13),
	('TIN3084', N'Cấu trúc dữ liệu và thuật toán', N'Lê Nam Anh', N'Toán Học', N'Đại Học Khoa Học Huế', '05/02/2023',05, 05	),
	('LLCTTT2', N'Tư tưởng Hồ Chí Minh', N'Võ Văn Tín', N'Xã Hội và Nhân Văn', N'Đại Học Khoa Học Huế', '06/03/2023', 02, 02)

/* create new table -- history*/
use LIBRARI
create table History(
id int PRIMARY KEY,
id_user varchar(100) NOT NULL,
id_book varchar(100) NOT NULL,
quantity int NOT NULL,
date_borrow date NOT NULL,
date_back date NOT NULL,
stus nvarchar(100) NOT NULL,
)
/* insert data -- table History*/

USE LIBRARI
INSERT INTO History(id, id_user, id_book, quantity, date_borrow, date_back, stus)
VALUES
(1, 'manhtien', 'HH1', 2, '02-17-2022', '02-17-2023', N'Đã Trả'),
(2, 'lacnguyen', 'II1', 4, '07-27-2023', '09-17-2023', N'Đã Mượn'),
(3, 'votien', 'HK4', 1, '02-11-2023', '08-17-2023', N'Đã Hủy')	
/*kiểm tra quyền hạn nhập vào có tồn tại id nhập vào*/
use LIBRARI
select Account.id from Account, Information
where Account.id = 'manhtien' and Account.pass = '123456' and Information.position = 'Sinh Viên' and Account.id = Information.id
/*lấy thông tin sách theo yêu cầu*/
use LIBRARI
select * from Books
where Books.fullname like '%Cô%'
/*lấy thông tin sách theo mã sách*/
use LIBRARI
select * from Books
where Books.id like '%I%'
/*lấy số lượng lịch sử mượn	*/
select count(id) as id from History
/*lấy lịch sử của 1 user	*/
select * from History
where History.id_user = 'manhtien'
/*thay đổi thông tin 1 sách*/
	use LIBRARI
	update Books
	SET 
	fullname = 'admin11', 
	author = 'admin11', 
	catelory = 'admin11', 
	company_publishing = 'admin11', 
	date_publishing = '1999-10-10', 
	quantity = 20
	WHERE ID = 'admin11'

/*tìm và in ra thông tin tất cả sinh viên*/
USE LIBRARI
SELECT * FROM Information
WHERE Information.position = 'Sinh Viên'
/*cập nhật các thông tin có thể thay đổi của user*/
USE LIBRARI
UPDATE Information
SET
phonenumber	

/*trừ đi số lượng sách khách mượn vào số lượng sách hiện có*/
USE LIBRARI
update Books
set quantity_available = quantity_available +6
where Books.id = 'KNM5012'

/*thay đổi trạng thái của history*/
USE LIBRARI
update History
set stus = N'Đã Hủy'
where History.id = 0