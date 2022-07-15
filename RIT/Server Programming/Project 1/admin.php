<?php
	include('LIB_project1.php');
	
	// Header + Page Title
	$document = ProjectOne::html_header("Yu-Gi-Oh! Cards Admin Page");
	
	// Main Title
	$document .= ProjectOne::html_maintitle();
	
	// Navigation
	$document .= ProjectOne::html_nav("Admin");
	
	// Admin Section
	$document .= ProjectOne::html_admin();
	
	// Footer
	$document .= ProjectOne::html_footer();
	
	echo $document;
?>