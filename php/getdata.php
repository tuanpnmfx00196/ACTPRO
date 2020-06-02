<?php
require "connect.php";
$query = "SELECT * FROM opticalcableact";
$data = mysqli_query($connect,$query);
class Cable{
  function Cable($id, $cableid, $province){
    $this->ID=$id;
    $this->CableId=$cableid;
    $this->Province =$province;
    }
}

$ArrayCable = array();
while($row = mysqli_fetch_assoc($data)){
  array_push($ArrayCable, new Cable($row['id'],$row['cableid'],$row['province']));
}
echo json_encode($ArrayCable);
?>
