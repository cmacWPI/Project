<?php
	include('LIB_project1.php');
	
	// Header + 
	$document = ProjectOne::html_header("Yu-Gi-Oh! Cards Home Page");
	
	// Main Title
	$document .= ProjectOne::html_maintitle();
	
	// Navigation
	$document .= ProjectOne::html_nav("Home");
	
	// Sale Section
	$document .= ProjectOne::html_sale();
	
	// Catalog Section
	$document .= ProjectOne::html_catalog();
	
	// Footer
	$document .= ProjectOne::html_footer();
	
	echo $document;
?>