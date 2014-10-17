-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.27 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-09-18 10:58:21
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for course
DROP DATABASE IF EXISTS `course`;
CREATE DATABASE IF NOT EXISTS `course` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `course`;


-- Dumping structure for table course.course
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table course.course: ~3 rows (approximately)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`ID`, `NAME`, `DESCIPTION`) VALUES
	(1, '2008-2009', 'From year 2008-2009'),
	(3, '2009-2010', 'From year 2009-2010'),
	(27, '2010-2011', 'From year 2010-2011');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;



-- Dumping structure for table course.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `SUR_NAME` varchar(100) DEFAULT NULL,
  `PHONE_NUMBER` varchar(50) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table course.student: ~4 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`ID`, `NAME`, `SUR_NAME`, `PHONE_NUMBER`, `BIRTHDAY`) VALUES
	(2, 'Thu', 'Dinh', '090.8828.007', '2012-09-24'),
	(4, 'Phương', 'Tran', '090.8828.007', '2012-09-11'),
	(8, 'Luong', 'Tran', '090.8828.007', '2012-09-26'),
	(13, 'Quoc', 'Tran', '090.8828.007', '2012-09-17');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- Dumping structure for table course.subject
DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `NUMBER_OF_CLASS` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table course.subject: ~6 rows (approximately)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`ID`, `NAME`, `DESCRIPTION`, `NUMBER_OF_CLASS`) VALUES
	(16, 'Draw', 'Draw', NULL),
	(17, 'Math', 'Math', NULL),
	(18, 'English', 'English', NULL),
	(19, 'Biology', '', 1),
	(21, 'Object oriented propraming', '', NULL),
	(24, 'Data base management system', '', NULL);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- Dumping structure for table course.score
DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `SCORE` double DEFAULT '0',
  `STUDENT_ID` int(11) DEFAULT '0',
  `SUBJECT_ID` int(11) DEFAULT '0',
  `COURSE_ID` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_student` (`STUDENT_ID`),
  KEY `FK__subject` (`SUBJECT_ID`),
  KEY `FK__course` (`COURSE_ID`),
  CONSTRAINT `FK_student` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK__course` FOREIGN KEY (`COURSE_ID`) REFERENCES `course` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `FK__subject` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- Dumping data for table course.score: ~16 rows (approximately)
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` (`ID`, `SCORE`, `STUDENT_ID`, `SUBJECT_ID`, `COURSE_ID`) VALUES
	(1, 7.5, 2, 16, 1),
	(2, 8, 2, 17, 1),
	(24, 8, 4, 17, 1),
	(29, 9, 4, 21, 1),
	(31, 10, 4, 16, 1),
	(32, 3, 4, 16, 1),
	(33, 6, 8, 16, 1),
	(34, 9, 2, 16, 1),
	(35, 6, 2, 16, 3),
	(36, 8, 2, 19, 1),
	(37, 9, 2, 18, 1),
	(38, 10, 8, 17, 1),
	(39, 8, 8, 16, 1),
	(40, 9, 13, 16, 1),
	(41, 8, 13, 18, 1),
	(42, 4, 13, 21, 1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;


/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
