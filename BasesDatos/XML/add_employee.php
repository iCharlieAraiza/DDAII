<?php
if(isset($_POST['submitSave'])) {
	$employees = simplexml_load_file('data/empleados.xml');
	$employee = $employees->addChild('employee');
	$employee->addAttribute('id', $_POST['id']);
	$employee->addChild('name', $_POST['name']);
	$employee->addChild('lastName', $_POST['lastName']);
	$employee->addChild('age', $_POST['age']);
	$employee->addChild('gender', $_POST['gender']);
	$employee->addChild('position', $_POST['position']);
	file_put_contents('data/empleados.xml', $employees->asXML());
	header('location:employee.php');
}
?>
<form method="post">
	<table cellpadding="2" cellspacing="2">
		<tr>
			<td>ID_EMP</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="name"></td>
		</tr>
        <tr>
			<td>Apellido</td>
			<td><input type="text" name="lastName"></td>
		</tr>
		<tr>
			<td>Edad</td>
			<td><input type="text" name="age"></td>
		</tr>
        <tr>
			<td>Sexo</td>
			<td><input type="text" name="gender"></td>
		</tr>
        <tr>
			<td>Position</td>
			<td><input type="text" name="position"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save" name="submitSave"></td>
		</tr>
	</table>
</form>
