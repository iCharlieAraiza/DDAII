<?php
$employees = simplexml_load_file('data/empleados.xml');

if(isset($_POST['submitSave'])) {

	foreach($employees->employee as $employee){
		if($employee['id']==$_POST['id']){
			$employee->name = $_POST['name'];
			$employee->lastName = $_POST['lastName'];
            $employee->age = $_POST['age'];
            $employee->gender = $_POST['gender'];
            $employee->position = $_POST['position'];
			break;
		}
	}
    file_put_contents('data/empleados.xml', $employees->asXML());
	header('location:employee.php');
}

foreach($employees->employee as $employee){
	if($employee['id']==$_GET['id']){
		$id = $employee['id'];
		$name = $employee->name;
		$lastName = $employee->lastName;
        $age = $employee->age;
        $gender = $employee->gender;
        $position = $employee->position;
		break;
	}
}

?>
<form method="post">
	<table cellpadding="2" cellspacing="2">
		<tr>
			<td>Id</td>
			<td><input type="text" name="id" value="<?php echo $id; ?>" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="name" value="<?php echo $name; ?>"></td>
		</tr>
		<tr>
			<td>Apellido</td>
			<td><input type="text" name="lastName" value="<?php echo $lastName; ?>"></td>
		</tr>
        <tr>
			<td>Edad</td>
			<td><input type="text" name="age" value="<?php echo $age; ?>"></td>
		</tr>
        <tr>
			<td>Sexo</td>
			<td><input type="text" name="gender" value="<?php echo $gender; ?>"></td>
		</tr>
        <tr>
			<td>Puesto</td>
			<td><input type="text" name="position" value="<?php echo $position; ?>"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save" name="submitSave"></td>
		</tr>
	</table>
</form>
