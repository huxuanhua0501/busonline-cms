(function() {
	  var $buslineName=$("#lineName"),
	      oBusName,buslevel="",network="",buslinedir="";
	
	   //搜索线路
	   $("#searchBusLine,#searchCondition").click(function(){
            oBusName=$buslineName.val();//线路名称
            $(".buslineLeve").each(function(){
              if($(this).attr('checked')){
                  buslevel=$(this).attr('value');
                  console.log(buslevel);
              }
            });
            
            //inintData()
	   });
	      //查询数据分页
    //查询数据分页
   var classId = " ";
   var classLevel;
   var pageNum = 0;
   var $content = $('#ajax_content'); //数据列表容器
   var size = 5; //每页显示条数据
   //组装数据
   var inintData = function(e, data, cur) {
     e.nextAll().remove(); //先清空遗留数据
     var html = '',
       categorys = '';
     var curList = (cur - 1) * size;
     var info = data.result.pageList;
     for (var i = 0; i < info.length; i++) {
       for (j = 0; j < info[i].category.length; j++) {
         categorys += info[i].category[j].name + ' ';
       }
       curList++;
       html += "<tr align='center'><td class='attributeId' category-id=" + info[i].id + ">" + curList + "</td><td class='attrNameList'>" + info[i].name +
         "</td><td class='values'>" + info[i].values.toString() + "</td><td class='categorys'>" + categorys + "</td><td><a class='updateBtn' >修改</a><a class='deletedBtn'>删除</a></td></tr>"
       categorys = '';
     }
     e.after(html);

   };
   var loadData = function() {
     var loadFlag = false;
     var attrName = $(".attName").val();
     $.getJSON('/queryAttributeByPageForAjax.do', {
       "current_page": 1,
       "page_size": size,
       'name': attrName,
       'category_id': classId
     }, function(data) { //第一次先访问第一页获取总条数
       //组装数据
       inintData($content, data, 1); //第一次得到的数据
       //设置分页
       $content.ampager({
         'pagerName': 'ajax_pager',
         'mode': 'ajax',
         'needNumInput': true,
         'dataCount': data.result.totalCount, //后台获取总条数
         'viewCount': size, //配置每页显示
         'listCount': 7,
         'callback': function(e, i, c) { //分别代表e：数据父容器，i：当前页，c：每页显示多少条数据
           //(首次加载不进入此步骤)
           if (!loadFlag)
             return false;
           $.getJSON('/queryAttributeByPageForAjax.do', {
             "current_page": i,
             "page_size": c,
             'name': attrName,
             'category_id': classId
           }, function(data2) {
             inintData(e, data2, i); //这里用$content也行
           });
         }
       });

       loadFlag = true;
     });
   };
   //页面第一次加载并分页
  // loadData();
   //查询加载并分页

   // $('#seclectAttr').click(function() {
   //   if ($(".threeLevel").val() == 0) {
   //     classLevel = $(".twoLevel").val();
   //     if (classLevel == 0) {
   //       classId = $(".oneLevel").val();
   //     } else {
   //       classId = $(".twoLevel").val();
   //     }

   //   } else {
   //     classId = $(".threeLevel").val();
   //   }
   //   loadData();
   // });
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
						linetypecon += '<label><input name="networkType" type="checkbox" value="' + linetypes[j].id + '"/>' + linetypes[j].name + ' </label>';
					}
					$("#citylist").html(citysCon);
					$("#network").html(linetypecon);
					$("input[name=networkType]:eq(0),input[name=linkdir]:eq(0)").attr("checked", 'checked');
				}
				
	  	 	}
	  	 });        
	  };

	 // ajaxDictionaries();//初始化城市，类型，上行下行
      $("#condition").click(function(){
      	$(".conditionBox").show();
      });
       $("#cancelCondition").click(function(){
      	$(".conditionBox").hide();
      });
       $(".main").on("click",".busUpdate",function(){
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