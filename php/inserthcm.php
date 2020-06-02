<?php
 require "connect.php";
 $ = $_POST['addName'];

 $query = "INSERT INTO management VALUES(null, '$name','$birthday','$address')";
 if(mysqli_query($connect,$query)){
   echo "Insert success";
 }
 else{
   echo "Insert error";
 }
?>
