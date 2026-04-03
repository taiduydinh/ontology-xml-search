<?php
	@require_once("../common/com.php");
	function getsourl( $oid, $fmt ) {
		return "so.php?oid=" . $oid . "&fmt=" . $fmt . "&ctr=" . getCtr( $oid, $fmt );
	}
	$path = "data";	
	$expiredtime = 300; 
	$scriptfilename = basename( $_SERVER['SCRIPT_NAME'] );
	$lang = "fr";
	$perpage = 5; 
	$ctr = "b36f28f8255428bc64b01949441932f5";	
	$website = $_SERVER['HTTP_HOST'] . dirname( $_SERVER['SCRIPT_NAME'] );
	$website = substr( $website, 0, strpos( $website, '/_iserv') );
	if( $lang == "fr" ) 
	{
		$OrderLabel = "Fichiers de commandes du site";
		$DeleteBtn = "Supprimer";
		$DeleteAllBtn = "Tout supprimer";
		$DeleteConfirm = "Cette action est irrÃ©versible ! Etes-vous sÃ»r de vouloir supprimer cette commande ?";
		$DeleteAllConfirm = "Cette action est irrÃ©versible ! Etes-vous sÃ»r de vouloir supprimer TOUS vos fichiers de commande ?";
		$noroderfiles = "Aucun fichier de commande";
		$loginLabel = "Identifiant:";
		$pwdLabel = "Mot de passe:";
		$LogonBtn = "Connexion";
		$LogoffBtn = "DÃ©connexion";
		$TXTVersion = "Fichier TXT";
		$HTMLVersion = "Fichier HTML";
		$DateFmt = "d/m/y H:i:s";
	}
	else
	{
		$OrderLabel = "Order files of";
		$DeleteBtn = "Delete";
		$DeleteAllBtn = "Delete all files";
		$DeleteConfirm = "This action is irreversible ! Are you sure you want to delete this order ?";
		$DeleteAllConfirm = "This action is irreversible ! Are you sure you want to delete ALL your order files ?";
		$noroderfiles = "No order file";
		$loginLabel = "Login:";
		$pwdLabel = "Password:";
		$LogonBtn = "Log in";
		$LogoffBtn = "Log out";
		$TXTVersion = "TXT file";
		$HTMLVersion = "HTML file";
		$DateFmt = "m/d/y H:i:s";
	}
	$dir_handle = @opendir($path) or die( $noroderfiles );      
	$lg = $_POST['login'];
	$pw = $_POST['pwd'];
	$curpage = intval($_POST['curpage']);
	if( $curpage == 0 )
		$curpage = 1;
	if( $_POST['logoff'] != "" )
	{
		setcookie("ollogin", "", time() - $expiredtime);
		unset( $_COOKIE['ollogin'] );
		setcookie("olpwd", "", time() - $expiredtime);
		unset( $_COOKIE['olpwd'] );
	}
	else if( strlen($lg) > 0 && strlen($pw) > 0 )
	{
		setcookie("ollogin", $lg, time() + $expiredtime);
		setcookie("olpwd", $pw, time() + $expiredtime);
	}
	else
	{
		$lg = $_COOKIE['ollogin'];
		$pw = $_COOKIE['olpwd'];
	}
	if( $ctr != getCtr( $lg, $pw ) )
	{
		unset( $_COOKIE['ollogin'] );
		unset( $_COOKIE['olpwd'] );
		die( "<center><h2>Backoffice $website</h2><form method=\"post\" action=\"$scriptfilename\"><table>
		<tr><td align='right'>$loginLabel</td><td><input type=\"text\" name=\"login\"></td></tr>
		<tr><td align='right'>$pwdLabel</td><td><input type=\"password\" name=\"pwd\"></td></tr>
		<tr><td>&nbsp;</td><td><input type=\"submit\" value=\"$LogonBtn\"></td></tr>
		</table></form></center>"
		);
	}
	echo '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		  <html>
			<head>
				<meta http-equiv="content-type" content="text/html;charset=UTF-8">
				<style>
					body, table { font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif; font-size: 12px; }
				</style>
			</head>
		  <body>';
	echo "<center><h2>$OrderLabel $website</h2>";
	$delfile = $_REQUEST['delallfiles'];
	if( strlen($delfile) > 0 ) 
	{
		while ( $file = readdir($dir_handle) ) 
		{
			if( $file == "." || $file == ".." || $file == $scriptfilename )          
				continue;         
			$ext = strtolower( substr($file, strrpos($file, '.') + 1) );
			if( $ext == "txt" || $ext == "html")
				unlink("$path/$file");
		}
	} 
	else
	{
		function getOList() {
			global $path, $dir_handle;
			$oarray = array();
			while( false !== ($file = readdir($dir_handle)) ) 
			{
				$ext = strtolower( substr($file, strrpos($file, '.') + 1) );
				if($file == "." || $file == ".." || $ext != "txt" )
					continue;
				$oarray[] = array( substr($file, 0, strrpos($file, '.')), filectime("$path/$file"));
			}
			function cmp($a, $b) {
				if($a[1] == $b[1])
					return 0;
				else
					return ($a[1] < $b[1]) ? 1 : -1;
			}
			usort($oarray, 'cmp');
			return $oarray;
		}
		$delfile = $_REQUEST['delfile'];
		if( strlen($delfile) > 5 && strlen($delfile) < 22 && substr($delfile, -4) == ".txt" ) {
			unlink("$path/$delfile");	
			unlink(str_replace(".txt", ".html", "$path/$delfile"));	
		}
		echo "<table cellspacing=0 cellpadding=0 border=0><tr>".
			"</tr>";
		$oarray = getOList();
		$npages = ceil(count($oarray)/$perpage);
		if( $curpage > $npages )
			$curpage = $npages;
		$idx0 = ($curpage-1)*$perpage;
		$navbar = "";
		if( $perpage < count($oarray) ) {
			$navbar = "<tr><td colspan='5' style='padding:5px 0px 5px 0px;' align='center'><table cellspacing=0 cellpadding=0 border=0><tr>";
			if( $curpage > 1 ) {
				$navbar .= "<td style='padding:0px 5px 0px 5px;' valign='center'><form method='post' action='$scriptfilename'><input type='submit' value='<'><input type='hidden' name='curpage' value='" . ($curpage-1) . "'></form></td>";
			}
			$navbar .= "<td style='padding:0px 5px 0px 5px;' valign='center'>Page $curpage/$npages</td>";
			if( $idx0+$perpage < count($oarray) ) {
				$navbar .= "<td style='padding:0px 5px 0px 5px;' valign='center'><form method='post' action='$scriptfilename'><input type='submit' value='>'><input type='hidden' name='curpage' value='" . ($curpage+1) . "'></form></td>";
			}
			$navbar .= "</tr></table></td></tr>";
		}
		echo $navbar;
		for( $i=$idx0;$i<min(count( $oarray ), $idx0+$perpage);$i++ )
		{
			$oid = $oarray[$i][0];
			$file = $oarray[$i][0].".txt";	
			echo "<tr>
			<td style='padding:0px 5px 0px 5px;' valign='center'>" . date($DateFmt, $oarray[$i][1]) . "</td>
			<td style='padding:0px 5px 0px 5px;' valign='center'><b>$oid</b></td>
			<td style='padding:0px 5px 0px 5px;' valign='center'><a href=\"" . getsourl( $oid, "txt" ) . "\">$TXTVersion</a></td>
			<td style='padding:0px 5px 0px 5px;' valign='center'>" . ( file_exists( "./$path/$oid.html" ) ? "<a href=\"" . getsourl( $oid, "html" ) . "\">$HTMLVersion</a>" : "" ) . "</td>
			<td style='padding:0px 5px 0px 5px;'><form method=\"post\" action=\"$scriptfilename\" style=\"margin:0;padding:0\"><input type=\"submit\" onclick='return(confirm(\"$DeleteConfirm\"));' value=\"$DeleteBtn\"><input type=\"hidden\" name=\"delfile\" value=\"$file\"></form></td>
			<tr>";      
		}      
		echo $navbar;
		echo "</table><br/>";
		if( count($oarray) >= 5 )
			echo "<form method='post' action='$scriptfilename'><input type='submit' onclick='return(confirm(\"$DeleteAllConfirm\"));' value=\"$DeleteAllBtn\"><input type=\"hidden\" name=\"delallfiles\" value=\"ok\"></form>";
	}
	if( count($oarray) == 0 )
		echo "$noroderfiles&nbsp;<br>";
	echo "&nbsp;<br><form method=\"post\" action=\"$scriptfilename\"><input type=\"submit\" value=\"$LogoffBtn\"><input type=\"hidden\" name=\"logoff\" value=\"ok\"></form>";
	echo "</center></body></html>";
	closedir($dir_handle);  
?>  
