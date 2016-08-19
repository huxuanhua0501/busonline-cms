(function() {
	
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

	  ajaxDictionaries();//初始化城市，类型，上行下行

})()