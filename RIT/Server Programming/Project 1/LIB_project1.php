<?php
class ProjectOne
{
	// Header + Page Title Section
	static function html_header($title= "Untitled")
	{
		$string = <<<END
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>$title</title>
	<link href="css/Project1Style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="content">\n
END;
		return $string;
	}

	// Main Title Section
	static function html_maintitle()
	{
		$string = <<<END
		<img class='imgLogo' src='images/Yu-Gi-Oh!_Logo.png' alt='Logo' /><h1>Cards List</h1>\n
END;
		return $string;
	}
	
	// Navigation Section
	static function html_nav($title)
	{
		// Based on page, will give different navigation
		if($title == "Home")
		{
			$string = <<<END
		<ul class="nav_link">
			<li><a href="#" id="current" title="Home Page">Home</a></li>
			<li><a href="cart.php" title="Cart Page">Cart</a></li>
			<li><a href="admin.php" title="Admin Page">Admin</a></li>
		</ul>\n
END;
		}
		elseif($title == "Cart")
		{
			$string = <<<END
		<ul class="nav_link">
			<li><a href="index.php" title="Home Page">Home</a></li>
			<li><a href="#" id="current" title="Cart Page">Cart</a></li>
			<li><a href="admin.php" title="Admin Page">Admin</a></li>
		</ul>\n
END;
		}
		elseif($title == "Admin")
		{
			$string = <<<END
		<ul class="nav_link">
			<li><a href="index.php" title="Home Page">Home</a></li>
			<li><a href="cart.php" title="Cart Page">Cart</a></li>
			<li><a href="#" id="current" title="Admin Page">Admin</a></li>
		</ul>\n
END;
		}
		return $string;
	}	
	
	// Sale Section
	static function html_sale()
	{
		// Connect to Database
		$mysqli = self::connectDB();		
	
		$string = <<<END
		<div id='header'>
			<h2 id='title'>Sale Items</h2>
		</div>\n
END;

		$itemCheck = true;
		
		// If "Add to Cart" is clicked
		if (isset($_POST['Add']))
		{
			// Product ID
			$prodID = $_POST["itemID"];
			
			// SELECT statement - Get data based on Product ID
			$selectString = "SELECT * FROM products WHERE prodID = ?";
			if($select = $mysqli -> prepare($selectString))
			{
				$select -> bind_param("i", $prodID);
				$select -> execute();
				$select -> store_result();
				$select -> bind_result($pid, $pname, $pdesc, $pprice, $pquantity, $pimage, $psprice);
			}
			
			// Check if product quantity is above 0, show success message, else show message for an item that is out of stock
			if($select -> num_rows > 0)
			{
				$select -> fetch();
				if($pquantity > 0)
				{
					$string .= <<<END
		<h2>$pname has been added to cart!</h2>\n
END;
					--$pquantity;
				}
				else
				{
					$itemCheck = false;
					$string .= <<<END
		<h2>We are sorry, $pname is out of stock.</h2>\n
END;
				}
			}
			
			// Check if product quantity is above 0 then proceed to updating data for Products table then insert data into Carts table
			if($itemCheck)
			{
				// Update the product table by decreasing product quantity by 1 when "Add to Cart" button is clicked
				$updateString = "UPDATE products SET pquantity = '$pquantity' WHERE prodID = ?";
				if($update = $mysqli -> prepare($updateString))
				{
					$update -> bind_param("i", $prodID);
					$update -> execute();
					$update -> store_result();
					$update -> close();
				}
				
				// Insert the data based on user clicked "Add to Cart" button to Cart table, then close statement for SELECT and INSERT.
				$insertString = "INSERT INTO cart (prodname, pdesc, pquantity, saleprice) VALUES (?,?,?,?)";
				if($insert = $mysqli -> prepare($insertString))
				{
					$itemquantity = 1;
					
					$insert -> bind_param("ssid", $pname, $pdesc, $itemquantity, $psprice);
					$insert -> execute();
					$insert -> store_result();
					$insert -> close();
					$select -> close();
				}
			}
		}
		$string .= <<<END
		<div id='sales'>\n
END;

		// SELECT statement - Get all information from products table
		if($statement = $mysqli -> prepare("SELECT * FROM products"))
		{
			$statement -> execute();
			$statement -> store_result();
			$statement -> bind_result($id, $name, $desc, $price, $quantity, $image, $sprice);
		}
		if($statement -> num_rows > 0)
		{
			while($statement -> fetch())
			{
				// Specify 4 Product IDs for it to be on sale.
				if($id == 2 || $id == 6 || $id == 7 || $id == 11)
				{				
					$string .= <<<END
			<div class="aItem">
				<h3>$name</h3>
					<img class='imgLeft' src='images/$image' alt='Card Image' /><p>$desc</p>
					<p><strong>Sale Price:</strong> $$sprice (Original Price: $$price).  Only <strong>$quantity</strong> left!</p>
				<div class="aCart">
					<form action='index.php?page=1' method='post'><input type='submit' name='Add' value='Add To Cart'/><input type='hidden' name='itemID' value='$id'/></form>
				</div>
			</div>\n
END;
				}
			}
			$statement -> close();
		}
		$string .= <<<END
		</div>\n
END;
		return $string;
	}

