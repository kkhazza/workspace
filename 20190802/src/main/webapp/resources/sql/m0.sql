use `201907`;

CREATE TABLE m0_stu (
	no INT,
	nm VARCHAR(10)
);

CREATE TABLE m0_stu_point (
	no INT,
	korean INT,
	math INT,
	english INT,
	science INT 
);

/***************************************************************************************
 * INSERT INTO m0_stu (no, nm) VALUES (?, ?);
 * INSERT INTO m0_stu_point (no, korean, math, english, science) VALUES (?, ?, ?, ?, ?);
 ***************************************************************************************/