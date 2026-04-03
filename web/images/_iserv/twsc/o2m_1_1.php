<?php
	@require_once("../common/ipenv.php");
	@require_once("../common/com.php");
	$BACKUP_ORDER	= true;		
	$MERCHANT_TO	= "<>";		
	$MERCHANT_FROM	= "<>";	
	$MERCHANT_BCC 	= true;		
	$MERCHANT_DATE_FORMAT = "dd/mm/yyyy";	
	$VO_CTR = "11b7fe5f13a9e4bfd7c5b3e2d55df82f";					
	$hostsite = $_SERVER['HTTP_HOST'];
	$c_subject = str_replace( '{SiteUrl}', $hostsite, stripslashes( $_REQUEST['client_subject'] ) );
	$v_subject = str_replace( '{SiteUrl}', $hostsite, stripslashes( $_REQUEST['vendor_subject'] ) );
	$cinst = stripslashes( $_REQUEST['cinst'] );		
	$oanchor = stripslashes( $_REQUEST['oanchor'] );	
	$ohtml = stripslashes( $_REQUEST['ohtml'] );		
	$otxt = stripslashes( $_REQUEST['otxt'] );			
	$otxt = str_replace( "<br/>", " ", $otxt );			
	$cemail = $_REQUEST['cemail'];
	$ctr = $_REQUEST['ctr'];
	$orderID = $_REQUEST['oid'];
	$result = '';
	$ohtml_url = "";
	$otxt_url = "";
	$otxt_server = "";		
	if( strstr($otxt, '[COMMANDE]') === false ) {
		$otxt_server = "[SERVER]\n";		
	} else
		$otxt_server = "[SERVEUR]\n";		
	$otxt_server .= "Client IP = " . PMA_getIp() . "\nDate = ";
	if( $MERCHANT_DATE_FORMAT == "dd/mm/yyyy" ) {
		$otxt_server .= date("d-m-Y @ H:i:s (\G\M\TO)") . "\n";
	} else
		$otxt_server .= date("m-d-Y @ H:i:s (\G\M\TO)") . "\n";
	if( $hostsite != "" )
		$otxt_server .= "Site = " . $hostsite . "\n";
	if( $ctr == "" || $ctr != $VO_CTR )	
		die( 'ERR_CTR '  );
	if( $BACKUP_ORDER && is_dir( 'data' ) ) {
		$BOM = "\xEF\xBB\xBF";
		$logfile = "data/$orderID-log.txt";
		$fp=fopen( $logfile, "w" );
		if( $fp !== false ) { 
			fwrite( $fp, "error=$orderID" );
			fclose( $fp );
		} else
			$logfile = "";
		checkIServDataDir( '../', 'twsc', false );
		$fp=fopen( "data/$orderID.txt", "w" );
		if( $fp !== false ) { 
			if( fwrite($fp, $BOM . $otxt) === false )	
				$result .= 'ERR_BKTXT ';
			else {
				$otxt_url = "http://" . $hostsite . dirname( $_SERVER['SCRIPT_NAME'] ) . "/so.php?oid=$orderID&fmt=txt&ctr=" . getCtr( $orderID, "txt" );
				$otxt_server .= "Text Order = $otxt_url\n";
			}
			fclose($fp);
			$fp=fopen( "data/$orderID.html", "w" );
			if( $fp !== false ) { 
				if( fwrite($fp, $ohtml) === false )
					$result .= 'ERR_BKHTM ';
				else {
					$ohtml_url = "http://" . $hostsite . dirname( $_SERVER['SCRIPT_NAME'] ) . "/so.php?oid=$orderID&fmt=html&ctr=" . getCtr( $orderID, "html" );
					$otxt_server .= "HTML Order = $ohtml_url\n";
				}
				fclose($fp);
			}
			if( strlen($logfile) > 0 )
				unlink($logfile);
		}
	}
	$otxt = "$otxt_server\n$otxt";
	$LF = "\n";											
	$to = "";
	$headers = "MIME-Version: 1.0" . $LF;
	$headers .= "Content-type: text/plain; charset=utf-8" . $LF;
	$to = $MERCHANT_TO;
	$headers .= "From: $MERCHANT_FROM" . $LF;
	$headers .= "Return-Path: $MERCHANT_FROM" . $LF;
	if( @mail( $to, '=?UTF-8?B?'.base64_encode($v_subject).'?=', $otxt, $headers ) === false )
		$result .= 'ERR_MLC ';
	if( $cemail != "" ) {
		sleep(1);
		$LF = "\r\n";											
		$to = "";
		$headers = "MIME-Version: 1.0" . $LF;
		$headers .= "Content-type: text/html; charset=utf-8" . $LF;
		$to = $cemail;
		if( $MERCHANT_BCC )
			$headers .= "Bcc: $MERCHANT_TO" . $LF;
		$headers .= "From: $MERCHANT_FROM" . $LF;
		$headers .= "Return-Path: $MERCHANT_FROM" . $LF;
		if( strpos( $cinst, "<html" ) === false )
			$cinst = "<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"></head><body>$cinst</body></html>";
		if( $ohtml_url !== '' )
			$cinst = str_replace( '<!--EOID-->', '</a>', str_replace( '<!--SOID-->', "<a href=\"$ohtml_url\">", $cinst ) );
		if( @mail( $to, '=?UTF-8?B?'.base64_encode($c_subject).'?=', $cinst, $headers ) === false )
			$result .= 'ERR_MLV ';
	}
	echo $result;
?>
