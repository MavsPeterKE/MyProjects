<?php

//Database connection and selection
mysql_connect("localhost","root","") or die(mysql_error());

mysql_select_db('json') or die(mysql_error());

  extract($_POST);
 //convert json object to php associative array  
  $obj = json_decode($mydata);
  
  //Extract Usefull data
  $fname = $obj->fname;
  $mname = $obj->mname;
  $lname = $obj->lname;

  


//checks that all fields are entered
if ( !$fname || !$mname || !$lname)
  { 
 echo "3";
 exit();
  }
  
 //checks double username registration
 $dupe1 = mysql_num_rows(mysql_query("SELECT * FROM users WHERE fname='$fname' AND mname='$mname' AND lname='$lname'")); 
 
        if ($dupe1 > 0) { 
  
                    echo "2";
                     exit();
                         }


//insert data into the database
$sql = "INSERT INTO users(fname, mname, lname) VALUES ('$fname','$mname','$lname')";

$result=mysql_query($sql) or die("Cannot Execute".mysql_error());

if ($result)
 {
    echo "4";
}
else{
    echo "Error";
}

?>

