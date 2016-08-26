(function(){
	var  $dictionaryname=$("#nameLevel1"),
	     $dictionaryname2=$("#nameLevel2"),
	     dictionaryname,parentid=0,
	     leve2=$('.levelone option:selected').val();
	    var selectList=function(){
	    	  $(".tianjia_box").html(" ");
	    	  $(".levelone").html(" ");
            $.ajax({
				url: '../dict/selectDicOne.do',
				type: 'post',
				dataType: 'json',
            data:{
                userid:user_Id
            },
				success: function(res) {
                  if(res.code==200){
                  	 var resData=res.data;
                  	 var concent="",options="";
                  	 for(var i=0;i<resData.length;i++){
                  	 	concent+="<li>"+resData[i].dictionaryname+"</li>";
                  	 	options+="<option value ='"+resData[i].id+"'>"+resData[i].dictionaryname+"</option>";
                  	 }
                     $(".tianjia_box").html(concent);
                     $(".levelone").html(options);
                     concent="";
                     options="";
                      selectList2();
                     
                  }else if(res.code == 420){
          window.location.href="login.html";
        }
                  else{
                  	alert("添加失败");
                  }
				}
			});
	    };
        selectList();
	     var selectList2=function(){
	     	$(".leve2con").html(" ");
	     	 leve2=$('.levelone option:selected').val();
            $.ajax({
				url: '../dict/selectDicTwo.do',
				type: 'post',
				dataType: 'json',
				data: {
					id:leve2,
               userid:user_Id
				},
				success: function(res) {
                  if(res.code==200){
                  	 var resData=res.data;
                  	 var leveCon="";
                  	 for(var i=0;i<resData.length;i++){
                  	 	leveCon+="<span>"+resData[i].dictionaryname+"</span>";
                  	 }
                      $(".leve2con").append(leveCon);
                     leveCon="";
                  }else if(res.code == 420){
          window.location.href="login.html";
        }
				}
			});
	    };
        if(leve2){
          selectList2();
        }
		var addlevel = function() {
			$.ajax({
				url: '../dict/insertdict.do',
				type: 'post',
				dataType: 'json',
				data: {
               userid:user_Id,
					parentid:parentid,
					dictionaryname: dictionaryname
				},
				success: function(res) {
                  if(res.code==200){
                  	 alert("添加成功");
                  	 if(parentid>0){
                  	 	 selectList();
                  	 	 selectList2();
                  	 }
                  	 else{
                  	 	 selectList();
                  	 }
                  	 $dictionaryname.val("");
                  	 $dictionaryname2.val("");

                  }else if(res.code == 420){
          window.location.href="login.html";
        }
                  else{
                  	alert("添加失败");
                  }
				}
			});
		};
    //正则判断输入格式
   var regFunction = function(textString) {
      var reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]+[\,{0,1}\u4e00-\u9fa5_a-zA-Z0-9]*$/;
      if (textString&&reg.test(textString)) {
         addlevel();
      } else {
         alert("不能为空或多个关键词只能以英文逗号隔开，且不能有空格");
      }
   };
      //添加一级目录索引
      $(".add").click(function(){
      	 var idx=$(".add").index(this);
            if (idx == 0) {
               dictionaryname = $dictionaryname.val();
               parentid = 0;
               regFunction(dictionaryname);
            }
         if (idx == 1) {
            dictionaryname = $dictionaryname2.val();
            parentid = $('.levelone option:selected').val();
            regFunction(dictionaryname);
      	 
      	}
      });

      $(".levelone").change(function(){
      	 parentid=$('.levelone option:selected').val();
      	 selectList2();
      })
})()
