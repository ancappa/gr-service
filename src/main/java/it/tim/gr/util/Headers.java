package it.tim.gr.util;

import static it.tim.gr.service.IdsGenerator.generateTransactionId;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Headers {
    
    public static void updateHeaders(HttpHeaders headers) {
    	
    	Date now = new Date(System.currentTimeMillis());
    	
    	headers.set("MessageID", generateTransactionId(24));
    	
    	headers.remove("sourceSystem");
    	headers.remove("SourceSystem");
    	headers.set("SourceSystem", "CBE");
    	headers.set("interactionDate-Date", getDate(now));
    	headers.set("interactionDate-Time", getTime(now));

    	headers.remove("sessionjwt");

    	headers.set("Accept-Encoding", "identity");
    	
    	log.debug("OUTPUT HEADERS: " + headers);
    }
    
    
    
	private static String getTime(Date d){
		SimpleDateFormat sdfTime = new SimpleDateFormat ( "HH:mm:ss.SSS" );
		return sdfTime.format(d);
	}

	private static String getDate(Date d){
		SimpleDateFormat sdfDate = new SimpleDateFormat ( "yyyy-MM-dd" );
		return sdfDate.format(d);
	}
	
}
