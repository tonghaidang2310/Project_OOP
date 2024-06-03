create database if not exists Project_OOP;

use Project_OOP;

drop database project_oop;

# Học sinh
CREATE TABLE IF NOT EXISTS Student (
    StudentID varchar(50) PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Email VARCHAR(50),
    Gender ENUM('Male', 'Female', 'Other') NOT NULL
);

-- Bảng Lecturers
CREATE TABLE IF NOT EXISTS Lecturers (
    LecturersID VARCHAR(50) PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15),
    Email VARCHAR(50),
    Gender ENUM('Male', 'Female', 'Other') NOT NULL
);

-- Bảng Course
CREATE TABLE IF NOT EXISTS Course (
    CourseID VARCHAR(50) PRIMARY KEY,
    NameCourse VARCHAR(100) NOT NULL,
    LecturersID VARCHAR(50),
    TuitionFee DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (LecturersID) REFERENCES Lecturers(LecturersID)
);

-- Tạo bảng StudentCourse để quản lý mối quan hệ nhiều-nhiều giữa Student và Course
CREATE TABLE IF NOT EXISTS StudentCourse (
    StudentID VARCHAR(50),
    CourseID VARCHAR(50),
    RegistrationStatus ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    PRIMARY KEY (StudentID, CourseID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- Tạo bảng Account để lưu thông tin tài khoản
CREATE TABLE IF NOT EXISTS Account (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    UserType ENUM('Student', 'Lecturer') NOT NULL,
    StudentID VARCHAR(50),
    LecturerID VARCHAR(50),
    CONSTRAINT fk_student FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_lecturer FOREIGN KEY (LecturerID) REFERENCES Lecturers(LecturersID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tạo trigger để đảm bảo ràng buộc logic
DELIMITER //
CREATE TRIGGER before_account_insert
BEFORE INSERT ON Account
FOR EACH ROW
BEGIN
    IF NEW.UserType = 'Student' AND NEW.StudentID IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'StudentID must be non-null when UserType is Student';
    END IF;

    IF NEW.UserType = 'Lecturer' AND NEW.LecturerID IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'LecturerID must be non-null when UserType is Lecturer';
    END IF;
    
    IF NEW.UserType = 'Student' AND NEW.LecturerID IS NOT NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'LecturerID must be null when UserType is Student';
    END IF;

    IF NEW.UserType = 'Lecturer' AND NEW.StudentID IS NOT NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'StudentID must be null when UserType is Lecturer';
    END IF;
END;
//
DELIMITER ;

-- Tạo bảng Classroom để lưu thông tin về các phòng học
CREATE TABLE IF NOT EXISTS Classroom (
    ClassroomID VARCHAR(50) PRIMARY KEY,
    RoomNumber VARCHAR(50) NOT NULL,
    Building VARCHAR(100) NOT NULL
);

-- Tạo bảng Schedule để lưu thông tin thời khóa biểu
CREATE TABLE IF NOT EXISTS Schedule (
    ScheduleID INT AUTO_INCREMENT PRIMARY KEY,
    CourseID VARCHAR(50),
    ClassroomID VARCHAR(50),
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    DayOfWeek ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID),
    FOREIGN KEY (ClassroomID) REFERENCES Classroom(ClassroomID)
);

-- Bảng Enrollment để lưu trữ thông tin chi tiết về quá trình đăng ký
CREATE TABLE IF NOT EXISTS Enrollment (
    EnrollmentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentID VARCHAR(50),
    CourseID VARCHAR(50),
    RegistrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    RegistrationStatus ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- Trigger để đảm bảo chỉ có thể thay đổi trạng thái thành 'Approved' hoặc 'Rejected' từ trạng thái 'Pending'
DELIMITER //
CREATE TRIGGER before_enrollment_update
BEFORE UPDATE ON Enrollment
FOR EACH ROW
BEGIN
    IF NEW.RegistrationStatus NOT IN ('Pending', 'Approved', 'Rejected') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid registration status';
    END IF;

    IF OLD.RegistrationStatus = 'Pending' AND NEW.RegistrationStatus = 'Approved' THEN
        SET NEW.RegistrationStatus = 'Approved';
    ELSEIF OLD.RegistrationStatus = 'Pending' AND NEW.RegistrationStatus = 'Rejected' THEN
        SET NEW.RegistrationStatus = 'Rejected';
    ELSEIF OLD.RegistrationStatus != 'Pending' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cannot change registration status once it is approved or rejected';
    END IF;
END;
//
DELIMITER ;

-- Tạo bảng Payment để lưu trữ thông tin thanh toán học phí
CREATE TABLE IF NOT EXISTS Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentID VARCHAR(50),
    CourseID VARCHAR(50),
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    PaymentStatus ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

DELIMITER //
-- Trigger để đảm bảo chỉ có thể thay đổi trạng thái thanh toán thành 'Completed' hoặc 'Failed' từ trạng thái 'Pending'
CREATE TRIGGER before_payment_update
BEFORE UPDATE ON Payment
FOR EACH ROW
BEGIN
    IF NEW.PaymentStatus NOT IN ('Pending', 'Completed', 'Failed') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid payment status';
    END IF;

    IF OLD.PaymentStatus = 'Pending' AND NEW.PaymentStatus = 'Completed' THEN
        SET NEW.PaymentStatus = 'Completed';
    ELSEIF OLD.PaymentStatus = 'Pending' AND NEW.PaymentStatus = 'Failed' THEN
        SET NEW.PaymentStatus = 'Failed';
    ELSEIF OLD.PaymentStatus != 'Pending' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cannot change payment status once it is completed or failed';
    END IF;
END;
//
DELIMITER ;

-- Bảng GradeType
CREATE TABLE IF NOT EXISTS GradeType (
    GradeTypeID INT AUTO_INCREMENT PRIMARY KEY,
    TypeName VARCHAR(50) NOT NULL,
    Weight DECIMAL(5, 2) NOT NULL
);

-- Bảng Grade
CREATE TABLE IF NOT EXISTS Grade (
    GradeID INT AUTO_INCREMENT PRIMARY KEY,
    StudentID VARCHAR(50),
    CourseID VARCHAR(50),
    LecturerID VARCHAR(50),
    GradeTypeID INT,
    Grade DECIMAL(4, 2),
    GradeDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID),
    FOREIGN KEY (LecturerID) REFERENCES Lecturers(LecturersID),
    FOREIGN KEY (GradeTypeID) REFERENCES GradeType(GradeTypeID),
    CONSTRAINT uc_student_course_grade UNIQUE (StudentID, CourseID, GradeTypeID)
);

DELIMITER //
CREATE TRIGGER before_grade_insert
BEFORE INSERT ON Grade
FOR EACH ROW
BEGIN
    DECLARE v_Count INT;
    DECLARE v_UserType ENUM('Student', 'Lecturer');

    -- Kiểm tra loại điểm tồn tại trong bảng GradeType
    SELECT COUNT(*) INTO v_Count FROM GradeType WHERE GradeTypeID = NEW.GradeTypeID;
    IF v_Count = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Grade type does not exist';
    END IF;

    -- Kiểm tra người dùng có phải là giảng viên hay không
    SELECT UserType INTO v_UserType FROM Account WHERE AccountID = CURRENT_USER();
    IF v_UserType != 'Lecturer' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Only lecturers are allowed to add grades';
    END IF;
END;
//
DELIMITER ;