	// Catalog Section
	static function html_catalog()
	{
		// Connect to Database
		$mysqli = self::connectDB();
		
		// SELECT statement - Get all information from products table
		if($statement = $mysqli -> prepare("SELECT * FROM products"))
		{
			$statement -> execute();
			$statement -> store_result();
			$statement -> bind_result($id, $name, $desc, $price, $quantity, $image, $sprice);
		}
		
		// Variables for Catalog
		$page = $firstCount = 1;
		$count = $lastCount = 0;
		$pageNum = $_GET['page'];
		$pageDom = $pageTotal = "";
		$pageArray = $itemFCount = $itemLCount = $boldPageAry = $pageLoopAry = array();
		$pageLoop;
		$boldPage;
		
		
		$string = <<<END
		<div id='header'>
			<h2 id='title'>Catalog</h2>
		</div>
		<div id='catalog'>\n
END;
		if($statement -> num_rows > 0)
		{
			while($statement -> fetch())
			{
				// If page number is 1, get first 5 row of data, ignores the Product ID from Sale section
				// Else if page number is above 1, get 4 row of data each and again, ignores the Product ID from Sale section
				// Else restart the count, get last row of data before moving to next page, ignores the Product ID from Sale section
				if($count < 5 && $page == 1)
				{
					// Exclude 4 Product ID that were on Sale Section
					if($id != 2 && $id != 6 && $id != 7 && $id != 11)
					{
						$pageDom .= self::makeDOMItem($page, $id, $name, $desc, $price, $quantity, $image, $sprice);
						$count++;
						$lastCount++;
						$pageLoop = " <a href='index.php?page=$page'>$page</a>";
						$boldPage = " [<b>$page</b>]";
					}
				}
				elseif($count < 4 && $page > 1)
				{
					// Exclude 4 Product ID that were on Sale Section
					if($id != 2 && $id != 6 && $id != 7 && $id != 11)
					{
						$pageDom .= self::makeDOMItem($page, $id, $name, $desc, $price, $quantity, $image, $sprice);
						$count++;
						$lastCount++;
						$pageLoop = " <a href='index.php?page=$page'>$page</a>";
						$boldPage = " [<b>$page</b>]";
					}
				}
				else
				{
					++$page;
					$count = 0;
					$pageArray[] = $pageDom;
					$pageDom = "";
					$pageLoopAry[] = $pageLoop;
					$boldPageAry[] = $boldPage;
					$itemFCount[] = $firstCount;
					$firstCount = $firstCount + 5;
					$itemLCount[] = $lastCount;					
					if($count == 0 && $page > 1)
					{
						// Exclude 4 Product ID that were on Sale Section
						if($id != 2 && $id != 6 && $id != 7 && $id != 11)
						{
							$pageDom .= self::makeDOMItem($page, $id, $name, $desc, $price, $quantity, $image, $sprice);
							$lastCount++;
							$pageLoop = " <a href='index.php?page=$page'>$page</a>";
							$boldPage = " [<b>$page</b>]";
						}
					}
				}
			}
						
			$statement -> close();
			
			$pageArray[] = $pageDom;
			$itemFCount[] = $firstCount;
			$itemLCount[] = $lastCount;
			
			$pageLoopAry[] = $pageLoop;
			$boldPageAry[] = $boldPage;
			
			// For Catalog section, if page number exceeds the total page, go to last page available (Last Page will be bolded)
			// If page number is above 1, will loop and bold it based on page number while other are linked.
			// If page number is 1, will loop until to end of page count (First Page will be bolded)
			if($pageNum > count($pageArray))
			{
				for($i = 0; $i < count($pageLoopAry) - 1; $i++)
				{
					$pageTotal .= $pageLoopAry[$i];
				}
				$pageTotal .= $boldPageAry[count($pageArray) - 1];
				$fCount = $itemFCount[count($pageArray) - 1];
				$lCount = $itemLCount[count($pageArray) - 1];
				$string .= $pageArray[count($pageArray) - 1];
				$string .= <<<END
			<div id="pagenum">
				<span id="right" >Showing items of 6 - $lCount</span> $pageTotal
			</div>
		</div>\n
END;
			}
			elseif($pageNum > 1)
			{
				for($i = 0; $i < count($pageLoopAry); $i++)
				{
					if($pageNum == $i + 1)
					{
						$pageTotal .= $boldPageAry[$i];
					}
					else
					{
						$pageTotal .= $pageLoopAry[$i];
					}
				}
				$fCount = $itemFCount[$pageNum - 1];
				$lCount = $itemLCount[$pageNum - 1];
				$string .= $pageArray[$pageNum - 1];
				$string .= <<<END
			<div id="pagenum">
				<span id="right" >Showing items of $fCount - $lCount</span> $pageTotal
			</div>
		</div>\n
END;
			}
			else
			{
				$pageTotal .= $boldPageAry[0];
				for($i = 0; $i < count($pageLoopAry) - 1; $i++)
				{
					$pageTotal .= $pageLoopAry[$i + 1];
				}
				$fCount = $itemFCount[0];
				$lCount = $itemLCount[0];
				$string .= $pageArray[0];
				$string .= <<<END
			<div id="pagenum">
				<span id="right" >Showing items of $fCount - $lCount</span> $pageTotal
			</div>
		</div>\n
END;
			}
		}
		return $string;
	}

