(function() {
	var $citylist = $("#citylist"),
		$busName = $("#lineName"),
		$search = $("#search"),
		cityval = $citylist.find("option:selected").text(),
		cityid=$citylist.find("option:selected").val(),
		tmpRet = "", //全局变量，存储返回结果
		tmpRetIndex = 0, //数据下标
		stations = "",
		busNameval,
		busNamNew,
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
			cityid=$citylist.find("option:selected").val();
            map.centerAndZoom(cityval,12);
            $(".onbuslineShow").css("display","none");
		});
		//查询线路
         $search.click(function(){
			 busNameval = $busName.val();
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
						linetypecon += '<label><input name="networkType" type="radio" value="' + linetypes[j].id + '"/>' + linetypes[j].name + ' </label>';
					}
				
					$("#citylist").html(citysCon);
					$("#network").html(linetypecon);
					$("input[name=networkType]:eq(0),input[name=linkdir]:eq(0)").attr("checked", 'checked');
				}
				
	  	 	}
	  	 });        
	  };

	  ajaxDictionaries();//初始化城市，类型，上行下行

      //查询是否采集接口
		  var ajaxcollectionbus=function(){
		  	  $.ajax({
		  	 	url:'/iBusGather/busName.do?cityid='+cityid+'&busline='+busNameval,
		  	 	type:'post',
		  	 	dataType:'json',
		  	 	success:function(res){
					if (res.code == 200) {
						var collectarr = res.data.busline;
						for (var h = 0; h < collect.length; h++) {
							if ($.inArray(collect[h], collectarr) >= 0) {
								$(".identity").eq(h).html("已采集").parent().css({
									"background-color": "#f00",
									"border-color": "#f00"
								});
							}
						}
					} 
		  	 	}
		  	 });	    
		  }
		   //保存接口调用
		 var ajaxSave=function(){
		    	var sitearr=[];
		    	var siteNameObj={};
		    	var savaIdx=$(".open").find(".showhid").index();
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
		       var starttime=$(".startTime").eq(savaIdx).html();
		       var endtime=$(".endTime").eq(savaIdx).html();
		      var  busNameBd=$(".busNameBd").eq(savaIdx).html();
		       var busName=$("#busName").val();
		       var price=$(".price").eq(savaIdx).html();
		       cityid=$citylist.find("option:selected").val();
		       var  datas = {};
					datas.start_time = starttime;
					datas.end_time = endtime;
					datas.linename = busName;
					datas.linename_bd = busNameBd;
					datas.linkdir = linkdirval;
					datas.city_id = cityid;
					datas.linetype = networkType;
					datas.price = price;
					datas.site = sitearr;
						$.ajax({
							url: 'http://192.168.109.227:40000/iBusGather/upload.do',
							type: 'post',
							dataType: 'json',
							data:{
								busLine:JSON.stringify(datas)
							},
							success: function(res) {
								if (res.code == 200) {
									alert("插入成功");
									$(".buslineSites").removeClass('open');
									$(".showhid").html("+途径站点");
						         	$(".stationsbox,.popup").hide();
									busline.getBusList(busNameval);
								} else {
									alert("插入失败");
								}
							}
						});

					};

	     //验证是否可以插入接口
		  var ajaxTestInsert=function(){
		  	  $.ajax({
		  	 	url:'/iBusGather/validate.do?buslinename='+busNamNew+'&cityid='+cityid,
		  	 	type:'post',
		  	 	dataType:'json',
		  	 	success:function(res){
					if (res.code == 200) {
                        ajaxSave();
					} else {
						alert("插入失败");
					}
		  	 	}
		  	 });
              
		  }
		
        //点击保存按钮
        $("#save").click(function(){
        	 busNamNew=$("#busName").val();
        	 cityid=$citylist.find("option:selected").val();
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