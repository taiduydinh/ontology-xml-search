<?php
	function actual_date( $datefmt, $UseTimeServer, $HourOffset ) {
		if ( $HourOffset < -12 || $HourOffset > 12 ) 
			$HourOffset = 0;
		$timestamp = time() + $HourOffset * 3600;
		if( $UseTimeServer )
			return date( $datefmt, $timestamp );  
		return gmdate( $datefmt, $timestamp );
	}
	function getCtr( $oid, $fmt ) {
		$SALT = '77c37bb5';	
		return md5( $SALT.$oid.$SALT.$fmt.$SALT );
	}
	function checkIServDataDir( $relpath, $iserv, $diplayresult )
	{
		$idir = $relpath . $iserv . '/data';
		if( is_dir( $idir ) )
		{
			$result = "";
			if( file_exists( $idir .'/index.html' ) ) {
				$result = "$iserv ok<br>";
			} else {
			    if( !$handle = fopen( $idir .'/index.html', 'w' ) ) {
					$result = "$iserv KO<br>";
			    } else {
					if( fwrite($handle, " ") === FALSE ) {
						$result = "$iserv KO<br>";
					} else {
						$result = "$iserv DONE<br>";
					}					
					fclose($handle);
					if( $iserv == "twsc" ) {
						if( !$handle = fopen( $idir .'/.htaccess', 'w' ) ) {
						} else {
							fwrite($handle, "order deny,allow\r\ndeny from all");
							fclose($handle);
						}
					}
			    }
			}
			if( $diplayresult )
				echo $result;
		}
	}
	function checkAllIServDataDirs( $relpath, $diplayresult )
	{
		if( $relpath == "" )
			$relpath = "../";
		checkIServDataDir( $relpath, 'twsc', $diplayresult );
		checkIServDataDir( $relpath, 'poll', $diplayresult );
		checkIServDataDir( $relpath, 'form2mail', $diplayresult );
		checkIServDataDir( $relpath, 'blog', $diplayresult );
	}
?>