	// Cart Section
	static function html_cart()
	{
		// Connect to Database
		$mysqli = self::connectDB();
		
		// If "Empty Cart" button is pressed, delete all info from cart table.
		if (isset($_POST['Empty']))
		{
			$deleteString = "DELETE FROM cart";
			if($delete = $mysqli -> prepare($deleteString))
			{
				$delete -> execute();
				$delete -> close();
			}
		}
		
		// SELECT statement, get all information from cart table
		if($statement = $mysqli -> prepare("SELECT * FROM cart"))
		{
			$statement -> execute();
			$statement -> store_result();
			$statement -> bind_result($name, $desc, $quantity, $sprice);
		}	
		$string = <<<END
		<div id='header'>
			<h2 id='title'>Cart Contents</h2>
		</div>
		<div id='cart'>\n
END;

		// Total Cost
		$tprice = 0.00;
		
		// If there is data in cart table, create cart item info, else show user the message of cart being empty.
		if($statement -> num_rows > 0)
		{
			while($statement -> fetch())
			{
				$tprice += $sprice;
				$string .= <<<END
			<div class="cItem">
				<h3>$name</h3>
					<p>$desc</p>
					<p>Quantity: <strong>$quantity</strong> at $$sprice each. Total: <strong>$$sprice</strong></p>
			</div>\n
END;
			}
			$string .= <<<END
			<h2 id='theader'>Total Cost: $$tprice</h2>
			<div class="cCart">
				<form action='cart.php' method='post'><input type='submit' name='Empty' value='Empty Cart'/></form>
			</div>
END;
			$statement -> close();
		}
		else
		{
			$string .= <<<END
			<div class="cItem">
				<h3>Your cart is empty!</h3>
			</div>\n
END;
			$statement -> close();
		}
		$string .= <<<END
		</div>\n
END;
		return $string;
	}

