<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctx" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>금강하구 조사자료</title>

<link href="${ctx }/css/default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/jq/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx }/css/jq/ui.jqgrid.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx }/css/jq/ui.jqgrid.noncursor.css" rel="stylesheet" type="text/css" media="screen" />

<script src="${ctx }/js/jq/jquery-1.11.1.js" type="text/javascript" ></script>
<script src="${ctx }/js/common.js" type="text/javascript" ></script>
<script src="${ctx }/js/jq/i18n/grid.locale-kr.js" type="text/javascript" ></script>
<script src="${ctx }/js/jq/jquery.jqGrid.src.js" type="text/javascript" ></script>



<script>
$(document).ready(function(){
	$("#list").jqGrid({
		url: "${ctx}/koem/HAGU/JsonViewList.do",    
        datatype: "json",
        postData: { 
        		 id:"WemoData",
        		 sdate: '2014-01-01',
        		 edate: '2014-02-01',
        		 sta_cde: '107'
        },
        colNames:['STA_CDE','OBS_DAT', 'STA_NAM', 'PH','COD','NTU','WTEMP','DOX','SALINITY'], //칼럼 이름
        colModel: [ //데이터 매핑 및 로우 속성
            {name:'STA_CDE',index:'STA_CDE', width:50, align:"center", sorttype:"int"},
            {name:'OBS_DAT',index:'OBS_DAT', width:50, align:"center", sorttype:"string"},
            {name:'STA_NAM',index:'STA_NAM', width:80, align:"center", sorttype:"string"},
            {name:'PH',index:'PH', width:200, align:"left", sorttype:"string"},
            {name:'COD',index:'COD', width:100,align:"left", sorttype:"string"},
            {name:'NTU',index:'NTU', width:100, align:"left", sortable:"string"},
            {name:'WTEMP',index:'WTEMP', width:120, align:"center", sortable:"string"},
            {name:'DOX',index:'DOX', width:120, align:"center", sortable:"string"},
            {name:'SALINITY',index:'SALINITY', width:120, align:"center", sortable:"string"}
            ],
            height: 320,
            loadtext: "조회중...",
            //width:800,
    		rowNum:10, // 한 화면에 보여줄 갯수
    		rowList:[10,30,50],
    		pager: '#pager',
    		pagination:true,
			viewrecords: true,
	        autowidth : true,
			loadonce: false,
			shrinkToFit: false,
			sortname: 'OBS_DAT',
			sortorder: 'ASC'

        });
	
	$("#getSearch").click (function(){  
		
		
		$("#list").jqGrid('setGridParam', {
			postData : {
	       		 id:"WemoData",
	    		 sdate: '2014-01-01',
	    		 edate: '2014-02-01',
	    		 sta_cde: '107'
			},
			page : 1,
			mtype : "POST"		// mtype: "POST" -> 한글깨짐 방지
		}).trigger("reloadGrid");		
		
	});
	
});


</script>





</head>
<body>



<!-- 컨텐츠 시작 -->
		
		<form name="form" method="post" >
			<table id="list"></table>
			<div id="pager"></div>
			
		<select id="searchName">
			<option value="TITLE" selected="selected" >제목</option>
			<option value="CONTENT">내용</option>
			<option value="WRITER">작성자</option>
		</select>
		
		<input type="text" id="searchValue" name="searchValue" value="" style="width: 180px;" />
		<input type="button" value="검색" id="getSearch" name="getSearch" />	
			
		</form>
</body>
</html>