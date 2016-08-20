(function() {
	  var $buslineName=$("#lineName"),$citylist = $("#citylist"),
	      oBusName,buslevel="",network="",buslinedir="";
	  var  cityval = $citylist.find("option:selected").text();
	  
	    //请求字典信息接口
	  var ajaxDictionaries=function(){
	  	 $.ajax({
	  	 	url:'/iBusGather/dictionary.do',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	success:function(res){
				if (res.code == 200) {
					var citys = res.data.city,
						linetypes = res.data.linetype,
						citysCon = "",
						linetypecon = "",
						linkdircon = "";
					for (var i = 0; i < citys.length; i++) {
						citysCon += '<option value="' + citys[i].id + '">' + citys[i].name + '</option>';
					}
					for (var j = 0; j < linetypes.length; j++) {
						linetypecon += '<label><input name="networkType" class="networkType" type="checkbox" value="' + linetypes[j].id + '"/>' + linetypes[j].name + ' </label>';
					}
					$("#citylist").html(citysCon);
					$(".network").html(linetypecon);
					$("input[name=networkType]:eq(0),input[name=linkdir]:eq(0)").attr("checked", 'checked');
				}
				
	  	 	}
	  	 });        
	  };
	  ajaxDictionaries();//初始化城市，类型，上行下行

    //查询数据分页
   var pageNum = 0;
   var $content = $('#ajax_content'); //数据列表容器
   var size = 10; //每页显示条数据
   //组装数据
   var inintData = function(e, data, cur) {
     e.nextAll().remove(); //先清空遗留数据
     var html = '';
     var curList = (cur - 1) * size;
     var info = data.data.listAudit;
     var dir;
     for (var i = 0; i < info.length; i++) {
       curList++;
       if(info[i].linkdir==1){
         dir="下行";
       }
       else{
       	  dir="上行";
       }
       html += '<tr><td>'+curList+'</td><td>'+info[i].linename+'</td><td>'+info[i].linetype+'</td><td>'+info[i].linetype+'</td><td>'+info[i].dictionaryname+'</td><td>'+dir+'</td><td>'+info[i].matchnumber+'</td><td>'+info[i].installationnumber+
       '</td><td>'+info[i].startstop+'</td><td>'+info[i].endstop+'</td><td>'+info[i].totalstop+'</td><td><a class="stationList" data-id="'+info[i].id+'">查看站点</a><a  class="busUpdate"  data-id="'+info[i].id+'">修改</a>'+
       '<a  class="busDelete"  data-id="'+info[i].id+'">删除</a></td></tr>';
     }
     e.after(html);

   };
   var loadData = function() {
     var loadFlag = false;
     $.getJSON('/lineview/getallLine', {
       "currentPage ": 1,
       "pageSize": size,
       'cityname': cityval,
       'linename': oBusName,
       "linkdir ": buslinedir,
       "linetype": buslevel,
       'dictionaryid': network
     }, function(data) { //第一次先访问第一页获取总条数
       //组装数据
       inintData($content, data, 1); //第一次得到的数据
       //设置分页
       $content.ampager({
         'pagerName': 'ajax_pager',
         'mode': 'ajax',
         'needNumInput': true,
         'dataCount': data.data.total, //后台获取总条数
         'viewCount': size, //配置每页显示
         'listCount': 7,
         'callback': function(e, i, c) { //分别代表e：数据父容器，i：当前页，c：每页显示多少条数据
           //(首次加载不进入此步骤)
           if (!loadFlag)
             return false;
           $.getJSON('/lineview/getallLine', {
             "currentPage": i,
             "pageSize": c,
             'cityname': cityval,
             'linename': oBusName,
             'linkdir': buslinedir,
             'linetype': buslevel,
             'dictionaryid': network
           }, function(data2) {
             inintData(e, data2, i); //这里用$content也行
           });
         }
       });
       loadFlag = true;
     });
   };
   //页面第一次加载并分页
   loadData();
 //搜索线路
	   $("#searchBusLine,#searchCondition").click(function(){
            oBusName=$buslineName.val();//线路名称
            //线路等级
            $(".buslineLeve").each(function(){
              if($(this).attr('checked')){
                  buslevel+=$(this).attr('value')+",";
              }
            });
             //网络类型
             $(".networkType").each(function(){
              if($(this).attr('checked')){
                  network+=$(this).attr('value')+",";
              }
            });
              //网络类型
             $(".buslineDir").each(function(){
              if($(this).attr('checked')){
                  buslinedir+=$(this).attr('value')+",";
              }
            });
             if(!oBusName&&!buslevel&&!network&&!buslinedir){
             	alert("请选择查询条件");
             }
            else{
              loadData();
            }
            
	   });
	 var lineId;
	 //修改
    //  var  busLineUpdate=function(){
    //        $.ajax({
	  	//  	url:'/lineview/getLineById',
	  	//  	type:'post',
	  	//  	dataType:'json',
	  	//  	data:{
	  	//  		id:lineId
	  	//  	}
	  	//  	success:function(res){
				// if (res.code == 200) {
					
				// }
				
	  	//  	}
	  	//  });        
    //  }
      $("#condition").click(function(){
      	$(".conditionBox").show();
      });
       $("#cancelCondition").click(function(){
      	$(".conditionBox").hide();
      });
       $(".main").on("click",".busUpdate",function(){
       	   lineId=$(this).attr("data-id");
       	   busLineUpdate();
           $(".updateWindow").show();
       });
        $("#cancelUpdate,.close").click(function(){
      	$(".updateWindow").hide();
      });
     $(".stationList").click(function(){
     	$(".buslineDetail").hide();
     	$(".stationDetail,.stationTableList").show();
     });
     $(".buslineTableList").click(function(){
     	$(".buslineDetail").show();
     	$(".updateStaionWindow").hide();
     	$(".stationDetail,.stationTableList").hide();
     });
      $(".closeStation").click(function(){
     	$(".buslineDetail").show();
     	$(".updateStaionWindow").hide();
     	$(".stationDetail,.stationTableList").hide();
     });
       $(".main").on("click",".stationUpdate",function(){
           $(".updateStaionWindow").show();
       });
      $("#cancelstationUpdate,.closestation").click(function(){
      	$(".updateStaionWindow").hide();
      });
})()