	// Admin Section
	static function html_admin()
	{
		// Empty Variables
		$vname = $vdesc = $vprice = $vquantity = $vimage = $vsprice = "";
		$ename = $edesc = $eprice = $equantity = $eimage = $esprice = "";
		$validateMsg = $validateAdd = "";
			
		$editCheck = false;
		
		// Administrator Pass
		$adminPass = "100YugiohAdmin1000";
		
		// Connect to Database
		$mysqli = self::connectDB();

		if($statement = $mysqli -> prepare("SELECT * FROM products"))
		{
			$statement -> execute();
			$statement -> store_result();
			$statement -> bind_result($aid, $aname, $adesc, $aprice, $aquantity, $aimage, $asprice);
		}
		
		// Prevent exploits
		$checkPHP = htmlspecialchars($_SERVER["PHP_SELF"]);
		
		$string = <<<END
		<div id='header'>
			<h2 id='title'>Inventory Administration Page</h2>\n
END;
		
		// Add an Item
		if (isset($_POST['add_item']))
		{
			$ID = $_POST['itemID'];
			$PW = $_POST['password'];
			
			// Validation for "Add Item" form
			if (empty($_POST["name"]) || $_POST["name"].length >= 75)
			{
				$string .= <<<END
				<span id='error'>Name is required and must be less than 75 characters long.</span><br>\n
END;
			}
			else
			{
				$vname = self::check_input($_POST["name"]);
				$validateAdd .= "P";
			}
			if (empty($_POST["description"]) || $_POST["description"].length >= 1000)
			{
				$string .= <<<END
				<span id='error'>Description is required and must be less than 1000 characters long.</span><br>\n
END;
			}
			else
			{
				$vdesc = self::check_input($_POST["description"]);
				$validateAdd .= "a";
			}
			if (empty($_POST["itemprice"]) || $_POST["itemprice"] == 0 || !preg_match('/^[0-9]+(\.[0-9]{1,2})?$/', $_POST["itemprice"]))
			{
				$string .= <<<END
				<span id='error'>Item Price is required and must be number greater than 0 with a max of 2 decimal places.</span><br>\n
END;
			}
			else
			{
				$vprice = self::check_input($_POST["itemprice"]);
				$validateAdd .= "s";
			}
			if (empty($_POST["quantity"]) || $_POST["quantity"] == 0 || !is_int((int)$_POST["quantity"]))
			{
				$string .= <<<END
				<span id='error'>Quantity is required and must be integer greater than 0.</span><br>\n
END;
			}
			else
			{
				$vquantity = self::check_input($_POST["quantity"]);
				$validateAdd .= "s";
			}
			if (empty($_POST["saleprice"]) || $_POST["saleprice"] < 0 || !preg_match('/^[0-9]+(\.[0-9]{1,2})?$/', $_POST["saleprice"]))
			{
				$string .= <<<END
				<span id='error'>Sale Price is required and must be number greater than or equal to 0 with a max of 2 decimal places.</span><br>\n
END;
			}
			else
			{
				$vsprice = self::check_input($_POST["saleprice"]);
				$validateAdd .= "e";
			}
			
			// If an new image is uploaded, proceed more validation, else validation fails.
			if(!empty($_FILES['image']) && $_FILES['image']['error'] == 0)
			{
				$filename = basename($_FILES['image']['name']);
				$ext = substr($filename, strpos($filename, ".") + 1);

				// Checks if the file is image, and if it is, start uploading the image file
				if($ext == "png" || $ext == ".jpg" || $ext == ".jpeg" || $ext == "gif" && ($_FILES['image']['type'] == "image/x-png" || $_FILES['image']['type'] == "image/gif" || $_FILES['image']['type'] == "image/jpeg"))
				{
					// Uploading to images folder
					$imageFile = "./images/$filename";
					$vimage = $filename;
					
					// Attempt to upload the image file and if success, proceeds to update the information
					if(move_uploaded_file($_FILES['image']['tmp_name'], $imageFile))
					{
						chmod($imageFile, 0644); // Permissions
						$validateAdd .= "d";
					}
					else
					{
						$string .= <<<END
				<span id='error'>The file cannot be uploaded.</span><br>\n
END;
					}
				}
				else
				{
					$string .= <<<END
				<span id='error'>The file has to be image and has to be in following format .png, .jpeg, or .gif file.</span><br>\n
END;
				}
			}
			else
			{
					$string .= <<<END
				<span id='error'>An image is required for new item.</span><br>\n
END;
			}
			if($validateAdd == "Passed")
			{
				// If the administrator's password is correct, proceed to update the info based on Product's ID, else retry again.
				if($PW == $adminPass)
				{
					$insertString = "INSERT INTO products (prodname, pdesc, prodprice, pquantity, image, saleprice) VALUES (?,?,?,?,?,?)";
					if($insert = $mysqli -> prepare($insertString))
					{
						$insert -> bind_param("ssdisd", $vname, $vdesc, $vprice, $vquantity, $vimage, $vsprice);
						$insert -> execute();
						$insert -> store_result();
						$insert -> close();
					}					
					$string .= <<<END
			<h2>$vname has been successfully added!</h2>\n
END;
					// Make them blank again
					$vname = $vdesc = $vprice = $vquantity = $vimage = $vsprice = "";
				}
				else
				{
					$string .= <<<END
				<span id='error'>Incorrect Password. Please retry again.</span><br>\n
END;
				}
			}				
		}

		
		// Edit the item
		if (isset($_POST['edit_item']))
		{
			$editCheck = true;
			$ID = $_POST['itemID'];
			$PW = $_POST['password'];
			
			// Validation for "Edit Item" form
			if (empty($_POST["name"]) || $_POST["name"].length >= 75)
			{
				$string .= <<<END
				<span id='error'>Name is required and must be less than 75 characters long.</span><br>\n
END;
			}
			else
			{
				$ename = self::check_input($_POST["name"]);
				$validateMsg .= "P";
			}
			if (empty($_POST["description"]) || $_POST["description"].length >= 1000)
			{
				$string .= <<<END
				<span id='error'>Description is required and must be less than 1000 characters long.</span><br>\n
END;
			}
			else
			{
				$edesc = self::check_input($_POST["description"]);
				$validateMsg .= "a";
			}
			if (empty($_POST["itemprice"]) || $_POST["itemprice"] == 0 || !preg_match('/^[0-9]+(\.[0-9]{1,2})?$/', $_POST["itemprice"]))
			{
				$string .= <<<END
				<span id='error'>Item Price is required and must be number greater than 0 with a max of 2 decimal places.</span><br>\n
END;
			}
			else
			{
				$eprice = self::check_input($_POST["itemprice"]);
				$validateMsg .= "s";
			}
			if (empty($_POST["quantity"]) || $_POST["quantity"] == 0 || !is_int((int)$_POST["quantity"]))
			{
				$string .= <<<END
				<span id='error'>Quantity is required and must be integer greater than 0.</span><br>\n
END;
			}
			else
			{
				$equantity = self::check_input($_POST["quantity"]);
				$validateMsg .= "s";
			}
			if (empty($_POST["saleprice"]) || $_POST["saleprice"] < 0 || !preg_match('/^[0-9]+(\.[0-9]{1,2})?$/', $_POST["saleprice"]))
			{
				$string .= <<<END
				<span id='error'>Sale Price is required and must be number greater than or equal to 0 with a max of 2 decimal places.</span><br>\n
END;
			}
			else
			{
				$esprice = self::check_input($_POST["saleprice"]);
				$validateMsg .= "e";
			}
			
			// If an new image is uploaded, proceed more validation, else sets image as old image.
			if(!empty($_FILES['image']) && $_FILES['image']['error'] == 0)
			{
				$filename = basename($_FILES['image']['name']);
				$ext = substr($filename, strpos($filename, ".") + 1);

				// Checks if the file is image, and if it is, start uploading the image file
				if($ext == "png" || $ext == ".jpg" || $ext == ".jpeg" || $ext == "gif" && ($_FILES['image']['type'] == "image/x-png" || $_FILES['image']['type'] == "image/gif" || $_FILES['image']['type'] == "image/jpeg"))
				{
					// Uploading to images folder
					$imageFile = "./images/$filename";
					$eimage = $filename;
					
					// Attempt to upload the image file and if success, proceeds to update the information
					if(move_uploaded_file($_FILES['image']['tmp_name'], $imageFile))
					{
						chmod($imageFile, 0644); // Permissions
						$validateMsg .= "d";
					}
					else
					{
						$string .= <<<END
				<span id='error'>The file cannot be uploaded.</span><br>\n
END;
					}
				}
				else
				{
					$string .= <<<END
				<span id='error'>The file has to be image and has to be in following format .png, .jpeg, or .gif file.</span><br>\n
END;
				}
			}
			else
			{
				$filename = basename($_FILES['image']['name']);
				$eimage = $_POST['oldImage'];
				$validateMsg .= "d";
			}
			if($validateMsg == "Passed")
			{
				// If the administrator's password is correct, proceed to update the info based on Product's ID, else retry again.
				if($PW == $adminPass)
				{
					$updateString = "UPDATE products SET prodname = '$ename', pdesc = '$edesc', prodprice = '$eprice', pquantity = '$equantity', image = '$eimage', saleprice = '$esprice' WHERE prodID = ?";
					
					if($update = $mysqli -> prepare($updateString))
					{
						$update -> bind_param("i", $ID);
						$update -> execute();
						$update -> store_result();
						$update -> close();
					}					
					$string .= <<<END
			<h2>$ename has been successfully edited.</h2>\n
END;
					// Make the form in Edit Item not show up.
					$editCheck = false;
				}
				else
				{
					$string .= <<<END
				<span id='error'>Incorrect Password. Please retry again.</span><br>\n
END;
				}
			}
		}
		
		$string .= <<<END
		</div>
		<div id='admin'>
			<div class="eItem">
				<h3>Edit Item</h3>
				<form action='admin.php' method='post'>
					<span id='aDesc'>Choose an item to edit:</span>
					<select name='selectOne'>\n
END;
		
		// Creates the drop-down menu of all products in "Edit Item" section
		if($statement -> num_rows > 0)
		{
			while($statement -> fetch())
			{
				$string .= <<<END
						<option value='$aid'>$aname</option>\n
END;
			}
			$string .= <<<END
					</select>
					<input type='submit' name='Edit' value='Select'/>
				</form>\n
END;
			$statement -> close();
		}

		// If validation did not pass in edit section, will repeat the edit section until it is passed.
		if($editCheck)
		{
			$productID = $_POST['itemID'];
			$string .= self::editItem($mysqli, $productID, $ename, $edesc, $eprice, $equantity, $eimage, $esprice);
		}
		
		// When "Select" button in Edit Item section is pressed, it gets the info based on product's ID
		if(isset($_POST['Edit']))
		{
			$productID = $_POST['selectOne'];
			
			// SELECT statement based on Product ID
			$selectString = "SELECT * FROM products WHERE prodID = ?";
			if($select = $mysqli -> prepare($selectString))
			{
				$select -> bind_param("i", $productID);
				$select -> execute();
				$select -> store_result();
				$select -> bind_result($pid, $pname, $pdesc, $pprice, $pquantity, $pimage, $psprice);
			}
			if($select -> num_rows > 0)
			{
				$select -> fetch();
				$string .= self::editItem($mysqli, $productID, $pname, $pdesc, $pprice, $pquantity, $pimage, $psprice);
				$select -> close();
			}
		}
		
		// Form for "Add Item" section
		$string .= <<<END
			</div>
			<div class="eItem">
				<h3>Add Item</h3>
				<form action="$checkPHP" method='post' enctype="multipart/form-data">
					<table>
						<tr>
							<td><span id='aDesc'>Name:</span> </td><td><input type="text" name="name" size="40" value="$vname"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Description:</span> </td><td><textarea name="description" rows="8" cols="80">$vdesc</textarea></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Item Price:</span> </td><td><input type="text" name="itemprice" size="10" value="$vprice"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Quantity:</span> </td><td><input type="text" name="quantity" size="10" value="$vquantity"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Sale Price:</span> </td><td><input type="text" name="saleprice" size="10" value="0.00"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Image:</span> </td><td><input type="file" name="image"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'><strong>Password:</strong></span> </strong></td><td><input type="password" name="password" size="25"/></td>
						</tr>
					</table>
					<div class="adminControl">
						<input type="reset" value="Reset Form"/><input type="submit" name="add_item" value="Add Item"/>
					</div>
				</form>\n
END;

		$string .= <<<END
			</div>
		</div>\n
END;
		return $string;		
	}
	
