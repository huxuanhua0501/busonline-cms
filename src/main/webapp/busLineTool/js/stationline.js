updateLinebyid(function() {
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
						linetypeconRadio = "";
					for (var i = 0; i < citys.length; i++) {
						citysCon += '<option value="' + citys[i].id + '">' + citys[i].name + '</option>';
					}
					for (var j = 0; j < linetypes.length; j++) {
						linetypecon += '<label><input name="networkType" class="networkType" type="checkbox" value="' + linetypes[j].id + '"/>' + linetypes[j].name + ' </label>';
						linetypeconRadio+= '<label><input name="networkTypeRadio" class="networkType" type="radio" value="' + linetypes[j].id + '"/>' + linetypes[j].name + ' </label>';
					}
					$("#citylist").html(citysCon);
					$(".network").eq(0).html(linetypecon);
					$(".network").eq(1).html(linetypeconRadio);
					citysCon = "",
					linetypecon = "",
					linetypeconRadio = "";
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
       html += '<tr><td>'+curList+'</td><td>'+info[i].linename+'</td><td>'+info[i].linetype+'</td><td>暂无</td><td>'+info[i].dictionaryname+'</td><td>'+dir+'</td><td>'+info[i].matchnumber+'</td><td>'+info[i].installationnumber+
       '</td><td>'+info[i].startstop+'</td><td>'+info[i].endstop+'</td><td>'+info[i].totalstop+'</td><td><a class="stationList" data-id="'+info[i].id+'">查看站点</a><a  class="busUpdate"  data-id="'+info[i].id+'">修改</a>'+
       '<a  class="busDelete"  data-id="'+info[i].id+'">删除</a></td></tr>';
     }
     e.after(html);
       buslevel="";
       network="";
       buslinedir="";

   };
   var loadData = function() {
     var loadFlag = false;
     $.getJSON('/lineview/getallLine', {
       "currentPage": 1,
       "pageSize": size,
       'cityname': cityval,
       'linename': oBusName,
       "linkdir": buslinedir,
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
    
              loadData();

            
            
	   });
	 var lineId;
	 //修改按钮
     var  busLineUpdate=function(){
           $.ajax({
	  	 	url:'/lineview/getLineById',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	data:{
	  	 		id:lineId
	  	 	},
	  	 	success:function(res){
				if (res.code == 200) {
					var resData=res.data;
						$(".updateName").val(resData.linename);
					    $("#dirction").val(resData.linkdir);
					    $("input[type=radio][name=networkTypeRadio][value="+resData.dictionaryid+"]").attr("checked",'checked');
					    $("#linetype").val(resData.linetype);
              if(resData.matchnumber){
                $(".matchNumber").val(resData.matchnumber);
              }
						 if(resData.installationnumber){
                $(".installationNumber").val(resData.installationnumber);
              }
						
						$(".company").html("暂无公司信息");
						$(".startStation").html(resData.startstop);
						$(".endtStation").html(resData.endstop);
						$(".stationNum").html(resData.totalstop);
						$(".stationTime").html(resData.starttime+"-"+resData.endtime);
						$(".stationPrice").html(resData.price);
						$(".updateWindow").show();

				}
				
	  	 	}
	  	 });        
     }
    var updateName,dictionaryid,linetype,linkdir,installationnumber,matchnumber;
    var saveLineBus=function(){
    	  $.ajax({
	  	 	url:'/lineview/updateLinebyid',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	data:{
	  	 		linename:updateName,
	  	 		dictionaryid:dictionaryid,
	  	 		linetype:linetype,
	  	 		linkdir:linkdir,
          installationnumber:installationnumber,
          matchnumber:matchnumber,
          id:lineId
	  	 	},
	  	 	success:function(res){
				if (res.code == 200) {
					alert("修改成功");
					$(".updateWindow").hide();
					loadData();
				}
				else{
					alert("修改失败");
				}
	  	 	}
	  	 });   
    };
     var busLineDelete=function(){
    	  $.ajax({
	  	 	url:'/lineview/delLineandstopbyid',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	data:{
                id:lineId
	  	 	},
	  	 	success:function(res){
				if (res.code == 200) {
					alert("删除成功");
					loadData();
				}
				else{
					alert("删除成功");
				}
	  	 	}
	  	 });   
    };
     var busLineStationList=function(){
     	$(".buslineStation").nextAll().remove(); 
    	  $.ajax({
	  	 	url:'/lineview/selectendstop',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	data:{
                lineid:lineId
	  	 	},
	  	 	success:function(res){
				if (res.code == 200) {
					var resData=res.data;
				    var stationsAll="";
					for(var i=0;i<resData.length;i++){
                        stationsAll+='<tr><td>'+(i+1)+'</td><td class="endstop">'+resData[i].endstop+'</td><td class="stoptype">'+resData[i].stoptype+'</td><td class="lons">'+resData[i].lon+'</td><td class="lats">'+resData[i].lat+
                        '</td><td><a  class="stationUpdate" data-station="'+resData[i].id+'">修改</a></td></tr>';
					}
					$(".buslineStation").after(stationsAll);
				}
				
	  	 	}
	  	 });   
    };
    var stationSave=function(){
    	 $.ajax({
	  	 	url:'/lineview/updatestopbyid',
	  	 	type:'post',
	  	 	dataType:'json',
	  	 	data:{
                id:stationsId,
                stopname:stationUpdateName,
                lon:lon,
                lat:lat,
                stoptype:stationsLevel
	  	 	},
	  	 	success:function(res){
				if (res.code == 200) {
					alert("修改成功");$(".updateStaionWindow").hide();
					busLineStationList();
				}
				else{
					alert("修改失败");
				}
				
	  	 	}
	  	 });   
    }
    $("#saveLine").click(function(){
          updateName=$(".updateName").val();
          linkdir = $("#dirction").find("option:selected").val();
          linetype= $("#linetype").find("option:selected").val();
          dictionaryid=$("input[type='networkTypeRadio']:checked").text();

          matchnumber =$(".matchNumber").val();
          installationnumber=$(".installationNumber").val();
          saveLineBus();
    });
      $("#condition").click(function(){
      	$(".conditionBox").show();
      });
       $("#cancelCondition").click(function(){
        $("input[name = networkType]").attr("checked", false);
        $(".buslineLeve").attr("checked", false);
        $(".buslineDir").attr("checked", false);
      	$(".conditionBox").hide();
      });
       $(".main").on("click",".busUpdate",function(){
       	   lineId=$(this).attr("data-id");
       	   busLineUpdate();
           
       });
        $(".main").on("click",".busDelete",function(){
       	   lineId=$(this).attr("data-id");
       	   if (confirm("确定要删除吗？")) {
				 busLineDelete();
			}
       	  
       });
        $("#cancelUpdate,.close").click(function(){
      	$(".updateWindow").hide();
      });
          $(".main").on("click",".stationList",function(){
       	   lineId=$(this).attr("data-id");
       	  $(".buslineDetail").hide();
       	  $(".stationDetail,.stationTableList").show();
       	  busLineStationList();
       });
     var stationUpdateName,stationsLevel,lon,lat,stationsId;
     $(".stationSave").click(function(){
     	stationUpdateName=$(".stationUpdateName").val();
     	stationsLevel=$("#stationsLevel").find("option:selected").val();
     	lon=$("#lon").val();
     	lat=$("#lat").val();
     	stationSave();
     })
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
           stationsId=$(this).attr("data-station");
           var endstop=$(this).parent().siblings('.endstop').html(),
           stoptype=$(this).parent().siblings('.stoptype').html(),
           lons=$(this).parent().siblings('.lons').html(),
           lats=$(this).parent().siblings('.lats').html();
           $(".stationUpdateName").val(endstop);
            $("#lon").val(lons);
            $("#lat").val(lats);
            $("#stationsLevel").val(stoptype);
           $(".updateStaionWindow").show();
       });
      $("#cancelstationUpdate,.closestation").click(function(){
      	$(".updateStaionWindow").hide();
      });
})()