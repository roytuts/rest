<?php

/**
* Author : https://www.roytuts.com
*/

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['file'])) {
	if (isset($_FILES['file']['name'])) {
		if (0 < $_FILES['file']['error']) {
			echo 'Error during file upload ' . $_FILES['file']['error'];
		} else {
			$upload_path = 'uploads/';
			if (file_exists($upload_path . $_FILES['file']['name'])) {
				echo 'File already exists => ' . $upload_path . $_FILES['file']['name'];
			} else {
				if (!file_exists($upload_path)) {
					mkdir($upload_path, 0777, true);
				}
				move_uploaded_file($_FILES['file']['tmp_name'], $upload_path . $_FILES['file']['name']);
				echo 'File successfully uploaded => "' . $upload_path . $_FILES['file']['name'];
			}
		}
	} else {
		echo 'Please choose a file';
	}
	echo nl2br("\n");
}