CREATE OR REPLACE TYPE obj_persona AS OBJECT(
dni_p NUMBER,
nombre_p VARCHAR2(30),
sexo CHAR,
MAP MEMBER FUNCTION get_dni RETURN NUMBER,
MEMBER FUNCTION get_sexo RETURN CHAR,
MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_persona)
) NOT FINAL;

CREATE OR REPLACE TYPE obj_profesor UNDER obj_persona(
ncuenta_pr NUMBER(12),
area VARCHAR(30),
OVERRIDING MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_profesor),
MEMBER FUNCTION get_area RETURN VARCHAR
);

CREATE OR REPLACE TYPE obj_alumno UNDER obj_persona(
edad NUMBER,
semestre NUMBER,
OVERRIDING MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_alumno),
MEMBER FUNCTION get_semestre RETURN NUMBER
);

CREATE OR REPLACE TYPE obj_asignatura AS OBJECT(
codigo_as NUMBER,
nombre_as VARCHAR2(30),
semestre NUMBER,
MEMBER FUNCTION get_semestre RETURN NUMBER,
MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_asignatura)
);

CREATE OR REPLACE TYPE BODY obj_persona AS
MAP MEMBER FUNCTION get_dni RETURN NUMBER IS 
BEGIN 
RETURN dni_p; 
END; 
MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_persona) IS
BEGIN
DBMS_OUTPUT.PUT_LINE(dni_p || ' ' || nombre_p || ' ' || sexo);
END;
MEMBER FUNCTION get_sexo RETURN CHAR IS
BEGIN
RETURN sexo;
END;
END;
/

CREATE OR REPLACE TYPE BODY obj_profesor AS
OVERRIDING MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_profesor) IS
BEGIN
DBMS_OUTPUT.PUT_LINE(dni_p || ' ' || nombre_p || ' ' || sexo || ' '  || area);
END;
MEMBER FUNCTION get_area RETURN VARCHAR IS
BEGIN
RETURN area;
END;
END;
/

CREATE OR REPLACE TYPE BODY obj_alumno AS
OVERRIDING MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_alumno) IS
BEGIN
DBMS_OUTPUT.PUT_LINE(dni_p || ' ' || nombre_p || ' ' || sexo || ' '  || semestre);
END;
MEMBER FUNCTION get_semestre RETURN NUMBER IS
BEGIN
RETURN semestre;
END;
END;
/

CREATE OR REPLACE TYPE BODY obj_asignatura AS
MEMBER PROCEDURE display(SELF IN OUT NOCOPY obj_asignatura) IS
BEGIN
DBMS_OUTPUT.PUT_LINE(codigo_as || ' ' || nombre_as || ' ' || semestre);
END;
MEMBER FUNCTION get_semestre RETURN NUMBER IS
BEGIN
RETURN semestre;
END;
END;
/


CREATE TABLE tab_alumno OF obj_alumno;
CREATE TABLE tab_profesor OF obj_profesor;
CREATE TABLE tab_asignatura OF obj_asignatura;


CREATE TABLE Rel_Alumno_asignatura(
id_Rel NUMBER,
alumno obj_alumno,
asignatura obj_asignatura,
profesor obj_profesor
);


INSERT INTO tab_alumno VALUES(1, 'Juan', 'H', 21, 1);
INSERT INTO tab_alumno VALUES(2, 'María', 'M', 19, 2);
INSERT INTO tab_alumno VALUES(3, 'Pedro', 'H', 20, 2);
INSERT INTO tab_alumno VALUES(4, 'Brenda', 'M', 18, 1);
INSERT INTO tab_alumno VALUES(5, 'Carlos', 'H', 20, 2);
INSERT INTO tab_alumno VALUES(6, 'Ivonne', 'M', 18, 1);

INSERT INTO tab_profesor VALUES(1, 'Manuel', 'H', 234, 'Matemáticas');
INSERT INTO tab_profesor VALUES(2, 'Marta', 'M', 4037, 'Informática');
INSERT INTO tab_profesor VALUES(3, 'Alejandra', 'M', 1345, 'Humanidades');

INSERT INTO tab_asignatura VALUES(1, 'Cálculo', 1);
INSERT INTO tab_asignatura VALUES(2, 'Ecuaciones Dif', 2);
INSERT INTO tab_asignatura VALUES(3, 'Programación Estr', 1);
INSERT INTO tab_asignatura VALUES(4, 'Programacion OO', 2);
INSERT INTO tab_asignatura VALUES(5, 'Humanidades I', 1);
INSERT INTO tab_asignatura VALUES(6, 'Humanidades II', 1);


INSERT INTO Rel_Alumno_asignatura VALUES (
1,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=1),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=1),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
2,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=4),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=1),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
3,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=6),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=1),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1)
);


INSERT INTO Rel_Alumno_asignatura VALUES (
4,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=2),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=2),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
5,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=3),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=2),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
6,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=1),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=3),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
7,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=4),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=3),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
8,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=6),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=3),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
9,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=2),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=4),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
10,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=3),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=4),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
11,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=5),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=4),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=2)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
12,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=1),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=5),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
13,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=4),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=5),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
14,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=6),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=5),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
15,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=2),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=6),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
16,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=3),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=6),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);

INSERT INTO Rel_Alumno_asignatura VALUES (
17,
(SELECT VALUE(al) FROM tab_alumno al WHERE al.dni_p=5),
(SELECT VALUE(asi) FROM tab_asignatura asi WHERE asi.codigo_as=6),
(SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=3)
);


SELECT VALUE(pro) FROM tab_profesor pro WHERE pro.dni_p=1;


SELECT * FROM tab_alumno WHERE semestre = 1;

SELECT * FROM tab_asignatura WHERE semestre = 1;

SELECT al.alumno, al.asignatura FROM Rel_Alumno_asignatura al WHERE  al.asignatura.get_semestre() = 1;




DELETE FROM tab_profesor WHERE dni_p=1;
UPDATE tab_alumno WHERE




CREATE TYPE Empleado UNDER Personas(
Nombre_emp VARCHAR2(30),
Semestre INT
);
