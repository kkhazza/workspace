CREATE TABLE `panel` (
	`NO` INT NOT NULL AUTO_INCREMENT,
	`TITLE` VARCHAR(30) NOT NULL,
	`URL` VARCHAR(100) NOT NULL,
	`COMMENT` VARCHAR(50) NULL DEFAULT '',
	`DELYN` CHAR(1) NOT NULL DEFAULT 'N',
	PRIMARY KEY (`NO`)
);

INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST1','https://placehold.it/150x80?text=TEST1','이미지 연습1');
INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST2','https://placehold.it/150x80?text=TEST2','이미지 연습2');
INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST3','https://placehold.it/150x80?text=TEST3','이미지 연습3');
INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST4','https://placehold.it/150x80?text=TEST4','이미지 연습4');
INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST5','https://placehold.it/150x80?text=TEST5','이미지 연습5');
INSERT INTO panel (`TITLE`, `URL`, `COMMENT`) VALUES ('TEST6','https://placehold.it/150x80?text=TEST6','이미지 연습6');
