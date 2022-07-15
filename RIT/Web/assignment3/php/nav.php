<body>
<!--Navigaton Bar-->
	<div id="nav">
		<ul>
			<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods Home Page') ? ' class="active"': '';?>>
				<a href="<?php echo $path;?>index.php" title="">Home</a></li>
		</ul>
		<ul>
			<li><h2>Overview</h2><ul>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods Infomation') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/info.php" title="">Infomation</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods Summary') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/summary.php" title="">Summary</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods Screenshots') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/screenshot.php" title="">Screenshots</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Tralier') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/tralier.php" title="">Tralier</a></li>
			</ul></li>
		</ul>
		<ul>
			<li><h2>Characters</h2><ul>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Goku') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/goku.php" title="">Goku</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Vegeta') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/vegeta.php" title="">Vegeta</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Birius') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/birus.php" title="">Birus</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Whis') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/whis.php" title="">Whis</a></li>
			</ul></li>
		</ul>
		<ul>
			<li><h2>Others/Extras</h2><ul>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Links') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/links.php" title="">Links</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Resources') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/resources.php" title="">Resources</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Feedback') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/final/comment/feedbackDBZ.php" title="">Feedback Form</a></li>
				<li<?php echo (isset($page) && $page=='Dragon Ball Z: Battle of Gods - Feedback Data') ? ' class="active"': '';?>>
					<a href="<?php echo $path;?>pages/final/databaseData.php" title="">Feedback Data</a></li>
			</ul></li>
		</ul>
	</div>
<!--Navigaton Bar-->

