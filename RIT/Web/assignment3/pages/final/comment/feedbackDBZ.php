<?php
	$page='Dragon Ball Z: Battle of Gods - Feedback';
	$path="../../../";
	include($path."./php/header.php");
?>
	<script>
	function validateForm()
	{
		var genderValid = false;
		var x = document.forms["DBZForm"]["FirstName"].value;
		if (x==null || x=="")
	  	{
	  		alert("First Name must be filled out.");
	  		return false;
	  	}
	  	else if(x.length > 50)
	  	{
	  		alert("First Name cannot be more than 50 characters.");
	  		return false;
	  	}
		var y = document.forms["DBZForm"]["LastName"].value;
		if (y == null || y == "")
	  	{
	  		alert("Last Name must be filled out.");
	  		return false;
	  	}
	   	else if(y.length >= 50)
	  	{
	  		alert("Last Name cannot be more than 50 characters.");
	  		return false;
	  	}
	    var genderCheck = document.getElementsByName("Gender");
		if (genderCheck[0].checked == true) 
		{
	    	genderValid = true;
	    } 
	    else if(genderCheck[1].checked == true) 
	    {
	    	genderValid = true;
	    }   
	    if(!genderValid)
	    { 
	    	alert("You must select either Male or 'Female, cannot skip this!");
	  		return false;
	  	}
		var z = document.forms["DBZForm"]["Email"].value;
		var position1 = z.indexOf("@");
		var position2 = z.lastIndexOf(".");
		if (position1 < 1 || position2 < position1+2 || position2+2 >= z.length)
	  	{
	  		alert("Not a valid e-mail address");
	  		return false;
	  	}
	  	else if(z == null || z == "")
	  	{
	  		alert("Email Address must be filled out");
	  		return false;  		
	  	}
	  	else if(z.length > 70)
	  	{
	  		alert("Email Address cannot be more than 70 characters");
	  		return false;  		
	  	}
		var c = document.forms["DBZForm"]["Comment"].value;
		if(c == null || c == "")
	  	{
	  		alert("Feedback cannot be left blank, please enter your feedback.");
	  		return false;
	  	}
	  	else if(c.length > 100)
	  	{
	  		alert("Feedback must be no longer than 100 characters.");
	  		return false;
	  	}
	}
	</script>
</head>
<?php
	include($path."./php/nav.php");
?>
	<div id="content">
	<h1>Dragon Ball Z: Battle of Gods - Feedback Form</h1>
	<p>Here in this page, you can fill out the form and send the feedback about my site.</p>
	<p>All fields are required.</p>
		<form method="GET" form name="DBZForm" onsubmit="return validateForm()" method="post" action="feedbackDBZ.php">
			.      First Name: <input type="text" name="FirstName">
			<br><br>
			.   Last Name: <input type="text" name="LastName">
			<br><br>
			.   Gender:
			<input type="radio" name="Gender" value="M">Male
			<input type="radio" name="Gender" value="F">Female
			<br><br>
			.   Email Address: <input type="text" name="Email">
			<br><br>
			.   Feedback: <textarea name="Comment" rows="5" cols="40"></textarea>
			<br><br>
			.   <input type="submit" name="submit" value="Submit">
		</form>
<?php
	include("../../../../../dbInfo.php"); //Hook up to my Database
	$dbLink = mysql_connect($host,$user,$pass)
	or die("couldn't connect: ".mysql_error()); 			
	mysql_select_db($user);
	if (isset($_GET['submit'])) 
	{		
		//stop sql injections for $_GET
		foreach($_GET as $key=>$val) 
		{
			//additionally would probably want to sanitize as well
			$_GET[$key] = mysql_real_escape_string($val);
		}
		//stop sql injections for $_POST
		foreach($_POST as $key=>$val) 
		{
			//additionally would probably want to sanitize as well
			$_POST[$key] = mysql_real_escape_string($val);
		}
				    
		//build query and insert info into the db
		$query = "insert into dbzDB values ('', '".$_GET['FirstName']."' , '".
		$_GET['LastName']."' ,'".$_GET['Gender']."', '".$_GET['Email'].
		"' , '".$_GET['Comment']."')";
		$res = mysql_query($query);
		echo "<h2>Thank you for your feedback.</h2>";
	}	
?>

	</div>
<?php
	include($path."./php/footer.php");
?>
