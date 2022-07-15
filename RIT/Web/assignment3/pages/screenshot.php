<?php
	$page='Dragon Ball Z: Battle of Gods Screenshots';
	$path="../";
	include($path."./php/header.php");
?>
<script type="text/javascript">	/* This javascript is used to swap pictures, when clicked on.*/
	//Creates new Array
	var newImage= new Array();
	
	//Loads Image 
	function loadImage(numberImage) 
	{
		var path = "../images/";
		for (var i=1; i <= numberImage; i++) 
		{
			newImage[i] = new Image();
			newImage[i].src= path+"DBZ_Battle_Of_Gods" + i + "Large.jpg";
		}
	}
	
	//Change Image
	function changeImage(which) 
	{
		var start = which.indexOf("/DBZ_Battle_Of_Gods") + 19;
		var end = which.indexOf(".jpg");
		var num = which.substring(start,end);
		document.images[0].src = newImage[num].src;
	}
	
</script>
</head>
<body onload="loadImage(document.getElementById('imageThumbnailsHolder').getElementsByTagName('img').length);">
<?php
	include($path."./php/nav.php");
?>

	<div id="content">
		<h1>Dragon Ball Z: Battle of Gods Screenshots</h1>
		<p>In here, you will see the screenshots of the movie, Dragon
		Ball Z: Battle of Gods. You can see what the movie is like in
		present time, with recent animation technology. Click 
		on the picture for bigger view!</p>
		
		<!--Preview Image-->
    	<div id="PreviewImage">
        	<img src="../images/DBZ_Battle_Of_Gods1Large.jpg"/>    
    	</div>
    	
    	<!--This section holds all of picture thumbnails-->
    	<div class="previewImage">
        	<div style="height:160px; width:975px; overflow:auto; white-space: nowrap;" id="imageThumbnailsHolder">
            	<img onClick= "changeImage(this.src)"  onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods1.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods2.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods3.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods4.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods5.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods6.jpg" border="0" />
        	    <img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods7.jpg" border="0" /> 
            	<img onClick= "changeImage(this.src)"   onmouseover="this.style.cursor='pointer';" src="../images/DBZ_Battle_Of_Gods8.jpg" border="0" />
        	</div>
    	</div>
    </div>
<?php
	include($path."./php/footer.php");
?>
		