//json uri
var URI = '/'; //local
//var URI = 'http://garam.kwater.or.kr/'; //server

//image uri
//var image_uri = 'http://localhost:81/garam';			//local
var image_uri = 'http://garam.kwater.or.kr/image_path/';	//server

// 	한글 깨지는 경우 WAS에 인코딩 처리필요		
//	1. web.xml에 filter에 encoding확인 
//	2. tomcat server.xml 에 아래와 같이 추가

// 예) tomcat server.xml 에서 두부분을 찾아서. 추가
//		<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" 				URIEncoding="UTF-8" UseBodyEncodingForURL="true"/>
//		<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" 											URIEncoding="UTF-8"/>



$.ajaxSetup({
    cache: false
});

/**
 * 서버에 데이터 전송
 * @param sqlType select : true, 기타 : false (Boolean)
 * @param sendData 전송 데이터
 * @param sucFun 성공 일경우 실행 될 함수
 * @param errFun 실패 일경우 실행 될 함수
 */
function ajaxSend(sqlType, sendData, sucFun) {
    
    var url;
    if(sqlType) {
        url = URI+"common/jsonSelectList.do";
    } else {
        url = URI+"common/jsonArrayExec.do";
    }
   
    //에러처리하고
    var errFun = function( jqXHR, textStatus, errorThrown )
    {
        alert( textStatus + ", " + errorThrown +"\n에러가 발생하였습니다.\n잠시후에 사용하여 주십시오." );
    };
    
    $.ajax({
        url             : url,
        type            : 'POST',
        contentType     : "application/json; charset=UTF-8",
        cache           : false,
        dataType        : "json",
        data            : sendData,
        timeout         : 15000,
        success         : sucFun,
        error           : errFun
    },"json");
    

}

	/**
 * 서버에 데이터 전송
 * @param sqlType select : true, 기타 : false (Boolean)
 * @param sendData 전송 데이터
 * @param sucFun 성공 일경우 실행 될 함수
 * @param errFun 실패 일경우 실행 될 함수
 */
function ajaxXSend(sqlType, sendData, sucFun) {
    
	$.ajaxPrefilter('json', function(options, orig, jqXHR) {
        return 'jsonp';
    }); 
	
    var url;
    if(sqlType) {
        url = URI+"common/jsonXSelectList.do";
    } else {
        url = URI+"common/jsonXArrayExec.do"
    }
    
    //에러처리하고
    var errFun = function( jqXHR, textStatus, errorThrown )
    {
        alert( textStatus + ", " + errorThrown +"\n에러가 발생하였습니다.\n잠시후에 사용하여 주십시오." );
    };
    
    $.ajax({
        url             : url,
        type            : 'POST',
        crossDomain 	: true,
        contentType     : "application/json; charset=UTF-8",
        cache           : false,
        dataType        : "json",
        data            : sendData,
        timeout         : 15000,
        success         : sucFun,
        error           : errFun
    },"json");
    

}


///**
// * 콩통코드 로딩
// * @param sql
// * @param codeKey
// */
//function commonCode(sql, codeKey) {
//    //데이터 셋팅
//    var sendData = {};
//    var data = {};
//    data.sql_id    = encData( sql      );
//    data.exec      = encData( 'select' );
//    sendData.jsonArray = new Array(data);
//    var sucFun = function( data, textStatus, jqXHR )
//    { 
//        if( data.length > 0 ) {
////        	alert(JSON.stringify(data));
////            sessionStorage.setItem( codeKey, JSON.stringify(data) );
////            alert("공콩코드값 저장 로딩 완료");
//        } else {
//            alert("공통 코드값 로드 실패!");
//        }
//    }
//    var errFun = function( jqXHR, textStatus, errorThrown )
//    {
//        alert( textStatus + ", " + errorThrown +"\n에러가 발생하였습니다.\n잠시후에 사용하여 주십시오." );
//    }
//    ajaxSend(true, JSON.stringify(sendData), sucFun, errFun);
//}
