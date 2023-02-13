CREATE TRIGGER InsertAuthority
ON [User]
AFTER INSERT
AS
BEGIN
	DECLARE @ID BIGINT;
	DECLARE @ROLE NVARCHAR(10) = 'GUEST';
	SELECT @ID = inserted.Id From inserted; 	
	PRINT('inserted successfully id = ' + CAST(@ID AS NVARCHAR(100)) + ' role = ' + @ROLE )
	INSERT INTO [Authority](UserId,RoleId) VALUES (@ID,@ROLE); 
END

INSERT INTO [User](Username, [Password], Fullname, Email, PhoneNumber, Avatar, Activated)
VALUES ('ABC','123','Nguyen Van An','gamil','0366156144','asdghggh',1);

SELECT * from [User]
SELECT * from [Role]

SELECT * from [Authority]