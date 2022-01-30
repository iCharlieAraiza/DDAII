<?php
if(isset($_GET['action'])) {
	$employees = simplexml_load_file('data/empleados.xml');
	$id = $_GET['id'];
	$index = 0;
	$i = 0;
	foreach($employees->employee as $employee){
		if($employee['id'] == $id){
			$index = $i;
			break;
		}
		$i++;
	}
	unset($employees->employee[$index]);
	file_put_contents('data/empleados.xml', $employees->asXML());
}
$employees = simplexml_load_file('data/empleados.xml');
echo 'Number of products: '.count($employees);
echo '<br>List Product Information';
?>
<br>
<a href="add_employee.php">Add new product</a>
<br>
<table cellpadding="2" cellspacing="2" border="1">
	<tr>
		<th>ID_EMP</th>
		<th>Nombre</th>
		<th>Apellido</th>
        <th>Edad</th>
        <th>Sexo</th>
        <th>Cargo</th>
		<th>Option</th>
	</tr>
	<?php foreach($employees->employee as $employee) { ?>
	<tr>
		<td><?php echo $employee['id']; ?></td>
		<td><?php echo $employee->name; ?></td>
		<td><?php echo $employee->lastName; ?></td>
        <td><?php echo $employee->age; ?></td>
        <td><?php echo $employee->gender; ?></td>
        <td><?php echo $employee->position; ?></td>
		<td><a href="edit.php?id=<?php echo $employee['id']; ?>">Edit</a> |
			<a href="index.php?action=delete&id=<?php echo $employee['id']; ?>" onclick="return confirm('Are you sure?')">Delete</a></td>
	</tr>
	<?php } ?>
</table>
