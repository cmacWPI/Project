<?php
	include('LIB_project1.php');
	
	// Header + Page Title
	$document = ProjectOne::html_header("Yu-Gi-Oh! Cards Cart Page");
	
	// Main Title
	$document .= ProjectOne::html_maintitle();
	
	// Navigation
	$document .= ProjectOne::html_nav("Cart");
	
	// Cart Section
	$document .= ProjectOne::html_cart();
	
	// Footer
	$document .= ProjectOne::html_footer();
	
	echo $document;
?>