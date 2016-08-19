(function(){
	var  $dictionaryname=$("#nameLevel1"),
	     $dictionaryname2=$("#nameLevel2"),
	     dictionaryname,parentid=0,
	     leve2=$('.levelone option:selected').val();
	    var selectList=function(){
	    	  $(".leve1con").html(" ");
              $(".leve2con").append("");
            $.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicOne',
				type: 'post',
				dataType: 'json',
				success: function(res) {
                  if(res.code==200){
                  	 var resData=res.data;
                  	 var concent="",options="";
                  	 for(var i=0;i<resData.length;i++){
                  	 	concent+="<li>"+resData[i].dictionaryname+"</li>";
                  	 	options+="<option value ='"+resData[i].id+"'>"+resData[i].dictionaryname+"</option>  "
                  	 }
                     $(".leve1con").html(concent);
                      $(".leve2con").html(options);
                     concent="";
                     options="";
                  }
                  else{
                  	alert("添加失败");
                  }
				}
			});
	    };
        selectList();
	     var selectList2=function(){
	     	 leve2=$('.levelone option:selected').val();
            $.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicTwo',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:leve2,
				},
				success: function(res) {
                  if(res.code==200){
                  	 var resData=res.data;
                  	 var leveCon="";
                  	 for(var i=0;i<resData.length;i++){
                  	 	leveCon+="<span>"+resData[i].dictionaryname+"</span>";
                  	 }
                      $(".addlist").append(leveCon);
                     leveCon="";
                  }
				}
			});
	    };
        if(leve2){
          selectList2();
        }
		var addlevel = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/insertdict',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:parentid,
					dictionaryname: dictionaryname
				},
				success: function(res) {
                  if(res.code==200){
                     selectList();
                  	 alert("添加成功");
                  	 $dictionaryname.val("");
                  	 $dictionaryname2.val("");

                  }
                  else{
                  	alert("添加失败");
                  }
				}
			});
		};

      //添加一级目录索引
      $(".add").click(function(){
      	 var idx=$(".add").index(this);
      	 if(idx==0){
      	 	  dictionaryname=$dictionaryname.val();
      	 	  parentid=0;
				if (dictionaryname) {
					addlevel();
				} else {
					alert("目录索引不能为空");
					return false;
				}
      	 }
      	 if(idx==1){
      	 	dictionaryname=$dictionaryname2.val();
      	    parentid=$('.levelone option:selected').val();
      	 	   if (dictionaryname) {
					addlevel();
				} else {
					alert("目录索引不能为空");
					return false;
				}
      	 }
      	
      })
})()
