<?php
	$page='Dragon Ball Z: Battle of Gods - Feedback Data';
	$path="../../";
	include($path."./php/header.php");
?>
</head>
<?php
	include($path."./php/nav.php");
?>

	<div id="content">
	<h1>Dragon Ball Z: Battle of Gods Feedback Data</h1>
	<p>Here you will see all of feedbacks I have received for the site.</p>
	<?php
	include("../../../../dbInfo.php"); //Hook up to my Database
	$dbLink = mysql_connect($host,$user,$pass)
	or die("couldn't connect: ".mysql_error()); 			
	mysql_select_db($user);
	$query = "select	 * from dbzDB";
	$res = mysql_query($query);
		if (mysql_num_rows($res) == 0) 
		{
			echo "<h2>No Records Found!</h2>";
		} 
		else 
		{
			//turn into 2d ARRAY
			if ($res) 
			{
				//print out as a table
				$result = '<table border="1">';
				$firstRow = true;
				while ($row=mysql_fetch_assoc($res) ) 
				{
					//var_dump($records);die();
					if ($firstRow) 
					{
						//give the table a header...
						$result .= "<tr>";
						foreach($row as $index =>$val) 
						{
							$result .= "<th>$index</th>";
						}
						$result .= "</tr>";
						$firstRow = false;
					}
					//build the table
					$result .="<tr>";
					foreach($row as $index => $val) 
					{
						$result .= '<td style="padding: 10px;">'.$val.'</td>';
					} //each column
					$result .="</tr>";
				}
				$result .= "</table><hr />";
				echo $result;
			}
		}
	?>
	</div>
<?php
	include($path."./php/footer.php");
?>
	