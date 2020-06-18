<?php
 require "connect.php";
 $oldid = $_POST['Addoldid'];
 $cableid = $_POST['Addcableid'];
 $province = $_POST['Addprovince'];
 $hanging4fo = $_POST['Addhanging4fo'];
 $hanging6fo = $_POST['Addhanging6fo'];
 $hanging12fo = $_POST['Addhanging12fo'];
 $hanging24fo = $_POST['Addhanging24fo'];
 $du12fo = $_POST['Adddu12fo'];
 $odf6fo = $_POST['Addodf6fo'];
 $odf12fo = $_POST['Addodf12fo'];
 $odf24fo = $_POST['Addodf24fo'];
 $odf96fo = $_POST['Addodf96fo'];
 $closure6fo = $_POST['Addclosure6fo'];
 $closure12fo = $_POST['Addclosure12fo'];
 $closure24fo = $_POST['Addclosure24fo'];
 $buloongti300 = $_POST['Addbuloongti300'];
 $buloongti400 = $_POST['Addbuloongti400'];
 $clamp = $_POST['Addclamp'];
 $poleu8 = $_POST['Addpoleu8'];
 $ironpole6 = $_POST['Addironpole6'];
 $sc_lc5 = $_POST['Addsc_lc5'];
 $sc_lc10 = $_POST['Addsc_lc10'];
 $sc_sc5 = $_POST['Addsc_sc5'];
 $datechange = $_POST['Adddatechange'];
 $comment = $_POST['Addcomment'];
 $userchange = $_POST['Adduserchange'];
 $query = "INSERT INTO history VALUES(null, '$oldid','$cableid','$province',$hanging4fo,$hanging6fo,
 $hanging12fo,$hanging24fo,$du12fo,$odf6fo,$odf12fo,$odf24fo,$odf96fo,$closure6fo,$closure12fo,$closure24fo,
 $buloongti300,$buloongti400,$clamp,$poleu8,$ironpole6,$sc_lc5,$sc_lc10,$sc_sc5,$datechange,$comment,$userchange
 )";
 if(mysqli_query($connect,$query)){
   echo "Insert success";
 }
 else{
   echo "Insert error";
 }
?>
