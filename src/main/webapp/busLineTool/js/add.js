(function(){
	var  $dictionaryname=$("#nameLevel1"),
	     $dictionaryname2=$("#nameLevel2"),
	     dictionaryname,parentid=0,
	     leve2=$('.levelone option:selected').val();
	    var selectList=function(){
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
                     $(".tianjia_box").append(concent);
                      $(".levelone").append(options);
                     concent="";
                     options="";
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

       	 selectList2();
       
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
					console.log(res);
                  if(res.code==200){
                     selectList();
                     selectList2();
                  	 alert("添加成功");
                  }
				}
			});
		};

      //添加一级目录索引
      $(".add").click(function(){
      	
      	 var idx=$(this).index();
      	 if(idx==0){
      	 	  dictionaryname=$dictionaryname.val();

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
