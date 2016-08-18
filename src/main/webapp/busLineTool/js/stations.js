(function() {
	var $citylist = $("#citylist"),
		$busName = $("#lineName"),
		$search = $("#search"),
		cityval = $citylist.find("option:selected").text(),
		tmpRet = "", //全局变量，存储返回结果
		tmpRetIndex = 0, //数据下标
		stations = "",
		collect = []; //存放线路名称
		var map = new BMap.Map("l-map"); // 创建Map实例
		map.centerAndZoom(cityval, 12);
		map.enableScrollWheelZoom(true);
		var busline = new BMap.BusLineSearch(map, {
			renderOptions: {
				map: map,
				panel: "results"
			},
			onGetBusListComplete: function(result) {
				if (result) {
					tmpRet = result;	
					stations = "";
					$(".onbuslinelistshow").html("");				
					onBusLineHtmlShow(tmpRet);
                    
				}
			}, //新加的获取经纬度
			onBusLineHtmlSet: function(html) {
				html = "";
				// $("img[id*='imgBLIcon']").click(function(){
				// 	tmpRetIndex = $("img[id*='imgBLIcon']").index(this);
				// });
				var list = tmpRet._listItems;
				var line = list[tmpRetIndex];
				var sites = tmpRet[tmpRetIndex]._stations;					
					//linenameHtml = line.name+"\n";
					//html += linenameHtml;
					var startTime = tmpRet[tmpRetIndex].startTime;
					var endTime = tmpRet[tmpRetIndex].endTime;
					for (var i in sites) {
						var site = sites[i];
						var name = site.name;
						var lng = site.position.lng;
						var lat = site.position.lat;
						//var point = new BMap.Point(lng,lat);
						//pois.push(point);
						var gcjXY = GPS.bd_decrypt(lat, lng);
						var gpsXY = GPS.gcj_decrypt(gcjXY.lat, gcjXY.lon);
						//var gpsXY = GPS.gcj_decrypt_2(gcjXY.lat,gcjXY.lon);
						//html += "：百度 " + lng + "，" + lat + "；EXGPS " + gcjXY.lon + "，" + gcjXY.lat + startTime + endTime + "\n";
						stations += "<p class='stationSite' data-lng='"+lng+"' data-lat='"+lat+"' data-glat='"+gcjXY.lon+"' data-glon='"+gcjXY.lat+"'>" + site.name + "</p>";
					}
					//$("#lineGeo").val(html);
							$(".stationsbox").eq(tmpRetIndex).append(stations);
							$(".popup").show();
							$(".times").eq(tmpRetIndex).html("首末班：<span class='startTime'>" + startTime + "</span>-<span class='endTime'>" + endTime+"</span>");

			}
		});
		//切换城市
		$citylist.on("change", function() {
			cityval = $citylist.find("option:selected").text();
            map.centerAndZoom(cityval,12);
            $(".onbuslineShow").css("display","none");
		});
		//查询线路
         $search.click(function(){
			var busNameval = $busName.val();
				if (busNameval) {
					busline.getBusList(busNameval);
				} else {
					alert("请输入线路名");
				}
        });
         //取消
         $("#cancel").click(function(){
         	$(".buslineSites").removeClass('open');
			$(".showhid").html("+途径站点");
         	$(".stationsbox,.popup").hide();
			return false;
         });
          //请求字典信息接口
	
	  var ajaxDictionaries=function(){
	  	 // $.ajax({
	  	 // 	url:'http://www.baidu.com',
	  	 // 	type:'post',
	  	 // 	dataType:'json',
	  	 // 	data: {
           //category_id: classValue
     //      },
	  	 // 	success:function(res){
                 
	  	 // 	}
	  	 // });
	  	  var ajaxdatas={"code":200,"msg":"success","data":{"city":[{ "id":1, "name":"北京" },{ "id":2, "name":"南京市" }],"linetype":[{ "id":2, "name":"A网"}],"linkdir":[{"id":3, "name":"上行" },{"id":2, "name":"下行" },{"id":1, "name":"其他" } ]}};
          var citys=ajaxdatas.data.city,
              linetypes=ajaxdatas.data.linetype,
              linkdirs=ajaxdatas.data.linkdir,
              citysCon="",linetypecon="",linkdircon="";
           for(var i=0;i<citys.length;i++){
              citysCon+='<option value="'+citys[i].id+'">'+citys[i].name+'</option>';
           }
           for(var j=0;j<linetypes.length;j++){
              linetypecon+='<label><input name="networkType" type="radio" value="'+linetypes[j].id+'"/>'+linetypes[j].name+' </label>';
           } 
            for(var k=0;k<linkdirs.length;k++){
              linkdircon+='<label><input name="linkdir" type="radio" value="'+linkdirs[k].id+'"/>'+linkdirs[k].name+' </label>';
           } 
            $("#citylist").html(citysCon); 
            $("#network").html(linetypecon); 
            $("#dir").html(linkdircon); 
            $("input[name=networkType]:eq(0),input[name=linkdir]:eq(0)").attr("checked",'checked');
	  };

	  ajaxDictionaries();//初始化城市，类型，上行下行

      //查询是否采集接口
		  var ajaxcollectionbus=function(){
		  	  // $.ajax({
		  	 // 	url:'http://www.baidu.com',
		  	 // 	type:'post',
		  	 // 	dataType:'json',
		  	 // 	data: {
	     //        //category_id: classValue
	     //      },
		  	 // 	success:function(res){
	                 
		  	 // 	}
		  	 // });
		     var ajaxdatas={"code":200,"msg":"success","data":{ "busline":[ "915快区间(东大桥环岛-东直门枢纽站)","915路(东直门枢纽站-南彩汽车站)" ]}};
		     var collectarr=ajaxdatas.data.busline;
              for(var h=0; h<collect.length;h++){
              	 if($.inArray(collect[h], collectarr)>=0){
              	 	$(".identity").eq(h).html("已采集").parent().css({"background-color":"#f00","border-color":"#f00"});
              	 }
              }
		  }
	     //验证是否可以插入接口
		  var ajaxTestInsert=function(){
		  	  // $.ajax({
		  	 // 	url:'http://www.baidu.com',
		  	 // 	type:'post',
		  	 // 	dataType:'json',
		  	 // 	data: {
	     //        //category_id: classValue
	     //      },
		  	 // 	success:function(res){
	                   
		  	 // 	}
		  	 // });
               ajaxSave();
		  }
		 //保存接口调用
		 var ajaxSave=function(){
		    	var sitearr=[];
		    	var siteNameObj={};
		    	var savaIdx=$(".open").index();
		    	var $stationSite=$(".stationSite");
		    	for(var i=0;i<$stationSite.length;i++){
                        siteNameObj.name=$stationSite.eq(i).html();
                        siteNameObj.lat=$stationSite.eq(i).attr("data-lat");
                        siteNameObj.lon=$stationSite.eq(i).attr("data-lng");
                        siteNameObj.glat=$stationSite.eq(i).attr("data-glat");
                        siteNameObj.glon=$stationSite.eq(i).attr("data-glon");
                        sitearr.push(siteNameObj);
                        siteNameObj={};
		    	}
		    	var networkType=$("input[name=networkType]:checked").val();
		    	var linkdirval=$("input[name=linkdir]:checked").val();
		       var startTime=$(".startTime").eq(savaIdx).html();
		       var endtime=$(".startTime").eq(savaIdx).html();
		      var  busNameBd=$(".busNameBd").eq(savaIdx).html();
		       var busName=$("#busName").val();
		       var price=$(".price").eq(savaIdx).html();
		       var cityvalue = $citylist.find("option:selected").val();
				// $.ajax({
				// 	url: 'http://www.baidu.com',
				// 	type: 'post',
				// 	dataType: 'json',
				// 	data: {
				// 		linename: "巴士在线线路名称",
				// 		linename_bd: "百度线路名称",
				// 		linkdir: linkdirval,
				// 		city_id: 1,
				// 		linetype: 2,
				// 		price: "2",
				// 		start_time: "开始时间",
				// 		end_time: "结束时间",
				// 		site:sitearr
				// 	},
				// 	success: function(res) {

				// 	}
				// });

		  }
        //点击保存按钮
        $("#save").click(function(){
        	var busNamNew=$("#busName").val();
        	if(busNamNew){
               ajaxTestInsert();//验证是否可以插入
        	}
        	else{
        		alert("请输入线路名称");
        	}
        })
		//查询结果展示
		function onBusLineHtmlShow(result) {
			var onbuslineDatas = result,
				onbuslineArr = onbuslineDatas._listItems,
				onbuslinelist = "";
                collect=[];
			for (var i = 0; i < onbuslineArr.length; i++) {
				onbuslinelist += '<div class="onbuslineBlock"> <div class="onbuslineBlock_l fl"><p class="identity">未采集</p></div>' +
					'<div class="onbuslineBlock_r fl"><p class="busNameBd">' + onbuslineArr[i].name + '</p><p><span class="times">首末班：暂无</span>起步票价<span class="price">' + onbuslineArr[i]._poiType + '</span>元</p></div><div class="buslineSites fl' +
					'"><p class="showhid">+途径站点</p><div class="stationsbox"></div></div></div>';
				collect.push(onbuslineArr[i].name);
			}
			$(".onbuslineShow").css("display", "block");
			$(".onbuslineNum").html("共找到" + onbuslineArr.length + "条线路");
			$(".onbuslinelistshow").append(onbuslinelist);
			ajaxcollectionbus();
			onbuslinelist = "";
		};
		//查询站点
		$(".onbuslineShow").on("click", ".buslineSites", function() {
			var idx = $(".buslineSites").index(this);
			if ( $(this).hasClass('open')) {
				$(this).removeClass('open');
				$(".showhid").eq(idx).html("+途径站点");
				$(".stationsbox,.popup").hide();
				return false;				
			} else {
				tmpRetIndex = idx;
				//还原
				$(".buslineSites").removeClass('open');
				$(".stationsbox,.popup").hide();
				$(".showhid").html("+途径站点");
				//当前操作的状态
				$(".showhid").eq(idx).html("-途径站点");
				$("#busName").val($(".busNameBd").eq(idx).html());
				$(this).addClass('open');
				$(".stationsbox").eq(idx).show();
				var fstLine = tmpRet.getBusListItem(idx);
				busline.getBusLine(fstLine);
			}
		});

})()