	// Footer - Prints out information based on user and present it.
	static function html_footer()
	{
		$date = date('F d, Y g:i a', time());
		$url = $_SERVER['SERVER_NAME'];
		$url .= $_SERVER['REQUEST_URI'];
		$ip = $_SERVER['REMOTE_ADDR'];
		$browser = self::getUserBrowser();
		$string = <<<END
		<div id="footer">
			<p><em>Accessed on $date.</em></p>
			<h3>Your Information</h3>
			<ul>
				<li>Your browser is $browser and your IP address is <em>$ip</em>.</li>
				<li>You clicked a link on <em>$url</em></li>
				<li><script>document.write('Your screen resolution is ' + screen.width + ' x ' + screen.height + '.');</script></li>
			</ul>
		</div>
	</div>
</body>
</html>
END;
		return $string;
	}
	
	// Connect to Database
	static function connectDB()
	{
		// Database
		$host = 'localhost';
		$user = 'cjm4547';
		$password = 'fr1end';
		$db = 'cjm4547';
		
		// Create connection
		$mysqli = new mysqli($host, $user, $password, $db);
		
		// Check connection
		if($mysqli -> connect_error)
		{
			die();
		}
		return $mysqli;
	}
	
	// Sanitizing the input.
	static function check_input($input) 
	{
		$input = trim($input);
		$input = stripslashes($input);
		$input = strip_tags($input, "<br>");
		return $input;
	}
	
	// Get User's Browser information
	static function getUserBrowser()
	{
		$user_agent = $_SERVER['HTTP_USER_AGENT'];
		if(preg_match('/MSIE/i', $user_agent))
		{
			$browser = "Internet Explorer";
		}
		elseif(preg_match('/Firefox/i', $user_agent))
		{
			$browser = "Firefox";
		}
		elseif(preg_match('/Safari/i', $user_agent))
		{
			$browser = "Safari";
		}
		elseif(preg_match('/Chrome/i', $user_agent))
		{
			$browser = "Chrome";
		}
		elseif(preg_match('/Opera/i', $user_agent))
		{
			$browser = "Opera";
		}
		else
		{
			$browser = "unknown";
		}
		return $browser;
	}
	
	// Edit an item, keeping the form in edit section up until the buttin in Add Item section is clicked
	static function editItem($mysqli, $productID, $productname, $productdesc, $productprice, $productquantity, $productimage, $productsprice)
	{
		// Form for "Edit Item" section
		$selectString = "SELECT * FROM products WHERE prodID = ?";
		if($select = $mysqli -> prepare($selectString))
		{
			$select -> bind_param("i", $productID);
			$select -> execute();
			$select -> store_result();
			$select -> bind_result($pid, $pname, $pdesc, $pprice, $pquantity, $pimage, $psprice);
		}
		if($select -> num_rows > 0)
		{
			$select -> fetch();
			$string = <<<END
				<form action="$checkPHP" method='post' enctype="multipart/form-data">
				   	<input type="hidden" name="itemID" value="$pid"/>
				   	<input type="hidden" name="oldImage" value="$productimage"/>
					<table>
						<tr>
							<td><span id='aDesc'>Name:</span> </td><td><input type="text" name="name" size="50" value="$productname"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Description:</span> </td><td><textarea name="description" rows="8" cols="80">$productdesc</textarea></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Item Price:</span> </td><td><input type="text" name="itemprice" size="10" value="$productprice"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Quantity:</span> </td><td><input type="text" name="quantity" size="10" value="$productquantity"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>Sale Price:</span> </td><td><input type="text" name="saleprice" size="10" value="$productsprice"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'>New Image:</span> </td><td><input type="file" name="image"/></td>
						</tr>
						<tr>
							<td><span id='aDesc'><strong>Password:</strong></span> </strong></td><td><input type="password" name="password" size="25"/></td>
						</tr>
					</table>
					<div class="adminControl">
						<input type="reset" value="Reset Form"/><input type="submit" name="edit_item" value="Edit Item"/>
					</div>
				</form>\n
END;
			$select -> close();
		}
		return $string;
	}
	
	// Create each item for document in catalog section.
	static function makeDOMItem($page, $id, $name, $desc, $price, $quantity, $image, $sprice)
	{
		// If the sale price is $0.00, the item will not be on sale.
		if($sprice == 0)
		{
			$pageDom = <<<END
			<div class="aItem">
				<h3>$name</h3>
					<img class='imgLeft' src='images/$image' alt='Card Image' /><p>$desc</p>
					<p><strong>Sale Price:</strong> $$sprice (Original Price: $$price).  Only <strong>$quantity</strong> left!</p>
					<p id='error'>This item is not for sale.</p>
			</div>\n
END;
		}
		else
		{
			$pageDom = <<<END
			<div class="aItem">
				<h3>$name</h3>
					<img class='imgLeft' src='images/$image' alt='Card Image' /><p>$desc</p>
					<p><strong>Sale Price:</strong> $$sprice (Original Price: $$price).  Only <strong>$quantity</strong> left!</p>
				<div class="aCart">
					<form action='index.php?page=$page' method='post'><input type='submit' name='Add' value='Add To Cart'/><input type='hidden' name='itemID' value='$id'/></form>
				</div>
			</div>\n
END;
		}
		return $pageDom;
	}
} // End class
?>